package co.uk.randompanda30.fileguilds.commands.ranks;

import co.uk.randompanda30.fileguilds.commands.handle.Command;
import co.uk.randompanda30.fileguilds.config.Messages;
import co.uk.randompanda30.fileguilds.data.PlayerData;
import co.uk.randompanda30.fileguilds.object.GuildOB;
import co.uk.randompanda30.fileguilds.string.Dispatch;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRankTitle extends Command {

    public CommandRankTitle() {
        super("guild rank title <Rank name> <Rank title>", "", "Changes the title for a rank in your guild", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // args[0] = rank
        // args[1] = title
        // args[2] = rank name
        // args[3] = rank title


        if (args.length < 4) {
            Dispatch.sendMessage(Messages.MessagesValues.GUILDS_SYNTAX.value.toString().
                    replace("%syntax", getUsage()), player);
            return true;
        }

        if (!PlayerData.isInGuild(player)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_NOTINGUILD.value, player);
            return true;
        }

        GuildOB guild = new GuildOB(PlayerData.getGuild(player));

        if (!guild.getRank(PlayerData.getRank(player)).canRankTitle()) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_NORANKPERM.value, player);
            return true;
        }

        if (!guild.isRank(args[2])) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_RANK_DELETE_NOTEXISTS.value, player);
            return true;
        }

        StringBuilder buffer = new StringBuilder();
        boolean doneFirst = false;
        for (int i = 3; i < args.length; i++) {
            if (!doneFirst) {
                buffer.append(args[i]);
                doneFirst = true;
            } else {
                buffer.append(' ').append(args[i]);
            }
        }

        String newTitle = buffer.toString();

        guild.changeTitle(args[2], newTitle);
        Dispatch.sendMessage(Messages.MessagesValues.GUILD_RANK_TITLE_CHANGED.value.toString().
                replace("%title", newTitle), player);

        return true;
    }
}