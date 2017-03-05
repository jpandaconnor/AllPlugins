package co.uk.RandomPanda30.Guilds.Plots;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import co.uk.RandomPanda30.Guilds.Data;

public class Plot {

	public String guild;

	public String world;

	public Location p1;
	public Location p2;
	public Location spawn;

	public Plot (String guild, Location p1, Location p2, Location spawn) {
		this.guild = guild;

		this.p1 = p1;
		this.p2 = p2;
		this.spawn = spawn;

		this.world = p1.getWorld().getName();
	}

	public void dump() {
		try {
			Data.mysql.updateSQL("UPDATE guilds SET plot_world = '" + world
					+ "' WHERE guild = '" + guild + "'");

			Data.mysql.updateSQL("UPDATE guilds SET plot_1_x = "
					+ (int) p1.getX() + " WHERE guild = '" + guild + "'");
			Data.mysql.updateSQL("UPDATE guilds SET plot_2_x = "
					+ (int) p2.getX() + " WHERE guild = '" + guild + "'");
			Data.mysql.updateSQL("UPDATE guilds SET plot_3_x = "
					+ (int) spawn.getX() + " WHERE guild = '" + guild + "'");

			Data.mysql.updateSQL("UPDATE guilds SET plot_1_y = "
					+ (int) p1.getY() + " WHERE guild = '" + guild + "'");
			Data.mysql.updateSQL("UPDATE guilds SET plot_2_y = "
					+ (int) p2.getY() + " WHERE guild = '" + guild + "'");
			Data.mysql.updateSQL("UPDATE guilds SET plot_3_y = "
					+ (int) spawn.getY() + " WHERE guild = '" + guild + "'");

			Data.mysql.updateSQL("UPDATE guilds SET plot_1_z = "
					+ (int) p1.getZ() + " WHERE guild = '" + guild + "'");
			Data.mysql.updateSQL("UPDATE guilds SET plot_2_z = "
					+ (int) p2.getZ() + " WHERE guild = '" + guild + "'");
			Data.mysql.updateSQL("UPDATE guilds SET plot_3_z = "
					+ (int) spawn.getZ() + " WHERE guild = '" + guild + "'");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static void load() {
		ArrayList<String> guilds = new ArrayList<>();

		ResultSet guildsList;
		try {
			guildsList = Data.mysql.querySQL("SELECT guild FROM guilds");
			if (guildsList.next()) {
				guilds.add(guildsList.getString("guild"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		ResultSet hasPlot;
		for (String s : guilds) {

			boolean b = false;

			try {
				hasPlot = Data.mysql.querySQL(
						"SELECT * FROM guilds WHERE guild = '" + s + "'");
				while (hasPlot.next()) {
					String pworld = hasPlot.getString("plot_world");
					if (!pworld.equals("NULL")) {
						b = true;
					}
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

			if (b) {
				PlotHandler ph = new PlotHandler(s);
				new Plot(s, new Location(Bukkit.getWorld(ph.getWorld()),
						ph.getCo('x', 1), ph.getCo('y', 1), ph.getCo('z', 1)),
						new Location(Bukkit.getWorld(ph.getWorld()),
								ph.getCo('x', 2), ph.getCo('y', 2),
								ph.getCo('z', 2)),
						new Location(Bukkit.getWorld(ph.getWorld()),
								ph.getCo('x', 3), ph.getCo('y', 3),
								ph.getCo('z', 3)));
			}
		}
	}

	public String getGuild() {
		return guild;
	}
}
