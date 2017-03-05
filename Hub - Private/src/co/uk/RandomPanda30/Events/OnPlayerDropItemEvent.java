package co.uk.RandomPanda30.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import co.uk.RandomPanda30.Main.Main;

public class OnPlayerDropItemEvent implements Listener {
	
	public OnPlayerDropItemEvent(Main plugin) {
		Main.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerDropItemEvent(PlayerDropItemEvent e) {
		Player player = e.getPlayer();
		if(player.getWorld().getName().equals("Hub")) {
			if(Main.plugin.getConfig().getBoolean("AllowDrops") == false) {
				e.setCancelled(true);
			}else{
				e.setCancelled(false);
			}
		}
	}
}
