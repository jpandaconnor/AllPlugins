package co.uk.RandomPanda30.ERHub.Methods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.ERHub.ERHubData;

public class StringMethods {

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
		return string
				.replaceAll("%TAG", (String) ERHubData.messagesC.get("TAG"))
				.replaceAll("%N", (String) ERHubData.messagesC.get("NORMAL"))
				.replaceAll("%W", (String) ERHubData.messagesC.get("WARNING"))
				.replaceAll("%E", (String) ERHubData.messagesC.get("ERROR"))
				.replaceAll("%A", (String) ERHubData.messagesC.get("ARG"))
				.replaceAll("%H", (String) ERHubData.messagesC.get("HEADER"))
				.replaceAll("%G", (String) ERHubData.messagesC.get("ON"))
				.replaceAll("%B", (String) ERHubData.messagesC.get("OFF"))
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