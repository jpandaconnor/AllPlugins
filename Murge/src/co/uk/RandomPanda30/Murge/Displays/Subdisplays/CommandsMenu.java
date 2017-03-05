package co.uk.RandomPanda30.Murge.Displays.Subdisplays;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import co.uk.RandomPanda30.Murge.Items.DefaultItems;
import co.uk.RandomPanda30.Murge.Items.Inventories.Subinventories.CommandsMenuItems;

public class CommandsMenu {

	public static void openCommandsMenu(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 45,
				"Commands options settings");

		inventory.setItem(0, CommandsMenuItems.obtainStart());
		inventory.setItem(9, CommandsMenuItems.obtainStop());
		inventory.setItem(18, CommandsMenuItems.obtainFixwb());

		inventory.setItem(43, DefaultItems.obtainBack());
		inventory.setItem(44, DefaultItems.obtainExit());
		player.openInventory(inventory);
	}
}