package co.uk.RandomPanda30.Murge.Events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import co.uk.RandomPanda30.Murge.MurgeData;

public class OnEntityDamageEvent implements Listener {

	@EventHandler
	public void onEntityDamageEvent(EntityDamageEvent event) {
		Entity entity = event.getEntity();
		Player player = null;

		if (entity instanceof Player) {
			player = (Player) entity;
			if (player.getWorld().equals(MurgeData.getWorld())) {
				if (event.getCause().equals(DamageCause.LIGHTNING)) {
					event.setCancelled(true);
				}
			}
		}
	}
}