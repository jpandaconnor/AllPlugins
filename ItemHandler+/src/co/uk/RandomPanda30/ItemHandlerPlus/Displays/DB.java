package co.uk.RandomPanda30.ItemHandlerPlus.Displays;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import co.uk.RandomPanda30.ItemHandlerPlus.Methods.U;

public class DB {
	
	public static void openItemInventory(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 54,
				"Drag items in here");
		if (U.loadInv() != null) {
			inventory.setContents(U.loadInv());
		}
		player.openInventory(inventory);
	}
}