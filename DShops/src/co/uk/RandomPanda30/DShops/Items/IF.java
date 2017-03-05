package co.uk.RandomPanda30.DShops.Items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.DShops.B;
import co.uk.RandomPanda30.DShops.Methods.P;
import co.uk.RandomPanda30.DShops.Methods.Q;

public class IF {

	public static ItemStack obtainPlusButton_DOORPRICE() {
		return Q.createItem((String) B.itemsC.get("ADDBUTTON.NAME"),
				Material.GOLD_NUGGET, 1, 0, new ArrayList<String>(), false);
	}

	public static ItemStack obtainMinusButton_DOORPRICE() {
		return Q.createItem((String) B.itemsC.get("MINUSBUTTON.NAME"),
				Material.GOLD_NUGGET, 1, 0, new ArrayList<String>(), false);
	}
	
	public static ItemStack obtainPlusButton_RENTPRICE() {
		return Q.createItem((String) B.itemsC.get("ADDBUTTON.NAME"),
				Material.GOLD_NUGGET, 1, 0, new ArrayList<String>(), false);
	}

	public static ItemStack obtainMinusButton_RENTPRICE() {
		return Q.createItem((String) B.itemsC.get("MINUSBUTTON.NAME"),
				Material.GOLD_NUGGET, 1, 0, new ArrayList<String>(), false);
	}

	public static ItemStack obtainDoorPriceIndicator(String location) {
		return Q.createItem(
				(String) B.itemsC
						.get("DOORPRICE.NAME")
						.toString()
						.replaceAll("'price'",
								Integer.toString(P.getCustomPrice(location))),
				Material.PAPER, 1, 0, new ArrayList<String>(), false);
	}
}
