package co.uk.RandomPanda30.KnightCrates.Events;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.KnightCrates.KnightCrates;

public class OnInventoryCloseEvent implements Listener {

	public KnightCrates plugin;

	public OnInventoryCloseEvent (KnightCrates plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onInventoryCloseEvent(InventoryCloseEvent event) {
		Inventory inventory = event.getInventory();
		ArrayList<ItemStack> is = new ArrayList<>();
		switch (inventory.getName()) {
		case "Crate editor for: iron":
			if (!(inventory.getContents().length == 0)) {
				for (ItemStack i : inventory.getContents()) {
					if (i != null) {
						if (!(i.getType().equals(Material.AIR))) {
							is.add(i);
						}
					}
				}
				KnightCrates.iron.setItems(is);
				plugin.saveConfig();
			}
			break;
		case "Crate editor for: gold":
			if (!(inventory.getContents().length == 0)) {
				for (ItemStack i : inventory.getContents()) {
					if (i != null) {
						if (!(i.getType().equals(Material.AIR))) {
							is.add(i);
						}
					}
				}
				KnightCrates.gold.setItems(is);
			}
			break;
		case "Crate editor for: diamond":
			if (!(inventory.getContents().length == 0)) {
				for (ItemStack i : inventory.getContents()) {
					if (i != null) {
						if (!(i.getType().equals(Material.AIR))) {
							is.add(i);
						}
					}
				}
				KnightCrates.diamond.setItems(is);
			}
			break;
		}
	}
}