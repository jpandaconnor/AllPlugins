package co.uk.RandomPanda30.KnightCrates.Events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import co.uk.RandomPanda30.KnightCrates.KnightCrates;

public class OnBlockBreakEvent implements Listener {

	public KnightCrates plugin;

	public OnBlockBreakEvent (KnightCrates plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
	}

	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Location location = event.getBlock().getLocation();

		if (KnightCrates.locations.contains(location))
			if (!player.hasPermission("kc.crate")) {
				KnightCrates.sendMessage(
						KnightCrates.tag
								+ "&CYou do not have permission to break this",
						player);
				event.setCancelled(true);
			} else {
				KnightCrates.sendMessage(
						KnightCrates.tag
								+ "&AYou have removed this crate location",
						player);
				if (KnightCrates.locations.contains(location)) {
					KnightCrates.locations.remove(location);
				}
			}
	}
}