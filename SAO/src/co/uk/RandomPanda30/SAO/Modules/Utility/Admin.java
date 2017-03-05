package co.uk.RandomPanda30.SAO.Modules.Utility;

import co.uk.RandomPanda30.SAO.Config.Config;
import co.uk.RandomPanda30.SAO.Config.Messages;
import co.uk.RandomPanda30.SAO.Manager.BanManager;
import co.uk.RandomPanda30.SAO.Manager.KickManager;
import co.uk.RandomPanda30.SAO.Manager.PlayerManager;
import co.uk.RandomPanda30.SAO.Manager.ServerManager;
import co.uk.RandomPanda30.SAO.Objects.Sender;
import co.uk.RandomPanda30.SAO.SAO;
import co.uk.RandomPanda30.SAO.Utilities.Dispatch;
import co.uk.RandomPanda30.SAO.Utilities.No;
import co.uk.RandomPanda30.SAO.Utilities.String.Format;
import co.uk.RandomPanda30.SAO.Utilities.String.ID;
import co.uk.RandomPanda30.SAO.Utilities.Syntax;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.Calendar;
import java.util.UUID;

/*

Ban
tempban
kick
mute
tempmute
jail

 */

public class Admin implements Listener, CommandExecutor {

    public SAO plugin;

    public Admin(SAO plugin) {
        this.plugin = plugin;

        if ((Boolean) Config.ConfigValues.MODULES_ADMIN_ENABLED.value) {
            plugin.getCommand("ban").setExecutor(this);
        } else {
            Dispatch.sendMessage(Format.format(Messages.MessagesValues.MODULE_OFF.value.toString().replace("mod", "Admin")), null);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Sender s = new Sender(sender);

        StringBuilder sb = new StringBuilder();

        String nu = "";
        String time = "";
        String reason = "";

        switch (cmd.getName().toLowerCase()) {
            case "ban":
                if (args.length != 3) {
                    new Syntax("/ban", "/ban <Playername/UUID> <Time> <Reason>", s);
                    return true;
                }

                if (s.isPlayer) {
                    if (!s.getPlayer().hasPermission("sao.admin.ban")) {
                        Dispatch.sendMessage((String) Messages.MessagesValues.NOPERM.value, null);
                        return true;
                    }
                }

                nu = args[0];
                time = args[1];
                reason = "";

                sb = new StringBuilder();

                for (int i = 2; i < args.length; i++) {
                    sb.append(' ').append(args[i]);
                }

                reason = sb.toString();

                String finalTime = "";
                String finalReason = Messages.MessagesValues.BAN_REASON.value + (reason == null ?
                        Messages.MessagesValues.BAN_NOREASON.value + "%N." : reason + "%N.");

                String[] splitter = {time};

                int days = 0;
                int hours = 0;
                int minutes = 0;
                int seconds = 0;

                if (!time.contains("d") && !time.contains("h") && !time.contains("m")
                        && !time.contains("s")) {
                    if (No.isNumber(time)) {
                        minutes = No.getNumber(time);
                    } else {
                        time = "0";
                    }
                } else {
                    if (time.contains("d")) {
                        splitter = splitter[0].split("d");
                        days = No.getNumber(splitter[0]);
                        if (splitter.length == 2) {
                            splitter[0] = splitter[1];
                        }
                    }

                    if (time.contains("h")) {
                        splitter = splitter[0].split("h");
                        hours = No.getNumber(splitter[0]);
                        if (splitter.length == 2) {
                            splitter[0] = splitter[1];
                        }
                    }

                    if (time.contains("m")) {
                        splitter = splitter[0].split("m");
                        minutes = No.getNumber(splitter[0]);
                        if (splitter.length == 2) {
                            splitter[0] = splitter[1];
                        }
                    }

                    if (time.contains("s")) {
                        splitter = splitter[0].split("s");
                        seconds = No.getNumber(splitter[0]);
                    }
                }

                if (days != 0) {
                    finalTime += " %A" + days + Messages.MessagesValues.BAN_DAYS.value;
                }

                if (hours != 0) {
                    finalTime += " %A" + hours + Messages.MessagesValues.BAN_HOURS.value;
                }

                if (minutes != 0) {
                    finalTime += " %A" + minutes + Messages.MessagesValues.BAN_MINUTES.value;
                }

                if (seconds != 0) {
                    finalTime += " %A" + seconds + Messages.MessagesValues.BAN_SECONDS;
                }

                finalReason += "&u" + Messages.MessagesValues.BAN_LENGTH.value + (time.equals("0")
                        ? Messages.MessagesValues.BAN_PERM.value + "%N." : "%A" + finalTime + "%N.");
                finalTime = (time.equals("0") ? (String) Messages.MessagesValues.BAN_PERM.value : finalTime);

                long length = 0;
                if (!time.equals("0")) {
                    length += Calendar.getInstance().getTimeInMillis() + (days * 1000 * 60 * 60 * 24) +
                            (hours * 1000 * 60 * 60) + (minutes * 1000 * 60) + (seconds * 1000);
                }

                BanManager bm = new BanManager();

                if (ID.isUUID(nu)) {
                    UUID uuid = UUID.fromString(nu);
                    if (!PlayerManager.isInSystem(uuid)) {
                        /* They should be offline here anyway!
                        Since they're not actually on the server
                         */
                        PlayerManager.addNewOfflinePlayer(uuid.toString());
                        bm.banOfflinePlayer(uuid,
                                (s.isPlayer ? s.getPlayer().getUniqueId() :
                                        null), length, reason);
                        s.send(Messages.MessagesValues.BAN_YOUBANNED.value.toString().replace("%player", nu));
                    } else {
                        if (Bukkit.getPlayer(uuid) != null) {
                            if (Bukkit.getPlayer(uuid).isOnline()) {
                                Player player = Bukkit.getPlayer(uuid);
                                player.kickPlayer(Format.format(finalReason));
                                bm.banPlayer(player.getUniqueId(), (s.isPlayer ? s.getPlayer().getName() : null),
                                        (s.isPlayer ? s.getPlayer().getUniqueId() :
                                                null), length, reason);
                                s.send(Messages.MessagesValues.BAN_YOUBANNED.value.toString().replace("%player", nu));
                            } else {
                                bm.banOfflinePlayer(uuid,
                                        (s.isPlayer ? s.getPlayer().getUniqueId() :
                                                null), length, reason);
                                s.send(Messages.MessagesValues.BAN_YOUBANNED.value.toString().replace("%player", nu));
                            }
                        } else {
                            s.send((String) Messages.MessagesValues.BAN_PLAYERDOESNOTEXIST.value);
                            return true;
                        }
                    }
                } else {
                    if (!PlayerManager.isInSystem(nu)) {
                        PlayerManager.addNewOfflinePlayer(nu);
                        bm.banPlayer(ServerManager.getUUID(nu), (s.isPlayer ? s.getPlayer().getName() : null),
                                (s.isPlayer ? s.getPlayer().getUniqueId() :
                                        null), length, reason);
                        s.send(Messages.MessagesValues.BAN_YOUBANNED.value.toString().replace("%player", nu));
                    } else {
                        if (Bukkit.getPlayer(nu) != null) {
                            if (Bukkit.getPlayer(nu).isOnline()) {
                                Player player = Bukkit.getPlayer(nu);
                                player.kickPlayer(Format.format(finalReason));
                                bm.banPlayer(player.getUniqueId(), (s.isPlayer ? s.getPlayer().getName() : null),
                                        (s.isPlayer ? s.getPlayer().getUniqueId() :
                                                null), length, reason);
                                s.send(Messages.MessagesValues.BAN_YOUBANNED.value.toString().replace("%player", nu));
                            } else {
                                bm.banPlayer(ServerManager.getUUID(nu), (s.isPlayer ? s.getPlayer().getName() : null),
                                        (s.isPlayer ? s.getPlayer().getUniqueId() :
                                                null), length, reason);
                                s.send(Messages.MessagesValues.BAN_YOUBANNED.value.toString().replace("%player", nu));
                            }
                        } else {
                            s.send((String) Messages.MessagesValues.BAN_PLAYERDOESNOTEXIST.value);
                            return true;
                        }
                    }
                }


                // Check if UUID
                // If so insert just UUID if not found

                // Else check player name
                // If in ban
                // If not create new row and ban

                /*
                Don't forget to work on the update method
                 */
                break;
            case "kick":
                if (args.length != 2) {
                    new Syntax("/kick", "/kick <Player name> <Reason>", s);
                    return true;
                }

                if (s.isPlayer) {
                    if (!s.getPlayer().hasPermission("sao.admin.kick")) {
                        Dispatch.sendMessage((String) Messages.MessagesValues.NOPERM.value, null);
                        return true;
                    }
                }

                if (Bukkit.getServer().getPlayer(nu) == null
                        || !Bukkit.getServer().getPlayer(nu).isOnline()) {
                    s.send(Messages.MessagesValues.KICK_PLAYERNOTONLINE.value.toString().replace("%player", nu));
                    return true;
                }

                nu = args[0];

                sb = new StringBuilder();

                for (int i = 1; i < args.length; i++) {
                    sb.append(' ').append(args[i]);
                }

                reason = sb.toString();

                KickManager km = new KickManager();
                km.kick(Bukkit.getPlayer(nu), (s.isPlayer ? s.getPlayer().getUniqueId() : null), reason);

                Bukkit.getPlayer(nu).kickPlayer(Format.format(Messages.MessagesValues.KICK_HAVEKICKED.value.toString().replace("%reason", reason)));

                s.send(Messages.MessagesValues.KICK_YOUKICKED.value.toString().replace("%player", nu));

                break;
            case "mute":

                break;
            case "tempmute":

                break;

            case "jail":

                break;
        }

        return true;
    }

}