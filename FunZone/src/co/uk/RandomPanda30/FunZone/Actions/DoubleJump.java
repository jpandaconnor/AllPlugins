package co.uk.RandomPanda30.FunZone.Actions;

import java.util.List;
import java.util.UUID;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.Vector;

import co.uk.RandomPanda30.FunZone.FunZone;
import co.uk.RandomPanda30.FunZone.Configs.Config.ConfigValues;

@SuppressWarnings("unchecked")
public class DoubleJump implements Listener {

	public FunZone plugin;

	public DoubleJump (FunZone plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent event) {
		Player player = event.getPlayer();

		UUID uuid = player.getUniqueId();
		World world = player.getWorld();

		List<String> list = (List<String>) plugin.config
				.getStat(ConfigValues.DOUBLEJUMP_WORLDDISABLE);
		for (String wn : list) {
			if (world.getName().equals(wn))
				return;
		}

		if (player.getGameMode() == GameMode.CREATIVE)
			return;

		if ((plugin.getPValues().cooldown.containsKey(uuid)
				&& (((Boolean) plugin.getPValues().cooldown.get(uuid))
						.booleanValue()))) {
			player.setAllowFlight(true);
		} else {
			player.setAllowFlight(false);
		}

		if (player.isOnGround()) {
			plugin.getPValues().cooldown.put(uuid, Boolean.valueOf(true));
		}
	}

	@EventHandler
	public void onPlayerToggleFlightEvent(PlayerToggleFlightEvent event) {
		Player player = event.getPlayer();

		Location location = player.getLocation();
		UUID uuid = player.getUniqueId();
		World world = player.getWorld();

		List<String> list = (List<String>) plugin.config
				.getStat(ConfigValues.DOUBLEJUMP_WORLDDISABLE);
		for (String wn : list) {
			if (world.getName().equals(wn))
				return;
		}

		if (player.getGameMode() == GameMode.CREATIVE)
			return;

		if (((Boolean) plugin.getPValues().cooldown.get(uuid)).booleanValue()) {
			plugin.getPValues().cooldown.put(uuid, Boolean.valueOf(false));
			player.setVelocity(
					location.getDirection().multiply(1.6D).setY(1.0D));
			player.playSound(location, Sound.WITHER_SHOOT, 1.0F, 1.0F);
			player.setAllowFlight(false);
			event.setCancelled(true);
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerToggleSneakEvent(PlayerToggleSneakEvent event) {
		Player player = event.getPlayer();

		UUID uuid = player.getUniqueId();
		World world = player.getWorld();

		List<String> list = (List<String>) plugin.config
				.getStat(ConfigValues.DOUBLEJUMP_WORLDDISABLE);
		for (String wn : list) {
			if (world.getName().equals(wn))
				return;
		}

		if (player.getGameMode() == GameMode.CREATIVE)
			return;

		if ((!player.isOnGround())
				&& (plugin.getPValues().cooldown.get(uuid) != null)
				&& (!((Boolean) plugin.getPValues().cooldown.get(uuid))
						.booleanValue())) {
			player.setVelocity(new Vector(0, -5, 0));
		}
	}
}