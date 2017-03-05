package co.uk.RandomPanda30.ItemHandlerPlus.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import co.uk.RandomPanda30.ItemHandlerPlus.Methods.U;

public class EB implements Listener {

	@EventHandler
	public void onInventoryCloseEvent(InventoryCloseEvent event) {
		if (event.getInventory().getName().equals("Drag items in here")) {
			U.saveInventory(event.getInventory().getContents());
		}
	}
}