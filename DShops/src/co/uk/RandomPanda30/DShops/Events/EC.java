package co.uk.RandomPanda30.DShops.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class EC implements Listener {

	@EventHandler
	public void onInventoryCloseEvent(InventoryCloseEvent event) {
		/*
		 * String name = event.getInventory().getName(); for (String s :
		 * EB.getInventories()) { if (name.contains(s) || name.equals(s)) {
		 * Bukkit.broadcastMessage(name);
		 * B.inInv.remove(event.getPlayer().getUniqueId()); } }
		 */
	}
}