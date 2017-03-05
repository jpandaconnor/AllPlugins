package co.uk.RandomPanda30.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import co.uk.RandomPanda30.Main.Main;

@SuppressWarnings("deprecation")
public class OnPlayerChatEvent implements Listener {

	public OnPlayerChatEvent (Main plugin) {
		Main.plugin = plugin;
	}

	@EventHandler
	public void onPlayerChatEvent(PlayerChatEvent e) {
		Player player = e.getPlayer();
		if(Main.plugin.getConfig().getStringList("BannedWords").contains(e.getMessage().toLowerCase())) {
			e.setCancelled(true);
			player.sendMessage("§CDo not use bad language");
		}
	}
}