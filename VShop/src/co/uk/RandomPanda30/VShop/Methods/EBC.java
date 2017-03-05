package co.uk.RandomPanda30.VShop.Methods;

import java.util.HashMap;

import org.bukkit.Location;

import co.uk.RandomPanda30.VShop.B;

public class EBC {

	public static HashMap<String, Location> signLocations = new HashMap<String, Location>();

	static int count = 0;

	public static void loadSigns() {
		signLocations.clear();
		B.getKeys();
		for (String s : B.keys) {
			if (!signLocations.containsKey(s)) {
				Location loc = X.decompileLocation(B.shopsC.getString(s
						+ ".sign"));
				signLocations.put(s, loc);
				count++;
			}
		}
	}
}