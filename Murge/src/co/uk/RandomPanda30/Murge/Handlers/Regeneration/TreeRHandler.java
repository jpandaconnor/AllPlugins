package co.uk.RandomPanda30.Murge.Handlers.Regeneration;

import java.io.IOException;
import java.util.Calendar;

import org.bukkit.Location;
import org.bukkit.TreeType;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Collection.World.TimeCollection;
import co.uk.RandomPanda30.Murge.Methods.LocationMethods;
import co.uk.RandomPanda30.Murge.Methods.StringMethods;

public class TreeRHandler {

	private Location location;
	private TreeType type;
	private long time;

	public TreeRHandler (Location location, TreeType type) {
		this.setLocation(location);
		this.setType(type);

		String time = TimeCollection.getCollection().getTreeRegenerationTime();
		String[] splitter = { time };
		long days = 0;
		long hours = 0;
		long minutes = 0;
		long seconds = 0;

		long length = 0;

		if (time.contains("d")) {
			splitter = splitter[0].split("d");
			days = new Long(splitter[0]);
			if (splitter.length == 2) {
				splitter[0] = splitter[1];
			}
		}

		if (time.contains("h")) {
			splitter = splitter[0].split("h");
			hours = new Long(splitter[0]);
			if (splitter.length == 2) {
				splitter[0] = splitter[1];
			}
		}

		if (time.contains("m")) {
			splitter = splitter[0].split("m");
			minutes = new Long(splitter[0]);
			if (splitter.length == 2) {
				splitter[0] = splitter[1];
			}
		}

		if (time.contains("s")) {
			splitter = splitter[0].split("s");
			seconds = new Long(splitter[0]);
		}

		length = Calendar.getInstance().getTimeInMillis()
				+ (days * 1000 * 24 * 60 * 60) + (hours * 1000 * 60 * 60)
				+ (minutes * 1000 * 60) + (seconds * 1000);
		this.setTime(length);
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public TreeType getType() {
		return type;
	}

	public void setType(TreeType type) {
		this.type = type;
	}

	public String build() {
		return LocationMethods.compileLocation(location) + type.toString()
				+ time;
	}

	public void grow() {
		location.getWorld().generateTree(location, type);
		StringMethods.sendMessage(
				"%TAG %NTree regeneration - World: %A"
						+ location.getWorld().getName() + " %NX: %A"
						+ Double.toString(location.getX()) + " %NY: %A"
						+ Double.toString(location.getY()) + " %NZ: %A"
						+ Double.toString(location.getZ()) + " %N. Type: %A"
						+ type.toString(), null);
	}

	public void remove() {
		if (Murge.cso.getDumpC().contains("Trees." + build())) {
			Murge.cso.getDumpC().set("Trees." + build(), null);
			try {
				Murge.cso.getDumpC().save(Murge.cso.getDump());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}