package co.uk.RandomPanda30.DShops.Items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.DShops.B;
import co.uk.RandomPanda30.DShops.Methods.Q;

public class IC {

	public static ItemStack obtainAddFriend() {
		return Q.createItem(
				(String) B.itemsC.get("EDITFRIENDSINV.ADDFRIEND.NAME"),
				Material.SKULL_ITEM, 1, 0, new ArrayList<String>(), false);
	}

	public static ItemStack obtainRemoveFriend() {
		return Q.createItem(
				(String) B.itemsC.get("EDITFRIENDSINV.REMOVEFRIEND.NAME"),
				Material.SKULL_ITEM, 1, 0, new ArrayList<String>(), false);
	}
}
