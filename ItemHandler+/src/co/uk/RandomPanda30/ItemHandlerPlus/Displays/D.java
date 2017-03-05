package co.uk.RandomPanda30.ItemHandlerPlus.Displays;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import co.uk.RandomPanda30.ItemHandlerPlus.Items.I;

public class D {

	public static void openIHPMenu(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 9,
				"ItemHandler+ menu");

		inventory.setItem(0, I.obtainPaperDetails());
		inventory.setItem(2, I.obtainHotbarRestriction());
		// inventory.setItem(4, I.obtainItemDropRestrction());
		inventory.setItem(8, I.obtainExitButton());

		player.openInventory(inventory);
	}
}