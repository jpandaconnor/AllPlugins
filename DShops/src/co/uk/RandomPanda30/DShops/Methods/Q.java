package co.uk.RandomPanda30.DShops.Methods;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Q {

	public static ItemStack createItem(String name, Material material,
			int amount, int type, ArrayList<String> lores, boolean glow) {
		ItemStack is = new ItemStack(material, amount, (short) type);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(L.formatMessage(name));
		ArrayList<String> newLores = new ArrayList<String>();
		for (String s : lores) {
			newLores.add(L.formatMessage(s));
		}
		im.setLore(newLores);
		is.setItemMeta(im);
		return is;
	}
}