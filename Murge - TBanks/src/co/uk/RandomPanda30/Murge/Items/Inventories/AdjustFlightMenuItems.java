package co.uk.RandomPanda30.Murge.Items.Inventories;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.Murge.Methods.ItemMethods;
import co.uk.RandomPanda30.Murge.Stats.StatsHandler;

public class AdjustFlightMenuItems {

	public static ItemStack obtain1(Player player) {
		return ItemMethods
				.createItem(
						(StatsHandler.getFlySpeed().get(player.getUniqueId()) == 1 ? "%G1"
								: "%B1"), Material.GLASS_BOTTLE, 1, 0,
						new ArrayList<String>());
	}

	public static ItemStack obtain2(Player player) {
		return ItemMethods
				.createItem(
						(StatsHandler.getFlySpeed().get(player.getUniqueId()) == 2 ? "%G2"
								: "%B2"), Material.GLASS_BOTTLE, 1, 0,
						new ArrayList<String>());
	}

	public static ItemStack obtain3(Player player) {
		return ItemMethods
				.createItem(
						(StatsHandler.getFlySpeed().get(player.getUniqueId()) == 3 ? "%G3"
								: "%B3"), Material.GLASS_BOTTLE, 1, 0,
						new ArrayList<String>());
	}

	public static ItemStack obtain4(Player player) {
		return ItemMethods
				.createItem(
						(StatsHandler.getFlySpeed().get(player.getUniqueId()) == 4 ? "%G4"
								: "%B4"), Material.GLASS_BOTTLE, 1, 0,
						new ArrayList<String>());
	}

	public static ItemStack obtain5(Player player) {
		return ItemMethods
				.createItem(
						(StatsHandler.getFlySpeed().get(player.getUniqueId()) == 5 ? "%G5"
								: "%B5"), Material.GLASS_BOTTLE, 1, 0,
						new ArrayList<String>());
	}
}