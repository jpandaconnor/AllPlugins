package co.uk.RandomPanda30.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import co.uk.RandomPanda30.Main.Main;

public class OnPlayerQuitEvent implements Listener {
	
	public OnPlayerQuitEvent(Main plugin) {
		Main.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		if(Main.plugin.getConfig().getBoolean("LeaveMessage") == false) {
			e.setQuitMessage(null);
		}else{
			return;
		}
	}

}
