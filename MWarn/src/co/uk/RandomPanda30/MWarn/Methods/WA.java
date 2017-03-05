package co.uk.RandomPanda30.MWarn.Methods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import co.uk.RandomPanda30.MWarn.A;
import co.uk.RandomPanda30.MWarn.B;

public class WA extends BukkitRunnable {

	public WA (A plugin) {
		B.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	public void run() {
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			W.checkWarnings(player);
		}
	}
}