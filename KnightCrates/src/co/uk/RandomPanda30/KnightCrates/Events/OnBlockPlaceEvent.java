package co.uk.RandomPanda30.KnightCrates.Events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.KnightCrates.KnightCrates;
import co.uk.RandomPanda30.KnightCrates.Items.Crates;

public class OnBlockPlaceEvent implements Listener {

	public KnightCrates plugin;

	public OnBlockPlaceEvent (KnightCrates plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		ItemStack item = player.getItemInHand();
		Location location = event.getBlock().getLocation();

		if (item == null) {
			return;
		}

		if (item.getType().equals(Material.AIR)) {
			return;
		}

		if (item.getItemMeta().equals(Crates.getCrate().getItemMeta())) {
			if (!player.hasPermission("kc.crate")) {
				KnightCrates.sendMessage(
						KnightCrates.tag
								+ "&CYou do not have permission to place this",
						player);
				if (player.getInventory().contains(Crates.getCrate())) {
					player.getInventory().remove(Crates.getCrate());
				}
				event.setCancelled(true);
			} else {
				KnightCrates.sendMessage(
						KnightCrates.tag
								+ "&AYou have added this crate to the crates list",
						player);

				if (player.getInventory().contains(Crates.getCrate())) {
					int amount = item.getAmount();
					if (amount == 0) {
						player.getInventory().remove(Crates.getCrate());
					} else {
						player.getInventory().remove(Crates.getCrate());
						item.setAmount(item.getAmount() - 1);
						player.getInventory().addItem(item);
					}
				}

				KnightCrates.locations.add(location);
			}
		}
	}
}