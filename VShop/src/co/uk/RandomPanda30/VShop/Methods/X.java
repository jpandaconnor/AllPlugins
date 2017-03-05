package co.uk.RandomPanda30.VShop.Methods;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.VShop.Files.Messages;

public class X {

	// Text class

	public static void sendMessage(String string, Player player) {
		if (player == null) {
			Bukkit.getConsoleSender().sendMessage(formatMessage(string));
		} else {
			player.sendMessage(formatMessage(string));
		}
	}

	public static String formatMessage(String string) {
		return string.replaceAll("%TAG", (String) Messages.TAG.value)
				.replaceAll("%N", (String) Messages.NORMAL.value)
				.replaceAll("%W", (String) Messages.WARNING.value)
				.replaceAll("%E", (String) Messages.ERROR.value)
				.replaceAll("%A", (String) Messages.ARG.value)
				.replaceAll("%H", (String) Messages.HEADER.value)
				.replaceAll("(&([a-fk-or0-9]))", "\u00A7$2")
				.replaceAll("&u", "\n");
	}

	public static String compileLocation(Location location) {
		String s = location.getWorld().getName().toString() + ";"
				+ Double.toString(location.getX()) + ";"
				+ Double.toString(location.getY()) + ";"
				+ Double.toString(location.getZ());
		return s;
	}

	public static Location decompileLocation(String string) {
		String[] s = string.split(";");
		String world = s[0];
		double x = Double.parseDouble(s[1]);
		double y = Double.parseDouble(s[2]);
		double z = Double.parseDouble(s[3]);
		return new Location(Bukkit.getWorld(world), x, y, z);
	}
}
