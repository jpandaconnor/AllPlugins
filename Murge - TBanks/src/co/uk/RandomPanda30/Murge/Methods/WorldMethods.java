package co.uk.RandomPanda30.Murge.Methods;

import org.bukkit.Bukkit;

public class WorldMethods {

	public static boolean checkWorld(String name) {
		if (Bukkit.getWorld(name) == null) {
			return false;
		}
		return true;
	}
}