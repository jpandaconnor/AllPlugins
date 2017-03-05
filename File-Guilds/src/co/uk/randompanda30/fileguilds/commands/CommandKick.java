package co.uk.randompanda30.fileguilds.commands;

import co.uk.randompanda30.fileguilds.commands.handle.Command;
import co.uk.randompanda30.fileguilds.config.Messages;
import co.uk.randompanda30.fileguilds.data.PlayerData;
import co.uk.randompanda30.fileguilds.object.GuildOB;
import co.uk.randompanda30.fileguilds.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CommandKick extends Command {


    public CommandKick() {
        super("guild kick <Player name>", "", "Kicks a player from your guild", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // args[0] = kick
        // args[1] = name

        if (args.length != 2) {
            Dispatch.sendMessage(Messages.MessagesValues.GUILDS_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        if (!PlayerData.isInGuild(player)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_NOTINGUILD.value, player);
            return true;
        }

        GuildOB guild = new GuildOB(PlayerData.getGuild(player));

        if (!guild.getRank(PlayerData.getRank(player)).canKick()) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_NORANKPERM.value, player);
            return true;
        }

        // Check if null in file
        String target = args[1];
        if (Bukkit.getServer().getPlayer(target) != null) {
            Player ptk = Bukkit.getPlayer(target);
            UUID uuid = ptk.getUniqueId();

            if (!PlayerData.isInGuild(uuid)) {
                Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_TARGETNOTINGUILD.value, player);
                return true;
            }

            if (guild.getRank(PlayerData.getRank(ptk)).getName().equals("Leader")) {
                Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_KICK_CANNOTKICKLEADER.value, player);
                return true;
            }

            if (ptk.isOnline()) {
                guild.kickPlayer(ptk);
                // Messgae
                Dispatch.sendMessage(Messages.MessagesValues.GUILD_KICK_KICKEDPLAYER.value.toString().
                        replace("%player", ptk.getName()), player);
                Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_KICK_KICKED.value, ptk);
            } else {
                Dispatch.sendMessage(Messages.MessagesValues.GUILD_KICK_KICKEDPLAYER.value.toString().
                        replace("%player", args[1]), player);
                guild.kickPlayer(uuid);
            }
        } else {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_TARGETNOTFOUND.value, player);
            return true;
        }
        return true;
    }
}
