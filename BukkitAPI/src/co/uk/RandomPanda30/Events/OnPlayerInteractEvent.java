package co.uk.RandomPanda30.Events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import co.uk.RandomPanda30.Handlers.InventoryHandler;
import co.uk.RandomPanda30.Handlers.Text_Handlers;
import co.uk.RandomPanda30.Main.Main;

public class OnPlayerInteractEvent implements Listener {

	public OnPlayerInteractEvent (Main plugin) {
		Main.plugin = plugin;
	}

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent e) {
		Action a = e.getAction();
		Player p = e.getPlayer();
		if (a == Action.RIGHT_CLICK_AIR) {
			if (p.getItemInHand().getType() == Material.COMPASS
					&& p.getItemInHand().getItemMeta().hasDisplayName()
					&& p.getItemInHand().getItemMeta().getDisplayName()
							.equals(Text_Handlers.compassName.toString())) {
				InventoryHandler.openTeleportMenu(p);
			}
		}
	}
}
