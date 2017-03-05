package co.uk.RandomPanda30.MWarn.Methods;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class U {

	public static ItemStack createItem(String name, Material material,
			int amount, int type, ArrayList<String> lores, boolean glow) {
		ItemStack is = new ItemStack(material, amount);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(Y.formatMessage(name));
		ArrayList<String> newLores = new ArrayList<String>();
		for (String s : lores) {
			newLores.add(Y.formatMessage(s));
		}
		im.setLore(newLores);
		is.setItemMeta(im);
		if (glow) {
			R.addGlow(is);
		}
		return is;
	}
}