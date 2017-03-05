package co.uk.RandomPanda30.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import co.uk.RandomPanda30.Handlers.Text_Handlers;
import co.uk.RandomPanda30.Main.Main;

@SuppressWarnings("deprecation")
public class OnPlayerChatEvent implements Listener {

	public OnPlayerChatEvent(Main plugin) {
		Main.plugin = plugin;
	}

	@EventHandler
	public void onPlayerChat(PlayerChatEvent e) {
		Player player = e.getPlayer();
		if (Main.muted.containsValue(player)) {
			e.setCancelled(true);
			player.sendMessage(Text_Handlers.Btag.toString()
					+ "§CYou have been muted");
		}else{
			return;
		}
	}

}
