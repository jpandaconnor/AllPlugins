package co.uk.RandomPanda30.LandProtect.Handlers;

import org.bukkit.Bukkit;

public class TextH {

	public static String blue = "\u00A71";
	public static String green = "\u00A7a";
	public static String red = "\u00A7c";
	public static String aqua = "\u00A7b";
	public static String orange = "\u00A76";
	public static String white = "\u00A7f";

	public static String noPermission = red
			+ "You don't have permission to do this!";
	public static String loadingBasics = white + "[" + aqua + "LandProtect+"
			+ white + "] " + green + "Loading!";
	public static String closingBasics = white + "[" + aqua + "LandProtect+"
			+ white + "] " + red + "Closing!";
	public static String madeBy = white + "[" + aqua + "LandProtect+" + white
			+ "]" + aqua + " Made by RandomPanda30"; // +

	public static void sendOwnerMessage(String message) {
		
	}

	public static void sendConsoleMessage(String message) {
		Bukkit.getConsoleSender().sendMessage(message);
	}

}
