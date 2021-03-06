package com.vartala.soulofw0lf.rpgguilds;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class oHandler implements CommandExecutor {

	RpgGuilds Rpgg;

	public oHandler (RpgGuilds rpgg) {
		this.Rpgg = rpgg;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player = (Player) sender;
		String guildn = this.Rpgg.getConfig().getString(
				player.getUniqueId().toString() + ".Guild.Name");
		String grank = this.Rpgg.getConfig().getString(
				"Guilds." + guildn + ".Players."
						+ player.getUniqueId().toString() + ".Rank");
		if (!this.Rpgg.getConfig().contains(player.getUniqueId().toString())) {
			player.sendMessage("You are not even in a guild? how can you expect to talk in one?");
			return true;
		}

		if (!this.Rpgg.getConfig().getBoolean(
				"Guilds." + guildn + ".Ranks." + grank + ".Ochat")) {
			player.sendMessage("You do not have permission to talk in officer chat!");
			return true;
		}

		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			buffer.append(' ').append(args[i]);
		}
		String s = buffer.toString();

		String title = this.Rpgg.getConfig().getString(
				"Guilds." + guildn + ".Ranks." + grank + ".Title");
		for (String key : this.Rpgg.getConfig()
				.getConfigurationSection("Guilds." + guildn + ".Players")
				.getKeys(false)) {
			String prank = this.Rpgg.getConfig().getString(
					"Guilds." + guildn + ".Players." + key + ".Rank");
			if (Bukkit.getPlayer(UUID.fromString(key)) != null) {
				Player p = Bukkit.getPlayer(UUID.fromString(key));
				if (this.Rpgg.getConfig().getBoolean(
						"Guilds." + guildn + ".Ranks." + prank + ".Ochat")) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							"&F[" + title + "&F] " + player.getName() + ":&a"
									+ s));
				}
			}
		}
		return true;
	}
}