package co.uk.RandomPanda30.Murge.Methods;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Values.Enums.MessagesValues;

@SuppressWarnings("unchecked")
// Unchecked here as we know that the arraylist is a safe cast!
public class StringMethods {

	public static void sendMessage(String string, Player player) {
		if (player == null) {
			Bukkit.getConsoleSender().sendMessage(formatMessage(string));
		} else {
			player.sendMessage(formatMessage(string));
		}
	}
	
	public static void sendUnknownMessage(String string, CommandSender sender) {
		sender.sendMessage(formatMessage(string));
	}

	public static void broadcastMessage(String string) {
		Bukkit.broadcastMessage(formatMessage(string));
	}

	public static String formatMessage(String string) {
		return string
				.replaceAll("%TAG",
						(String) Murge.mMap.getStat(MessagesValues.TAG))
				.replaceAll("%N",
						(String) Murge.mMap.getStat(MessagesValues.NORMAL))
				.replaceAll("%W",
						(String) Murge.mMap.getStat(MessagesValues.WARNING))
				.replaceAll("%E",
						(String) Murge.mMap.getStat(MessagesValues.ERROR))
				.replaceAll("%A",
						(String) Murge.mMap.getStat(MessagesValues.ARG))
				.replaceAll("%H",
						(String) Murge.mMap.getStat(MessagesValues.HEADER))
				.replaceAll("%G",
						(String) Murge.mMap.getStat(MessagesValues.ON))
				.replaceAll("%B",
						(String) Murge.mMap.getStat(MessagesValues.OFF))
				.replaceAll("%T",
						(String) Murge.mMap.getStat(MessagesValues.TEXT))
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
		for (String s : (ArrayList<String>) Murge.mMap
				.getStat(MessagesValues.SM)) {
			sendMessage(s, null);
		}
	}

	public static void shutDownMessages() {
		for (String s : (ArrayList<String>) Murge.mMap
				.getStat(MessagesValues.SHM)) {
			sendMessage(s, null);
		}
	}

	public static String convertTicksToTimeString(int ticks) {
		String finalTime = "";
		long hours = 0;
		long minutes = 0;
		long seconds = 0;

		hours = ticks / 3600;
		minutes = (ticks % 3600) / 60;
		seconds = ticks % 60;

		if (hours != 0) {
			finalTime += " %A" + hours + "%Nh";
		}

		if (minutes != 0) {
			finalTime += " %A" + minutes + "%Nm";
		}

		if (seconds != 0) {
			finalTime += " %A" + seconds + "%Ns";
		}
		return finalTime;
	}

	public static void sendMessageToWorldPlayers(String message) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (player.getWorld().equals(MurgeData.getWorld())) {
				player.sendMessage(formatMessage(message));
			}
		}
	}

	public static void sendMessageToOPS(String message) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (player.isOp()) {
				player.sendMessage(formatMessage(message));
			}
		}
	}
}