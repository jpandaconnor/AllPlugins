package co.uk.RandomPanda30.CasinoM.Methods;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationMethods {

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