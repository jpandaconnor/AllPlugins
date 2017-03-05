package co.uk.RandomPanda30.SAO.Events;

import co.uk.RandomPanda30.SAO.Manager.PlayerManager;
import co.uk.RandomPanda30.SAO.SAO;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnPlayerJoinEvent implements Listener {

    public OnPlayerJoinEvent(SAO plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!PlayerManager.isInSystem(player)) {
            PlayerManager.addNewPlayer(player);
        }
    }
}