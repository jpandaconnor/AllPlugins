package co.uk.RandomPanda30.ItemHandlerPlus.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import co.uk.RandomPanda30.ItemHandlerPlus.B;
import co.uk.RandomPanda30.ItemHandlerPlus.Items.IB;

public class ED implements Listener {

	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		if ((Boolean) B.configC.get("GIVECLOCK.GIVE") == true) {
			event.getPlayer().getInventory().setItem(8, IB.obtainClock());
		}
	}
}