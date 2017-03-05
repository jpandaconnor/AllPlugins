package co.uk.RandomPanda30.Murge.Collection.World;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Values.Enums.ConfigValues;

public class WorldCollection {

	private static WorldCollection instance = new WorldCollection();

	public static WorldCollection getCollection() {
		return instance;
	}

	public boolean isWeatherEnabled() {
		return (Boolean) Murge.cMap.getStat(ConfigValues.WEATHERENABLED);
	}

	public void setWeatherEnabled(boolean c) {
		Murge.cMap.setStat(ConfigValues.WEATHERENABLED, c);
	}

	public long getTime() {
		return MurgeData.getWorld().getTime();
	}

	public void setTime(long time) {
		MurgeData.getWorld().setTime(time);
	}
}