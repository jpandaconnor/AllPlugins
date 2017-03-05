package co.uk.RandomPanda30.MWarn.Items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.MWarn.B;
import co.uk.RandomPanda30.MWarn.Methods.U;
import co.uk.RandomPanda30.MWarn.Methods.Y;

@SuppressWarnings("unchecked")
public class ID {

	public static ItemStack obtainMWarn() {
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("ONOFFINV.MWARNONOFF.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		boolean set = (Boolean) B.configC.get("PLUGIN.ENABLE");
		String setS = "";
		setS = set ? "%GOn" : "%BOff";
		for (String s : lores) {
			newLores.add(Y.formatMessage(s.replaceAll("'set'", setS)));
		}
		ItemStack is = U.createItem(
				(String) B.itemsC.get("ONOFFINV.MWARNONOFF.NAME"),
				Material.STONE_BUTTON, 1, 0, newLores, true);
		B.ei.add(is);
		return is;
	}

	public static ItemStack obtainCMDWARN() {
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("ONOFFINV.CMDWARN.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		boolean set = (Boolean) B.configC.get("CMD.WARN");
		String setS = "";
		setS = set ? "%GOn" : "%BOff";
		for (String s : lores) {
			newLores.add(Y.formatMessage(s.replaceAll("'set'", setS)));
		}
		ItemStack is = U.createItem(
				(String) B.itemsC.get("ONOFFINV.CMDWARN.NAME"),
				Material.STONE_BUTTON, 1, 0, newLores, true);
		B.ei.add(is);
		return is;
	}

	public static ItemStack obtainCMDCLEAR() {
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("ONOFFINV.CMDCLEAR.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		boolean set = (Boolean) B.configC.get("CMD.CLEAR");
		String setS = "";
		setS = set ? "%GOn" : "%BOff";
		for (String s : lores) {
			newLores.add(Y.formatMessage(s.replaceAll("'set'", setS)));
		}
		ItemStack is = U.createItem(
				(String) B.itemsC.get("ONOFFINV.CMDCLEAR.NAME"),
				Material.STONE_BUTTON, 1, 0, newLores, true);
		B.ei.add(is);
		return is;
	}

	public static ItemStack obtainCMDREMOVE() {
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("ONOFFINV.CMDREMOVE.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		boolean set = (Boolean) B.configC.get("CMD.REMOVE");
		String setS = "";
		setS = set ? "%GOn" : "%BOff";
		for (String s : lores) {
			newLores.add(Y.formatMessage(s.replaceAll("'set'", setS)));
		}
		ItemStack is = U.createItem(
				(String) B.itemsC.get("ONOFFINV.CMDREMOVE.NAME"),
				Material.STONE_BUTTON, 1, 0, newLores, true);
		B.ei.add(is);
		return is;
	}

	public static ItemStack obtainBroadcastWarning() {
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("ONOFFINV.BROADCASTWARNINGS.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		boolean set = (Boolean) B.configC.get("BROADCAST.WARNING");
		String setS = "";
		setS = set ? "%GOn" : "%BOff";
		for (String s : lores) {
			newLores.add(Y.formatMessage(s.replaceAll("'set'", setS)));
		}
		ItemStack is = U.createItem(
				(String) B.itemsC.get("ONOFFINV.BROADCASTWARNINGS.NAME"),
				Material.STONE_BUTTON, 1, 0, newLores, true);
		B.ei.add(is);
		return is;
	}

	public static ItemStack obtainBroadcastBan() {
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("ONOFFINV.BROADCASTBAN.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		boolean set = (Boolean) B.configC.get("BROADCAST.BAN");
		String setS = "";
		setS = set ? "%GOn" : "%BOff";
		for (String s : lores) {
			newLores.add(Y.formatMessage(s.replaceAll("'set'", setS)));
		}
		ItemStack is = U.createItem(
				(String) B.itemsC.get("ONOFFINV.BROADCASTBAN.NAME"),
				Material.STONE_BUTTON, 1, 0, newLores, true);
		B.ei.add(is);
		return is;
	}

	public static ItemStack obtainBroadcastUnban() {
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("ONOFFINV.BROADCASTUNBAN.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		boolean set = (Boolean) B.configC.get("BROADCAST.UNBAN");
		String setS = "";
		setS = set ? "%GOn" : "%BOff";
		for (String s : lores) {
			newLores.add(Y.formatMessage(s.replaceAll("'set'", setS)));
		}
		ItemStack is = U.createItem(
				(String) B.itemsC.get("ONOFFINV.BROADCASTUNBAN.NAME"),
				Material.STONE_BUTTON, 1, 0, newLores, true);
		B.ei.add(is);
		return is;
	}

	public static ItemStack obtainBroadcastClearWarnings() {
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("ONOFFINV.BROADCASTCLEARWARNINGS.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		boolean set = (Boolean) B.configC.get("BROADCAST.CLEARWARNINGS");
		String setS = "";
		setS = set ? "%GOn" : "%BOff";
		for (String s : lores) {
			newLores.add(Y.formatMessage(s.replaceAll("'set'", setS)));
		}
		ItemStack is = U.createItem(
				(String) B.itemsC.get("ONOFFINV.BROADCASTCLEARWARNINGS.NAME"),
				Material.STONE_BUTTON, 1, 0, newLores, true);
		B.ei.add(is);
		return is;
	}

	public static ItemStack obtainBroadcastRemoveWarning() {
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("ONOFFINV.BROADCASTREMOVEWARNING.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		boolean set = (Boolean) B.configC.get("BROADCAST.REMOVEWARNING");
		String setS = "";
		setS = set ? "%GOn" : "%BOff";
		for (String s : lores) {
			newLores.add(Y.formatMessage(s.replaceAll("'set'", setS)));
		}
		ItemStack is = U.createItem(
				(String) B.itemsC.get("ONOFFINV.BROADCASTREMOVEWARNING.NAME"),
				Material.STONE_BUTTON, 1, 0, newLores, true);
		B.ei.add(is);
		return is;
	}
}