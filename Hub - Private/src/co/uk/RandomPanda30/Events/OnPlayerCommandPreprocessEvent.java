package co.uk.RandomPanda30.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import co.uk.RandomPanda30.Main.Main;

public class OnPlayerCommandPreprocessEvent implements Listener {

	public OnPlayerCommandPreprocessEvent (Main plugin) {
		Main.plugin = plugin;
	}

	@EventHandler
	public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		String command = e.getMessage();
		if (Main.plugin.getConfig().getStringList("BannedCommands")
				.contains(command)) {
			e.setCancelled(true);
			p.sendMessage("§CThis command is banned");
		}
	}

}
