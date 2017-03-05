package co.uk.RandomPanda30.SAdminO.Events;

import co.uk.RandomPanda30.SAdminO.Data;
import co.uk.RandomPanda30.SAdminO.SAdminO;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class OnPlayerJoinEvent implements Listener {

    public SAdminO plugin;

    public OnPlayerJoinEvent(SAdminO plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        UUID uuid = player.getUniqueId();

		/* Add to the database if not there */
        if (!Data.getCollection().isInSystem(uuid)) {
            Data.getCollection().addToSystem(uuid);
        }


    }
}