package co.uk.RandomPanda30.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import co.uk.RandomPanda30.Main.Main;

public class OnEntityDamageEvent implements Listener {

	public OnEntityDamageEvent (Main plugin) {
		Main.plugin = plugin;
	}

	@EventHandler
	public void onEntityDamageEvent(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			if (e.getCause() == DamageCause.FALL
					|| e.getCause() == DamageCause.FIRE
					|| e.getCause() == DamageCause.LAVA
					|| e.getCause() == DamageCause.DROWNING
					|| e.getCause() == DamageCause.FIRE_TICK) {
				if (e.getEntity().getWorld().getName().equals("Hub")) {
					e.setCancelled(true);
				} else {
					return;
				}
			}
		}
	}
}
