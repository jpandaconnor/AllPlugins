package co.uk.RandomPanda30.SAO.Modules.IO;

import co.uk.RandomPanda30.SAO.Config.Config;
import co.uk.RandomPanda30.SAO.Config.Messages;
import co.uk.RandomPanda30.SAO.Config.Modules.Whitelist;
import co.uk.RandomPanda30.SAO.Manager.ServerManager;
import co.uk.RandomPanda30.SAO.Objects.Sender;
import co.uk.RandomPanda30.SAO.SAO;
import co.uk.RandomPanda30.SAO.Utilities.Dispatch;
import co.uk.RandomPanda30.SAO.Utilities.String.Format;
import co.uk.RandomPanda30.SAO.Utilities.String.ID;
import co.uk.RandomPanda30.SAO.Utilities.Syntax;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Entry implements Listener, CommandExecutor {

    public SAO plugin;

    public Entry(SAO plugin) {
        this.plugin = plugin;

        if ((Boolean) Config.ConfigValues.MODULES_ENTRY_ENABLED.value) {
            plugin.getServer().getPluginManager().registerEvents(this, plugin);
            plugin.getCommand("whitelist").setExecutor(this);
        } else {
            Dispatch.sendMessage(Format.format(Messages.MessagesValues.MODULE_OFF.value.toString().replace("mod", "Entry")), null);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLoginEvent(PlayerLoginEvent event) {
        Player player = event.getPlayer();

        List<String> nus = Whitelist.whitelistf.getStringList("Listed");

        if ((Boolean) Config.ConfigValues.MODULES_ENTRY_CUSTOMWHITELIST.value) {
            if (!nus.contains(player.getUniqueId().toString())) {
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, (String) Messages.MessagesValues.LOGIN_WHITELIST
                        .value);
            }
        }

        // XP Restriction here
        if ((Boolean) Config.ConfigValues.MODULES_ENTRY_XPRESTRICTION.value) {
            int levelNeeded = (Integer) Config.ConfigValues.MODULES_ENTRY_LEVEL.value;
            if (!(Boolean) Config.ConfigValues.MODULES_ENTRY_CHECKDATABASELEVEL.value) {
                if (player.getLevel() < levelNeeded) {
                    event.disallow(PlayerLoginEvent.Result.KICK_OTHER, Format.format(
                            Messages.MessagesValues.ENTRY_XPNOTMET.value.toString().replace("%level",
                                    Integer.toString(levelNeeded))));
                }
            } else {
                ResultSet rs;
                int databaseLevel = -1;
                try {
                    rs = SAO.mysql.querySQL("SELECT * FROM playerdata WHERE uuid = '" + player.getUniqueId().toString() + "'");
                    while (rs.next()) {
                        databaseLevel = rs.getInt("sao_level");
                    }
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }

                if (databaseLevel < levelNeeded) {
                    event.disallow(PlayerLoginEvent.Result.KICK_OTHER, Format.format(
                            Messages.MessagesValues.ENTRY_XPNOTMET.value.toString().replace("%level",
                                    Integer.toString(levelNeeded))));
                }
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Sender s = new Sender(sender);

        if (args.length == 0) {
            new Syntax("/whitelist", new ArrayList<String>() {
                {
                    add("/whitelist add <Player name>");
                    add("/whitelist add <UUID>");
                    add("/whitelist remove <Player name>");
                    add("/whitelist remove <UUID>");
                }
            }, s);
            return true;
        }

        String nu = null;
        switch (args[0]) {
            case "add":
                if (s.isPlayer) {
                    if (!s.getPlayer().hasPermission("sao.admin.whitelist")) {
                        Dispatch.sendMessage((String) Messages.MessagesValues.NOPERM.value, s.getPlayer());
                        return true;
                    }
                }

                if (args.length != 2) {
                    new Syntax("/whitelist add", new ArrayList<String>() {
                        {
                            add("/whitelist add <Player name>");
                            add("/whitelist add <UUID>");
                        }
                    }, s.getPlayer());
                }

                nu = args[1];

                if (!Whitelist.whitelistf.contains("Listed")) {
                    ArrayList<String> pl = new ArrayList<>();
                    if (s.isPlayer) {
                        pl.add(s.getPlayer().getUniqueId().toString());
                    }

                    if (!ID.isUUID(nu)) {
                        String playerUUID = ServerManager.getUUID(nu).toString();

                        if (pl.contains(playerUUID)) {
                            Dispatch.senderMessage(Messages.MessagesValues.WHITELIST_ALREADYIN.value.toString().replace("%pl", nu), s);
                            return true;
                        }

                        if (playerUUID != null) {
                            pl.add(playerUUID);
                        }
                    } else {
                        pl.add(nu);
                    }

                    Whitelist.whitelistf.set("Listed", pl);
                    Whitelist.save();
                } else {
                    List<String> nus = Whitelist.whitelistf.getStringList("Listed");
                    ArrayList<String> editable = new ArrayList<>();

                    for (String st : nus) {
                        editable.add(st);
                    }

                    if (!ID.isUUID(nu)) {
                        String playerUUID = ServerManager.getUUID(nu).toString();

                        if (editable.contains(playerUUID)) {
                            Dispatch.senderMessage(Messages.MessagesValues.WHITELIST_ALREADYIN.value.toString().replace("%pl", nu), s);
                            return true;
                        }

                        if (playerUUID != null) {
                            editable.add(playerUUID);
                        }
                    } else {
                        editable.add(nu);
                    }

                    Dispatch.senderMessage(Messages.MessagesValues.WHITELIST_ADDED.value.toString().replace("%pl", nu), s);

                    Whitelist.whitelistf.set("Listed", editable);
                    Whitelist.save();
                }

                break;
            case "remove":
                if (s.isPlayer) {
                    if (!s.getPlayer().hasPermission("sao.admin.whitelist")) {
                        Dispatch.sendMessage((String) Messages.MessagesValues.NOPERM.value, s.getPlayer());
                        return true;
                    }
                }

                if (args.length != 2) {
                    new Syntax("/whitelist remove", new ArrayList<String>() {
                        {
                            add("/whitelist remove <Player name>");
                            add("/whitelist remove <UUID>");
                        }
                    }, s.getPlayer());
                }

                nu = args[1];

                if (!Whitelist.whitelistf.contains("Listed")) {
                    ArrayList<String> pl = new ArrayList<>();

                    if (!ID.isUUID(nu)) {
                        String playerUUID = ServerManager.getUUID(nu).toString();

                        if (!pl.contains(playerUUID)) {
                            Dispatch.senderMessage(Messages.MessagesValues.WHITELIST_NOTIN.value.toString().replace("%pl", nu), s);
                            return true;
                        }

                        if (playerUUID != null) {
                            pl.remove(playerUUID);
                        }
                    } else {
                        pl.remove(nu);
                    }

                    Whitelist.whitelistf.set("Listed", pl);
                    Whitelist.save();
                } else {
                    List<String> nus = Whitelist.whitelistf.getStringList("Listed");
                    ArrayList<String> editable = new ArrayList<>();

                    for (String st : nus) {
                        editable.remove(st);
                    }

                    if (!ID.isUUID(nu)) {
                        String playerUUID = ServerManager.getUUID(nu).toString();

                        if (!editable.contains(playerUUID)) {
                            Dispatch.senderMessage(Messages.MessagesValues.WHITELIST_NOTIN.value.toString().replace("%pl", nu), s);
                            return true;
                        }

                        if (playerUUID != null) {
                            editable.remove(playerUUID);
                        }
                    } else {
                        if (editable.contains(nu)) {
                            editable.remove(nu);
                        }
                    }

                    Dispatch.senderMessage(Messages.MessagesValues.WHITELIST_REMOVED.value.toString().replace("%pl", nu), s);

                    Whitelist.whitelistf.set("Listed", editable);
                    Whitelist.save();
                }
                break;
        }

        return true;
    }
}