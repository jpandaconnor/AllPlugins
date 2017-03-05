package co.uk.RandomPanda30.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import co.uk.RandomPanda30.Main.Main;

public class OnEntityDamageByEntityEvent implements Listener {

	public OnEntityDamageByEntityEvent (Main plugin) {
		Main.plugin = plugin;
	}

	@EventHandler
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			if (e.getEntity() instanceof Player) {
				if (e.getEntity().getWorld().getName().equals("Hub")) {
					e.setCancelled(true);
				} else {
					return;
				}
			}
		}
	}

}
