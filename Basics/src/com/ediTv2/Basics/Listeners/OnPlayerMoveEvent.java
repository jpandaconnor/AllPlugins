package com.ediTv2.Basics.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.ediTv2.Basics.Basics;
import com.ediTv2.Basics.Text.TextH;

public class OnPlayerMoveEvent implements Listener {

	public OnPlayerMoveEvent (Basics plugin) {
		Basics.plugin = plugin;
	}

	@EventHandler
	public void onFall(PlayerMoveEvent event) {
		Player p = event.getPlayer();
		if (p.getWorld().getName().equalsIgnoreCase("root")) {
			if (Bukkit.getWorld("root") != null) {

				/**
				 * You got stuck on this part so let me give you a lesson in
				 * Maths ;D
				 * 
				 * You should of seen the symbols < > etc... So use this to
				 * check if the players Y co-ordiante is lower than 0 ;3
				 */

				if (p.getLocation().getBlockY() < 0) { // If the players block Y
														// is LESS than 0
					Location spawn = Bukkit.getWorld("root").getSpawnLocation();
					/**
					 * I prefer to get the spawn location of the world. Never
					 * hard code the co-ordiantes in, let the plugin find the
					 * spawn location for itself ;3
					 */
					p.teleport(spawn);
				} else {
					return;

					/**
					 * Returning if the player is above the co-ordinate 0
					 */
				}
			} else {
				p.sendMessage(TextH.worldError);
			}
		}
	}
}
