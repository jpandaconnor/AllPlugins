package co.uk.RandomPanda30.GBlock.Plot;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import co.uk.RandomPanda30.GBlock.GBlock;
import co.uk.RandomPanda30.GBlock.GBlockData;
import co.uk.RandomPanda30.GBlock.Configs.Config.ConfigValues;

public class PlotHandler {

	public String guild;

	public PlotHandler (String guild) {
		this.guild = guild;
	}

	public String getWorld() {
		return GBlock.getRPGFile().getConfiguration()
				.getString("Guilds." + guild + ".Plot.World");
	}

	public Location getSpawn() {
		return new Location(Bukkit.getWorld(getWorld()), getCo('X', 3),
				getCo('Y', 3), getCo('Z', 3));
	}

	public int getCo(char axis, int option) {
		return GBlock.getRPGFile().getConfiguration().getInt("Guilds." + guild
				+ ".Plot." + option + "." + Character.toUpperCase(axis));
	}

	public void createPlot(Player player) {
		boolean negative = false;

		int x = 0;
		int z = 0;

		int rx = 0;
		int rz = 0;

		Random random = new Random();
		// Add 350. 0 0 0 - Spawn

		if (!negative) {
			rx = random.nextInt(1690) + 10;
			rz = random.nextInt(1690) + 10;

			x = (700 * rx) + 350;
			z = (700 * rz) + 350;

			ArrayList<String> guilds = new ArrayList<String>();

			for (String s : GBlock.getRPGFile().getConfiguration()
					.getConfigurationSection("Guilds").getKeys(false)) {
				guilds.add(s);
			}

			for (String g : guilds) {
				if (GBlock.getRPGFile().getConfiguration()
						.contains("Guilds." + g + ".Plot")) {
					if (GBlock.getRPGFile().getConfiguration()
							.getInt("Guilds." + g + ".Plot.3.X") == x
							&& GBlock.getRPGFile().getConfiguration()
									.getInt("Guilds." + g + ".Plot.3.Z") == z) {
						player.sendMessage(
								ChatColor.translateAlternateColorCodes('&',
										"&CWe couldn't find a space to put your plot. To fix this, simply do /guild plot create again"));
						return;
					}
				}
			}

			player.teleport(
					new Location(
							Bukkit.getWorld((String) GBlock.getConfigFile()
									.getStat(ConfigValues.PLOTWORLD)),
							x, 65, z));

			Location bottom = new Location(
					Bukkit.getWorld((String) GBlock.getConfigFile()
							.getStat(ConfigValues.PLOTWORLD)),
					x + 49, 0, z + 49);
			Location top = new Location(
					Bukkit.getWorld((String) GBlock.getConfigFile()
							.getStat(ConfigValues.PLOTWORLD)),
					x - 51, 63, z - 49);

			Location spawn = new Location(Bukkit.getWorld((String) GBlock
					.getConfigFile().getStat(ConfigValues.PLOTWORLD)), x, 65,
					z);

			Plot plot = new Plot(guild, bottom, top, spawn);
			plot.dump();

			player.sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&AYou have created a new plot"));
		}
	}

	public void deletePlot(Player player) {
		Plot plot = null;
		for (Plot plot2 : GBlockData.plots) {
			if (plot2.getGuild().equals(guild)) {
				plot = plot2;
				break;
			}
		}

		if (plot != null) {
			GBlock.getRPGFile().getConfiguration()
					.set("Guilds." + guild + ".Plot", null);
			GBlock.getRPGFile().saveConfig();

			player.sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&AYou have deleted your plot"));
			player.teleport(Bukkit
					.getWorld((String) GBlock.getConfigFile()
							.getStat(ConfigValues.FALLBACKWORLD))
					.getSpawnLocation());

			/* Safe way of removing here ;P */

			if (GBlockData.plots.contains(plot)) {
				GBlockData.plots.remove(plot);
			}
		} else {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&CYou never had a plot to begin with"));
		}
	}

	public void resetPlot(Player player) {
		int x1 = getCo('X', 1);
		int y1 = getCo('Y', 1);
		int z1 = getCo('Z', 1);

		int x2 = getCo('X', 2);
		int y2 = 128;
		int z2 = getCo('Z', 2);

		World world = Bukkit.getWorld((String) GBlock.getConfigFile()
				.getStat(ConfigValues.PLOTWORLD));

		Location pos1 = new Location(world, x1, y1, z1);
		Location pos2 = new Location(world, x2, y2, z2);

		Vector max = Vector.getMaximum(pos1.toVector(), pos2.toVector());
		Vector min = Vector.getMinimum(pos1.toVector(), pos2.toVector());

		for (int i = min.getBlockX(); i <= max.getBlockX(); i++) {
			for (int j = min.getBlockY(); j <= max.getBlockY(); j++) {
				for (int k = min.getBlockZ(); k <= max.getBlockZ(); k++) {
					Block block = world.getBlockAt(i, j, k);

					if (j == 63) {
						block.setType(Material.GRASS);
					} else if (j < 63) {
						block.setType(Material.AIR);
					} else if (j == 128) {
						break;
					} else {
						block.setType(Material.STONE);
					}
				}
			}
		}

		for (String names : GBlock.getRPGFile().getConfiguration()
				.getConfigurationSection("Guilds." + guild + ".Players")
				.getKeys(false)) {
			if (Bukkit.getPlayer(UUID.fromString(names)) != null
					&& Bukkit.getPlayer(UUID.fromString(names)).isOnline()) {
				Player memeber = Bukkit.getPlayer(UUID.fromString(names));
				if (memeber.getWorld().getName().toString()
						.equals(getWorld())) {
					memeber.teleport(getSpawn());
				}
			}
		}
	}

	public void sendHome(Player player) {
		player.teleport(getSpawn());
		player.sendMessage(ChatColor.translateAlternateColorCodes('&',
				"&AYou have been sent to your plot"));
	}
}