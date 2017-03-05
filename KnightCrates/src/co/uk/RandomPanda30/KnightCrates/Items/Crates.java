package co.uk.RandomPanda30.KnightCrates.Items;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Crates {

	public static ItemStack getCrate() {
		ItemStack is = new ItemStack(Material.CHEST, 1);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(
				ChatColor.translateAlternateColorCodes('&', "&A&lCrate"));
		ArrayList<String> lores = new ArrayList<>();
		lores.add(ChatColor.translateAlternateColorCodes('&',
				"&7Place to set a crate point"));
		lores.add(ChatColor.translateAlternateColorCodes('&',
				"&7Delete to remove the crate point"));
		im.setLore(lores);
		is.setItemMeta(im);
		return is;
	}
}