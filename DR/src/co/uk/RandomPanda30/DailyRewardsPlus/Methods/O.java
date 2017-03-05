package co.uk.RandomPanda30.DailyRewardsPlus.Methods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import co.uk.RandomPanda30.DailyRewardsPlus.Items.I;

public class O {

	public static void openMenu(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 9, "Daily rewards");

		inventory.setItem(0, I.obtainMainButton(player));
		inventory.setItem(8, I.obtainExitButton());

		player.openInventory(inventory);
	}

	public static void openAdminMenu(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 9, "Daily rewards");

		inventory.setItem(0, I.obtainMainButton(player));
		inventory.setItem(2, I.obtainEditItemsButton());
		inventory.setItem(8, I.obtainExitButton());

		player.openInventory(inventory);
	}

	public static void openItemInventory(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 54,
				"Drag items in here");
		if (N.loadInv() != null) {
			inventory.setContents(N.loadInv());
		}
		player.openInventory(inventory);
	}
}