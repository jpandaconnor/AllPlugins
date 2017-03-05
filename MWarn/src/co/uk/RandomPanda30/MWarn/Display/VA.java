package co.uk.RandomPanda30.MWarn.Display;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.MWarn.Items.I;
import co.uk.RandomPanda30.MWarn.Methods.W;

public class VA {

	public static ArrayList<ItemStack> heads = new ArrayList<ItemStack>();

	public static boolean next = true;
	public static int page = 1;
	public static int epp = 52;
	public static int si = (page - 1) * epp;
	public static int ei = si + epp;
wa
		if (ei > heads.size()) {
			ei = heads.size();
			next = false;
		}
		Inventory inventory = Bukkit.createInventory(null, 54, "\u00A7r "
				+ "Active warnings (" + page + ")");
		for (ItemStack is : heads.subList(si, ei)) {
			inventory.addItem(is);
		}

		if (next) {
			inventory.setItem(53, I.obtainNext());
		}

		if (page != 1) {
			inventory.setItem(52, I.obtainPrevious());
		}

		player.openInventory(inventory);
	}

	public static void nextPage(Player player) {
		next = true;
		page = page + 1;
		si = (page - 1) * epp;
		ei = si + epp;
		if (ei > heads.size()) {
			ei = heads.size();
			next = false;
		}
		Inventory inventory = Bukkit.createInventory(null, 54, "\u00A7r "
				+ "Page: " + page);
		for (ItemStack is : heads.subList(si, ei)) {
			inventory.addItem(is);
		}

		if (next) {
			inventory.setItem(53, I.obtainNext());
		}

		if (page != 1) {
			inventory.setItem(52, I.obtainPrevious());
		}
		player.openInventory(inventory);
	}

	public static void previousPage(Player player) {
		next = true;
		page = page - 1;
		si = (page - 1) * epp;
		ei = si + epp;
		if (ei > heads.size()) {
			ei = heads.size();
			next = false;
		}
		Inventory inventory = Bukkit.createInventory(null, 54, "\u00A7r "
				+ "Page: " + page);
		for (ItemStack is : heads.subList(si, ei)) {
			inventory.addItem(is);
		}

		if (next) {
			inventory.setItem(53, I.obtainNext());
		}

		if (page != 1) {
			inventory.setItem(52, I.obtainPrevious());
		}
		player.openInventory(inventory);
	}

	private static void resetInventory() {
		next = true;
		page = 1;
	}
}