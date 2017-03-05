package co.uk.randompanda30.fileguilds.commands;

import co.uk.randompanda30.fileguilds.TEMP;
import co.uk.randompanda30.fileguilds.commands.handle.Command;
import co.uk.randompanda30.fileguilds.config.Data;
import co.uk.randompanda30.fileguilds.config.Guild;
import co.uk.randompanda30.fileguilds.config.Messages;
import co.uk.randompanda30.fileguilds.data.PlayerData;
import co.uk.randompanda30.fileguilds.object.GuildOB;
import co.uk.randompanda30.fileguilds.object.Rank;
import co.uk.randompanda30.fileguilds.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by panda on 17/04/16.
 */
public class CommandDisband extends Command {

    public CommandDisband() {
        super("guild disband", "", "Allows a leader to disband the guild", false);
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

        if (!rank.getName().equals("Leader")) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_DISBAND_NOTOWNER.value, player);
            return true;
        }

        Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_DISBAND_DISBANDED.value, player);

        for (String s : new ArrayList<>(guild.getMembers())) {
            if (Bukkit.getServer().getPlayer(s) != null && Bukkit.getServer().getPlayer(s).isOnline()) {
                Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_DISBAND_HAS.value, Bukkit.getServer()
                        .getPlayer(s));
            }

            TEMP.pdatac.set(s, null);
            Data.save();

            TEMP.guildsc.set(guild.getName(), null);
            Guild.save();
        }

        if(TEMP.pdatac.contains(player.getUniqueId().toString())) {
            TEMP.pdatac.set(player.getUniqueId().toString(), null);
            Data.save();
        }
        return true;
    }
}