package co.uk.RandomPanda30.VShop.Items;

import java.util.ArrayList;
import java.util.Calendar;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.VShop.B;
import co.uk.RandomPanda30.VShop.Files.Items;
import co.uk.RandomPanda30.VShop.Methods.X;

public class I {

	@SuppressWarnings("unchecked")
	public static ItemStack obtainWand() {
		ItemStack is = new ItemStack(Material.STICK,
				(Integer) Items.ITEM_WAND_AMOUNT.value);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(X.formatMessage((String) Items.ITEM_WAND_NAME.value));
		ArrayList<String> lores = (ArrayList<String>) Items.ITEM_WAND_LORES.value;
		ArrayList<String> newLores = new ArrayList<String>();
		for (String lore : lores) {
			newLores.add(X.formatMessage(lore));
		}
		im.setLore(newLores);
		is.setItemMeta(im);
		return is;
	}

	@SuppressWarnings("unchecked")
	public static ItemStack obtainDetails(String plotName, int details) {
		ItemStack is = new ItemStack(Material.PAPER, 1);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(X
				.formatMessage((String) Items.ITEM_PLOTDETAILS_NAME.value));
		ArrayList<String> lores = (ArrayList<String>) Items.ITEM_PLOTDETAILS_LORES.value;
		ArrayList<String> newLores = new ArrayList<String>();
		for (String lore : lores) {
			newLores.add(X.formatMessage(lore)
					.replaceAll("'plotname'", plotName)
					.replaceAll("'price'", Integer.toString(details)));
		}
		im.setLore(newLores);
		is.setItemMeta(im);
		return is;
	}

	public static ItemStack obtainBuy() {
		ItemStack is = new ItemStack(Material.EMERALD_BLOCK, 1);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(X
				.formatMessage((String) Items.ITEM_BUYPLOT_NAME.value));
		is.setItemMeta(im);
		return is;
	}

	@SuppressWarnings("unchecked")
	public static ItemStack obtainExit() {
		ItemStack is = new ItemStack(Material.REDSTONE_BLOCK, 1);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(X
				.formatMessage((String) Items.ITEM_EXITBLOCK_NAME.value));
		ArrayList<String> lores = (ArrayList<String>) Items.ITEM_EXITBLOCK_LORES.value;
		ArrayList<String> newLores = new ArrayList<String>();
		for (String lore : lores) {
			newLores.add(X.formatMessage(lore));
		}
		im.setLore(newLores);
		is.setItemMeta(im);
		return is;
	}

	@SuppressWarnings("unchecked")
	public static ItemStack obtainSell() {
		ItemStack is = new ItemStack(Material.REDSTONE, 1);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(X
				.formatMessage((String) Items.ITEM_SELLPLOT_NAME.value));
		ArrayList<String> lores = (ArrayList<String>) Items.ITEM_SELLPLOT_LORES.value;
		ArrayList<String> newLores = new ArrayList<String>();
		for (String lore : lores) {
			newLores.add(X.formatMessage(lore));
		}
		im.setLore(newLores);
		is.setItemMeta(im);
		return is;
	}

	@SuppressWarnings("unchecked")
	public static ItemStack obtainUpdate(String plotName) {
		ItemStack is = new ItemStack(Material.REDSTONE_TORCH_ON, 1);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(X
				.formatMessage((String) Items.ITEM_UPDATETIME_NAME.value));
		ArrayList<String> lores = (ArrayList<String>) Items.ITEM_UPDATETIME_LORES.value;
		ArrayList<String> newLores = new ArrayList<String>();

		String finalTime = "";
		String time = B.shopsC.getString(plotName + ".owner.time");

		long cTime = Calendar.getInstance().getTimeInMillis();
		long finTime = Long.parseLong(time) - cTime;

		long days = 0;
		long hours = 0;
		long minutes = 0;
		long seconds = 0;

		days = finTime / 1000 / 60 / 60 / 24;
		hours = (finTime / 1000 / 60 / 60) - (days * 24);
		minutes = (finTime / 1000 / 60) - (hours * 60) - (days * 24 * 60);
		seconds = (finTime / 1000) - (minutes * 60) - (hours * 60 * 60)
				- (days * 24 * 60 * 60);

		if (hours != 0) {
			finalTime += "%A" + hours + "%Nh";
		}

		if (minutes != 0) {
			finalTime += " %A" + minutes + "%Nm";
		}

		if (seconds != 0) {
			finalTime += " %A" + seconds + "%Ns";
		}

		for (String lore : lores) {
			newLores.add(X.formatMessage((lore).replaceAll("'tijd'",
					finalTime.replaceAll("-", ""))));
		}
		im.setLore(newLores);
		is.setItemMeta(im);
		return is;
	}
}