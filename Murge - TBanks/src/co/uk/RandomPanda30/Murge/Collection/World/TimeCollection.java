package co.uk.RandomPanda30.Murge.Collection.World;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Values.Enums.ConfigValues;

public class TimeCollection {

	private static TimeCollection instance = new TimeCollection();

	public static TimeCollection getCollection() {
		return instance;
	}

	public int getTimeUntilPurge() {
		return (Integer) Murge.cMap.getStat(ConfigValues.TIMEBEFOREPURGE);
	}

	public int getPurgeDuration() {
		return (Integer) Murge.cMap.getStat(ConfigValues.PURGEDURATION);
	}

	public int getTimeBeforePurge() {
		return (Integer) Murge.cMap.getStat(ConfigValues.TIMEBEFOREPURGE);
	}

	public void setTimeBeforePurge(int time) {
		Murge.cMap.setStat(ConfigValues.TIMEBEFOREPURGE, time);
	}

	public void setPurgeDuration(int time) {
		Murge.cMap.setStat(ConfigValues.PURGEDURATION, time);
	}

	public int getFightCooldown() {
		return (Integer) Murge.cMap.getStat(ConfigValues.FIGHTCOOLDOWN);
	}

	public void setFightCooldown(int time) {
		Murge.cMap.setStat(ConfigValues.FIGHTCOOLDOWN, time);
	}
}