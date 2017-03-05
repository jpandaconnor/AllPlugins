package co.uk.RandomPanda30.ItemHandlerPlus.Displays;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.ItemHandlerPlus.Items.I;
import co.uk.RandomPanda30.ItemHandlerPlus.Items.IA;

public class DA {

	public static void openHotbarMenu(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 27,
				"Click to toggle on/off");

		for (ItemStack is : IA.obtainHead()) {
			inventory.addItem(is);
		}
		
		inventory.setItem(25, I.obtainBackButton());
		inventory.setItem(26, I.obtainExitButton());

		player.openInventory(inventory);
	}

}
