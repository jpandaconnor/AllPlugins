package co.uk.RandomPanda30.Murge.Displays.Subdisplays;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import co.uk.RandomPanda30.Murge.Items.DefaultItems;
import co.uk.RandomPanda30.Murge.Items.Inventories.Subinventories.RegenTimesItems;

public class RegenTimesMenu {

	public static void openRegenTimes(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 54, "Regen times");

		inventory.setItem(0, RegenTimesItems.obtainDays_MINUS());
		inventory.setItem(1, RegenTimesItems.obtainHours_MINUS());
		inventory.setItem(2, RegenTimesItems.obtainMinutes_MINUS());
		inventory.setItem(3, RegenTimesItems.obtainSeconds_MINUS());
		inventory.setItem(4, RegenTimesItems.obtainOreTime());
		inventory.setItem(5, RegenTimesItems.obtainSeconds_PLUS());
		inventory.setItem(6, RegenTimesItems.obtainMinutes_PLUS());
		inventory.setItem(7, RegenTimesItems.obtainHours_PLUS());
		inventory.setItem(8, RegenTimesItems.obtainDays_PLUS());
		
		inventory.setItem(18, RegenTimesItems.obtainDays_MINUS());
		inventory.setItem(19, RegenTimesItems.obtainHours_MINUS());
		inventory.setItem(20, RegenTimesItems.obtainMinutes_MINUS());
		inventory.setItem(21, RegenTimesItems.obtainSeconds_MINUS());
		inventory.setItem(22, RegenTimesItems.obtainTreeTime());
		inventory.setItem(23, RegenTimesItems.obtainSeconds_PLUS());
		inventory.setItem(24, RegenTimesItems.obtainMinutes_PLUS());
		inventory.setItem(25, RegenTimesItems.obtainHours_PLUS());
		inventory.setItem(26, RegenTimesItems.obtainDays_PLUS());

		inventory.setItem(52, DefaultItems.obtainBack());
		inventory.setItem(53, DefaultItems.obtainExit());

		player.openInventory(inventory);
	}					 
}