package co.uk.RandomPanda30.Guilds.Util.Basics;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Guilds.Config.Messages.MessagesValues;

public class MUtil {

	public static String format(String message) {
		return message.replaceAll("%TAG", (String) MessagesValues.TAG.value)
				.replaceAll("%N", (String) MessagesValues.NORMAL.value)
				.replaceAll("%W", (String) MessagesValues.WARNING.value)
				.replaceAll("%E", (String) MessagesValues.ERROR.value)
				.replaceAll("%A", (String) MessagesValues.ARG.value)
				.replaceAll("%H", (String) MessagesValues.HEADER.value)
				.replaceAll("%G", (String) MessagesValues.GOOD.value)
				.replaceAll("%B", (String) MessagesValues.BAD.value)
				.replaceAll("(&([a-fk-or0-9]))", "\u00A7$2")
				.replaceAll("&u", "\n");
	}

	public static void sendMessage(String message, Player player) {
		if (message == null)
			return;

		if (player == null) {
			Bukkit.getConsoleSender().sendMessage(format(message));
		} else {
			player.getPlayer().sendMessage(format(message));
		}
	}
}