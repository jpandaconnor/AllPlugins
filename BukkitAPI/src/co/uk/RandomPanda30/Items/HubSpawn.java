package co.uk.RandomPanda30.Items;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Main.Main;

public class HubSpawn {
	
	public HubSpawn(Main plugin) {
		Main.plugin = plugin;
	}
	
	public static void hubSpawn(Player player) {
		String spawn = "Spawn";
		World hub = Bukkit.getWorld(spawn);
		Location l = hub.getSpawnLocation();
		player.teleport(l);
	}
}
