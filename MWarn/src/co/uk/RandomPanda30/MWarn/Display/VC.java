package co.uk.RandomPanda30.MWarn.Display;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.MWarn.B;
import co.uk.RandomPanda30.MWarn.Items.I;
import co.uk.RandomPanda30.MWarn.Items.IC;
import co.uk.RandomPanda30.MWarn.Methods.U;
import co.uk.RandomPanda30.MWarn.Methods.W;

public class VC {

	public static ArrayList<ItemStack> heads = new ArrayList<ItemStack>();

	public static boolean next = true;
	public static int page = 1;
	public static int epp = 35;
	public static int si = (page - 1) * epp;
	public static int ei = si + epp;

	@SuppressWarnings("deprecation")
	public static void openSearchInventory(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 54, "\u00A7r "
				+ "Search for player (" + page + ")");
		resetInventory();
		for (Player player2 : Bukkit.getOnlinePlayers()) {
			ArrayList<String> lores = new ArrayList<String>();
			lores.add((String) B.itemsC
					.get("SINV.AW")
					.toString()
					.replaceAll("'no'",
							Integer.toString(W.getActiveWarnings(player2))));
			lores.add((String) B.itemsC
					.get("SINV.EW")
					.toString()
					.replaceAll("'no'",
							Integer.toString(W.getExpiredWarnings(player2))));
			ItemStack is = U.createItem(player2.getName(), Material.SKULL_ITEM,
					1, 0, lores, false);
			if (!heads.contains(is)) {
				heads.add(is);
			}
		}

		inventory.setItem(36, I.obtainBorder());
		inventory.setItem(37, I.obtainBorder());
		inventory.setItem(38, I.obtainBorder());
		inventory.setItem(39, I.obtainBorder());
		inventory.setItem(40, I.obtainBorder());
		inventory.setItem(41, I.obtainBorder());
		inventory.setItem(42, I.obtainBorder());
		inventory.setItem(43, I.obtainBorder());
		inventory.setItem(44, I.obtainBorder());

		inventory.setItem(49, IC.obtainSearchWithLetter());

		si = (page - 1) * epp;
		ei = si + epp;
		if (ei > heads.size()) {
			ei = heads.size();
			next = false;
		}

		for (ItemStack is : heads.subList(si, ei)) {
			inventory.addItem(is);
		}

		if (next) {
			inventory.setItem(53, I.obtainNext());
		}

		if (page != 1) {
			inventory.setItem(45, I.obtainPrevious());
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
				+ "Search for player (" + page + ")");
		inventory.setItem(36, I.obtainBorder());
		inventory.setItem(37, I.obtainBorder());
		inventory.setItem(38, I.obtainBorder());
		inventory.setItem(39, I.obtainBorder());
		inventory.setItem(40, I.obtainBorder());
		inventory.setItem(41, I.obtainBorder());
		inventory.setItem(42, I.obtainBorder());
		inventory.setItem(43, I.obtainBorder());
		inventory.setItem(44, I.obtainBorder());

		inventory.setItem(49, IC.obtainSearchWithLetter());

		for (ItemStack is : heads.subList(si, ei)) {
			inventory.addItem(is);
		}

		if (next) {
			inventory.setItem(53, I.obtainNext());
		}

		if (page != 1) {
			inventory.setItem(45, I.obtainPrevious());
		}
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
				+ "Search for player (" + page + ")");
		inventory.setItem(36, I.obtainBorder());
		inventory.setItem(37, I.obtainBorder());
		inventory.setItem(38, I.obtainBorder());
		inventory.setItem(39, I.obtainBorder());
		inventory.setItem(40, I.obtainBorder());
		inventory.setItem(41, I.obtainBorder());
		inventory.setItem(42, I.obtainBorder());
		inventory.setItem(43, I.obtainBorder());
		inventory.setItem(44, I.obtainBorder());

		inventory.setItem(49, IC.obtainSearchWithLetter());

		for (ItemStack is : heads.subList(si, ei)) {
			inventory.addItem(is);
		}

		if (next) {
			inventory.setItem(53, I.obtainNext());
		}

		if (page != 1) {
			inventory.setItem(45, I.obtainPrevious());
		}
	}

	private static void resetInventory() {
		next = true;
		page = 1;
		heads.clear();
	}
}
