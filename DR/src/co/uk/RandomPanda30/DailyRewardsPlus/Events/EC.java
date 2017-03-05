package co.uk.RandomPanda30.DailyRewardsPlus.Events;

import java.io.IOException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.DailyRewardsPlus.B;
import co.uk.RandomPanda30.DailyRewardsPlus.Methods.N;

public class EC implements Listener {

	@EventHandler
	public void onInventoryCloseEvent(InventoryCloseEvent event) {
		Inventory inventory = event.getInventory();
		if (inventory.getName().equals("Drag items in here")) {
			ItemStack[] items = inventory.getContents();
			if (items != null) {
				N.saveInventory(items);
				try {
					B.dataC.save(B.data);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}