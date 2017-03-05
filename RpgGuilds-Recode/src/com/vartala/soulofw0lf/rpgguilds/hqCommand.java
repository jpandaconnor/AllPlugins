package com.vartala.soulofw0lf.rpgguilds;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class hqCommand implements CommandExecutor {

	RpgGuilds Rpgg;

	public hqCommand (RpgGuilds rpgg) {
		this.Rpgg = rpgg;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (args.length <= 0) {
			return false;
		}
		if (args[0].equalsIgnoreCase("set")) {
			Player player = (Player) sender;

			List<String> worlds = Rpgg.getConfig().getStringList("NoHQWorlds");
			for (String s : worlds) {
				if (s != null) {
					if (player.getWorld().getName().equals(s)) {
						player.sendMessage(
								ChatColor.translateAlternateColorCodes('&',
										"&CYou cannot use this command in this world!"));
						return true;
					}
				}
			}

			if (player.getWorld().getName().equals("plots")) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&CYou cannot use this command in this world!"));
				return true;
			}

			if (!this.Rpgg.getConfig()
					.contains(player.getUniqueId().toString())) {
				player.sendMessage(
						"You are not even in a guild? how can you expect to create a guild Headquarters?");
				return true;
			}

			String guildn = this.Rpgg.getConfig()
					.getString(player.getUniqueId().toString() + ".Guild.Name");
			String grank = this.Rpgg.getConfig().getString("Guilds." + guildn
					+ ".Players." + player.getUniqueId().toString() + ".Rank");
			String leader = this.Rpgg.getConfig()
					.getString("Guilds." + guildn + ".DefTerm.Leader");
			if (!grank.equalsIgnoreCase(leader)) {
				player.sendMessage(
						"Only a guild leader can set the guild headquarters!");
				return true;
			}

			Location loc = player.getLocation();
			for (String guild : this.Rpgg.getConfig()
					.getConfigurationSection("Guilds").getKeys(false)) {
				if (this.Rpgg.getConfig().getConfigurationSection(
						"Guilds." + guild + ".HQ") != null) {
					double x = this.Rpgg.getConfig()
							.getDouble("Guilds." + guild + ".HQ.X");
					double y = this.Rpgg.getConfig()
							.getDouble("Guilds." + guild + ".HQ.Y");
					double z = this.Rpgg.getConfig()
							.getDouble("Guilds." + guild + ".HQ.Z");
					String world1 = this.Rpgg.getConfig()
							.getString("Guilds." + guild + ".HQ.World");
					if (player.getWorld().getName().equalsIgnoreCase(world1)) {
						Location gloc = new Location(Bukkit.getWorld(world1), x,
								y, z);
						if (loc.distance(gloc) <= 100.0D) {
							player.sendMessage(
									"This is too close to the guild headquarters of "
											+ guild + ".");
							return true;
						}
					}
				}
			}

			double X = loc.getX();
			double Y = loc.getY();
			double Z = loc.getZ();
			String world = loc.getWorld().getName();
			this.Rpgg.getConfig().set("Guilds." + guildn + ".HQ.X",
					Double.valueOf(X));
			this.Rpgg.getConfig().set("Guilds." + guildn + ".HQ.Y",
					Double.valueOf(Y));
			this.Rpgg.getConfig().set("Guilds." + guildn + ".HQ.Z",
					Double.valueOf(Z));
			this.Rpgg.getConfig().set("Guilds." + guildn + ".HQ.World", world);
			this.Rpgg.saveConfig();
			player.sendMessage("Guild Headquarters successfully saved!");
			return true;
		}

		if (args[0].equalsIgnoreCase("tp")) {
			Player player = (Player) sender;
			if (!this.Rpgg.getConfig()
					.contains(player.getUniqueId().toString())) {
				player.sendMessage(
						"You are not even in a guild? how can you expect to create a guild Headquarters?");
				return true;
			}

			String guild = this.Rpgg.getConfig()
					.getString(player.getUniqueId().toString() + ".Guild.Name");
			if (this.Rpgg.getConfig().getConfigurationSection(
					"Guilds." + guild + ".HQ") == null) {
				player.sendMessage(
						"Your guild does not have a Headquarters set!");
				return true;
			}

			double x = this.Rpgg.getConfig()
					.getDouble("Guilds." + guild + ".HQ.X");
			double y = this.Rpgg.getConfig()
					.getDouble("Guilds." + guild + ".HQ.Y");
			double z = this.Rpgg.getConfig()
					.getDouble("Guilds." + guild + ".HQ.Z");
			String world1 = this.Rpgg.getConfig()
					.getString("Guilds." + guild + ".HQ.World");
			final Location gloc = new Location(Bukkit.getWorld(world1), x, y,
					z);
			final Location ploc = player.getLocation();
			final Player p = player;
			new BukkitRunnable() {
				Integer i = Integer.valueOf(5);

				public void run() {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							"&2HQ teleport timer: &4" + this.i));
					if (this.i.intValue() == 0) {
						p.teleport(gloc);
						cancel();
						return;
					}

					if (p.getLocation().getX() != ploc.getX()) {
						p.sendMessage(
								"You must stand in one place to enter your guild hall!");
						cancel();
						return;
					}

					if (p.getLocation().getY() != ploc.getY()) {
						p.sendMessage(
								"You must stand in one place to enter your guild hall!");
						cancel();
						return;
					}

					if (p.getLocation().getZ() != ploc.getZ()) {
						p.sendMessage(
								"You must stand in one place to enter your guild hall!");
						cancel();
						return;
					}

					this.i = Integer.valueOf(this.i.intValue() - 1);
				}
			}.runTaskTimer(this.Rpgg, 0L, 20L);
			return true;
		}
		return false;
	}
}