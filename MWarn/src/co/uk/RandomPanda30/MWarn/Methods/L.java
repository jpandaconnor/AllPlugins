package co.uk.RandomPanda30.MWarn.Methods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import co.uk.RandomPanda30.MWarn.Items.I;
import co.uk.RandomPanda30.MWarn.Items.IE;

public class L {

	public static void openConfigValue(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 54, "\u00A7r "
				+ "Edit config values");
		inventory.setItem(0, IE.obtainWarningThreshold_PBUTTON());
		inventory.setItem(2, IE.obtainBanLength_PBUTTON());
		
		inventory.setItem(18, IE.obtainWarningThreshold_MBUTTON());
		inventory.setItem(20, IE.obtainBanLength_MBUTTON());

		inventory.setItem(9, IE.obtainWarningThreshold());
		inventory.setItem(11, IE.obtainBanLength());

		inventory.setItem(53, I.obtainPrevious());

		player.openInventory(inventory);
	}
}