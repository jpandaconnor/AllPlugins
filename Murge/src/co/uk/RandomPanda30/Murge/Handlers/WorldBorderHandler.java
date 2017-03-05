package co.uk.RandomPanda30.Murge.Handlers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldBorder;

import co.uk.RandomPanda30.Murge.Collection.World.WorldBorderCollection;

public class WorldBorderHandler {

	public static WorldBorderCollection wb = new WorldBorderCollection();

	public boolean closing = false;

	public static void createWorldBorder(Location centre, double size,
			int damage) {
		WorldBorder wb = centre.getWorld().getWorldBorder();
		wb.setCenter(centre);
		wb.setSize(size);
		wb.setDamageAmount(damage);
	}

	public static void closeWorldBorder() {
		Bukkit.getServer().dispatchCommand(
				Bukkit.getConsoleSender(),
				"worldborder set " + Integer.toString(wb.getCloseLimit()) + " "
						+ Integer.toString(wb.getCloseTime()));
	}
}