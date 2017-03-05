package co.uk.RandomPanda30.ItemHandlerPlus.Methods;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class W {

	public static ItemStack createItem(String name, Material material,
			int amount, int type, ArrayList<String> lores) {
		ItemStack is = new ItemStack(material, amount, (short) type);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(Y.formatMessage(name));
		ArrayList<String> newLores = new ArrayList<String>();
		for (String s : lores) {
			newLores.add(Y.formatMessage(s));
		}
		im.setLore(newLores);
		is.setItemMeta(im);
		return is;
	}
}