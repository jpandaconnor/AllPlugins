package co.uk.RandomPanda30.Wild.Commands;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Wild.Main;

public class Command_wild implements CommandExecutor {

	public Main plugin;

	public Command_wild (Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		if (!(sender instanceof Player)) {
			Bukkit.getConsoleSender()
					.sendMessage(ChatColor.translateAlternateColorCodes('&',
							"&CYou must be a player to do this command"));
			return true;
		}

		Player player = (Player) sender;
		World world = player.getLocation().getWorld();
		double max = 1000;

		if (!Main.inmap.containsKey(player.getUniqueId())) {
			Location location = new Location(world, 0, 0, 0);
			location.setX(world.getSpawnLocation().getX()
					+ Math.random() * max * 2 - max);
			location.setZ(world.getSpawnLocation().getZ()
					+ Math.random() * max * 2 - max);
			location.setY(world.getHighestBlockYAt(location.getBlockX(),
					location.getBlockZ()));
			player.teleport(location);

			long newTime = Calendar.getInstance().getTimeInMillis()
					+ TimeUnit.MINUTES.toMillis(30);
			Main.inmap.put(player.getUniqueId(), newTime);
		} else {
			if (!(Main.inmap.get(player.getUniqueId()) < Calendar.getInstance()
					.getTimeInMillis())) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&CYou can only do this command every 30 minutes"));
				return true;
			} else {
				Main.inmap.remove(player.getUniqueId());
				Location location = new Location(world, 0, 0, 0);
				location.setX(world.getSpawnLocation().getX()
						+ Math.random() * max * 2 - max);
				location.setZ(world.getSpawnLocation().getZ()
						+ Math.random() * max * 2 - max);
				location.setY(world.getHighestBlockYAt(location.getBlockX(),
						location.getBlockZ()));
				player.teleport(location);

				long newTime = Calendar.getInstance().getTimeInMillis()
						+ TimeUnit.MINUTES.toMillis(30);
				Main.inmap.put(player.getUniqueId(), newTime);
			}
		}
		return true;
	}

}
