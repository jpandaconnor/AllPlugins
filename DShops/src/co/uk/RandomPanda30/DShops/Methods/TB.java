package co.uk.RandomPanda30.DShops.Methods;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.DShops.Items.I;

public class TB {

	public static ArrayList<ItemStack> heads = new ArrayList<ItemStack>();

	public static boolean next = true;
	public static int page = 1;
	public static int epp = 35;
	public static int si = (page - 1) * epp;
	public static int ei = si + epp;

	public static void openLetterSearch(Player player, String letter) {
		Inventory inventory = Bukkit.createInventory(null, 54,
				"Search by letter (" + page + ")");
		resetInventory();
		for (Player player2 : Bukkit.getOnlinePlayers()) {
			if (player2.getName().startsWith(letter)) {
				ItemStack is = new ItemStack(Material.SKULL_ITEM, 1);
				ItemMeta im = is.getItemMeta();
				im.setDisplayName(player2.getName());
				is.setItemMeta(im);
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

		si = (page - 1) * epp;
		ei = si + epp;
		if (ei > heads.size()) {
			ei = heads.size();
			next = false;
		}

		for (ItemStack is : heads) {
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
