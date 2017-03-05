package com.ediTv2.Basics.Text;

public class TextH {

	/**
	 * To make the code a lot cleaner, we can put different strings of text in
	 * here. It is also a lot easier to change a bit of text if it doesn't
	 * appear right ;3
	 */

	/**
	 * Colours: I prefer using colour codes such as
	 * 
	 * \u00A71 (Blue)
	 * 
	 * I cannot do the funny S symbol because I am on Linux ;3
	 * 
	 */

	/**
	 * I don't normally do this for colours and I suggest you don't either but
	 * it saves some time ;3
	 */

	public static String aqua = "\u00A7b";
	public static String white = "\u00A7f";
	public static String lime = "\u00A7a";
	public static String red = "\u00A7c";

	// Making a tag is pretty cool
	public static String tag = white + "[" + aqua + "Basics" + white + "] ";

	public static String enabled = tag + lime + "is being enabled";
	public static String disabled = tag + red + "is being disabled";

	public static String serverName = "SERVERNAME";

	public static String welcome = aqua + "Welcome to " + serverName;

	public static String noDropItems = tag + red
			+ "You are not allowed to drop items in this world!";

	public static String worldError = tag + red
			+ "Error, something went wrong!";

	public static String noPerm = tag + red
			+ "You don't have permission to do this!";

	public static String alreadyMute = tag + red + "The chat is already muted";
	public static String alreadyUnmute = tag + red
			+ "The chat is already unmuted";

	public static String chatMute = tag + white + "Chat has been disabled!";
	public static String chatUnmute = tag + white + "Chat has been enabled!";

	public static String muteSyntax = tag + lime + "/mutechat";
	public static String unmuteSyntax = tag + lime + "/unmutechat";
	
	public static String needClass = tag + red + "A class for a command is missing somewhere!";

}
