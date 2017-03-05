package co.uk.RandomPanda30.MWarn.Display;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import co.uk.RandomPanda30.MWarn.B;
import co.uk.RandomPanda30.MWarn.Items.I;
import co.uk.RandomPanda30.MWarn.Items.IA;
import co.uk.RandomPanda30.MWarn.Items.IB;
import co.uk.RandomPanda30.MWarn.Methods.Y;

public class V {

	public static void openPlayerInventory(Player player) {
		String s = "\u00A7r"
				+ (String) B.messagesC.get("INVENTORY.PLAYERINVENTORY")
						.toString().replaceAll("'player'", player.getName());
		Inventory inventory = Bukkit.createInventory(null, 45,
				Y.formatMessage(s.substring(0, Math.min(s.length(), 32))));
		inventory.setItem(2, IA.obtainActiveWarnings(player));
		inventory.setItem(4, IA.obtainExpiredWarnings(player));
		inventory.setItem(6, IA.obtainBans(player));
		inventory.setItem(19, I.obtainMWarnWelcomePlayer());
		inventory.setItem(21, IA.obtainPaperDetails(player));
		inventory.setItem(26, I.obtainExit());
		player.openInventory(inventory);
	}

	public static void openAdminInventory(Player player) {
		String s = "\u00A7r"
				+ (String) B.messagesC.get("INVENTORY.ADMININVENTORY")
						.toString().replaceAll("'player'", player.getName());
		Inventory inventory = Bukkit.createInventory(null, 45,
				Y.formatMessage(s.substring(0, Math.min(s.length(), 32))));
		inventory.setItem(2, IA.obtainActiveWarnings(player));
		inventory.setItem(4, IA.obtainExpiredWarnings(player));
		inventory.setItem(6, IA.obtainBans(player));
		inventory.setItem(19, I.obtainMWarnWelcomeAdmin());
		inventory.setItem(21, IA.obtainPaperDetails(player));
		inventory.setItem(25, IB.obtainAccessButton());
		inventory.setItem(26, I.obtainExit());
		player.openInventory(inventory);
	}
}