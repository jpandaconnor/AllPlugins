package co.uk.randompanda30.fileguilds.commands.ranks;

import co.uk.randompanda30.fileguilds.commands.handle.Command;
import co.uk.randompanda30.fileguilds.config.Messages;
import co.uk.randompanda30.fileguilds.data.PlayerData;
import co.uk.randompanda30.fileguilds.object.GuildOB;
import co.uk.randompanda30.fileguilds.object.Rank;
import co.uk.randompanda30.fileguilds.string.Dispatch;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRankDelete extends Command {

    public CommandRankDelete() {
        super("guild rank delete <Rank name>", "", "Deletes a rank in your guild", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // args[0] = rank
        // args[1] = delete
        // args[2] = rank name

        if (args.length != 3) {
            Dispatch.sendMessage(Messages.MessagesValues.GUILDS_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        if (!PlayerData.isInGuild(player)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_NOTINGUILD.value, player);
            return true;
        }

        GuildOB guild = new GuildOB(PlayerData.getGuild(player));

        if (!guild.getRank(PlayerData.getRank(player)).canRankDelete()) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_NORANKPERM.value, player);
            return true;
        }

        if (!guild.isRank(args[2])) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_RANK_DELETE_NOTEXISTS.value, player);
            return true;
        }

        if (args[2].equalsIgnoreCase("Leader") || args[2].equalsIgnoreCase("Rookie")) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_RANK_DELETE_CANNOTTARGET.value, player);
            return true;
        }

        Rank rank = guild.getRank(args[2]);
        guild.removeRank(rank);

        Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_RANK_DELETE_DELETED.value.toString().
                replace("%rank", rank.getName()), player);
        Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_RANK_DELETE_DELETEDINFO.value, player);
        return true;
    }
}