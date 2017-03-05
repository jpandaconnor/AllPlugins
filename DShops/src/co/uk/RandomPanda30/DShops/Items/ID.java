package co.uk.RandomPanda30.DShops.Items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.DShops.B;
import co.uk.RandomPanda30.DShops.Methods.L;
import co.uk.RandomPanda30.DShops.Methods.Q;

public class ID {
	
	@SuppressWarnings("unchecked")
	public static ItemStack obtainSearchWithLetter() {
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("SINV.SBFL.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		for (String lore : lores) {
			newLores.add(L.formatMessage(lore));
		}
		ItemStack is = Q.createItem((String) B.itemsC.get("SINV.SBFL.NAME"),
				Material.GLASS, 1, 0, newLores, true);
		return is;
	}
}