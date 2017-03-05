package co.uk.RandomPanda30.MWarn.Methods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import co.uk.RandomPanda30.MWarn.B;
import co.uk.RandomPanda30.MWarn.Items.I;
import co.uk.RandomPanda30.MWarn.Items.IA;
import co.uk.RandomPanda30.MWarn.Items.IB;

public class V {

	public static void openPlayerInventory(Player player) {
		Inventory inventory = Bukkit.createInventory(
				null,
				45,
				Y.formatMessage("\u00A7r"
						+ (String) B.messagesC.get("INVENTORY.PLAYERINVENTORY")
								.toString()
								.replaceAll("'player'", player.getName())));
		inventory.setItem(0, IA.obtainPaperDetails(player));
		inventory.setItem(2, IA.obtainActiveWarnings(player));
		inventory.setItem(4, IA.obtainExpiredWarnings(player));
		inventory.setItem(26, I.obtainExit());
		player.openInventory(inventory);
	}

	public static void openAdminInventory(Player player) {
		Inventory inventory = Bukkit.createInventory(
				null,
				45,
				Y.formatMessage("\u00A7r"
						+ (String) B.messagesC.get("INVENTORY.ADMININVENTORY")
								.toString()
								.replaceAll("'player'", player.getName())));
		inventory.setItem(0, IA.obtainPaperDetails(player));
		inventory.setItem(2, IA.obtainActiveWarnings(player));
		inventory.setItem(4, IA.obtainExpiredWarnings(player));
		inventory.setItem(25, IB.obtainAccessButton());
		inventory.setItem(26, I.obtainExit());
		player.openInventory(inventory);
	}
}