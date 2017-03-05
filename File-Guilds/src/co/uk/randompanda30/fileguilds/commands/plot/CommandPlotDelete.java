package co.uk.randompanda30.fileguilds.commands.plot;

import co.uk.randompanda30.fileguilds.commands.handle.Command;
import co.uk.randompanda30.fileguilds.config.Messages;
import co.uk.randompanda30.fileguilds.data.PlayerData;
import co.uk.randompanda30.fileguilds.object.GuildOB;
import co.uk.randompanda30.fileguilds.plot.PlotHandler;
import co.uk.randompanda30.fileguilds.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class CommandPlotDelete extends Command {

    public CommandPlotDelete() {
        super("guild plot delete", "", "Deletes your plot guild", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // args[0] = plot
        // args[1] = create

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

        if (!guild.getRank(PlayerData.getRank(player)).canPlotDelete()) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_NORANKPERM.value, player);
            return true;
        }

        if (!guild.hasPlot()) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_PLOT_DELETE_NOPLOTINFIRSTPLACE.value, player);
            return true;
        }

        PlotHandler plotHandler = new PlotHandler(guild.getName());
        plotHandler.deletePlot();

        for (String s : new ArrayList<>(guild.getMembers())) {
            if (Bukkit.getServer().getPlayer(UUID.fromString(s)) != null &&
                    Bukkit.getServer().getPlayer(UUID.fromString(s)).isOnline()) {
                Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_PLOT_DELETE_DELETED.value,
                        Bukkit.getServer().getPlayer(UUID.fromString(s)));
            }
        }

        // Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_PLOT_DELETE_DELETED.value, player);
        return true;
    }
}