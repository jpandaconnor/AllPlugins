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

public class CommandTO extends Command {

    public CommandTO() {
        super("guild to <Player Name>", "", "Transfers guild ownership to another memeber", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // args[0] = to
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

        if (!guild.getRank(PlayerData.getRank(player)).canTransferOwnership()) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_NORANKPERM.value, player);
            return true;
        }

        String target = args[1];
        if (Bukkit.getServer().getPlayer(target) != null) {
            Player ptk = Bukkit.getPlayer(target);
            UUID uuid = ptk.getUniqueId();

            if (!ptk.hasPermission("guilds.beater")) {
                Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_NOPERM.value, player);
                return true;
            }

            if (PlayerData.isInGuild(uuid)) {
                if (!PlayerData.getGuild(ptk).equals(guild.getName())) {
                    Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_TO_NOTINYOURGUILD.value, player);
                    return true;
                }

                guild.setOwner(ptk);
                // Message here
            }
        } else {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_TARGETNOTFOUND.value, player);
            return true;
        }
        return true;
    }
}