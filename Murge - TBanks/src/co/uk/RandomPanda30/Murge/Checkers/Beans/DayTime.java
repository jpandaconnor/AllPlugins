package co.uk.RandomPanda30.Murge.Checkers.Beans;

import co.uk.RandomPanda30.Murge.Collection.World.TimeCollection;

public class DayTime {

	private static DayTime instance = new DayTime();

	private int tickTime = TimeCollection.getCollection().getTimeUntilPurge() * 60;

	private long difference = 13000 - 1000;
	private long tt = (TimeCollection.getCollection().getTimeUntilPurge() * 60) * 20;
	private long tt2 = difference / tt;

	public static DayTime getHandle() {
		return instance;
	}

	public void decrementTickTime() {
		tickTime--;
	}

	public int getTickTime() {
		return tickTime;
	}

	public long getTimeLeft() {
		return tt2;
	}

	public void resetTime() {
		tickTime = TimeCollection.getCollection().getTimeUntilPurge() * 60;
	}
}