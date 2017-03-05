package co.uk.RandomPanda30.Murge.Items.Inventories.Subinventories;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.Murge.Methods.ItemMethods;

public class KitsOptionsItems {

	public static ItemStack getStarterKit() {
		return ItemMethods.createItem("%NStarter kit", Material.PAPER, 1, 0,
				new ArrayList<String>());
	}

	public static ItemStack obtainBorder() {
		ItemStack is = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(" ");
		is.setItemMeta(im);
		return is;
	}

	public static ItemStack obtainAdd() {
		return ItemMethods.createItem("%GAdd kit", Material.WOOL, 1, 5,
				new ArrayList<String>());
	}

	public static ItemStack obtainEdit() {
		return ItemMethods.createItem("&3Edit kit", Material.WOOL, 1, 9,
				new ArrayList<String>());
	}

	public static ItemStack obtainRemove() {
		return ItemMethods.createItem("%BRemove kit", Material.WOOL, 1, 14,
				new ArrayList<String>());
	}

	public static ItemStack obtainNextPage() {
		return ItemMethods.createItem("%ANext page", Material.TRIPWIRE_HOOK, 1,
				0, new ArrayList<String>());
	}

	public static ItemStack obtainLastPage() {
		return ItemMethods.createItem("%ALast page", Material.TRIPWIRE_HOOK, 1,
				0, new ArrayList<String>());
	}
}