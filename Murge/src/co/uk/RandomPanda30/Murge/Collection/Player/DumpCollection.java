package co.uk.RandomPanda30.Murge.Collection.Player;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.Murge.Murge;

@SuppressWarnings("unchecked")
public class DumpCollection {

	private static DumpCollection instance = new DumpCollection();

	public static DumpCollection getCollection() {
		return instance;
	}

	public boolean isDumped(UUID uuid) {
		if (Murge.cso.getDumpC().contains(uuid.toString())) {
			return true;
		}
		return false;
	}

	public void dump(ItemStack[] inventoryList, ItemStack[] armour, UUID uuid) {
		ArrayList<ItemStack> newItems = new ArrayList<ItemStack>();
		ArrayList<ItemStack> newArmour = new ArrayList<ItemStack>();
		for (ItemStack is : inventoryList) {
			if (is != null) {
				newItems.add(is);
			}
		}

		for (ItemStack is : armour) {
			if (is != null) {
				newArmour.add(is);
			}
		}

		Murge.cso.getDumpC().set(uuid.toString() + ".Inventory", newItems);
		Murge.cso.getDumpC().set(uuid.toString() + ".Armour", newArmour);
	}

	public ItemStack[] getInventoryDump(UUID uuid) {
		ArrayList<ItemStack> is = (ArrayList<ItemStack>) Murge.cso.getDumpC()
				.get(uuid.toString() + ".Inventory");
		ItemStack[] i = new ItemStack[is.size()];
		return is.toArray(i);
	}

	public ItemStack[] getArmourDump(UUID uuid) {
		ArrayList<ItemStack> is = (ArrayList<ItemStack>) Murge.cso.getDumpC()
				.get(uuid.toString() + ".Armour");
		ItemStack[] i = new ItemStack[is.size()];
		return is.toArray(i);
	}

	public void clearDump(UUID uuid) {
		Murge.cso.getDumpC().set(uuid.toString(), null);
	}

	public boolean isPunishDump(UUID uuid) {
		if (Murge.cso.getDumpC().get("Topunish." + uuid.toString()) == null) {
			return false;
		}
		return true;
	}

	public void dumpPunish(UUID uuid) {
		Murge.cso.getDumpC().set("Topunish." + uuid.toString(), true);
	}

	public void removePunish(UUID uuid) {
		Murge.cso.getDumpC().set("Topunish." + uuid.toString(), null);
	}
}