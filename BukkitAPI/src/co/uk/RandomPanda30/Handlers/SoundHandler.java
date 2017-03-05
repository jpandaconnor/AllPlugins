package co.uk.RandomPanda30.Handlers;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import co.uk.RandomPanda30.Main.Main;

public class SoundHandler extends BukkitRunnable {

	public SoundHandler (Main plugin) {
		Main.plugin = plugin;
	}

	public void run() {
		Player player = Main.distressSound.get("player");
		if (player != null) {
			player.playSound(player.getLocation(), Sound.GHAST_FIREBALL, 80, 2);
		} else {
			return;
		}
	}
}
