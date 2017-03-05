package co.uk.RandomPanda30.MWarn.Items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.MWarn.B;
import co.uk.RandomPanda30.MWarn.Methods.U;
import co.uk.RandomPanda30.MWarn.Methods.Y;

@SuppressWarnings("unchecked")
public class IE {

	public static ItemStack obtainWarningThreshold_MBUTTON() {
		ItemStack is = U.createItem("%NWarning threshold %A-1",
				Material.STONE_BUTTON, 1, 0, new ArrayList<String>(), true);
		B.ei.add(is);
		return is;
	}

	public static ItemStack obtainWarningThreshold_PBUTTON() {
		ItemStack is = U.createItem("%NWarning threshold %A+1",
				Material.STONE_BUTTON, 1, 0, new ArrayList<String>(), true);
		B.ei.add(is);
		return is;
	}

	public static ItemStack obtainBanLength_MBUTTON() {
		ItemStack is = U.createItem("%NBan Length %A-1", Material.STONE_BUTTON,
				1, 0, new ArrayList<String>(), true);
		B.ei.add(is);
		return is;
	}

	public static ItemStack obtainBanLength_PBUTTON() {
		ItemStack is = U.createItem("%NBan Length %A+1", Material.STONE_BUTTON,
				1, 0, new ArrayList<String>(), true);
		B.ei.add(is);
		return is;
	}

	public static ItemStack obtainWarningThreshold() {
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("CONFIGVALUE.THRESHOLD.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		int setS = (Integer) B.configC.get("WARNINGS.THRESHOLD");
		for (String s : lores) {
			newLores.add(Y.formatMessage(s.replaceAll("'set'",
					Integer.toString(setS))));
		}
		ItemStack is = U.createItem(
				(String) B.itemsC.get("CONFIGVALUE.THRESHOLD.NAME"),
				Material.PAPER, setS, 0, newLores, false);
		return is;
	}

	public static ItemStack obtainBanLength() {
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("CONFIGVALUE.BANLENGTH.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		int setS = (Integer) B.configC.get("WARNINGS.BANLENGTH");
		for (String s : lores) {
			newLores.add(Y.formatMessage(s.replaceAll("'set'",
					Integer.toString(setS))));
		}
		ItemStack is = U.createItem(
				(String) B.itemsC.get("CONFIGVALUE.BANLENGTH.NAME"),
				Material.PAPER, setS, 0, newLores, false);
		return is;
	}
}