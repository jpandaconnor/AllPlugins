package co.uk.RandomPanda30.MWarn.Items;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.MWarn.B;
import co.uk.RandomPanda30.MWarn.Methods.Y;

@SuppressWarnings("unchecked")
public class I {

	public static ItemStack obtainExit() {
		ItemStack is = new ItemStack(Material.REDSTONE_BLOCK, 1);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(Y.formatMessage((String) B.itemsC
				.get("EXITBUTTON.NAME")));
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("EXITBUTTON.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		for (String s : lores) {
			newLores.add(Y.formatMessage(s));
		}
		im.setLore(newLores);
		is.setItemMeta(im);
		return is;
	}

	public static ItemStack obtainNext() {
		ItemStack is = new ItemStack(Material.EMERALD_BLOCK, 1);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(Y.formatMessage((String) B.itemsC
				.get("NEXTBUTTON.NAME")));
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("NEXTBUTTON.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		for (String s : lores) {
			newLores.add(Y.formatMessage(s));
		}
		im.setLore(newLores);
		is.setItemMeta(im);
		return is;
	}

	public static ItemStack obtainPrevious() {
		ItemStack is = new ItemStack(Material.REDSTONE_BLOCK, 1);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(Y.formatMessage((String) B.itemsC
				.get("PREVIOUSBUTTON.NAME")));
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("PREVIOUSBUTTON.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		for (String s : lores) {
			newLores.add(Y.formatMessage(s));
		}
		im.setLore(newLores);
		is.setItemMeta(im);
		return is;
	}

	public static ItemStack obtainBorder() {
		ItemStack is = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(" ");
		is.setItemMeta(im);
		return is;
	}

	public static ItemStack obtainMWarnWelcomePlayer() {
		ItemStack is = new ItemStack(Material.PAPER, 1);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(Y.formatMessage((String) B.itemsC
				.get("MWARN.WELCOMEMESSAGES.PLAYER.NAME")));
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("MWARN.WELCOMEMESSAGES.PLAYER.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		for (String s : lores) {
			newLores.add(Y.formatMessage(s));
		}
		im.setLore(newLores);
		is.setItemMeta(im);
		return is;
	}
	
	public static ItemStack obtainMWarnWelcomeAdmin() {
		ItemStack is = new ItemStack(Material.PAPER, 1);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(Y.formatMessage((String) B.itemsC
				.get("MWARN.WELCOMEMESSAGES.ADMIN.NAME")));
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("MWARN.WELCOMEMESSAGES.ADMIN.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		for (String s : lores) {
			newLores.add(Y.formatMessage(s));
		}
		im.setLore(newLores);
		is.setItemMeta(im);
		return is;
	}
}
