package co.uk.RandomPanda30.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import co.uk.RandomPanda30.Main.Main;

public class OnFoodLevelChangeEvent implements Listener {

	public OnFoodLevelChangeEvent (Main plugin) {
		Main.plugin = plugin;
	}

	@EventHandler
	public void onFoodChange(FoodLevelChangeEvent e) {
		Player player = (Player) e.getEntity();
		if (player.getWorld().getName().equals("Hub")) {
			if (Main.plugin.getConfig().getBoolean("HungerLoss") == false) {
				e.setCancelled(true);
			} else {
				e.setCancelled(false);
			}
		}
	}

}
