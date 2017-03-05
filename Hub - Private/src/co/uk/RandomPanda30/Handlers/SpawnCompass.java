package co.uk.RandomPanda30.Handlers;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.Main.Main;

public class SpawnCompass {

	public SpawnCompass (Main plugin) {
		Main.plugin = plugin;
	}

	public static void spawnCompass(Player player) {
		ItemStack compass = new ItemStack(Material.WATCH);
		ItemMeta compass_Im = compass.getItemMeta();
		compass_Im.setDisplayName("§6Toggle Players");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§6Click to toggle players!");
		compass_Im.setLore(lore);
		compass.setItemMeta(compass_Im);
		if (player.getInventory().contains(compass)) {
			return;
		} else {
			player.getInventory().addItem(compass);
		}
	}
}
