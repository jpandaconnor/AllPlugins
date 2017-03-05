package co.uk.RandomPanda30.Murge.Displays;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import co.uk.RandomPanda30.Murge.Items.PurgeButtons;
import co.uk.RandomPanda30.Murge.Items.Inventories.MainMenuItems;

public class MainMenu {

	public static void openMainMenu(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 45, "Murge");
		inventory.setItem(19, MainMenuItems.obtainDescription());
		inventory.setItem(23, MainMenuItems.obtainHead(player));
		player.openInventory(inventory);
	}

	public static void openMainMenuA(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 45, "Murge");
		inventory.setItem(14, MainMenuItems.obtainSettings());
		inventory.setItem(19, MainMenuItems.obtainDescription());
		inventory.setItem(23, MainMenuItems.obtainHead(player));
		inventory.setItem(36, PurgeButtons.getStart());
		inventory.setItem(37, PurgeButtons.getStop());
		player.openInventory(inventory);
	}
}