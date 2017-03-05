package co.uk.RandomPanda30.Murge.Collection.World;

import org.bukkit.Location;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Methods.LocationMethods;
import co.uk.RandomPanda30.Murge.Values.Enums.ConfigValues;

public class WorldBorderCollection {

	private static WorldBorderCollection instance = new WorldBorderCollection();

	public static WorldBorderCollection getCollection() {
		return instance;
	}

	public boolean isEnabled() {
		if ((Boolean) Murge.cMap.getStat(ConfigValues.WORLDBORDER_ENABLED)) {
			return true;
		}
		return false;
	}

	public boolean closes() {
		return (Boolean) Murge.cMap.getStat(ConfigValues.WORLDBORDER_CLOSES);
	}

	public void setCloses(boolean c) {
		Murge.cMap.setStat(ConfigValues.WORLDBORDER_CLOSES, c);
	}

	public int getCloseLimit() {
		return (Integer) Murge.cMap
				.getStat(ConfigValues.WORLDBORDER_CLOSELIMIT);
	}

	public void setCloseLimit(int limit) {
		Murge.cMap.setStat(ConfigValues.WORLDBORDER_CLOSELIMIT, limit);
	}

	public int getCloseTime() {
		return (Integer) Murge.cMap.getStat(ConfigValues.WORLDBORDER_CLOSETIME);
	}

	public void setCloseTime(int time) {
		Murge.cMap.setStat(ConfigValues.WORLDBORDER_CLOSETIME, time);
	}

	public void setEnabled(boolean option) {
		Murge.cMap.setStat(ConfigValues.WORLDBORDER_ENABLED, option);
	}

	public Location getCentre() {
		return LocationMethods.decompileLocation((String) Murge.cMap
				.getStat(ConfigValues.WORLDBORDER_CENTRE));
	}

	public void setLocation(Location location) {
		Murge.cMap.setStat(ConfigValues.WORLDBORDER_CENTRE,
				LocationMethods.compileLocation(location));
	}

	public double getSize() {
		return (Double) Murge.cMap.getStat(ConfigValues.WORLDBORDER_SIZE);
	}

	public void setSize(double size) {
		Murge.cMap.setStat(ConfigValues.WORLDBORDER_SIZE, size);
	}

	public int getDamage() {
		return (Integer) Murge.cMap.getStat(ConfigValues.WORLDBORDER_DAMAGE);
	}

	public void setDamage(int damage) {
		Murge.cMap.setStat(ConfigValues.WORLDBORDER_DAMAGE, damage);
	}

	public int getDeathThreshold() {
		return (Integer) Murge.cMap
				.getStat(ConfigValues.WORLDBORDERDEATHTHRESHOLD);
	}

	public void setDeathThreshold(int threshold) {
		Murge.cMap.setStat(ConfigValues.WORLDBORDERDEATHTHRESHOLD, threshold);
	}
}