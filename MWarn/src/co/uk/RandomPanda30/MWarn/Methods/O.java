package co.uk.RandomPanda30.MWarn.Methods;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.MWarn.B;
import co.uk.RandomPanda30.MWarn.Items.I;
import co.uk.RandomPanda30.MWarn.Items.IC;

public class O {

	public static ArrayList<ItemStack> heads = new ArrayList<ItemStack>();

	public static boolean next = true;
	public static int page = 1;
	public static int epp = 35;
	public static int si = (page - 1) * epp;
	public static int ei = si + epp;

	public static void openUnbanMenu(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 54, "\u00A7r "
				+ "Banned players (" + page + ")");
		resetInventory();

		for (String s : B.bansC.getConfigurationSection("").getKeys(false)) {
			ItemStack is = new ItemStack(Material.SKULL_ITEM, 1);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName(Y.formatMessage((String) B.itemsC
					.get("AWARNINGSINV.NAME").toString().replaceAll("'no'", s)));
			ArrayList<String> lores = new ArrayList<String>();

			lores.add(Y.formatMessage((String) B.itemsC
					.get("AWARNINGSINV.LASTNAME").toString()
					.replaceAll("'name'", B.bansC.getString(s + ".lastname"))));
			lores.add(Y.formatMessage((String) B.itemsC
					.get("AWARNINGSINV.DATE").toString()
					.replaceAll("'date'", B.bansC.getString(s + ".date"))));
			im.setLore(lores);
			is.setItemMeta(im);
			heads.add(is);
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
				+ "Banned players (" + page + ")");
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
				+ "Banned players (" + page + ")");
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

		player.openInventory(inventory);
	}

	private static void resetInventory() {
		next = true;
		page = 1;
		heads.clear();
	}
}