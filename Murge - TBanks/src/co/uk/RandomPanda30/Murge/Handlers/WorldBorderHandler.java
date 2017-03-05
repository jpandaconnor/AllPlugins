package co.uk.RandomPanda30.Murge.Handlers;

import org.bukkit.Location;
import org.bukkit.WorldBorder;

public class WorldBorderHandler {

	public static void createWorldBorder(Location centre, double size,
			int damage) {
		WorldBorder wb = centre.getWorld().getWorldBorder();
		wb.setCenter(centre);
		wb.setSize(size);
		wb.setDamageAmount(damage);
	}
}