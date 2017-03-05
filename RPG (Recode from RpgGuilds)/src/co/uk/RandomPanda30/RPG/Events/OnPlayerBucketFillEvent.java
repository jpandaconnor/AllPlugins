package co.uk.RandomPanda30.RPG.Events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketFillEvent;

import co.uk.RandomPanda30.RPG.RPG;
import co.uk.RandomPanda30.RPG.Files.Config.Enums.ConfigValues;

public class OnPlayerBucketFillEvent implements Listener {

	public RPG plugin;

	public OnPlayerBucketFillEvent (RPG plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerBucketFillEvent(PlayerBucketFillEvent event) {
		if ((Boolean) plugin.getRPGConfig().getConfigMap()
				.getStat(ConfigValues.NoBuild)) {
			Player player = event.getPlayer();
			Location loc = player.getLocation();

			if (plugin.getRPGValues().getConfigC()
					.getConfigurationSection("Guilds").getKeys(false) == null) {
				return;
			}

			for (String guild : plugin.getRPGValues().getConfigC()
					.getConfigurationSection("Guilds").getKeys(false)) {
				if (plugin.getRPGValues().getConfigC()
						.getConfigurationSection("Guilds." + guild + ".HQ") != null) {
					double x = plugin.getRPGValues().getConfigC()
							.getDouble("Guilds." + guild + ".HQ.X");
					double y = plugin.getRPGValues().getConfigC()
							.getDouble("Guilds." + guild + ".HQ.Y");
					double z = plugin.getRPGValues().getConfigC()
							.getDouble("Guilds." + guild + ".HQ.Z");
					String world1 = plugin.getRPGValues().getConfigC()
							.getString("Guilds." + guild + ".HQ.World");
					if (player.getWorld().getName().equalsIgnoreCase(world1)) {
						Location gloc = new Location(Bukkit.getWorld(world1),
								x, y, z);
						if (loc.distance(gloc) <= 50.0D) {
							if (!plugin.getRPGValues().getConfigC()
									.contains(player.getUniqueId().toString())) {
								// sendMessage(
								// getTag()
								// + "You cannot build in the headquarters of "
								// + guild + ".", player);
								event.setCancelled(true);
								return;
							}

							if (!plugin
									.getRPGValues()
									.getConfigC()
									.getString(
											player.getUniqueId().toString()
													+ "Guild.Name")
									.equalsIgnoreCase(guild)) {
								// sendMessage(
								// getTag()
								// + "You cannot build in the headquarters of "
								// + guild + ".", player);
								event.setCancelled(true);
								return;
							}
						}
					}
				}
			}
		}
	}
}