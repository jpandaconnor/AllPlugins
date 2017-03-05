package co.uk.randompanda30.fileguilds.commands.ranks;

import co.uk.randompanda30.fileguilds.TEMP;
import co.uk.randompanda30.fileguilds.commands.handle.Command;
import co.uk.randompanda30.fileguilds.config.Data;
import co.uk.randompanda30.fileguilds.config.Messages;
import co.uk.randompanda30.fileguilds.data.PlayerData;
import co.uk.randompanda30.fileguilds.object.GuildOB;
import co.uk.randompanda30.fileguilds.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CommandRankSet extends Command {

    public CommandRankSet() {
        super("guild rank set <Player name> <Rank name>", "", "Sets a players rank in your guild", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // args[0] = rank
        // args[1] = set
        // args[2] = <Player name>
        // args[3] = <Rank name>

        if (args.length != 4) {
            Dispatch.sendMessage(Messages.MessagesValues.GUILDS_SYNTAX.value.toString().
                    replace("%syntax", getUsage()), player);
            return true;
        }

        if (!PlayerData.isInGuild(player)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_NOTINGUILD.value, player);
            return true;
        }

        GuildOB guild = new GuildOB(PlayerData.getGuild(player));

        if (!guild.getRank(PlayerData.getRank(player)).canRankSet()) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_NORANKPERM.value, player);
            return true;
        }

        if (Bukkit.getPlayer(args[2]) != null) {
            UUID targetUUID = Bukkit.getPlayer(args[2]).getUniqueId();

            if (!guild.isRank(args[3])) {
                Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_RANK_SET_NOTARANK.value, player);
                return true;
            }

            if (!PlayerData.isInGuild(targetUUID) || !PlayerData.getGuild(Bukkit.getPlayer(targetUUID))
                    .equals(guild.getName())) {
                Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_RANK_SET_NOTINGUILD.value, player);
                return true;
            }

            TEMP.pdatac.set(targetUUID.toString() + ".rank", args[3]);
            Data.save();

            Dispatch.sendMessage(Messages.MessagesValues.GUILD_RANK_SET_SETTED.value.toString().
                    replace("%rank", args[3]), player);

            if (Bukkit.getPlayer(targetUUID).isOnline()) {
                Dispatch.sendMessage(Messages.MessagesValues.GUILD_RANK_SET_YOUHAVE.value.toString().
                        replace("%rank", args[3]), Bukkit.getPlayer(targetUUID));
            }
        } else {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_TARGETNOTFOUND.value, player);
        }
        return true;
    }

}
