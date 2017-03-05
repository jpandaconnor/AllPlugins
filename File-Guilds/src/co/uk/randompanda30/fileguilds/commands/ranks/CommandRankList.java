package co.uk.randompanda30.fileguilds.commands.ranks;

import co.uk.randompanda30.fileguilds.commands.handle.Command;
import co.uk.randompanda30.fileguilds.config.Messages;
import co.uk.randompanda30.fileguilds.data.PlayerData;
import co.uk.randompanda30.fileguilds.object.GuildOB;
import co.uk.randompanda30.fileguilds.object.Rank;
import co.uk.randompanda30.fileguilds.string.Dispatch;
import co.uk.randompanda30.fileguilds.string.Format;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

import static co.uk.randompanda30.fileguilds.TEMP.pdatac;

public class CommandRankList extends Command {

    public CommandRankList() {
        super("guild rank list", "", "Shows all the ranks + players in the guild", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // args[0] = rank
        // args[1] = list

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

        Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_RANK_LIST_STARTED.value, player);

        int i = 0;
        for (Rank r : new ArrayList<>(guild.getRanks())) {
            i++;
            Dispatch.sendMessage("&f" + Integer.toString(i) + (Format.format("&b: " + r.getName())), player);
            for (String s : pdatac.getConfigurationSection("").getKeys(false)) {
                if (pdatac.get(s + ".guild").equals(guild.getName())) {
                    if (pdatac.get(s + ".rank").equals(r.getName())) {
                        Dispatch.sendMessage("   &f- %A" + (Bukkit.getPlayer(UUID.fromString(s)) != null ?
                                Bukkit.getServer().getOfflinePlayer(UUID.fromString(s)).getName() : "Null"), player);
                    }
                }
            }
        }

        Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_RANK_LIST_ENDED.value, player);
        return true;
    }
}