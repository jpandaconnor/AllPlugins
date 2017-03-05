package co.uk.RandomPanda30.CasinoM.Items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.CasinoM.CasinoData;
import co.uk.RandomPanda30.CasinoM.Methods.ItemMethods;

@SuppressWarnings("unchecked")
public class BaseItems {

	public static ItemStack obtainWand() {
		return ItemMethods.createItem((String) CasinoData.itemsC
				.get("SELECTIONWAND.NAME"), Material.STICK, 1, 0,
				(ArrayList<String>) CasinoData.itemsC
						.get("SELECTIONWAND.LORES"));
	}
}