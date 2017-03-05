package co.uk.randompanda30.fileguilds.commands;

import co.uk.randompanda30.fileguilds.commands.handle.Command;
import co.uk.randompanda30.fileguilds.config.Messages;
import co.uk.randompanda30.fileguilds.data.PlayerData;
import co.uk.randompanda30.fileguilds.object.GuildOB;
import co.uk.randompanda30.fileguilds.string.Dispatch;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGuildPVP extends Command {

    public CommandGuildPVP() {
        super("guild pvp", "", "Toggles PVP between guild members", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // args[0] = pvp

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

        if (!guild.getRank(PlayerData.getRank(player)).canPVP()) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_NORANKPERM.value, player);
            return true;
        }

        guild.togglePVP();
        Dispatch.sendMessage(Messages.MessagesValues.GUILD_PVP_TOGGLED.value.toString().replace("%tog", (guild.getPVP()
                ? "&aOn" : "&cOff")), player);
        return true;
    }

}