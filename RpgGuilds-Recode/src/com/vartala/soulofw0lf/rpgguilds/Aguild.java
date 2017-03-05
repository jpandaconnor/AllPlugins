package com.vartala.soulofw0lf.rpgguilds;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Aguild implements CommandExecutor {

	public RpgGuilds plugin;

	public Aguild (RpgGuilds plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (args.length != 1) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&CError, use the command like: /aguild remove <UUID> OR <Guild name>"));
			return true;
		}

		Player player = null;
		if (sender instanceof Player) {
			player = (Player) sender;
		}

		if (!(sender instanceof Player)
				|| (player != null && player.hasPermission("guilds.admin"))) {
			if (args[0].equalsIgnoreCase("remove")) {
				String object = args[1];
				if (plugin.getConfig().contains(object)) {
					plugin.getConfig().set(object, null);
					plugin.saveConfig();
					sender.sendMessage(ChatColor.translateAlternateColorCodes(
							'&', "Object: " + object
									+ " has been removed from the config"));
				} else if (plugin.getConfig().getConfigurationSection("Guilds")
						.contains(object)) {
					plugin.getConfig().set("Guilds." + object, null);
					plugin.saveConfig();
					sender.sendMessage(ChatColor.translateAlternateColorCodes(
							'&', "Guild: " + object
									+ " has been removed from the config"));
				} else {
					sender.sendMessage(ChatColor.translateAlternateColorCodes(
							'&', "&CThis object does not exist in the config"));
				}
			}
		} else {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&CYou cannot do this command"));
		}
		return true;
	}
}
