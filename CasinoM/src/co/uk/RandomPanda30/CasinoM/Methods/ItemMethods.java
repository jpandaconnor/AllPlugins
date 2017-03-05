package co.uk.RandomPanda30.CasinoM.Methods;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemMethods {

	public static ItemStack createItem(String name, Material material,
			int amount, int type, ArrayList<String> lores) {
		ItemStack is = new ItemStack(material, amount, (short) type);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(StringMethods.formatMessage(name));
		ArrayList<String> newLores = new ArrayList<String>();
		for (String s : lores) {
			newLores.add(StringMethods.formatMessage(s));
		}
		im.setLore(newLores);
		is.setItemMeta(im);
		return is;
	}

	public static void giveItem(Player player, ItemStack is) {
		player.getInventory().addItem(is);
	}
}