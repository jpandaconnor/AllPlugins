package co.uk.RandomPanda30.Murge.Handlers.Splitters;

import org.bukkit.Location;
import org.bukkit.TreeType;

import co.uk.RandomPanda30.Murge.MurgeData;

public class TreeSplitter {

	private Location location;
	private TreeType treeType;
	private long time;

	public TreeSplitter (String tree) {
		String[] split = tree.split(";");
		this.setLocation(new Location(MurgeData.getPlugin().getServer()
				.getWorld(split[0]), Double.parseDouble(split[1].replace("=",
				".")), Double.parseDouble(split[2].replace("=", ".")), Double
				.parseDouble(split[3].replace("=", "."))));
		switch (split[4]) {
		case "ACACIA":
			this.setTreeType(TreeType.ACACIA);
			break;
		case "BIG_TREE":
			this.setTreeType(TreeType.BIG_TREE);
			break;
		case "BIRCH":
			this.setTreeType(TreeType.BIRCH);
			break;
		case "BROWN_MUSHROOM":
			this.setTreeType(TreeType.BROWN_MUSHROOM);
			break;
		case "COCOA_TREE":
			this.setTreeType(TreeType.COCOA_TREE);
			break;
		case "DARK_OAK":
			this.setTreeType(TreeType.DARK_OAK);
			break;
		case "JUNGLE":
			this.setTreeType(TreeType.JUNGLE);
			break;
		case "JUNGLE_BUSH":
			this.setTreeType(TreeType.JUNGLE_BUSH);
			break;
		case "MEGA_REDWOOD":
			this.setTreeType(TreeType.MEGA_REDWOOD);
			break;
		case "REDWOOD":
			this.setTreeType(TreeType.REDWOOD);
			break;
		case "RED_MUSHROOM":
			this.setTreeType(TreeType.RED_MUSHROOM);
			break;
		case "SMALL_JUNGLE":
			this.setTreeType(TreeType.SMALL_JUNGLE);
			break;
		case "SWAMP":
			this.setTreeType(TreeType.SWAMP);
			break;
		case "TALL_BIRCH":
			this.setTreeType(TreeType.TALL_BIRCH);
			break;
		case "TALL_REDWOOD":
			this.setTreeType(TreeType.TALL_REDWOOD);
			break;
		case "TREE":
			this.setTreeType(TreeType.TREE);
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

	public TreeType getTreeType() {
		return treeType;
	}

	public void setTreeType(TreeType treeType) {
		this.treeType = treeType;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
}