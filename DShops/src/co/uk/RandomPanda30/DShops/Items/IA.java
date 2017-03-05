package co.uk.RandomPanda30.DShops.Items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.DShops.B;
import co.uk.RandomPanda30.DShops.Methods.L;
import co.uk.RandomPanda30.DShops.Methods.P;
import co.uk.RandomPanda30.DShops.Methods.Q;

@SuppressWarnings("unchecked")
public class IA {

	public static ItemStack obtainPaperDetails() {
		return Q.createItem(
				(String) B.itemsC.get("FORSALEINV.PAPERDETAILS.NAME"),
				Material.PAPER, 1, 0, new ArrayList<String>(), false);
	}

	public static ItemStack obtainForSaleButton(String location) {
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("FORSALEINV.FORSALE.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		for (String s : lores) {
			newLores.add(L.formatMessage(s.replaceAll("'price'", Integer
					.toString((P.isCustomPrice(location) ? P
							.getCustomPrice(location) : P.getDoorPrice())))));
		}
		return Q.createItem((String) B.itemsC.get("FORSALEINV.FORSALE.NAME"),
				Material.EMERALD_BLOCK, 1, 0, newLores, false);
	}
}