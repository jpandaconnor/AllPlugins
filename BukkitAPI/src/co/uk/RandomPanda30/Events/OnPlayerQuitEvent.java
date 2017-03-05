package co.uk.RandomPanda30.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import co.uk.RandomPanda30.Handlers.Misc_Handlers;
import co.uk.RandomPanda30.Handlers.Text_Handlers;
import co.uk.RandomPanda30.Main.Main;

public class OnPlayerQuitEvent implements Listener {

	public OnPlayerQuitEvent (Main plugin) {
		Main.plugin = plugin;
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (p.getName().equals(Misc_Handlers.RandomPanda30.toString())) {
			e.setQuitMessage(null);
			Bukkit.broadcastMessage(Misc_Handlers.Dev_RandomPanda30.toString()
					+ "§Fhas left the game");
		}
		if (p.getName().equals(Misc_Handlers.Danmeal_.toString())) {
			e.setQuitMessage(null);
			Bukkit.broadcastMessage(Misc_Handlers.Documentor_Danmeal_
					.toString() + "§Fhas left the game");
		}
		if (p.getName().equals(Misc_Handlers.riley2465.toString())) {
			e.setQuitMessage(null);
			Bukkit.broadcastMessage(Misc_Handlers.Admin_riley2465.toString()
					+ "§Fhas left the game");
		} else {
			e.setQuitMessage(null);
			Bukkit.getConsoleSender().sendMessage(
					Text_Handlers.tag.toString() + "§B"
							+ e.getPlayer().getName().toString()
							+ " §Fhas left the game");
		}

		if (Main.admins.containsValue(p)) {
			Main.admins.remove(p);
		} else {
			return;
		}

		if (Main.documentors.containsValue(p)) {
			Main.documentors.remove(p);
		} else {
			return;
		}

		if (Main.devs.containsValue(p)) {
			Main.devs.remove(p);
		} else {
			return;
		}
		if (Main.playersOnline.size() == 0) {
			return;
		} else {
			Main.playersOnline.remove(1);
		}
	}
}
