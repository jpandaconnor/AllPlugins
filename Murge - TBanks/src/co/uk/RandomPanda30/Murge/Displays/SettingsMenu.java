package co.uk.RandomPanda30.Murge.Displays;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import co.uk.RandomPanda30.Murge.Items.DefaultItems;
import co.uk.RandomPanda30.Murge.Items.Inventories.SettingsMenuItems;

public class SettingsMenu {

	public static void openSettingsMenu(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 9,
				"Murge control panel");
		inventory.setItem(0, SettingsMenuItems.obtainWorldBorder());
		inventory.setItem(1, SettingsMenuItems.obtainLocations());
		inventory.setItem(2, SettingsMenuItems.obtainPurgeValues());
		inventory.setItem(3, SettingsMenuItems.obtainPurgeOptions());
		inventory.setItem(4, SettingsMenuItems.obtainBroadcastOptions());

		inventory.setItem(8, DefaultItems.obtainExit());
		inventory.setItem(7, DefaultItems.obtainBack());
		player.openInventory(inventory);
	}
}