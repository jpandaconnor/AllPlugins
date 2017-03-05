package co.uk.RandomPanda30.ItemHandlerPlus.Items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.ItemHandlerPlus.B;
import co.uk.RandomPanda30.ItemHandlerPlus.Methods.W;

@SuppressWarnings("unchecked")
public class I {

	public static ItemStack obtainExitButton() {
		return W.createItem((String) B.itemsC.get("EXITBUTTON.NAME"),
				Material.REDSTONE_BLOCK, 1, 0, new ArrayList<String>());
	}

	public static ItemStack obtainBackButton() {
		return W.createItem((String) B.itemsC.get("BACKBUTTON.NAME"),
				Material.ARROW, 1, 0, new ArrayList<String>());
	}

	public static ItemStack obtainPaperDetails() {
		return W.createItem((String) B.itemsC.get("PAPERDETAILS.NAME"),
				Material.PAPER, 1, 0,
				(ArrayList<String>) B.itemsC.get("PAPERDETAILS.LORES"));
	}

	public static ItemStack obtainHotbarRestriction() {
		return W.createItem((String) B.itemsC.get("HOTBARRESTRICTION.NAME"),
				Material.REDSTONE, 1, 0,
				(ArrayList<String>) B.itemsC.get("HOTBARRESTRICTION.LORES"));
	}

	public static ItemStack obtainItemDropRestrction() {
		return W.createItem((String) B.itemsC.get("ITEMDROPRESTRICTION.NAME"),
				Material.REDSTONE, 1, 0,
				(ArrayList<String>) B.itemsC.get("ITEMDROPRESTRICTION.LORES"));
	}
}