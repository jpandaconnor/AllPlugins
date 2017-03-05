package co.uk.RandomPanda30.Murge.Displays.Subdisplays;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.Murge.Collection.Player.KitsCollection;
import co.uk.RandomPanda30.Murge.Items.DefaultItems;
import co.uk.RandomPanda30.Murge.Items.Inventories.Subinventories.KitsOptionsItems;

public class KitsOptionsMenu {

	public static ArrayList<ItemStack> papers = new ArrayList<ItemStack>();
	public KitsCollection collection = new KitsCollection();

	private static boolean next = true;
	private static int page = 1;
	private static int epp = 36;
	private static int si = (page - 1) * epp;
	private static int ei = si + epp;

	public static void openKitsMenu(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 54, "Kits menu");
		papers = KitsCollection.getCollection().getKitAsObjects();
		resetInventory();
		if (page == 1) {
			inventory.setItem(0, KitsOptionsItems.getStarterKit());
		}

		si = (page - 1) * epp;
		ei = si + epp;
		if (ei > papers.size()) {
			ei = papers.size();
			next = false;
		}

		for (ItemStack is : papers.subList(si, ei)) {
			inventory.addItem(is);
		}

		if (next) {
			inventory.setItem(52, KitsOptionsItems.obtainNextPage());
		}

		if (page != 1) {
			inventory.setItem(51, KitsOptionsItems.obtainLastPage());
		}

		inventory.setItem(53, DefaultItems.obtainExit());

		inventory.setItem(44, KitsOptionsItems.obtainBorder());
		inventory.setItem(43, KitsOptionsItems.obtainBorder());
		inventory.setItem(42, KitsOptionsItems.obtainBorder());
		inventory.setItem(41, KitsOptionsItems.obtainBorder());
		inventory.setItem(40, KitsOptionsItems.obtainBorder());
		inventory.setItem(39, KitsOptionsItems.obtainBorder());
		inventory.setItem(38, KitsOptionsItems.obtainBorder());
		inventory.setItem(37, KitsOptionsItems.obtainBorder());
		inventory.setItem(36, KitsOptionsItems.obtainBorder());

		inventory.setItem(45, DefaultItems.obtainBack());
		inventory.setItem(47, KitsOptionsItems.obtainAdd());
		inventory.setItem(48, KitsOptionsItems.obtainEdit());
		inventory.setItem(49, KitsOptionsItems.obtainRemove());

		player.openInventory(inventory);
	}

	private static void resetInventory() {
		next = true;
		page = 1;
	}
}