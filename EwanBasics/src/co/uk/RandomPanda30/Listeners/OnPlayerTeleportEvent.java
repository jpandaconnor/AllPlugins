package co.uk.RandomPanda30.Listeners;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import co.uk.RandomPanda30.Basics.Main;

public class OnPlayerTeleportEvent implements Listener {

	public OnPlayerTeleportEvent (Main plugin) {
		Main.plugin = plugin;
	}

	@EventHandler
	public void onPlayerTeleportEvent(PlayerTeleportEvent event) {
		ArrayList<String> randomSpawnWorlds = (ArrayList<String>) Main.plugin
				.getConfig().getStringList("randomSpawnWorlds");
		Location to = event.getTo();
		Player player = event.getPlayer();
		for (String worlds : randomSpawnWorlds) {
			World world = Bukkit.getWorld(worlds);
			if (world != null) {
				if (world.getName() == to.getWorld().getName());
				double max = 9;
				Location location = new Location(world, 0, 0, 0);
				location.setX(world.getSpawnLocation().getX() + Math.random()
						* max * 2 - max);
				location.setZ(world.getSpawnLocation().getZ() + Math.random()
						* max * 2 - max);
				location.setY(world.getHighestBlockYAt(location.getBlockX(),
						location.getBlockZ()));
				player.teleport(location);
			}
		}
	}
}
