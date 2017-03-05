package co.uk.RandomPanda30.ItemHandlerPlus.Methods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.ItemHandlerPlus.B;

public class Y {
	
	public static void sendMessage(String string, Player player) {
		if (player == null) {
			Bukkit.getConsoleSender().sendMessage(formatMessage(string));
		} else {
			player.sendMessage(formatMessage(string));
		}
	}

	public static void broadcastMessage(String string) {
		Bukkit.broadcastMessage(formatMessage(string));
	}

	public static String formatMessage(String string) {
		return string.replaceAll("%TAG", (String) B.messagesC.get("TAG"))
				.replaceAll("%N", (String) B.messagesC.get("NORMAL"))
				.replaceAll("%W", (String) B.messagesC.get("WARNING"))
				.replaceAll("%E", (String) B.messagesC.get("ERROR"))
				.replaceAll("%A", (String) B.messagesC.get("ARG"))
				.replaceAll("%H", (String) B.messagesC.get("HEADER"))
				.replaceAll("%G", (String) B.messagesC.get("ON"))
				.replaceAll("%B", (String) B.messagesC.get("OFF"))
				.replaceAll("(&([a-fk-or0-9]))", "\u00A7$2")
				.replaceAll("&u", "\n");
	}

	public static String buildMessage(String[] input, int startArgument) {
		if (input.length - startArgument <= 0) {
			return null;
		}

		StringBuilder sb = new StringBuilder(input[startArgument]);
		for (int i = ++startArgument; i < input.length; i++) {
			sb.append(' ').append(input[i]);
		}
		return sb.toString();
	}
}