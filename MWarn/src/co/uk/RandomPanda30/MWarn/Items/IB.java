package co.uk.RandomPanda30.MWarn.Items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.MWarn.B;
import co.uk.RandomPanda30.MWarn.Methods.U;
import co.uk.RandomPanda30.MWarn.Methods.Y;

@SuppressWarnings("unchecked")
public class IB {

	public static ItemStack obtainAccessButton() {
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("ADMININV.ACCESSBUTTON.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		for (String s : lores) {
			newLores.add(Y.formatMessage(s));
		}
		ItemStack is = U.createItem(
				(String) B.itemsC.get("ADMININV.ACCESSBUTTON.NAME"),
				Material.NETHER_STAR, 1, 0, newLores, false);
		return is;
	}

	public static ItemStack obtainPaperDetails(Player player) {
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("ADMININV.ADMINDETAILS.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		for (String s : lores) {
			newLores.add(Y.formatMessage(s));
		}
		ItemStack is = U.createItem((String) B.itemsC.get("PLAYERINV.NAME")
				.toString().replaceAll("'playername'", player.getName()),
				Material.PAPER, 1, 0, newLores, false);
		return is;
	}

	public static ItemStack obtainAddWarning() {
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("ADMININV.ADDWARNING.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		for (String s : lores) {
			newLores.add(Y.formatMessage(s));
		}
		ItemStack is = U.createItem(
				(String) B.itemsC.get("ADMININV.ADDWARNING.NAME"),
				Material.STONE_BUTTON, 1, 0, newLores, true);
		B.ei.add(is);
		return is;
	}

	public static ItemStack obtainRemoveWarning() {
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("ADMININV.REMOVEWARNING.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		for (String s : lores) {
			newLores.add(Y.formatMessage(s));
		}
		ItemStack is = U.createItem(
				(String) B.itemsC.get("ADMININV.REMOVEWARNING.NAME"),
				Material.STONE_BUTTON, 1, 0, newLores, true);
		B.ei.add(is);
		return is;
	}

	public static ItemStack obtainClearWarnings() {
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("ADMININV.CLEARWARNINGS.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		for (String s : lores) {
			newLores.add(Y.formatMessage(s));
		}
		ItemStack is = U.createItem(
				(String) B.itemsC.get("ADMININV.CLEARWARNINGS.NAME"),
				Material.STONE_BUTTON, 1, 0, newLores, true);
		B.ei.add(is);
		return is;
	}

	public static ItemStack obtainInitConfigs() {
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("ADMININV.INITCONFIGS.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		for (String s : lores) {
			newLores.add(Y.formatMessage(s));
		}
		ItemStack is = U.createItem(
				(String) B.itemsC.get("ADMININV.INITCONFIGS.NAME"),
				Material.STONE_BUTTON, 1, 0, newLores, true);
		B.ei.add(is);
		return is;
	}

	public static ItemStack obtainMainButton() {
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("ADMININV.MAINBUTTON.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		for (String s : lores) {
			newLores.add(Y.formatMessage(s));
		}
		ItemStack is = U.createItem(
				(String) B.itemsC.get("ADMININV.MAINBUTTON.NAME"),
				Material.NETHER_STAR, 1, 0, newLores, false);
		return is;
	}

	public static ItemStack obtainBanButton() {
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("ADMININV.BAN.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		for (String s : lores) {
			newLores.add(Y.formatMessage(s));
		}
		ItemStack is = U.createItem((String) B.itemsC.get("ADMININV.BAN.NAME"),
				Material.STONE_BUTTON, 1, 0, newLores, true);
		B.ei.add(is);
		return is;
	}

	public static ItemStack obtainUnbanButton() {
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("ADMININV.UNBAN.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		for (String s : lores) {
			newLores.add(Y.formatMessage(s));
		}
		ItemStack is = U.createItem(
				(String) B.itemsC.get("ADMININV.UNBAN.NAME"),
				Material.STONE_BUTTON, 1, 0, newLores, true);
		B.ei.add(is);
		return is;
	}

	public static ItemStack obtainOffOnButton() {
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("ADMININV.OFFONOPTIONS.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		for (String s : lores) {
			newLores.add(Y.formatMessage(s));
		}
		ItemStack is = U.createItem(
				(String) B.itemsC.get("ADMININV.OFFONOPTIONS.NAME"),
				Material.STONE_BUTTON, 1, 0, newLores, true);
		B.ei.add(is);
		return is;
	}

	public static ItemStack obtainConfigValues() {
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("ADMININV.CONFIGVALUES.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		for (String s : lores) {
			newLores.add(Y.formatMessage(s));
		}
		ItemStack is = U.createItem(
				(String) B.itemsC.get("ADMININV.CONFIGVALUES.NAME"),
				Material.STONE_BUTTON, 1, 0, newLores, true);
		B.ei.add(is);
		return is;
	}
}