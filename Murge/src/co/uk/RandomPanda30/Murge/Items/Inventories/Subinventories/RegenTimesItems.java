package co.uk.RandomPanda30.Murge.Items.Inventories.Subinventories;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.Murge.Collection.World.TimeCollection;
import co.uk.RandomPanda30.Murge.Methods.ItemMethods;

public class RegenTimesItems {

	public static ItemStack obtainDays_PLUS() {
		return ItemMethods.createItem("%TDay %G+1", Material.GOLD_NUGGET, 1, 0,
				new ArrayList<String>());
	}

	public static ItemStack obtainHours_PLUS() {
		return ItemMethods.createItem("%THour %G+1", Material.GOLD_NUGGET, 1,
				0, new ArrayList<String>());
	}

	public static ItemStack obtainMinutes_PLUS() {
		return ItemMethods.createItem("%TMinute %G+1", Material.GOLD_NUGGET, 1,
				0, new ArrayList<String>());
	}

	public static ItemStack obtainSeconds_PLUS() {
		return ItemMethods.createItem("%TSecond %G+1", Material.GOLD_NUGGET, 1,
				0, new ArrayList<String>());
	}

	public static ItemStack obtainOreTime() {
		return ItemMethods.createItem("%TOre regen time: %A"
				+ TimeCollection.getCollection().getOreRegenerationTimer(),
				Material.PAPER, 1, 0, new ArrayList<String>());
	}

	public static ItemStack obtainTreeTime() {
		return ItemMethods.createItem("%TTree regen time: %A"
				+ TimeCollection.getCollection().getTreeRegenerationTimer(),
				Material.PAPER, 1, 0, new ArrayList<String>());
	}

	public static ItemStack obtainDays_MINUS() {
		return ItemMethods.createItem("%TDay %B-1", Material.GOLD_NUGGET, 1, 0,
				new ArrayList<String>());
	}

	public static ItemStack obtainHours_MINUS() {
		return ItemMethods.createItem("%THour %B-1", Material.GOLD_NUGGET, 1,
				0, new ArrayList<String>());
	}

	public static ItemStack obtainMinutes_MINUS() {
		return ItemMethods.createItem("%TMinute %B-1", Material.GOLD_NUGGET, 1,
				0, new ArrayList<String>());
	}

	public static ItemStack obtainSeconds_MINUS() {
		return ItemMethods.createItem("%TSecond %B-1", Material.GOLD_NUGGET, 1,
				0, new ArrayList<String>());
	}
}