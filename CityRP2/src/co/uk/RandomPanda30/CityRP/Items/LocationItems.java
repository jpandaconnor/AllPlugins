package co.uk.RandomPanda30.CityRP.Items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.CityRP.Configs.Enums.Items;
import co.uk.RandomPanda30.CityRP.Resources.ItemUtil;

public class LocationItems {

	@SuppressWarnings("unchecked")
	public static ItemStack getSpawnItem() {
		return ItemUtil.createItem((String) Items.LOCATION_SPAWN_NAME.value,
				Material.STAINED_CLAY, 1, (byte) 14,
				(ArrayList<String>) Items.LOCATION_SPAWN_LORES.value);
	}	
}