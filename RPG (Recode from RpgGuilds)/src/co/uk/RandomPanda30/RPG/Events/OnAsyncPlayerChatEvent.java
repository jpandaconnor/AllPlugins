package co.uk.RandomPanda30.RPG.Events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import co.uk.RandomPanda30.RPG.RPG;
import co.uk.RandomPanda30.RPG.Files.Config.Enums.ConfigValues;

public class OnAsyncPlayerChatEvent implements Listener {

	public RPG plugin;

	public OnAsyncPlayerChatEvent (RPG plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		if (((Boolean) plugin.getRPGConfig().getConfigMap()
				.getStat(ConfigValues.Chat))
				&& (plugin.getRPGValues().getConfigC().contains(player
						.getUniqueId().toString()))) {
			String guild = plugin.getRPGValues().getConfigC()
					.getString(player.getUniqueId().toString() + ".Guild.Name");
			String tag = plugin.getRPGValues().getConfigC()
					.getString("Guilds." + guild + ".Tag");
			if (plugin.getRPGValues().getConfigC().getBoolean("Chat")) {
				event.setMessage(ChatColor.translateAlternateColorCodes('&',
						"&F[&2" + tag + "&f] " + event.getMessage()));
			}
		}
	}
}