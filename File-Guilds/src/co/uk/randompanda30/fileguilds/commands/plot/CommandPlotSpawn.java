package co.uk.randompanda30.fileguilds.commands.plot;

import co.uk.randompanda30.fileguilds.commands.handle.Command;
import co.uk.randompanda30.fileguilds.config.Messages;
import co.uk.randompanda30.fileguilds.data.PlayerData;
import co.uk.randompanda30.fileguilds.object.GuildOB;
import co.uk.randompanda30.fileguilds.plot.Plot;
import co.uk.randompanda30.fileguilds.plot.PlotHandler;
import co.uk.randompanda30.fileguilds.string.Dispatch;
import co.uk.randompanda30.fileguilds.utils.VectorUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPlotSpawn extends Command {

    public CommandPlotSpawn() {
        super("guild plot spawn", "", "Sets the spawn of your plot", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // args[0] = plot
        // args[1] = spawn

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

        if (!guild.getRank(PlayerData.getRank(player)).getName().equals("Leader")) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_NORANKPERM.value, player);
            return true;
        }

        if (!guild.hasPlot()) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_PLOT_DELETE_NOPLOTINFIRSTPLACE.value, player);
            return true;
        }

        Plot plot = guild.getPlot();
        VectorUtil vector = new VectorUtil(plot.p1.toVector(), plot.p2.toVector());

        if(!vector.contains(player.getLocation())) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_PLOT_SPAWN_NOTONPLOT.value, player);
            return true;
        }

        PlotHandler plotHandler = new PlotHandler(guild.getName());
        plotHandler.setSpawn(player);

        Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_PLOT_SPAWN_SPAWNSET.value, player);
        return true;
    }
}