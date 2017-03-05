package co.uk.RandomPanda30.MWarn.Methods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import co.uk.RandomPanda30.MWarn.Items.I;
import co.uk.RandomPanda30.MWarn.Items.IB;

public class T {

	public static void openControlPanel(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 45, "\u00A7r "
				+ "MWarn control panel");
		inventory.setItem(0, IB.obtainPaperDetails(player));
		inventory.setItem(2, IB.obtainAddWarning());
		inventory.setItem(4, IB.obtainRemoveWarning());
		inventory.setItem(6, IB.obtainClearWarnings());
		inventory.setItem(8, IB.obtainMainButton());
		inventory.setItem(18, IB.obtainBanButton());
		inventory.setItem(20, IB.obtainUnbanButton());
		inventory.setItem(36, IB.obtainInitConfigs());
		inventory.setItem(38, IB.obtainOffOnButton());
		inventory.setItem(40, IB.obtainConfigValues());
		// inventory.setItem(26, I.obtainNext());
		inventory.setItem(44, I.obtainExit());
		player.openInventory(inventory);
	}

	public static void openMControlPanel(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 9, "\u00A7r "
				+ "MWarn control panel");
		inventory.setItem(0, IB.obtainPaperDetails(player));
		inventory.setItem(2, IB.obtainAddWarning());
		inventory.setItem(4, IB.obtainRemoveWarning());
		inventory.setItem(6, IB.obtainMainButton());
		inventory.setItem(8, I.obtainExit());
		player.openInventory(inventory);
	}
}