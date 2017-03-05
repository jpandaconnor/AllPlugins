package co.uk.randompanda30.fileguilds.commands;

import co.uk.randompanda30.fileguilds.commands.handle.Command;
import co.uk.randompanda30.fileguilds.config.Messages;
import co.uk.randompanda30.fileguilds.data.GuildData;
import co.uk.randompanda30.fileguilds.data.PlayerData;
import co.uk.randompanda30.fileguilds.string.Dispatch;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static co.uk.randompanda30.fileguilds.TEMP.guildsc;

public class CommandCreate extends Command {

    public CommandCreate() {
        super("guild create <Guild name> <Guild Tag>", "guilds.create", "Creates a new guild", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // args[0] = create
        // args[1] = <Guild name>
        // args[2] = <Guild tag>

        if (args.length != 3) {
            Dispatch.sendMessage(Messages.MessagesValues.GUILDS_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        if (PlayerData.isInGuild(player)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_ALREADYINGUILD.value, player);
            return true;
        }

        if(args[1].contains(".")) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_CREATE_NODOTS.value, player);
            return true;
        }

        if (guildsc.contains(args[1])) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_CREATE_NAMEEXISTS.value, player);
            return true;
        }

        if (ChatColor.stripColor(args[2]).length() != 4) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_CREATE_TAGNOT4.value, player);
            return true;
        }

        for (String s : guildsc.getConfigurationSection("").getKeys(false)) {
            if (ChatColor.stripColor((String) guildsc.get(s + ".tag")).equals(ChatColor.stripColor(args[2]))) {
                Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_CREATE_TAGEXISTS.value, player);
                return true;
            }
        }

        GuildData.createGuild(args[1], args[2], player);
        Dispatch.sendMessage(Messages.MessagesValues.GUILDS_CREATE_CREATED.value.toString().replaceAll
                ("%guild", args[1]), player);
        return true;
    }
}