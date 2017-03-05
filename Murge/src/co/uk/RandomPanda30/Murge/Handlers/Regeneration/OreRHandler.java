package co.uk.RandomPanda30.Murge.Handlers.Regeneration;

import java.io.IOException;
import java.util.Calendar;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Collection.World.TimeCollection;
import co.uk.RandomPanda30.Murge.Methods.LocationMethods;
import co.uk.RandomPanda30.Murge.Methods.StringMethods;

public class OreRHandler {

	public enum Oretype {
		GOLD, IRON, COAL, LL, DIAMOND, REDSTONE, EMERALD;
	}

	private Location location;
	private Oretype ore;
	private long time;

	public OreRHandler (Location location, Oretype ore) {
		this.setLocation(location);
		this.setOre(ore);

		String time = TimeCollection.getCollection().getOreRegenerationTime();
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

	public Oretype getOre() {
		return ore;
	}

	public void setOre(Oretype ore) {
		this.ore = ore;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public void place() {
		Block block = location.getWorld().getBlockAt(location);
		if (location.getBlock().getType().equals(Material.AIR)
				|| location.getBlock().getType().equals(Material.STONE)) {
			switch (ore) {
			case GOLD:
				block.setType(Material.GOLD_ORE);
				break;
			case IRON:
				block.setType(Material.IRON_ORE);
				break;
			case COAL:
				block.setType(Material.COAL_ORE);
				break;
			case DIAMOND:
				block.setType(Material.DIAMOND_ORE);
				break;
			case EMERALD:
				block.setType(Material.EMERALD_ORE);
				break;
			case LL:
				block.setType(Material.LAPIS_ORE);
				break;
			case REDSTONE:
				block.setType(Material.REDSTONE_ORE);
				break;
			}
		}
		StringMethods.sendMessage(
				"%TAG %NOre regeneration - World: %A"
						+ location.getWorld().getName() + " %NX: %A"
						+ Double.toString(location.getX()) + " %NY: %A"
						+ Double.toString(location.getY()) + " %NZ: %A"
						+ Double.toString(location.getZ()) + " %N. Type: %A"
						+ ore.toString(), null);
	}

	public String build() {
		return LocationMethods.compileLocation(location) + ore.toString()
				+ Long.toString(time);
	}

	public void remove() {
		if (Murge.cso.getDumpC().contains("Ores." + build())) {
			Murge.cso.getDumpC().set("Ores." + build(), null);
			try {
				Murge.cso.getDumpC().save(Murge.cso.getDump());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}