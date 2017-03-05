package co.uk.RandomPanda30.KnightCrates.Menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import co.uk.RandomPanda30.KnightCrates.KnightCrates;
import co.uk.RandomPanda30.KnightCrates.Items.Keys;

public class CrateMenu {

	public static void openCrateMenu(Player player) {
		Inventory inv = Bukkit.createInventory(null, 27,
				ChatColor.translateAlternateColorCodes('&', "&6&lCrate Menu"));

		if (KnightCrates.plugin.getConfig()
				.contains(player.getUniqueId().toString() + ".iron")) {
			inv.setItem(10, Keys.getIronKey(KnightCrates.plugin.getConfig()
					.getInt(player.getUniqueId().toString() + ".iron")));
		} else {
			inv.setItem(10, Keys.getIronKey(0));
		}

		if (KnightCrates.plugin.getConfig()
				.contains(player.getUniqueId().toString() + ".diamond")) {
			inv.setItem(13, Keys.getDiamondKey(KnightCrates.plugin.getConfig()
					.getInt(player.getUniqueId().toString() + ".diamond")));
		} else {
			inv.setItem(13, Keys.getDiamondKey(0));
		}

		if (KnightCrates.plugin.getConfig()
				.contains(player.getUniqueId().toString() + ".gold")) {
			inv.setItem(16, Keys.getGoldKey(KnightCrates.plugin.getConfig()
					.getInt(player.getUniqueId().toString() + ".gold")));
		} else {
			inv.setItem(16, Keys.getGoldKey(0));
		}
		
		player.openInventory(inv);
	}
}