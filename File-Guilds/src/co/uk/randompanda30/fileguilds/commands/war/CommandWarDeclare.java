package co.uk.randompanda30.fileguilds.commands.war;

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

public class CommandWarDeclare extends Command {

    public CommandWarDeclare() {
        super("guild war declare <Guild name>", "", "Declares war on a guild", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // args[0] = war
        // args[1] = declare
        // args[2] = guild

        if (args.length != 3) {
            Dispatch.sendMessage(Messages.MessagesValues.GUILDS_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        if (!PlayerData.isInGuild(player)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_NORANKPERM.value, player);
            return true;
        }

        GuildOB guild = new GuildOB(PlayerData.getGuild(player));

        if (!guild.getRank(PlayerData.getRank(player)).canWarDeclare()) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_NORANKPERM.value, player);
            return true;
        }

        if (!GuildData.isGuild(args[2])) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_GINVITE_NOTAGUILD.value, player);
            return true;
        }

        GuildOB target = new GuildOB(args[2]);

        guild.declareWar(args[2]);
        target.declareWar(guild.getName());

        for (String s : new ArrayList<>(guild.getMembers())) {
            if (Bukkit.getPlayer(UUID.fromString(s)) != null && Bukkit.getPlayer(UUID.fromString(s)).isOnline()) {
                Dispatch.sendMessage(Messages.MessagesValues.GUILD_WAR_DECLARE.value.toString()
                        .replace("%guild", target.getName()), Bukkit.getPlayer(UUID.fromString(s)));
            }
        }

        for (String s : new ArrayList<>(target.getMembers())) {
            if (Bukkit.getPlayer(UUID.fromString(s)) != null && Bukkit.getPlayer(UUID.fromString(s)).isOnline()) {
                Dispatch.sendMessage(Messages.MessagesValues.GUILD_WAR_DECLARED.value.toString()
                        .replace("%guild", guild.getName()), Bukkit.getPlayer(UUID.fromString(s)));
            }
        }
        return true;
    }
}