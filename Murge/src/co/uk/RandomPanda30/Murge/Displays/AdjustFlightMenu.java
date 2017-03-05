package co.uk.RandomPanda30.Murge.Displays;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import co.uk.RandomPanda30.Murge.Items.Inventories.AdjustFlightMenuItems;

public class AdjustFlightMenu {

	public static void openAdjustFlightMenu(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 9,
				"Adjust flight speed");
		inventory.setItem(0, AdjustFlightMenuItems.obtain1(player));
		inventory.setItem(2, AdjustFlightMenuItems.obtain2(player));
		inventory.setItem(4, AdjustFlightMenuItems.obtain3(player));
		inventory.setItem(6, AdjustFlightMenuItems.obtain4(player));
		inventory.setItem(8, AdjustFlightMenuItems.obtain5(player));
		player.openInventory(inventory);
	}
}
