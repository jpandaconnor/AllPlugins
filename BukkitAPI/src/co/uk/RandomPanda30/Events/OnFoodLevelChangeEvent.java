package co.uk.RandomPanda30.Events;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import co.uk.RandomPanda30.Main.Main;

public class OnFoodLevelChangeEvent implements Listener {
	
	public OnFoodLevelChangeEvent(Main plugin) {
		Main.plugin = plugin;
	}
	
	@EventHandler
	public void FoodLevelChange(FoodLevelChangeEvent e) {
		Player player = (Player) e.getEntity();
		String spawn = "Spawn";
		World world = Bukkit.getWorld(spawn);
		if(player.getWorld().equals(world)) {
			e.setCancelled(true);
		}
	}

}
