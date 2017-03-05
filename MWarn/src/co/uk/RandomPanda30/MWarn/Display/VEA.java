package co.uk.RandomPanda30.MWarn.Display;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class VEA {

	public static ArrayList<ItemStack> heads = new ArrayList<ItemStack>();

	public static void openPreSearch(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 27, "\u00A7r "
				+ "Choose a letter");
		resetInventory();
		for (char alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
			ItemStack is = new ItemStack(Material.SKULL_ITEM, 1);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName(Character.toString(alphabet));
			is.setItemMeta(im);
			heads.add(is);
		}

		for (ItemStack is : heads) {
			inventory.addItem(is);
		}

		player.openInventory(inventory);
	}

	private static void resetInventory() {
		heads.clear();
	}
}