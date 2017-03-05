package co.uk.RandomPanda30.Guilds.Objects;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.Guilds.Util.Basics.MUtil;

public class GItem {

	public static ItemStack createItem(String name, Material material,
			int amount, int type, ArrayList<String> lores) {
		ItemStack is = new ItemStack(material, amount, (short) type);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(MUtil.format(name));
		ArrayList<String> newLores = new ArrayList<String>();
		for (String s : lores) {
			newLores.add(MUtil.format(s));
		}
		im.setLore(newLores);
		is.setItemMeta(im);
		return is;
	}
}