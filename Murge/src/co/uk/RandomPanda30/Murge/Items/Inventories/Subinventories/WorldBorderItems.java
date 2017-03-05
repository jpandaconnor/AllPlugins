package co.uk.RandomPanda30.Murge.Items.Inventories.Subinventories;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.Murge.Collection.World.WorldBorderCollection;
import co.uk.RandomPanda30.Murge.Methods.ItemMethods;

public class WorldBorderItems {

	public static ItemStack obtainStatus() {
		boolean c = WorldBorderCollection.getCollection().isEnabled();
		String title = "";

		if (c) {
			title = "%GOn";
		} else {
			title = "%BOff";
		}

		return ItemMethods.createItem(title, Material.STONE_BUTTON, 1, 0,
				new ArrayList<String>());
	}

	public static ItemStack obtainSize_MINUS() {
		return ItemMethods.createItem("%TSize %B-10", Material.GOLD_NUGGET, 1,
				0, new ArrayList<String>());
	}

	public static ItemStack obtainSize_PLUS() {
		return ItemMethods.createItem("%TSize %G+10", Material.GOLD_NUGGET, 1,
				0, new ArrayList<String>());
	}

	public static ItemStack obtainSize() {
		return ItemMethods.createItem(
				"%TSize: %A"
						+ Double.toString(WorldBorderCollection.getCollection()
								.getSize()), Material.PAPER, 1, 0,
				new ArrayList<String>());
	}

	public static ItemStack obtainDamage_MINUS() {
		return ItemMethods.createItem("%TDamage %B-1", Material.GOLD_NUGGET, 1,
				0, new ArrayList<String>());
	}

	public static ItemStack obtainDamage_PLUS() {
		return ItemMethods.createItem("%TDamage %G+1", Material.GOLD_NUGGET, 1,
				0, new ArrayList<String>());
	}

	public static ItemStack obtainDamage() {
		return ItemMethods.createItem(
				"%TDamage: %A"
						+ Double.toString(WorldBorderCollection.getCollection()
								.getDamage()), Material.PAPER, 1, 0,
				new ArrayList<String>());
	}

	public static ItemStack obtainClosing() {
		boolean c = WorldBorderCollection.getCollection().closes();
		String title = "";

		if (c) {
			title = "%TClosing world border: %GOn";
		} else {
			title = "%TClosing world border: %BOff";
		}

		return ItemMethods.createItem(title, Material.STONE_BUTTON, 1, 0,
				new ArrayList<String>());
	}

	public static ItemStack obtainCloseLimit() {
		return ItemMethods.createItem(
				"%TClosing threshold (In blocks): %A"
						+ Double.toString(WorldBorderCollection.getCollection()
								.getCloseLimit()), Material.PAPER, 1, 0,
				new ArrayList<String>());
	}

	public static ItemStack obtainCloseLimit_MINUS() {
		return ItemMethods.createItem("%TBlocks %B-10", Material.GOLD_NUGGET,
				1, 0, new ArrayList<String>());
	}

	public static ItemStack obtainCloseLimit_PLUS() {
		return ItemMethods.createItem("%TBlocks %G+10", Material.GOLD_NUGGET,
				1, 0, new ArrayList<String>());
	}

	public static ItemStack obtainCloseTime() {
		return ItemMethods.createItem(
				"%TClose time: %A"
						+ Double.toString(WorldBorderCollection.getCollection()
								.getCloseTime()), Material.PAPER, 1, 0,
				new ArrayList<String>());
	}

	public static ItemStack obtainCloseTime_MINUS() {
		return ItemMethods.createItem("%TTime %B-1", Material.GOLD_NUGGET, 1,
				0, new ArrayList<String>());
	}

	public static ItemStack obtainCloseTime_PLUS() {
		return ItemMethods.createItem("%TTime %G+1", Material.GOLD_NUGGET, 1,
				0, new ArrayList<String>());
	}
}