package co.uk.RandomPanda30.RPG.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import co.uk.RandomPanda30.RPG.RPG;

public class OnPlayerDeathEvent implements Listener {

	public RPG plugin;

	public OnPlayerDeathEvent (RPG plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player killed = event.getEntity();
		if (killed.getKiller() instanceof Player) {
			Player killer = killed.getKiller();
			Integer kills = Integer.valueOf(plugin.getRPGValues().getConfigC()
					.getInt("Kills." + killer.getUniqueId().toString()));
			kills = Integer.valueOf(kills.intValue() + 1);
			plugin.getRPGValues().getConfigC()
					.set("Kills." + killer.getUniqueId().toString(), kills);
			plugin.getRPGConfig().saveAllConfigs();
		}
	}
}