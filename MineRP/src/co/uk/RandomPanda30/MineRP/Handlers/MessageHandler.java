package co.uk.RandomPanda30.MineRP.Handlers;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.MineRP.MineRPData;

@SuppressWarnings("unchecked")
// Unchecked here as we know that the arraylist is a safe cast!
public class MessageHandler {

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
				.replaceAll("%TAG", (String) MineRPData.messagesC.get("TAG"))
				.replaceAll("%N", (String) MineRPData.messagesC.get("NORMAL"))
				.replaceAll("%W", (String) MineRPData.messagesC.get("WARNING"))
				.replaceAll("%E", (String) MineRPData.messagesC.get("ERROR"))
				.replaceAll("%A", (String) MineRPData.messagesC.get("ARG"))
				.replaceAll("%H", (String) MineRPData.messagesC.get("HEADER"))
				.replaceAll("%G", (String) MineRPData.messagesC.get("ON"))
				.replaceAll("%B", (String) MineRPData.messagesC.get("OFF"))
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

	public static void startupMessages() {
		for (String s : (ArrayList<String>) MineRPData.messagesC.get("SM")) {
			sendMessage(s, null);
		}
	}

	public static void shutDownMessages() {
		for (String s : (ArrayList<String>) MineRPData.messagesC.get("SHM")) {
			sendMessage(s, null);
		}
	}
}
