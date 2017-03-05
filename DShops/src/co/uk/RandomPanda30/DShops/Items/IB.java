package co.uk.RandomPanda30.DShops.Items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.DShops.B;
import co.uk.RandomPanda30.DShops.Methods.Q;

public class IB {

	public static ItemStack obtainPaperDetails() {
		return Q.createItem(
				(String) B.itemsC.get("OWNEDINV.PAPERDETAILS.NAME"),
				Material.PAPER, 1, 0, new ArrayList<String>(), false);
	}

	public static ItemStack obtainOpenDoor() {
		return Q.createItem((String) B.itemsC.get("OWNEDINV.OPENDOOR.NAME"),
				Material.STAINED_CLAY, 1, 5, new ArrayList<String>(), false);
	}

	public static ItemStack obtainCloseDoor() {
		return Q.createItem((String) B.itemsC.get("OWNEDINV.CLOSEDOOR.NAME"),
				Material.STAINED_CLAY, 1, 14, new ArrayList<String>(), false);
	}

	public static ItemStack obtainEditFriends() {
		return Q.createItem((String) B.itemsC.get("OWNEDINV.EDITFRIENDS.NAME"),
				Material.SKULL_ITEM, 1, 0, new ArrayList<String>(), false);
	}

	public static ItemStack obtainSellDoor() {
		return Q.createItem((String) B.itemsC.get("OWNEDINV.SELLDOOR.NAME"),
				Material.REDSTONE, 1, 0, new ArrayList<String>(), false);
	}
}