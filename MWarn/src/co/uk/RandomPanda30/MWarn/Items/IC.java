package co.uk.RandomPanda30.MWarn.Items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.MWarn.B;
import co.uk.RandomPanda30.MWarn.Methods.U;
import co.uk.RandomPanda30.MWarn.Methods.Y;

@SuppressWarnings("unchecked")
public class IC {

	public static ItemStack obtainSearchWithLetter() {
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("SINV.SBFL.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		for (String lore : lores) {
			newLores.add(Y.formatMessage(lore));
		}
		ItemStack is = U.createItem((String) B.itemsC.get("SINV.SBFL.NAME"),
				Material.GLASS, 1, 0, newLores, true);
		B.ei.add(is);
		return is;
	}
}