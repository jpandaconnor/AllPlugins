package co.uk.RandomPanda30.Handlers;

public class Messages {

	public static String aqua = "\u00A7b";
	public static String blue = "\u00A71";
	public static String green = "\u00A7a";
	public static String orange = "\u00A76";
	public static String white = "\u00A7f";
	public static String red = "\u00A7c";

	public static String permString = "adminplus.";

	public static String tag = aqua + "[" + white + "Admin+" + aqua + "] ";

	public static String[] startupM = { tag, green + "Starting...",
			green + "Made by: " + aqua + Methods.getAuthor(0) };
	public static String[] shutdownM = { tag, green + "Stopping...",
			green + "Made by: " + aqua + Methods.getAuthor(0) };

}
