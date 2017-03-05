package co.uk.RandomPanda30.ItemHandlerPlus.Methods;

import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.ItemHandlerPlus.B;

@SuppressWarnings("unchecked")
public class U {

	public static void saveInventory(ItemStack[] inventoryList) {
		ArrayList<ItemStack> newItems = new ArrayList<ItemStack>();
		for (ItemStack is : inventoryList) {
			if (is != null) {
				newItems.add(is);
			}
		}
		B.configC.set("INVENTORY", newItems);
		try {
			B.configC.save(B.config);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ItemStack[] loadInv() {
		ArrayList<ItemStack> is = (ArrayList<ItemStack>) B.configC
				.get("INVENTORY");
		ItemStack[] i = new ItemStack[is.size()];
		return is.toArray(i);
	}
}