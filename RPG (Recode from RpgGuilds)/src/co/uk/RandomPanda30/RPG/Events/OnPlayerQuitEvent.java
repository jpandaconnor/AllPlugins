package co.uk.RandomPanda30.RPG.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import co.uk.RandomPanda30.RPG.RPG;

public class OnPlayerQuitEvent implements Listener {

	public RPG plugin;

	public OnPlayerQuitEvent (RPG plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		event.setQuitMessage(null);
		if (plugin.getRPGValues().getConfigC()
				.contains(player.getUniqueId().toString())) {
			String guild = plugin.getRPGValues().getConfigC()
					.getString(player.getUniqueId().toString() + ".Guild.Name");
			for (String key : plugin.getRPGValues().getConfigC()
					.getConfigurationSection("Guilds." + guild + ".Players")
					.getKeys(false)) {
				if (Bukkit.getPlayer(key) != null) {
					Player p = Bukkit.getPlayer(key);
					// sendMessage("&3" + player.getName()
					// + "&2 Has gone offline!", p);
				}
			}
		}
	}
}