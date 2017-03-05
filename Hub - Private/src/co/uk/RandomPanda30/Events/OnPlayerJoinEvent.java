package co.uk.RandomPanda30.Events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import co.uk.RandomPanda30.Handlers.SpawnCompass;
import co.uk.RandomPanda30.Main.Main;

public class OnPlayerJoinEvent implements Listener {

	public OnPlayerJoinEvent (Main plugin) {
		Main.plugin = plugin;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		Location l = Bukkit.getWorld("Hub").getSpawnLocation();

		if (!player.hasPlayedBefore()) {

			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
					900, 2));

			player.setGameMode(GameMode.ADVENTURE);
			SpawnCompass.spawnCompass(player);

			if (player.isFlying() == true) {
				player.setFlying(false);
			}
			
			if(Main.plugin.getConfig().getBoolean("ClearInventory") == true) {
				player.getInventory().clear();
			}

			e.setJoinMessage(null);

			player.teleport(l);
		} else {
			if (Main.plugin.getConfig().getList("SpecialPlayers")
					.contains(player.getName().toString())
					|| player.isOp()) {

				SpawnCompass.spawnCompass(player);
				player.setGameMode(GameMode.CREATIVE);

				e.setJoinMessage(null);
			} else {
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
						900, 2));

				player.setGameMode(GameMode.ADVENTURE);
				SpawnCompass.spawnCompass(player);

				if (player.isFlying() == true) {
					player.setFlying(false);
				}
				
				if(Main.plugin.getConfig().getBoolean("ClearInventory") == true) {
					player.getInventory().clear();
				}

				e.setJoinMessage(null);

				player.teleport(l);
			}
		}
	}
}
