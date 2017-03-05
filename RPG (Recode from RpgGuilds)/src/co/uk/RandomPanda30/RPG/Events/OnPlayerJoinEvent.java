package co.uk.RandomPanda30.RPG.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import co.uk.RandomPanda30.RPG.RPG;

public class OnPlayerJoinEvent implements Listener {

	public RPG plugin;

	public OnPlayerJoinEvent (RPG plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		event.setJoinMessage(null);
		Integer newkills = Integer.valueOf(0);
		if (plugin
				.getRPGValues()
				.getConfigC()
				.getConfigurationSection(
						"Kills." + player.getUniqueId().toString()) == null) {
			plugin.getRPGValues().getConfigC()
					.set("Kills." + player.getUniqueId().toString(), newkills);
		}

		if (plugin.getRPGValues().getConfigC()
				.contains(player.getUniqueId().toString())) {
			String guild = plugin.getRPGValues().getConfigC()
					.getString(player.getUniqueId().toString() + ".Guild.Name");
			for (String key : plugin.getRPGValues().getConfigC()
					.getConfigurationSection("Guilds." + guild + ".Players")
					.getKeys(false)) {
				if (Bukkit.getPlayer(key) != null) {
					if (Bukkit.getPlayer(key).getUniqueId().toString() == player
							.getUniqueId().toString()) {
						if (plugin.getRPGValues().getConfigC()
								.getString("Guilds." + guild + ".Gmotd") != null) {
							// sendMessage(
							// plugin.getRPGValues().getConfigC()
							// .getString(
							// "Guilds." + guild + ".Gmotd"),
							// player);
						} else {
							// sendMessage("You are a part of " + guild,
							// player);
						}
					} else {
						Player p = Bukkit.getPlayer(key);
						// sendMessage("&3" + player.getName()
						// + "&2 Has come online!", p);
					}
				}
			}
		}
	}
}