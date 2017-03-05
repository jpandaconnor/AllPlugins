package co.uk.RandomPanda30.Handlers;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import co.uk.RandomPanda30.Main.Main;

public class SoundCheck {

	public static void soundCheck(Player player) {
		if(Main.distressSound.isEmpty()) {
			BukkitTask soundSpam = new SoundHandler(Main.plugin).runTaskTimer(
					Main.plugin, 10, 10);
			soundSpam.cancel();
		}
		if (Main.distressSound.containsValue(player)) {
			@SuppressWarnings("unused")
			BukkitTask soundSpam = new SoundHandler(Main.plugin).runTaskTimer(
					Main.plugin, 10, 10);
		}
	}

}
