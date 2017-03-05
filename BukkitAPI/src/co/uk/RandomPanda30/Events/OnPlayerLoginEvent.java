package co.uk.RandomPanda30.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import co.uk.RandomPanda30.Handlers.Text_Handlers;
import co.uk.RandomPanda30.Main.Main;

public class OnPlayerLoginEvent implements Listener {

	public OnPlayerLoginEvent (Main plugin) {
		Main.plugin = plugin;
	}

	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent e) {
		Player player = e.getPlayer();
		if (player.isBanned()) {
			e.disallow(Result.KICK_BANNED, Text_Handlers.banReason.toString());
		}else{
			return;
		}
	}

}
