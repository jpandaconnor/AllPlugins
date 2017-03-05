package co.uk.RandomPanda30.Murge.Handlers.Splitters;

import org.bukkit.Location;

import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Handlers.Regeneration.OreRHandler.Oretype;

public class OreSplitter {

	private Location location;
	private Oretype oreType;
	private long time;

	public OreSplitter (String ore) {
		String[] split = ore.split(";");
		this.setLocation(new Location(MurgeData.getPlugin().getServer()
				.getWorld(split[0]), Double.parseDouble(split[1].replace("=",
				".")), Double.parseDouble(split[2].replace("=", ".")), Double
				.parseDouble(split[3].replace("=", "."))));
		switch (split[4]) {
		case "GOLD":
			this.oreType = Oretype.GOLD;
			break;
		case "IRON":
			this.oreType = Oretype.IRON;
			break;
		case "COAL":
			this.oreType = Oretype.COAL;
			break;
		case "LL":
			this.oreType = Oretype.LL;
			break;
		case "DIAMOND":
			this.oreType = Oretype.DIAMOND;
			break;
		case "REDSTONE":
			this.oreType = Oretype.REDSTONE;
			break;
		case "EMERALD":
			this.oreType = Oretype.EMERALD;
			break;
		}
		this.setTime(Long.parseLong(split[5]));
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Oretype getType() {
		return oreType;
	}

	public void setType(Oretype type) {
		this.oreType = type;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
}