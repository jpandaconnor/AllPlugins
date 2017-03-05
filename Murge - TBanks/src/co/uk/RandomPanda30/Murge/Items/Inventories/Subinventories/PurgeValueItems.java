package co.uk.RandomPanda30.Murge.Items.Inventories.Subinventories;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.Murge.Collection.ValueCollection;
import co.uk.RandomPanda30.Murge.Collection.World.TimeCollection;
import co.uk.RandomPanda30.Murge.Methods.ItemMethods;

public class PurgeValueItems {

	public static ItemStack obtainTimeBefore_MINUS() {
		return ItemMethods.createItem("%TTime before purge %B-1",
				Material.GOLD_NUGGET, 1, 0, new ArrayList<String>());
	}

	public static ItemStack obtainTimeBefore_PLUS() {
		return ItemMethods.createItem("%TTime before purge %G+1",
				Material.GOLD_NUGGET, 1, 0, new ArrayList<String>());
	}

	public static ItemStack obtainTimeBefore() {
		return ItemMethods.createItem(
				"%TTime before purge: %A"
						+ Integer.toString(TimeCollection.getCollection()
								.getTimeBeforePurge()), Material.PAPER, 1, 0,
				new ArrayList<String>());
	}

	public static ItemStack obtainPurgeDuration_MINUS() {
		return ItemMethods.createItem("%TPurge duration %B-1",
				Material.GOLD_NUGGET, 1, 0, new ArrayList<String>());
	}

	public static ItemStack obtainPurgeDuration_PLUS() {
		return ItemMethods.createItem("%TPurge duration %G+1",
				Material.GOLD_NUGGET, 1, 0, new ArrayList<String>());
	}

	public static ItemStack obtainPurgeDuration() {
		return ItemMethods.createItem(
				"%TPurge duration: %A"
						+ Integer.toString(TimeCollection.getCollection()
								.getPurgeDuration()), Material.PAPER, 1, 0,
				new ArrayList<String>());
	}

	public static ItemStack obtainFightCooldown_MINUS() {
		return ItemMethods.createItem("%TCombat cooldown %B-1",
				Material.GOLD_NUGGET, 1, 0, new ArrayList<String>());
	}

	public static ItemStack obtainFightCooldown_PLUS() {
		return ItemMethods.createItem("%TCombat cooldown %G+1",
				Material.GOLD_NUGGET, 1, 0, new ArrayList<String>());
	}

	public static ItemStack obtainFightCooldown() {
		return ItemMethods.createItem(
				"%TCombat cooldown: %A"
						+ Integer.toString(TimeCollection.getCollection()
								.getFightCooldown()), Material.PAPER, 1, 0,
				new ArrayList<String>());
	}

	public static ItemStack obtainKillEcon_PLUS_ONE() {
		return ItemMethods.createItem("%TMoney on kill %G+1",
				Material.GOLD_NUGGET, 1, 0, new ArrayList<String>());
	}

	public static ItemStack obtainKillEcon_PLUS_TEN() {
		return ItemMethods.createItem("%TMoney on kill %G+10",
				Material.GOLD_NUGGET, 1, 0, new ArrayList<String>());
	}

	public static ItemStack obtainKillEcon_MINUS_ONE() {
		return ItemMethods.createItem("%TMoney on kill %B-1",
				Material.GOLD_NUGGET, 1, 0, new ArrayList<String>());
	}

	public static ItemStack obtainKillEcon_MINUS_TEN() {
		return ItemMethods.createItem("%TMoney on kill %B-10",
				Material.GOLD_NUGGET, 1, 0, new ArrayList<String>());
	}

	public static ItemStack obtainKillEcon() {
		return ItemMethods.createItem(
				"%TMoney on kill: %A"
						+ Integer.toString(ValueCollection.getCollection()
								.getValueOnKill()), Material.PAPER, 1, 0,
				new ArrayList<String>());
	}
}