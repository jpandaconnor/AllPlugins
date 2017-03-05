package co.uk.RandomPanda30.KnightCrates.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Keys {

	public static ItemStack getIronKey(int amount) {
		ItemStack is = new ItemStack(Material.NETHER_STAR, amount);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(
				ChatColor.translateAlternateColorCodes('&', "&7&lIron Key"));
		is.setItemMeta(im);
		return is;
	}

	public static ItemStack getGoldKey(int amount) {
		ItemStack is = new ItemStack(Material.NETHER_STAR, amount);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(
				ChatColor.translateAlternateColorCodes('&', "&6&lGold Key"));
		is.setItemMeta(im);
		return is;
	}

	public static ItemStack getDiamondKey(int amount) {
		ItemStack is = new ItemStack(Material.NETHER_STAR, amount);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(
				ChatColor.translateAlternateColorCodes('&', "&B&lDiamond Key"));
		is.setItemMeta(im);
		return is;
	}
}