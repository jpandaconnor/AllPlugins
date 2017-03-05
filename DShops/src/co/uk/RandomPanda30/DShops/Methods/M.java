package co.uk.RandomPanda30.DShops.Methods;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class M {

	public static String compileLocation(Location location) {
		String s = location.getWorld().getName().toString() + ";"
				+ Double.toString(location.getX()) + ";"
				+ Double.toString(location.getY()) + ";"
				+ Double.toString(location.getZ());
		s = s.replace(".", "=");
		return s;
	}

	public static Location decompileLocation(String string) {
		String rawS = string.replace("=", ".");
		String[] s = rawS.split(";");
		String world = s[0];
		double x = Double.parseDouble(s[1]);
		double y = Double.parseDouble(s[2]);
		double z = Double.parseDouble(s[3]);
		return new Location(Bukkit.getWorld(world), x, y, z);
	}

	public static String compileDetails(UUID uuid, String name) {
		String s = uuid.toString() + ";" + name;
		return s;
	}

	public static String[] decompileDetails(String string) {
		String[] s = string.split(";");
		return s;
	}
}
