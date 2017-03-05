package co.uk.RandomPanda30.DailyRewards.Utils;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtil {

	public static ItemStack createItem(String name, Material material,
			int amount, byte type, ArrayList<String> lores) {
		ItemStack is = new ItemStack(material, amount);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(name);
		im.setLore(lores);
		is.setItemMeta(im);
		return is;
	}
}