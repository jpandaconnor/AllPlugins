package co.uk.RandomPanda30.DShops.Methods;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.DShops.B;

@SuppressWarnings("unchecked")
public class R {

	public static ArrayList<ItemStack> heads = new ArrayList<ItemStack>();

	public static void openFriendsList(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 54, "Friends list");
		resetInventory();
		ArrayList<String> details = (ArrayList<String>) B.doorsC.get(B.inInv
				.get(player.getUniqueId()) + ".friends");
		ArrayList<String> lores = new ArrayList<String>();
		for (String s : details) {
			String[] newS = M.decompileDetails(s);
			String name = newS[0];
			String uuid = newS[1];

			lores.add(L.formatMessage("%NUUID: %A" + name));

			ItemStack is = Q.createItem(uuid, Material.SKULL_ITEM, 1, 0, lores,
					false);
			if (!heads.contains(is)) {
				heads.add(is);
			}
		}

		for (ItemStack is : heads) {
			inventory.addItem(is);
		}
		player.openInventory(inventory);
	}

	public static void resetInventory() {
		heads.clear();
	}
}