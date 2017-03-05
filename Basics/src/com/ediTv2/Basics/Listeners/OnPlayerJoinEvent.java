package com.ediTv2.Basics.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.ediTv2.Basics.Basics;

public class OnPlayerJoinEvent implements Listener {

	public OnPlayerJoinEvent(Basics plugin) {
		Basics.plugin = plugin;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		event.setJoinMessage("");
	}
}