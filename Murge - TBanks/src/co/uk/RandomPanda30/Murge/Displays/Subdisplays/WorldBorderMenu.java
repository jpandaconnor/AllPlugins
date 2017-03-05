package co.uk.RandomPanda30.Murge.Displays.Subdisplays;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import co.uk.RandomPanda30.Murge.Items.DefaultItems;
import co.uk.RandomPanda30.Murge.Items.Inventories.Subinventories.WorldBorderItems;

public class WorldBorderMenu {

	public static void openWorldBorderSettings(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 45,
				"World border settings");

		inventory.setItem(1, WorldBorderItems.obtainStatus());
		
		inventory.setItem(9, WorldBorderItems.obtainSize_MINUS());
		inventory.setItem(10, WorldBorderItems.obtainSize());
		inventory.setItem(11, WorldBorderItems.obtainSize_PLUS());
		
		inventory.setItem(18, WorldBorderItems.obtainDamage_MINUS());
		inventory.setItem(19, WorldBorderItems.obtainDamage());
		inventory.setItem(20, WorldBorderItems.obtainDamage_PLUS());

		inventory.setItem(43, DefaultItems.obtainBack());
		inventory.setItem(44, DefaultItems.obtainExit());
		player.openInventory(inventory);
	}

}