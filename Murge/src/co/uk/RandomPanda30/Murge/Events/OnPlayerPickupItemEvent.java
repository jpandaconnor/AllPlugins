package co.uk.RandomPanda30.Murge.Events;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

import co.uk.RandomPanda30.Murge.Stats.StatsHandler;

public class OnPlayerPickupItemEvent implements Listener {

	@EventHandler
	public void onPlayerPickupItemEvent(PlayerPickupItemEvent event) {
		Player player = event.getPlayer();
		UUID uuid = player.getUniqueId();

		if (StatsHandler.inSpectators(uuid)) {
			event.setCancelled(true);
		}
	}
}