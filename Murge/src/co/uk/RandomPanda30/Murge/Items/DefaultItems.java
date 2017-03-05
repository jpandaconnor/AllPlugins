package co.uk.RandomPanda30.Murge.Items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Methods.ItemMethods;
import co.uk.RandomPanda30.Murge.Values.Enums.ItemsValues;

public class DefaultItems {

	public static ItemStack obtainExit() {
		return ItemMethods.createItem(
				(String) Murge.iMap.getStat(ItemsValues.EXIT),
				Material.REDSTONE_BLOCK, 1, 0, new ArrayList<String>());
	}

	public static ItemStack obtainBack() {
		return ItemMethods.createItem(
				(String) Murge.iMap.getStat(ItemsValues.BACK), Material.ARROW,
				1, 0, new ArrayList<String>());
	}
}