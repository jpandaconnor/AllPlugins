package co.uk.RandomPanda30.CityRP.Resources;

import org.bukkit.Bukkit;

import co.uk.RandomPanda30.CityRP.CityRPData;
import co.uk.RandomPanda30.CityRP.Configs.Enums.Messages;
import co.uk.RandomPanda30.CityRP.Objects.Player;

public class MessageUtil {

	public static void sendMessage(String message, Player player) {
		if (message == null)
			return;

		if (player == null) {
			Bukkit.getConsoleSender().sendMessage(format(message));
		} else {
			player.getPlayer().sendMessage(format(message));
		}
	}

	public static void broadcastMessage(String message) {
		if (message == null)
			return;

		for (Player player : CityRPData.players) {
			sendMessage(message, player);
		}
	}

	public static String format(String string) {
		return string.replaceAll("%TAG", (String) Messages.TAG.value)
				.replaceAll("%N", (String) Messages.NORMAL.value)
				.replaceAll("%W", (String) Messages.WARNING.value)
				.replaceAll("%E", (String) Messages.ERROR.value)
				.replaceAll("%A", (String) Messages.ARG.value)
				.replaceAll("%H", (String) Messages.HEADER.value)
				.replaceAll("%G", (String) Messages.GOOD.value)
				.replaceAll("%B", (String) Messages.BAD.value)
				.replaceAll("(&([a-fk-or0-9]))", "\u00A7$2")
				.replaceAll("&u", "\n");
	}
}