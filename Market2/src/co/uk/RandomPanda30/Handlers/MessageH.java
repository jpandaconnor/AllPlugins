package co.uk.RandomPanda30.Handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Markets.Main;

public class MessageH {
	
	public MessageH(Main plugin) {
		Main.plugin = plugin;
	}

	public static String blue = "\u00A71";
	public static String green = "\u00A7a";
	public static String red = "\u00A7c";
	public static String aqua = "\u00A7b";
	public static String orange = "\u00A76";
	public static String white = "\u00A7f";

	public static String tag = white + "[" + aqua + Main.desc.getName() + white
			+ "] ";

	public static String noPermission = red
			+ "You don't have permission to do this!";
	public static String loadingMarkets = white + "[" + aqua
			+ Main.desc.getName() + white + "] " + green + "Loading!";
	public static String closingMarkets = white + "[" + aqua
			+ Main.desc.getName() + white + "] " + red + "Closing!";
	public static String madeBy = white + "[" + aqua + Main.desc.getName()
			+ white + "]" + aqua + " Made by RandomPanda30";

	// WAND TEXT

	public static String wandName = tag + orange + "Selection tool";

	public static String lores[] = {
			aqua + "Left click for pos " + orange + "#1",
			aqua + "Right click for pos " + orange + "#2",
			aqua + "After making the " + orange + "2" + aqua + " selections, ",
			aqua + "execute command", aqua + "/" + orange + "mk create" };

	public static void sendCMDMessage(String message) {
		Bukkit.getConsoleSender().sendMessage(message);
	}

	public static void sendAllPlayersMessage(String message) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.sendMessage(message);
		}
	}

	public static void sendCoordinates(Player player, double x, double y,
			double z, int pos) {
		if (pos == 1) {
			player.sendMessage(tag + aqua + "Position 1: " + "X: " + orange + x
					+ " " + aqua + "Y: " + orange + y + " " + aqua + "Z: "
					+ orange + z);
		}

		if (pos == 2) {
			player.sendMessage(tag + aqua + "Position 2: " + "X: " + orange + x
					+ " " + aqua + "Y: " + orange + y + " " + aqua + "Z: "
					+ orange + z);
		}
	}

}
