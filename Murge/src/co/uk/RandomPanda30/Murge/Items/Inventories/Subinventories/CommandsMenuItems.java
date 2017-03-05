package co.uk.RandomPanda30.Murge.Items.Inventories.Subinventories;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.Murge.Collection.Commands.Fixwb;
import co.uk.RandomPanda30.Murge.Collection.Commands.Start;
import co.uk.RandomPanda30.Murge.Collection.Commands.Stop;
import co.uk.RandomPanda30.Murge.Methods.ItemMethods;

public class CommandsMenuItems {

	public static ItemStack obtainStart() {
		boolean c = Start.getCollection().isEnabled();
		String title = "";
		if (c) {
			title = "%NStart command: %GOn";
		} else {
			title = "%NStart command: %BOff";
		}
		ArrayList<String> lores = new ArrayList<String>();
		return ItemMethods
				.createItem(title, Material.STONE_BUTTON, 1, 0, lores);
	}

	public static ItemStack obtainStop() {
		boolean c = Stop.getCollection().isEnabled();
		String title = "";
		if (c) {
			title = "%NStop command: %GOn";
		} else {
			title = "%NStop command: %BOff";
		}
		ArrayList<String> lores = new ArrayList<String>();
		return ItemMethods
				.createItem(title, Material.STONE_BUTTON, 1, 0, lores);
	}

	public static ItemStack obtainFixwb() {
		boolean c = Fixwb.getCollection().isEnabled();
		String title = "";
		if (c) {
			title = "%NFixwb command: %GOn";
		} else {
			title = "%NFixwb command: %BOff";
		}
		ArrayList<String> lores = new ArrayList<String>();
		return ItemMethods
				.createItem(title, Material.STONE_BUTTON, 1, 0, lores);
	}
}