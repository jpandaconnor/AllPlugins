package com.ediTv2.Basics.Listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import com.ediTv2.Basics.Basics;
import com.ediTv2.Basics.Text.TextH;

public class OnPlayerLoginEvent implements Listener {

	public OnPlayerLoginEvent (Basics plugin) {
		Basics.plugin = plugin;
	}

	@EventHandler
	public void onLogin(PlayerLoginEvent event) {
		Player p = event.getPlayer();
		p.sendMessage(TextH.welcome);
		p.setGameMode(GameMode.ADVENTURE);
		p.getInventory().clear();
	}
	
	//This code has been done good as well ;3 Just 1 change
	//Update: GameMode doesn't seem to change aswell as inventory does not clear. D:
}
