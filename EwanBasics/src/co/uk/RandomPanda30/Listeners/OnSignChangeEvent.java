package co.uk.RandomPanda30.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import co.uk.RandomPanda30.Basics.Main;
import co.uk.RandomPanda30.Handler.MessageH;

public class OnSignChangeEvent implements Listener {

	public OnSignChangeEvent (Main plugin) {
		Main.plugin = plugin;
	}

	@EventHandler
	public void onSignPlace(SignChangeEvent event) {
		Player player = event.getPlayer();
		if (event.getLine(0).equalsIgnoreCase("[Random]")) {
			if (player.hasPermission("serverbasics.randomsign")
					|| player.isOp()) {
				event.setLine(0, null);
				event.setLine(0, MessageH.randomSign1);
				event.setLine(1, MessageH.randomSign2);
				event.setLine(2, MessageH.randomSign3);
				event.setLine(3, MessageH.randomSign4);
				event.getBlock().getState().update();
			} else {
				event.getBlock().breakNaturally();
				player.sendMessage(MessageH.noPermission);
			}
		}
	}
}