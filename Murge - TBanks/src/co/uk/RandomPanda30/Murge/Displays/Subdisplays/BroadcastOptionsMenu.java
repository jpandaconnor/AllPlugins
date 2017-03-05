package co.uk.RandomPanda30.Murge.Displays.Subdisplays;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import co.uk.RandomPanda30.Murge.Items.DefaultItems;
import co.uk.RandomPanda30.Murge.Items.Inventories.Subinventories.BroadcastOptionsItems;

public class BroadcastOptionsMenu {

	public static void openBroadcastOptionsMenu(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 45,
				"Broadcast options settings");
		inventory
				.setItem(0, BroadcastOptionsItems.obtainBroadcastPlayerLeave());

		inventory.setItem(43, DefaultItems.obtainBack());
		inventory.setItem(44, DefaultItems.obtainExit());
		player.openInventory(inventory);
	}
}