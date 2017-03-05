package co.uk.RandomPanda30.Murge.Items.Inventories;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Methods.ItemMethods;
import co.uk.RandomPanda30.Murge.Values.Enums.ItemsValues;

@SuppressWarnings("unchecked")
public class SettingsMenuItems {

	public static ItemStack obtainWorldBorder() {
		return ItemMethods.createItem((String) Murge.iMap
				.getStat(ItemsValues.SETTINGS_WORLDBORDERNAME),
				Material.STONE_BUTTON, 1, 0, (ArrayList<String>) Murge.iMap
						.getStat(ItemsValues.SETTINGS_WORLDBORDERLORES));
	}

	public static ItemStack obtainLocations() {
		return ItemMethods.createItem((String) Murge.iMap
				.getStat(ItemsValues.SETTINGS_LOCATIONNAME),
				Material.STONE_BUTTON, 1, 0, (ArrayList<String>) Murge.iMap
						.getStat(ItemsValues.SETTINGS_LOCATIONLORES));
	}

	public static ItemStack obtainPurgeValues() {
		return ItemMethods.createItem((String) Murge.iMap
				.getStat(ItemsValues.SETTINGS_PURGEVALUENAME),
				Material.STONE_BUTTON, 1, 0, (ArrayList<String>) Murge.iMap
						.getStat(ItemsValues.SETTINGS_PURGEVALUELORES));
	}

	public static ItemStack obtainPurgeOptions() {
		return ItemMethods.createItem((String) Murge.iMap
				.getStat(ItemsValues.SETTINGS_PURGEOPTIONSNAME),
				Material.STONE_BUTTON, 1, 0, (ArrayList<String>) Murge.iMap
						.getStat(ItemsValues.SETTINGS_PURGEOPTIONSLORES));
	}

	public static ItemStack obtainBroadcastOptions() {
		return ItemMethods.createItem((String) Murge.iMap
				.getStat(ItemsValues.SETTINGS_BROADCASTOPTIONSNAME),
				Material.STONE_BUTTON, 1, 0, (ArrayList<String>) Murge.iMap
						.getStat(ItemsValues.SETTINGS_BROADCASTOPTIONSLORES));
	}
}