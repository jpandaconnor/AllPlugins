package co.uk.RandomPanda30.RPG.Events;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import co.uk.RandomPanda30.RPG.RPG;

public class OnEntityDamageByEntityEvent implements Listener {

	public RPG plugin;

	public OnEntityDamageByEntityEvent (RPG plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player) {
			Player a = (Player) event.getEntity();
			Player b = null;
			if ((event.getDamager() instanceof Player)) {
				b = (Player) event.getDamager();
			}

			if (((event.getDamager() instanceof Arrow))
					&& ((((Arrow) event.getDamager()).getShooter() instanceof Player))) {
				b = (Player) ((Arrow) event.getDamager()).getShooter();
			}

			if ((b != null)
					&& (plugin.getRPGValues().getConfigC().contains(a
							.getUniqueId().toString()))
					&& (plugin.getRPGValues().getConfigC().contains(b
							.getUniqueId().toString()))) {
				String guild = plugin.getRPGValues().getConfigC()
						.getString(a.getUniqueId().toString() + ".Guild.Name");
				String guild1 = plugin.getRPGValues().getConfigC()
						.getString(b.getUniqueId().toString() + ".Guild.Name");
				if (guild.equalsIgnoreCase(guild1)) {
					event.setDamage(0);
					event.setCancelled(true);
				}
			}
		}
	}
}