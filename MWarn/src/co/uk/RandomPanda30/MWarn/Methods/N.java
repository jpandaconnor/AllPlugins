package co.uk.RandomPanda30.MWarn.Methods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import co.uk.RandomPanda30.MWarn.Items.I;
import co.uk.RandomPanda30.MWarn.Items.ID;

public class N {

	public static void openOnOffInventory(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 54, "\u00A7r "
				+ "Turn features on/off");
		inventory.setItem(0, ID.obtainMWarn());
		inventory.setItem(8, I.obtainPrevious());

		inventory.setItem(9, I.obtainBorder());
		inventory.setItem(10, I.obtainBorder());
		inventory.setItem(11, I.obtainBorder());
		inventory.setItem(12, I.obtainBorder());
		inventory.setItem(13, I.obtainBorder());
		inventory.setItem(14, I.obtainBorder());
		inventory.setItem(15, I.obtainBorder());
		inventory.setItem(16, I.obtainBorder());
		inventory.setItem(17, I.obtainBorder());

		inventory.setItem(18, ID.obtainCMDWARN());
		inventory.setItem(20, ID.obtainCMDCLEAR());
		inventory.setItem(22, ID.obtainCMDREMOVE());

		inventory.setItem(27, I.obtainBorder());
		inventory.setItem(28, I.obtainBorder());
		inventory.setItem(29, I.obtainBorder());
		inventory.setItem(30, I.obtainBorder());
		inventory.setItem(31, I.obtainBorder());
		inventory.setItem(32, I.obtainBorder());
		inventory.setItem(33, I.obtainBorder());
		inventory.setItem(34, I.obtainBorder());
		inventory.setItem(35, I.obtainBorder());

		inventory.setItem(36, ID.obtainBroadcastWarning());
		inventory.setItem(38, ID.obtainBroadcastBan());
		inventory.setItem(40, ID.obtainBroadcastUnban());
		inventory.setItem(42, ID.obtainBroadcastClearWarnings());
		inventory.setItem(44, ID.obtainBroadcastRemoveWarning());

		inventory.setItem(45, I.obtainBorder());
		inventory.setItem(46, I.obtainBorder());
		inventory.setItem(47, I.obtainBorder());
		inventory.setItem(48, I.obtainBorder());
		inventory.setItem(49, I.obtainBorder());
		inventory.setItem(50, I.obtainBorder());
		inventory.setItem(51, I.obtainBorder());
		inventory.setItem(52, I.obtainBorder());
		inventory.setItem(53, I.obtainBorder());

		player.openInventory(inventory);
	}
}