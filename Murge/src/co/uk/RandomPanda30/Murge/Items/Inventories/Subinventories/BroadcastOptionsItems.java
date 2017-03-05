package co.uk.RandomPanda30.Murge.Items.Inventories.Subinventories;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.Murge.Collection.BroadcastCollection;
import co.uk.RandomPanda30.Murge.Methods.ItemMethods;

public class BroadcastOptionsItems {

	public static ItemStack obtainBroadcastPlayerLeave() {
		boolean c = BroadcastCollection.broadcastLoggedOut();
		String title = "";
		if (c) {
			title = "%NPlayer leaves combat: %GOn";
		} else {
			title = "%NPlayer leaves combat: %BOff";
		}
		ArrayList<String> lores = new ArrayList<String>();
		lores.add("%TShould this message be broadcasted");
		lores.add("%Twhen a player quits during combat?");
		return ItemMethods
				.createItem(title, Material.STONE_BUTTON, 1, 0, lores);
	}
}