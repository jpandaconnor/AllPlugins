package co.uk.RandomPanda30.GBlock.Plot;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import co.uk.RandomPanda30.GBlock.GBlock;
import co.uk.RandomPanda30.GBlock.GBlockData;

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

		addToList();
	}

	public void dump() {
		GBlock.getRPGFile().getConfiguration()
				.set("Guilds." + guild + ".Plot.World", world);

		GBlock.getRPGFile().getConfiguration()
				.set("Guilds." + guild + ".Plot.1.X", (int) p1.getX());
		GBlock.getRPGFile().getConfiguration()
				.set("Guilds." + guild + ".Plot.2.X", (int) p2.getX());
		GBlock.getRPGFile().getConfiguration()
				.set("Guilds." + guild + ".Plot.3.X", (int) spawn.getX());

		GBlock.getRPGFile().getConfiguration()
				.set("Guilds." + guild + ".Plot.1.Y", (int) p1.getY());
		GBlock.getRPGFile().getConfiguration()
				.set("Guilds." + guild + ".Plot.2.Y", (int) p2.getY());
		GBlock.getRPGFile().getConfiguration()
				.set("Guilds." + guild + ".Plot.3.Y", (int) spawn.getY());

		GBlock.getRPGFile().getConfiguration()
				.set("Guilds." + guild + ".Plot.1.Z", (int) p1.getZ());
		GBlock.getRPGFile().getConfiguration()
				.set("Guilds." + guild + ".Plot.2.Z", (int) p2.getZ());
		GBlock.getRPGFile().getConfiguration()
				.set("Guilds." + guild + ".Plot.3.Z", (int) spawn.getZ());

		GBlock.getRPGFile().saveConfig();
	}

	public static void load() {
		for (String s : GBlock.getRPGFile().getConfiguration()
				.getConfigurationSection("Guilds").getKeys(false)) {
			if (GBlock.getRPGFile().getConfiguration()
					.contains("Guilds." + s + ".Plot")) {
				PlotHandler ph = new PlotHandler(s);
				new Plot(s, new Location(Bukkit.getWorld(ph.getWorld()),
						ph.getCo('X', 1), ph.getCo('Y', 1), ph.getCo('Z', 1)),
						new Location(Bukkit.getWorld(ph.getWorld()),
								ph.getCo('X', 2), ph.getCo('Y', 2),
								ph.getCo('Z', 2)),
						new Location(Bukkit.getWorld(ph.getWorld()),
								ph.getCo('X', 3), ph.getCo('Y', 3),
								ph.getCo('Z', 3)));
			}
		}
	}

	private void addToList() {
		GBlockData.plots.add(this);
	}

	public String getGuild() {
		return guild;
	}
}