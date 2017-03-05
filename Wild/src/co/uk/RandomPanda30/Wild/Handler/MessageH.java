package co.uk.RandomPanda30.Wild.Handler;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Wild.Main;

public class MessageH {

	public static String blue = "\u00A71";
	public static String green = "\u00A7a";
	public static String red = "\u00A7c";
	public static String aqua = "\u00A7b";
	public static String orange = "\u00A76";
	public static String white = "\u00A7f";

	public static String noPermission = red
			+ "You don't have permission to do this!";
	public static String loadingBasics = white + "[" + aqua + "Wild" + white
			+ "] " + green + "Loading!";
	public static String closingBasics = white + "[" + aqua + "Wild" + white
			+ "] " + red + "Closing!";
	public static String madeBy = white + "[" + aqua + "Wild" + white + "]"
			+ aqua + " Made by RandomPanda30";

	public static String randomSign1 = white + "[" + aqua + "Random" + white
			+ "]";
	public static String randomSign2 = orange + "Teleport to";
	public static String randomSign3 = orange + "a random";
	public static String randomSign4 = orange + "location";

	public static String applyMessage = aqua + "Apply: " + orange
			+ "http://www.knightville.co.uk/apply";
	public static String donateMessage = aqua + "Donate: " + orange
			+ "http://www.knightville.co.uk/donate";
	public static String voteMessage = aqua + "Vote: " + orange
			+ "http://www.knightville.co.uk/vote.html";
	public static String fbMessage = aqua + "Facebook: " + orange
			+ "http://www.facebook.com/knightvilleserver";
	public static String twMessage = aqua + "Twitter: " + orange
			+ "http://www.twitter.com/knightvilleMC";

	public static String enterAdminMessage = red + "/ad <Message>";

	public static void sendCMDMessage(String message) {
		Bukkit.getConsoleSender().sendMessage(message);
	}

	public static void sendAllPlayersMessage(String message) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.sendMessage(message);
		}
	}

	public static void sendAdminsMessage(String sender, String message) {
		List<String> adminNames = Main.plugin.getConfig()
				.getStringList("adminNames");
		for (String adminName : adminNames) {
			Player player = Bukkit.getPlayer(adminName);
			if (player != null) {
				if (message == null) {
					Player sent = Bukkit.getPlayer(sender);
					sent.sendMessage(MessageH.enterAdminMessage);
					return;
				}
				player.sendMessage(red + "*Admin Chat* " + white + "[" + green
						+ sender.toString() + white + "] " + message);
			} else {
				return;
			}
		}
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
