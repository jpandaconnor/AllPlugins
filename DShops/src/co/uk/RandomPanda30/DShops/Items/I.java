package co.uk.RandomPanda30.DShops.Items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.DShops.B;
import co.uk.RandomPanda30.DShops.Methods.L;
import co.uk.RandomPanda30.DShops.Methods.Q;

@SuppressWarnings("unchecked")
public class I {

	public static ItemStack obtainExitButton() {
		return Q.createItem((String) B.itemsC.get("EXITBUTTON.NAME"),
				Material.REDSTONE_BLOCK, 1, 0, new ArrayList<String>(), false);
	}

	public static ItemStack obtainBorder() {
		ItemStack is = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(" ");
		is.setItemMeta(im);
		return is;
	}

	public static ItemStack obtainNext() {
		ItemStack is = new ItemStack(Material.EMERALD_BLOCK, 1);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(L.formatMessage((String) B.itemsC
				.get("NEXTBUTTON.NAME")));
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("NEXTBUTTON.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		for (String s : lores) {
			newLores.add(L.formatMessage(s));
		}
		im.setLore(newLores);
		is.setItemMeta(im);
		return is;
	}

	public static ItemStack obtainPrevious() {
		ItemStack is = new ItemStack(Material.REDSTONE_BLOCK, 1);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(L.formatMessage((String) B.itemsC
				.get("PREVIOUSBUTTON.NAME")));
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("PREVIOUSBUTTON.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		for (String s : lores) {
			newLores.add(L.formatMessage(s));
		}
		im.setLore(newLores);
		is.setItemMeta(im);
		return is;
	}

	public static ItemStack obtainDoorSettings() {
		return Q.createItem((String) B.itemsC.get("DOORSETTINGS.NAME"),
				Material.NETHER_STAR, 1, 0, new ArrayList<String>(), false);
	}
}