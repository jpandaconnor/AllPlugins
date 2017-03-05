package co.uk.RandomPanda30.Guilds.Plots;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import co.uk.RandomPanda30.Guilds.Data;
import co.uk.RandomPanda30.Guilds.Config.Config.ConfigValues;

public class PlotHandler {

	public String guild;

	public PlotHandler (String guild) {
		this.guild = guild;
	}

	public String getWorld() {
		return (String) ConfigValues.PLOTWORLD.value;
	}

	public Location getSpawn() {
		return new Location(Bukkit.getWorld(getWorld()), getCo('x', 3),
				getCo('y', 3), getCo('z', 3));
	}

	public int getCo(char axis, int option) {
		String s = "SELECT plot_" + option + "_" + axis
				+ " FROM guilds WHERE guild = '" + guild + "'";
		int i = 0;
		ResultSet set;
		try {
			set = Data.mysql.querySQL(s);
			if (set.next()) {
				i = set.getInt("plot_" + option + "_" + axis);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return i;
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

			ArrayList<String> guilds = new ArrayList<>();

			ResultSet guildsList;

			try {
				guildsList = Data.mysql
						.querySQL("SELECT * FROM guilds.guilds");
				if (guildsList.next()) {
					guilds.add(guildsList.getString("guild"));
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

			for (String g : guilds) {

				int gxi = 0;
				int gzi = 0;

				ResultSet gx;
				ResultSet gz;

				try {
					gx = Data.mysql.querySQL(
							"SELECT plot_3_x FROM guilds.guilds WHERE guild = '"
									+ g + "'");
					if (gx.next()) {
						gxi = gx.getInt("plot_3_x");
					}
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}

				try {
					gz = Data.mysql.querySQL(
							"SELECT plot_3_z FROM guilds.guilds WHERE guild = '"
									+ g + "'");
					if (gz.next()) {
						gzi = gz.getInt("plot_3_z");
					}
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}

				if (x == gxi && z == gzi) {
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&',
							"&CWe couldn't find a space to put your plot. To fix this, simply do /guild plot create again"));
					return;
				}
			}
		}

		player.teleport(new Location(
				Bukkit.getWorld((String) ConfigValues.PLOTWORLD.value), x, 65,
				z));

		Location bottom = new Location(
				Bukkit.getWorld((String) ConfigValues.PLOTWORLD.value), x + 49,
				0, z + 49);
		Location top = new Location(
				Bukkit.getWorld((String) ConfigValues.PLOTWORLD.value), x - 51,
				63, z - 49);

		Location spawn = new Location(
				Bukkit.getWorld((String) ConfigValues.PLOTWORLD.value), x, 65,
				z);

		Plot plot = new Plot(guild, bottom, top, spawn);
		plot.dump();

		player.sendMessage(ChatColor.translateAlternateColorCodes('&',
				"&AYou have created a new plot"));

	}

	public void deletePlot(Player player) {
		Plot plot = null;
		for (Plot plot2 : Data.plots) {
			if (plot2.getGuild().equals(guild)) {
				plot = plot2;
				break;
			}
		}

		if (plot != null) {
			try {
				Data.mysql.updateSQL("UPDATE guilds SET plot_world = '-', "
						+ "plot_1_x = '-', plot_2_x = '-', plot_3_x = '-', "
						+ "plot_1_y = '-', plot_2_y = '-', plot_3_y = '-', "
						+ "plot_1_z = '-', plot_2_z = '-', plot_3_z = '-' WHERE guild = '"
						+ guild + "'");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

			player.sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&AYou have deleted your plot"));
			player.teleport(
					Bukkit.getWorld((String) ConfigValues.FALLBACKWORLD.value)
							.getSpawnLocation());

			/* Safe way of removing here ;P */

			if (Data.plots.contains(plot)) {
				Data.plots.remove(plot);
			}
		} else {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&CYou never had a plot to begin with"));
		}
	}

	public void resetPlot(Player player) {
		int x1 = getCo('x', 1);
		int y1 = getCo('y', 1);
		int z1 = getCo('z', 1);

		int x2 = getCo('x', 2);
		int y2 = 128;
		int z2 = getCo('z', 2);

		World world = Bukkit.getWorld((String) ConfigValues.PLOTWORLD.value);

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
	}

	public void sendHome(Player player) {
		player.teleport(getSpawn());
		player.sendMessage(ChatColor.translateAlternateColorCodes('&',
				"&AYou have been sent to your plot"));
	}
}