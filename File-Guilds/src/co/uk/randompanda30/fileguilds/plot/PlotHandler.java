package co.uk.randompanda30.fileguilds.plot;

import co.uk.randompanda30.fileguilds.config.Config;
import co.uk.randompanda30.fileguilds.config.Guild;
import co.uk.randompanda30.fileguilds.config.Messages;
import co.uk.randompanda30.fileguilds.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Random;

import static co.uk.randompanda30.fileguilds.TEMP.guildsc;

public class PlotHandler {

    String guild;

    public PlotHandler(String guild) {
        this.guild = guild;
    }

    public String getWorld() {
        return (String) Config.ConfigValues.PLOTWORLD.value;
    }

    public Location getSpawn() {
        return new Location(Bukkit.getWorld(getWorld()), getCo('X', 3), getCo('Y', 3), getCo('Z', 3));
    }

    public int getCo(char axis, int option) {
        return (int) guildsc.get(guild + ".plot." + option + "." + Character.toUpperCase(axis));
    }

    public void createPlot(Player player) {
        int x = 0;
        int z = 0;

        int rx = 0;
        int rz = 0;

        Random random = new Random();

        rx = random.nextInt(1690) + 10;
        rz = random.nextInt(1690) + 10;

        x = (700 * rx) + 350;
        z = (700 * rz) + 350;

        for (String s : guildsc.getConfigurationSection("").getKeys(false)) {
            if (guildsc.contains(s + ".plot")) {
                if (guildsc.get(s + ".plot.3.X") == x && guildsc.get(s + ".plot.3.Z") == z) {
                    Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_PLOT_CREATE_COULDNOTFIND.value, player);
                    return;
                }
            }
        }

        player.teleport(new Location(Bukkit.getWorld(getWorld()), x, 65, z));

        Location bottom = new Location(Bukkit.getWorld(getWorld()), x + 49, 0, z + 49);
        Location top = new Location(Bukkit.getWorld(getWorld()), x - 51, 100, z - 49);
        Location spawn = new Location(Bukkit.getWorld(getWorld()), x, 65, z);

        guildsc.set(guild + ".plot.world", getWorld());
        guildsc.set(guild + ".plot.1.X", (int) bottom.getX());
        guildsc.set(guild + ".plot.2.X", (int) top.getX());
        guildsc.set(guild + ".plot.3.X", (int) spawn.getX());

        guildsc.set(guild + ".plot.1.Y", (int) bottom.getY());
        guildsc.set(guild + ".plot.2.Y", (int) top.getY());
        guildsc.set(guild + ".plot.3.Y", (int) spawn.getY());

        guildsc.set(guild + ".plot.1.Z", (int) bottom.getZ());
        guildsc.set(guild + ".plot.2.Z", (int) top.getZ());
        guildsc.set(guild + ".plot.3.Z", (int) spawn.getZ());
        Guild.save();

        Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_PLOT_CREATE_CREATED.value, player);
    }

    public void setSpawn(Player player) {
        guildsc.set(guild + ".plot.3.X", (int) player.getLocation().getX());
        guildsc.set(guild + ".plot.3.Y", (int) player.getLocation().getY());
        guildsc.set(guild + ".plot.3.Z", (int) player.getLocation().getZ());
        Guild.save();
    }

    public void deletePlot() {
        guildsc.set(guild + ".plot", null);
        Guild.save();
    }

    public void sendHome(Player player) {
        player.teleport(getSpawn());
    }
}