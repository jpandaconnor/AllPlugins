package co.uk.RandomPanda30.Events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import co.uk.RandomPanda30.IAFK.Main;

public class OnPlayerMoveEvent implements Listener {

	public OnPlayerMoveEvent (Main plugin) {
		Main.plugin = plugin;
	}

	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent event) {

		final Player player = event.getPlayer();
		final double x = player.getLocation().getX();
		final double y = player.getLocation().getY();
		final double z = player.getLocation().getZ();
		
		Location location = new Location(player.getWorld(), x, y, z);
		
		if(player.getWalkSpeed() == 0) {
			
		}
	}
}