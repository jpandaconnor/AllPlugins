package co.uk.RandomPanda30.Murge.Items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Methods.ItemMethods;
import co.uk.RandomPanda30.Murge.Values.Enums.ItemsValues;

@SuppressWarnings("unchecked")
public class EditLocationItems {

	public static ItemStack obtainSpawnLocation() {
		return ItemMethods
				.createItem((String) Murge.iMap
						.getStat(ItemsValues.EDIT_SPAWNLOCATIONNAME),
						Material.STAINED_CLAY, 1, 5,
						(ArrayList<String>) Murge.iMap
								.getStat(ItemsValues.EDIT_SPAWNLOCATIONLORES));
	}

	public static ItemStack obtainSpectatorLocation() {
		return ItemMethods.createItem((String) Murge.iMap
				.getStat(ItemsValues.EDIT_SPECTATORLOCATIONNAME),
				Material.STAINED_CLAY, 1, 11, (ArrayList<String>) Murge.iMap
						.getStat(ItemsValues.EDIT_SPECTATORLOCATIONLORES));
	}

	public static ItemStack obtainExitEdit() {
		return ItemMethods.createItem((String) Murge.iMap
				.getStat(ItemsValues.EXITEDITMODE_NAME),
				Material.REDSTONE_BLOCK, 1, 0, (ArrayList<String>) Murge.iMap
						.getStat(ItemsValues.EXITEDITMODE_LORES));
	}
}