package co.uk.RandomPanda30.Murge.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;

import co.uk.RandomPanda30.Murge.MurgeData;

public class OnBlockIgniteEvent implements Listener {

	@EventHandler
	public void onBlockIgniteEvent(BlockIgniteEvent event) {
		if (event.getCause().equals(IgniteCause.LIGHTNING)) {
			if (event.getBlock().getLocation().getWorld()
					.equals(MurgeData.getWorld())) {
				event.setCancelled(true);
			}
		}
	}
}