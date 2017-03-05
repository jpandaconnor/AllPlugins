package co.uk.RandomPanda30.VShop.Methods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import co.uk.RandomPanda30.VShop.Items.I;

public class R {

	public static void openForSaleMenu(Player player, String plot) {
		Inventory inv = Bukkit.createInventory(null, 18, "\u00A7r" + plot);
		inv.setItem(0, I.obtainDetails(plot, U.getPlotPrice(plot)));
		inv.setItem(2, I.obtainBuy());
		inv.setItem(17, I.obtainExit());
		player.openInventory(inv);
	}

	public static void openOwnerMenu(Player player, String plot) {
		Inventory inv = Bukkit.createInventory(null, 18, "\u00A7r" + plot);
		inv.setItem(0, I.obtainDetails(plot, U.getPlotPrice(plot)));
		inv.setItem(2, I.obtainUpdate(plot));
		inv.setItem(15, I.obtainSell());
		inv.setItem(17, I.obtainExit());
		player.openInventory(inv);
	}

	public static void closeInventory(Player player) {
		player.closeInventory();
	}
}