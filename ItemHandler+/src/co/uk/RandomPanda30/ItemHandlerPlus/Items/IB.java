package co.uk.RandomPanda30.ItemHandlerPlus.Items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.ItemHandlerPlus.B;
import co.uk.RandomPanda30.ItemHandlerPlus.Methods.W;

@SuppressWarnings("unchecked")
public class IB {

	public static ItemStack obtainClock() {
		return W.createItem((String) B.itemsC.get("CLOCKNAME.NAME"),
				Material.WATCH, 1, 0,
				(ArrayList<String>) B.itemsC.get("CLOCKNAME.LORES"));
	}
}