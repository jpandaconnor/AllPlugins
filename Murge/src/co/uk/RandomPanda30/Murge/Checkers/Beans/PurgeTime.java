package co.uk.RandomPanda30.Murge.Checkers.Beans;

import co.uk.RandomPanda30.Murge.Collection.World.TimeCollection;

public class PurgeTime {

	private static PurgeTime instance = new PurgeTime();

	private int tickTime = TimeCollection.getCollection().getPurgeDuration() * 60;

	private long difference = 13000 - 1000;
	private long tt = (TimeCollection.getCollection().getPurgeDuration() * 60) * 20;
	private long tt2 = difference / tt;

	public static PurgeTime getHandle() {
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
		tickTime = TimeCollection.getCollection().getPurgeDuration() * 60;
	}
}