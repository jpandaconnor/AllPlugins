package co.uk.RandomPanda30.MineRP.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import co.uk.RandomPanda30.MineRP.Displays.JobsMenu;

public class OnPlayerDropItemEvent implements Listener {

	@EventHandler
	public void onPlayerDropItemEvent(PlayerDropItemEvent event) {
		JobsMenu.openJobsMenu(event.getPlayer());
		event.setCancelled(true);
	}
}