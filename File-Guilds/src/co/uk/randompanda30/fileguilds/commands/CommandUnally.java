package co.uk.randompanda30.fileguilds.commands;

import co.uk.randompanda30.fileguilds.commands.handle.Command;
import co.uk.randompanda30.fileguilds.config.Messages;
import co.uk.randompanda30.fileguilds.data.GuildData;
import co.uk.randompanda30.fileguilds.data.PlayerData;
import co.uk.randompanda30.fileguilds.object.GuildOB;
import co.uk.randompanda30.fileguilds.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class CommandUnally extends Command {

    public CommandUnally() {
        super("guild unally <Guild name>", "", "Unallies from a guild", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

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

        if (!guild.getRank(PlayerData.getRank(player)).canUnally()) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_NORANKPERM.value, player);
            return true;
        }

        if (!GuildData.isGuild(args[1])) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_GINVITE_NOTAGUILD.value, player);
            return true;
        }

        if (!guild.isAlly(args[1])) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_UNALLY_NOTALLIED.value, player);
            return true;
        }

        GuildOB targetGuild = new GuildOB(args[1]);

        guild.unallyGuild(args[1]);
        targetGuild.unallyGuild(guild.getName());

        for (String s : new ArrayList<>(guild.getMembers())) {
            if (Bukkit.getPlayer(UUID.fromString(s)) != null && Bukkit.getPlayer(UUID.fromString(s)).isOnline()) {
                Dispatch.sendMessage(Messages.MessagesValues.GUILD_UNALLY_UNALLIED.value.toString()
                        .replace("%guild", targetGuild.getName()), Bukkit.getPlayer(UUID.fromString(s)));
            }
        }

        for (String s : new ArrayList<>(targetGuild.getMembers())) {
            if (Bukkit.getPlayer(UUID.fromString(s)) != null && Bukkit.getPlayer(UUID.fromString(s)).isOnline()) {
                Dispatch.sendMessage(Messages.MessagesValues.GUILD_UNALLY_THEYUNALLIED.value.toString()
                        .replace("%guild", guild.getName()), Bukkit.getPlayer(UUID.fromString(s)));
            }
        }

        return true;
    }
}