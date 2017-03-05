package co.uk.RandomPanda30.Murge.Displays;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import co.uk.RandomPanda30.Murge.Items.DefaultItems;
import co.uk.RandomPanda30.Murge.Items.Inventories.SettingsMenuItems;

public class SettingsMenu {

	public static void openSettingsMenu(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 18,
				"Murge control panel");
		inventory.setItem(0, SettingsMenuItems.obtainWorldBorder());
		inventory.setItem(1, SettingsMenuItems.obtainLocations());
		inventory.setItem(2, SettingsMenuItems.obtainPurgeValues());
		inventory.setItem(3, SettingsMenuItems.obtainPurgeOptions());
		inventory.setItem(4, SettingsMenuItems.obtainBroadcastOptions());
		inventory.setItem(5, SettingsMenuItems.obtainCommandsOptions());
		inventory.setItem(6, SettingsMenuItems.obtainKits());
		inventory.setItem(9, SettingsMenuItems.obtainRegen());
		
		inventory.setItem(16, DefaultItems.obtainBack());
		inventory.setItem(17, DefaultItems.obtainExit());
		player.openInventory(inventory);
	}
}