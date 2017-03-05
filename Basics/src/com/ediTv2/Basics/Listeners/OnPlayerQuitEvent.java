package com.ediTv2.Basics.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.ediTv2.Basics.Basics;

public class OnPlayerQuitEvent implements Listener {
	
	public OnPlayerQuitEvent (Basics plugin) {
		Basics.plugin = plugin;
	}

	@EventHandler
	public void onQuit (PlayerQuitEvent event) {
		event.setQuitMessage("");
	}
}
