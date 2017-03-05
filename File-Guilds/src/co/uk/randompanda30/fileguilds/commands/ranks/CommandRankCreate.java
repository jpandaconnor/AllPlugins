package co.uk.randompanda30.fileguilds.commands.ranks;

import co.uk.randompanda30.fileguilds.commands.handle.Command;
import co.uk.randompanda30.fileguilds.config.Messages;
import co.uk.randompanda30.fileguilds.data.PlayerData;
import co.uk.randompanda30.fileguilds.object.GuildOB;
import co.uk.randompanda30.fileguilds.string.Dispatch;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRankCreate extends Command {

    public CommandRankCreate() {
        super("guild rank create <Rank name> <Rank title>", "", "Creates a new rank in your guild", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // args[0] = rank
        // args[1] = create
        // args[2] = Rank name
        // args[3] = Rank title

        if (args.length < 4) {
            Dispatch.sendMessage(Messages.MessagesValues.GUILDS_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        if (!PlayerData.isInGuild(player)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_NOTINGUILD.value, player);
            return true;
        }

        GuildOB guild = new GuildOB(PlayerData.getGuild(player));

        if (!guild.getRank(PlayerData.getRank(player)).canRankCreate()) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_NORANKPERM.value, player);
            return true;
        }

        String rankName = args[2];
        String title = "";

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

        title = buffer.toString();
        guild.addRank(rankName, title);

        Dispatch.sendMessage(Messages.MessagesValues.GUILD_RANK_CREATE_CREATED.value.toString()
                .replace("%rank", args[2]), player);

        return true;
    }
}