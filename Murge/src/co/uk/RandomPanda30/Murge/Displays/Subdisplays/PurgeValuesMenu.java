package co.uk.RandomPanda30.Murge.Displays.Subdisplays;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import co.uk.RandomPanda30.Murge.Items.DefaultItems;
import co.uk.RandomPanda30.Murge.Items.Inventories.Subinventories.PurgeValueItems;

public class PurgeValuesMenu {

	public static void openPurgeValuesMenu(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 45,
				"Purge value settings");
		inventory.setItem(0, PurgeValueItems.obtainTimeBefore_MINUS());
		inventory.setItem(1, PurgeValueItems.obtainTimeBefore());
		inventory.setItem(2, PurgeValueItems.obtainTimeBefore_PLUS());
		
		inventory.setItem(4, PurgeValueItems.obtainKillEcon_MINUS_ONE());
		inventory.setItem(5, PurgeValueItems.obtainKillEcon_MINUS_TEN());
		inventory.setItem(6, PurgeValueItems.obtainKillEcon());
		inventory.setItem(7, PurgeValueItems.obtainKillEcon_PLUS_TEN());
		inventory.setItem(8, PurgeValueItems.obtainKillEcon_PLUS_ONE());
		
		inventory.setItem(9, PurgeValueItems.obtainPurgeDuration_MINUS());
		inventory.setItem(10, PurgeValueItems.obtainPurgeDuration());
		inventory.setItem(11, PurgeValueItems.obtainPurgeDuration_PLUS());

		inventory.setItem(18, PurgeValueItems.obtainFightCooldown_MINUS());
		inventory.setItem(19, PurgeValueItems.obtainFightCooldown());
		inventory.setItem(20, PurgeValueItems.obtainFightCooldown_PLUS());

		inventory.setItem(43, DefaultItems.obtainBack());
		inventory.setItem(44, DefaultItems.obtainExit());
		player.openInventory(inventory);
	}
}