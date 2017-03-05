package co.uk.RandomPanda30.ExpIntercept.Methods;

import java.util.Calendar;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.ExpIntercept.ExpInterceptData;
import co.uk.RandomPanda30.ExpIntercept.Boosters.Booster;

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
				.replaceAll("%TAG",
						(String) ExpInterceptData.messagesC.get("TAG"))
				.replaceAll("%N",
						(String) ExpInterceptData.messagesC.get("NORMAL"))
				.replaceAll("%W",
						(String) ExpInterceptData.messagesC.get("WARNING"))
				.replaceAll("%E",
						(String) ExpInterceptData.messagesC.get("ERROR"))
				.replaceAll("%A",
						(String) ExpInterceptData.messagesC.get("ARG"))
				.replaceAll("%H",
						(String) ExpInterceptData.messagesC.get("HEADER"))
				.replaceAll("%G", (String) ExpInterceptData.messagesC.get("ON"))
				.replaceAll("%B",
						(String) ExpInterceptData.messagesC.get("OFF"))
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

	public static String compileBoosterInformation(UUID uuid, String name,
			int time, int multiplier, long startingTime) {
		return uuid.toString() + "=" + name + "=" + Integer.toString(time) + "="
				+ Integer.toString(multiplier) + "="
				+ Long.toString(startingTime);
	}

	public static String[] decompileBoosterInformation(String information) {
		String[] split = information.split("=");
		return split;
	}

	public static String compileDumpInformation(Booster booster,
			long currentTime) {
		return compileBoosterInformation(booster.getUUID(), booster.getName(),
				booster.getTime(), booster.getMulti(), booster.getStart()) + "="
				+ Long.toString(Calendar.getInstance().getTimeInMillis());
	}

	public static String[] decompileDumpInformation(String information) {
		String[] split = information.split("=");
		return split;
	}
}