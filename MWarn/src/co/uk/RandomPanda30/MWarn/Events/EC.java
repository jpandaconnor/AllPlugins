package co.uk.RandomPanda30.MWarn.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class EC implements Listener {

	@EventHandler
	public void onInventoryCloseEvent(InventoryCloseEvent event) {
		if (event.getInventory().getName().startsWith("\u00A7r ")) {
			// VA.resetInventory();
		}
	}
}