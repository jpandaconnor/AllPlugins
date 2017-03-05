package com.ediTv2.Basics.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.ediTv2.Basics.Basics;
import com.ediTv2.Basics.Text.TextH;

public class OnAsyncPlayerChatEvent implements Listener {

	public OnAsyncPlayerChatEvent(Basics plugin) {
		Basics.plugin = plugin;
	}

	@EventHandler
	public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		if (Basics.chatDisabled == true) {
			if (player.hasPermission("basics.bypassMute")) {
				event.setCancelled(false);
				return;
			}
			event.setCancelled(true);
			player.sendMessage(TextH.chatMute);
		}
	}
}
