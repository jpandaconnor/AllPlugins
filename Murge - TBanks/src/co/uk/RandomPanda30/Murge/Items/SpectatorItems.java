package co.uk.RandomPanda30.Murge.Items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Methods.ItemMethods;
import co.uk.RandomPanda30.Murge.Values.Enums.ItemsValues;

@SuppressWarnings("unchecked")
public class SpectatorItems {

	public static ItemStack obtainLeave() {
		return ItemMethods.createItem((String) Murge.iMap
				.getStat(ItemsValues.LEAVETOOL_NAME), Material.REDSTONE_BLOCK,
				1, 0, (ArrayList<String>) Murge.iMap
						.getStat(ItemsValues.LEAVETOOL_LORES));
	}

	public static ItemStack obtainCompass() {
		return ItemMethods.createItem((String) Murge.iMap
				.getStat(ItemsValues.COMPASS_NAME), Material.COMPASS, 1, 0,
				(ArrayList<String>) Murge.iMap
						.getStat(ItemsValues.COMPASS_LORES));
	}

	public static ItemStack obtainAdjustFly() {
		return ItemMethods.createItem((String) Murge.iMap
				.getStat(ItemsValues.ADJUSTFLY_NAME), Material.FEATHER, 1, 0,
				(ArrayList<String>) Murge.iMap
						.getStat(ItemsValues.ADJUSTFLY_LORES));
	}
}