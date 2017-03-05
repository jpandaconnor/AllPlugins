package co.uk.RandomPanda30.ItemHandlerPlus.Items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.ItemHandlerPlus.Methods.V;
import co.uk.RandomPanda30.ItemHandlerPlus.Methods.Y;

public class IA {

	public static ArrayList<ItemStack> obtainHead() {
		ArrayList<ItemStack> heads = new ArrayList<ItemStack>();
		for (int i = 1; i < 10; i++) {
			ItemStack is = new ItemStack(Material.SKULL_ITEM, i);
			ItemMeta im = is.getItemMeta();
			String naam = "%ASlot: "
					+ (V.isEnabled(i) ? "%G" + Integer.toString(i) : "%B"
							+ Integer.toString(i));
			im.setDisplayName(Y.formatMessage(naam));
			is.setItemMeta(im);
			heads.add(is);
		}
		return heads;
	}
}