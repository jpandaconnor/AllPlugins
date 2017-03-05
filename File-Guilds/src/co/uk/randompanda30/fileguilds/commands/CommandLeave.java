package co.uk.randompanda30.fileguilds.commands;

import co.uk.randompanda30.fileguilds.commands.handle.Command;
import co.uk.randompanda30.fileguilds.config.Messages;
import co.uk.randompanda30.fileguilds.data.PlayerData;
import co.uk.randompanda30.fileguilds.object.GuildOB;
import co.uk.randompanda30.fileguilds.object.Rank;
import co.uk.randompanda30.fileguilds.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandLeave extends Command {

    public CommandLeave() {
        super("guild leave", "", "Removes you from your current guild", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // args[0] = leave

        if (args.length != 1) {
            Dispatch.sendMessage(Messages.MessagesValues.GUILDS_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        if (!PlayerData.isInGuild(player)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_NOTINGUILD.value, player);
            return true;
        }

        GuildOB guild = new GuildOB(PlayerData.getGuild(player));

        Rank rank = guild.getRank(PlayerData.getRank(player));

        if (rank.getName().equals("Leader")) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_LEAVE_OWNER.value, player);
            return true;
        }

        guild.kickPlayer(player.getUniqueId());

        Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_LEAVE_YOU.value, player);

        for (String s : new ArrayList<>(guild.getMembers())) {
            if (Bukkit.getServer().getPlayer(s) != null && Bukkit.getServer().getPlayer(s).isOnline()) {
                Dispatch.sendMessage(Messages.MessagesValues.GUILD_LEAVE_THEYLEFT.value.toString()
                        .replace("%player", player.getName()), Bukkit.getServer()
                        .getPlayer(s));
            }
        }
        return true;
    }
}