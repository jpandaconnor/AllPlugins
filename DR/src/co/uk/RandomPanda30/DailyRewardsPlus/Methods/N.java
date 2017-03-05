package co.uk.RandomPanda30.DailyRewardsPlus.Methods;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.DailyRewardsPlus.B;

@SuppressWarnings("unchecked")
public class N {

	public static void saveInventory(ItemStack[] inventoryList) {
		ArrayList<ItemStack> newItems = new ArrayList<ItemStack>();
		for (ItemStack is : inventoryList) {
			if (is != null) {
				newItems.add(is);
			}
		}
		B.dataC.set("Inventory", newItems);
	}

	public static ItemStack[] loadInv() {
		ArrayList<ItemStack> is = (ArrayList<ItemStack>) B.dataC
				.get("Inventory");
		ItemStack[] i = new ItemStack[is.size()];
		return is.toArray(i);
	}
}