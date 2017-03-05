package co.uk.RandomPanda30.MineRP.Displays;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import co.uk.RandomPanda30.MineRP.Items.JobItems;

public class JobsMenu {

	public static void openJobsMenu(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 27, "Jobs Menu");
		inventory.setItem(0, JobItems.citizenItem());
		player.openInventory(inventory);
	}
}