package co.uk.RandomPanda30.Murge.Items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.Murge.Methods.ItemMethods;

public class PurgeButtons {

	public static ItemStack getStart() {
		ArrayList<String> lores = new ArrayList<String>();
		lores.add("%TClick here to start the purge");
		return ItemMethods.createItem("%GStart", Material.EMERALD_BLOCK, 1, 0,
				lores);
	}

	public static ItemStack getStop() {
		ArrayList<String> lores = new ArrayList<String>();
		lores.add("%TClick here to stop the purge");
		return ItemMethods.createItem("%BStop", Material.REDSTONE_BLOCK, 1, 0,
				lores);
	}
}