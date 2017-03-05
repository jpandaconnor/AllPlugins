package co.uk.RandomPanda30.ItemHandlerPlus.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import co.uk.RandomPanda30.ItemHandlerPlus.Methods.V;

public class EC implements Listener {

	@EventHandler
	public void onPlayerDropItemEvent(PlayerDropItemEvent event) {
		if (!V.isEnabled(event.getPlayer().getInventory().getHeldItemSlot() + 1)) {
			event.setCancelled(true);
		}
	}
}