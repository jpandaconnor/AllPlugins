package co.uk.randompanda30.fileguilds.events;

import co.uk.randompanda30.fileguilds.Guilds;
import co.uk.randompanda30.fileguilds.config.Config;
import co.uk.randompanda30.fileguilds.data.PlayerData;
import co.uk.randompanda30.fileguilds.object.GuildOB;
import co.uk.randompanda30.fileguilds.plot.Plot;
import co.uk.randompanda30.fileguilds.utils.VectorUtil;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class OnBlockPlaceEvent implements Listener {

    public OnBlockPlaceEvent() {
        Guilds.getPlugin().getServer().getPluginManager().registerEvents(this, Guilds.getPlugin());
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        Location location = event.getBlockPlaced().getLocation();
        World world = location.getWorld();

        if (world.getName().equalsIgnoreCase((String) Config.ConfigValues.PLOTWORLD.value)) {
            if (!PlayerData.isInGuild(player)) {
                if (!player.hasPermission("guilds.bypass")) {
                    event.setCancelled(true);
                } else {
                    return;
                }
            } else {
                GuildOB guildOB = new GuildOB(PlayerData.getGuild(player));

                if (!guildOB.hasPlot()) {
                    event.setCancelled(true);
                }

                Plot plot = guildOB.getPlot();
                VectorUtil vector = new VectorUtil(plot.p1.toVector(), plot.p2.toVector());
                if (!vector.contains(location)) {
                    event.setCancelled(true);
                } else {
                    event.setCancelled(false);
                }
            }
        }
    }
}