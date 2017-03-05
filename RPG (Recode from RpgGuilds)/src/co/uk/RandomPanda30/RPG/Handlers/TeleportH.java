package co.uk.RandomPanda30.RPG.Handlers;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import co.uk.RandomPanda30.RPG.RPG;

public class TeleportH {

	public RPG plugin;

	public TeleportH (RPG plugin) {
		this.plugin = plugin;
	}

	public void teleport(final Player player, final Player player2) {
		final Location loc = player.getLocation();
		final Location Loc = player2.getLocation();
		// sendMessage("About to teleport, don't move!", player);
		// sendMessage("About to teleport, don't move!", player2);
		new BukkitRunnable() {
			int count = 8;

			public void run() {
				// sendMessage("Wait " + this.count + " Seconds.", player);
				// sendMessage("Wait " + this.count + " Seconds.", player2);
				this.count -= 1;
				if (player.getLocation().getX() != loc.getX()) {
					// sendMessage("Cancelled teleport, don't move!", player);
					// sendMessage("Cancelled teleport, don't move!", player2);
					cancel();
				}

				if (player.getLocation().getZ() != loc.getZ()) {
					// sendMessage("Cancelled teleport, don't move!", player);
					// sendMessage("Cancelled teleport, don't move!", player2);
					cancel();
				}

				if (player2.getLocation().getX() != Loc.getX()) {
					// sendMessage("Cancelled teleport, don't move!", player);
					// sendMessage("Cancelled teleport, don't move!", player2);
					cancel();
				}

				if (player2.getLocation().getZ() != Loc.getZ()) {
					// sendMessage("Cancelled teleport, don't move!", player);
					// sendMessage("Cancelled teleport, don't move!", player2);
					cancel();
				}

				if (this.count == 0) {
					player2.teleport(loc);
					cancel();
				}
			}
		}.runTaskTimer(plugin, 20L, 20L);
	}
}