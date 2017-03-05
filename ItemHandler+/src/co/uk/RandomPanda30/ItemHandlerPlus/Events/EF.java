package co.uk.RandomPanda30.ItemHandlerPlus.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import co.uk.RandomPanda30.ItemHandlerPlus.B;
import co.uk.RandomPanda30.ItemHandlerPlus.Items.IB;

import com.gmail.filoghost.chestcommands.api.ChestCommandsAPI;

public class EF implements Listener {

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		Player player = event.getPlayer();

		if (player.getItemInHand() == null) {
			return;
		}

		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)
				|| event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (player.getItemInHand().equals(IB.obtainClock())) {
				if (ChestCommandsAPI.isPluginMenu((String) B.configC
						.get("GIVECLOCK.MENU"))) {
					ChestCommandsAPI.openPluginMenu(player,
							(String) B.configC.get("GIVECLOCK.MENU"));
				}
			}
		}
	}
}
