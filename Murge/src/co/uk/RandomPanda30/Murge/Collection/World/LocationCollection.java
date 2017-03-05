package co.uk.RandomPanda30.Murge.Collection.World;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Location;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Methods.LocationMethods;
import co.uk.RandomPanda30.Murge.Values.Enums.ConfigValues;

public class LocationCollection {

	public static boolean isSpawnSet() {
		if (!StringUtils.isEmpty(((String) Murge.cMap
				.getStat(ConfigValues.SPAWNLOCATION)))) {
			return true;
		}
		return false;
	}

	public static Location getSpawn() {
		return LocationMethods.decompileLocation((String) Murge.cMap
				.getStat(ConfigValues.SPAWNLOCATION));
	}

	public static void setSpawn(Location location) {
		Murge.cMap.setStat(ConfigValues.SPAWNLOCATION,
				LocationMethods.compileLocation(location));
	}

	public static void removeSpawn() {
		Murge.cMap.setStat(ConfigValues.SPAWNLOCATION, null);
	}

	public static boolean isSpectatorSpawnSet() {
		if (!StringUtils.isEmpty(((String) Murge.cMap
				.getStat(ConfigValues.SPECTATORLOCATION)))) {
			return true;
		}
		return false;
	}

	public static Location getSpectatorSpawn() {
		return LocationMethods.decompileLocation((String) Murge.cMap
				.getStat(ConfigValues.SPECTATORLOCATION));
	}

	public static void setSpectatorSpawn(Location location) {
		Murge.cMap.setStat(ConfigValues.SPECTATORLOCATION,
				LocationMethods.compileLocation(location));
	}

	public static void removeSpectatorSpawn() {
		Murge.cMap.setStat(ConfigValues.SPECTATORLOCATION, null);
	}
}