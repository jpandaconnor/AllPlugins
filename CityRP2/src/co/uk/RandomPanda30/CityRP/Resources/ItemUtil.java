package co.uk.RandomPanda30.CityRP.Resources;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtil {

	public static ItemStack createItem(String name, Material material,
			int amount, byte type, ArrayList<String> lores) {
		ItemStack is = new ItemStack(material, amount, type);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(MessageUtil.format(name));
		ArrayList<String> nlores = new ArrayList<>();
		for (String s : lores) {
			nlores.add(MessageUtil.format(s));
		}
		im.setLore(nlores);
		is.setItemMeta(im);
		return is;
	}

	public static boolean isSame(ItemStack item1, ItemStack item2) {
		boolean b = false;
		if (!item1.getItemMeta().hasDisplayName()
				|| !item2.getItemMeta().hasDisplayName()) {
			Bukkit.broadcastMessage("Test1");
			b = false;
		}

		if (item1.getType().equals(item2.getType())) {
			if (item1.getItemMeta().getDisplayName()
					.equals(item2.getItemMeta().getDisplayName())) {
				Bukkit.broadcastMessage("Test2");
				b = true;
			}
		}
		return b;
	}
}