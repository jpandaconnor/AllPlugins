package co.uk.RandomPanda30.RPG.Commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import co.uk.RandomPanda30.RPG.RPG;
import co.uk.RandomPanda30.RPG.Files.Messages.Enums.MessagesValues;
import co.uk.RandomPanda30.RPG.Handlers.GuildH;

public class CMD_hq implements CommandExecutor {

	public RPG plugin;

	public CMD_hq (RPG plugin) {
		this.plugin = plugin;
	}

	public GuildH gh = new GuildH(plugin);

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (!(sender instanceof Player)) {
			plugin.sendMessage((String) plugin.getRPGConfig().getMessagesMap()
					.getStat(MessagesValues.ONLYCONSOLE), null);
			return true;
		}

		if (args.length <= 0) {
			return true;
		}

		final Player player = (Player) sender;
		final UUID uuid = player.getUniqueId();
		final Location location = player.getLocation();
		String guildName = "";
		String guildRank = "";

		if (!gh.inGuild(uuid)) {
			plugin.sendMessage((String) plugin.getRPGConfig().getMessagesMap()
					.getStat(MessagesValues.NOTINAGUILD), player);
			return true;
		}

		guildName = gh.getGuild(uuid);
		guildRank = gh.getRank(uuid);

		if (args[0].equalsIgnoreCase("set")) {

			String leader = (String) plugin.getRPGValues().getConfigC()
					.get("Guilds." + guildName + ".DefTerm.Leader");
			if (!guildRank.equalsIgnoreCase(leader)) {
				// player.sendMessage("Only a guild leader can set the guild headquarters!");
				return true;
			}

			for (String guild : plugin.getRPGValues().getConfigC()
					.getConfigurationSection("Guilds").getKeys(false)) {
				if (plugin.getRPGValues().getConfigC()
						.getConfigurationSection("Guilds." + guild + ".HQ") != null) {
					double x = plugin.getRPGValues().getConfigC()
							.getDouble("Guilds." + guild + ".HQ.X");
					double y = plugin.getRPGValues().getConfigC()
							.getDouble("Guilds." + guild + ".HQ.Y");
					double z = plugin.getRPGValues().getConfigC()
							.getDouble("Guilds." + guild + ".HQ.Z");
					String world = plugin.getRPGValues().getConfigC()
							.getString("Guilds." + guild + ".HQ.World");
					if (player.getWorld().getName().equalsIgnoreCase(world)) {
						Location gloc = new Location(Bukkit.getWorld(world), x,
								y, z);
						if (location.distance(gloc) <= 100.0D) {
							// player.sendMessage("This is too close to the guild headquarters of "
							// + guild + ".");
							return true;
						}
					}
				}
			}

			double X = location.getX();
			double Y = location.getY();
			double Z = location.getZ();
			String world = location.getWorld().getName();

			plugin.getRPGValues().getConfigC()
					.set("Guilds." + guildName + ".HQ.X", Double.valueOf(X));
			plugin.getRPGValues().getConfigC()
					.set("Guilds." + guildName + ".HQ.Y", Double.valueOf(Y));
			plugin.getRPGValues().getConfigC()
					.set("Guilds." + guildName + ".HQ.Z", Double.valueOf(Z));
			plugin.getRPGValues().getConfigC()
					.set("Guilds." + guildName + ".HQ.World", world);
			plugin.saveConfig();
			// player.sendMessage("Guild Headquarters successfully saved!");
			return true;

		}

		if (args[0].equalsIgnoreCase("tp")) {
			if (!plugin.getRPGValues().getConfigC()
					.contains(player.getUniqueId().toString())) {
				plugin.sendMessage((String) plugin.getRPGConfig()
						.getMessagesMap().getStat(MessagesValues.NOTINAGUILD),
						player);
				return true;
			}

			if (plugin.getRPGValues().getConfigC()
					.getConfigurationSection("Guilds." + guildName + ".HQ") == null) {
				player.sendMessage("Your guild does not have a Headquarters set!");
				return true;
			}

			double x = plugin.getRPGValues().getConfigC()
					.getDouble("Guilds." + guildName + ".HQ.X");
			double y = plugin.getRPGValues().getConfigC()
					.getDouble("Guilds." + guildName + ".HQ.Y");
			double z = plugin.getRPGValues().getConfigC()
					.getDouble("Guilds." + guildName + ".HQ.Z");
			String world = (String) plugin.getRPGValues().getConfigC()
					.get("Guilds." + guildName + ".HQ.World");
			final Location gloc = new Location(Bukkit.getWorld(world), x, y, z);

			new BukkitRunnable() {
				Integer i = Integer.valueOf(5);

				public void run() {
					player.sendMessage("&2HQ teleport timer: &4" + this.i);
					if (this.i.intValue() == 0) {
						player.teleport(gloc);
						cancel();
						return;
					}

					if (player.getLocation().getX() != location.getX()) {
						// p.sendMessage("You must stand in one place to enter your guild hall!");
						cancel();
						return;
					}

					if (player.getLocation().getY() != location.getY()) {
						// p.sendMessage("You must stand in one place to enter your guild hall!");
						cancel();
						return;
					}

					if (player.getLocation().getZ() != location.getZ()) {
						// p.sendMessage("You must stand in one place to enter your guild hall!");
						cancel();
						return;
					}

					this.i = Integer.valueOf(this.i.intValue() - 1);
				}
			}.runTaskTimer(plugin, 0L, 20L);
			return true;
		}
		return true;
	}
}