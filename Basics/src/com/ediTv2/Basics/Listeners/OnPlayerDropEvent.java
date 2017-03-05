package com.ediTv2.Basics.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import com.ediTv2.Basics.Basics;
import com.ediTv2.Basics.Text.TextH;

//ALWAYS IMPLEMENT LISTENERS IN A LISTENER CLASS

public class OnPlayerDropEvent implements Listener {

	/**
	 * This next part is so important. If you don't do this then your plugin
	 * won't register this listener!
	 */

	public OnPlayerDropEvent (Basics plugin) {
		Basics.plugin = plugin;
	}

	/**
	 * Basically your doing -
	 * 
	 * public <This class name> (<Main class name> plugin) {
	 * 
	 * the bottom line is a bit harder. You have to create a variable in your
	 * main class. So I have done -
	 * 
	 * *public static Basics plugin;*
	 * 
	 * Always do this, it's a lot easier
	 * 
	 * So this second line -
	 * 
	 * Basics.plugin = plugin;
	 * 
	 * is like saying
	 * 
	 * <Main class>. <Main class variable> = <What you put in (Basics plugin) so
	 * in this case it would be *plugin*>
	 */

	/**
	 * Some people also put some reference to this class on the top but fuck
	 * dat. Just creating a static variable in your main class and you will be
	 * fine ;3
	 */

	// Normally you dont need to use priority ;3
	@EventHandler
	public void onDrop(PlayerDropItemEvent event) { // Dont be afraid to use the
													// word event ;3
		Player p = event.getPlayer();
		if (p.getWorld().getName().equalsIgnoreCase("world")) {

			/**
			 * Now I normally check if the world is acutally there by using a
			 * null check. != is the null check ;3
			 */
			
			if (Bukkit.getWorld("world") != null) { // Here I am saying if the
													// world is there! Also do
													// this as you might get a
													// random error if you dont
													// ;3
				event.setCancelled(true);
				p.sendMessage(TextH.noDropItems);
			} else { // Here I am saying what happens if the world isn't there
				p.sendMessage(TextH.worldError);
			}
		} else {
			event.setCancelled(false);
		}
	}
}

// You have done this bit of code well ;3
