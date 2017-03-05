package com.vartala.soulofw0lf.rpgguilds;

import java.util.Iterator;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class guildslist implements CommandExecutor {

	RpgGuilds Rpgg;

	public guildslist (RpgGuilds rpgg) {
		this.Rpgg = rpgg;
	}

	@SuppressWarnings("rawtypes")
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player = (Player) sender;
		Integer i = Integer.valueOf(0);
		Iterator localIterator2;
		for (Iterator localIterator1 = this.Rpgg.getConfig()
				.getConfigurationSection("Guilds").getKeys(false).iterator(); localIterator1
				.hasNext(); localIterator2.hasNext()) {
			String guilds = (String) localIterator1.next();
			i = Integer.valueOf(0);
			localIterator2 = this.Rpgg.getConfig()
					.getConfigurationSection("Guilds." + guilds + ".Players")
					.getKeys(false).iterator();
			String players = (String) localIterator2.next();
			if (players != null) {
				i = Integer.valueOf(i.intValue() + 1);
			}

			if (i.intValue() == 1) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&e" + guilds + ": &2" + i + " Player."));
			} else {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&e" + guilds + ": &2" + i + " Players."));
			}
		}
		return true;
	}
}