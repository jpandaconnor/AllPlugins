package co.uk.RandomPanda30.MineRP.Items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.MineRP.Job;
import co.uk.RandomPanda30.MineRP.Handlers.ItemHandler;
import co.uk.RandomPanda30.MineRP.Methods.DataCollection;

public class JobItems {

	public static ItemStack citizenItem() {
		return ItemHandler.createItem(DataCollection.getJobColour(Job.CITIZEN)
				+ "Citizen", Material.SKULL_ITEM, 1, 0,
				DataCollection.getJobDescription(Job.CITIZEN));
	}
}