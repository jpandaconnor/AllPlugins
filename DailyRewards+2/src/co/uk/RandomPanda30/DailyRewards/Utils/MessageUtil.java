package co.uk.RandomPanda30.DailyRewards.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.DailyRewards.DR;

public class MessageUtil {

	public static String format(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	public static void sendMessage(String message, Player player) {
		if (player == null) {
			Bukkit.getConsoleSender()
					.sendMessage(format(DR.getTag() + message));
		} else {
			player.sendMessage(format(DR.getTag() + message));
		}
	}
}