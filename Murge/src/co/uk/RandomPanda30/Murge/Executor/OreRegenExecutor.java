package co.uk.RandomPanda30.Murge.Executor;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Checkers.OreRegenTimer;
import co.uk.RandomPanda30.Murge.Collection.World.TimeCollection;
import co.uk.RandomPanda30.Murge.Executor.Base.ExecutorBase2;

public class OreRegenExecutor implements ExecutorBase2 {

	private static OreRegenExecutor instance = new OreRegenExecutor();

	public static OreRegenExecutor getExecutor() {
		return instance;
	}

	@Override
	public void execute() {
		String time = TimeCollection.getCollection().getOreRegenerationTimer();
		String[] splitter = { time };
		long days = 0;
		long hours = 0;
		long minutes = 0;
		long seconds = 0;

		long length = 0;

		if (time.contains("d")) {
			splitter = splitter[0].split("d");
			days = new Long(splitter[0]) * 60 * 60 * 24 * 20;
			if (splitter.length == 2) {
				splitter[0] = splitter[1];
			}
		}

		if (time.contains("h")) {
			splitter = splitter[0].split("h");
			hours = new Long(splitter[0]) * 60 * 60 * 20;
			if (splitter.length == 2) {
				splitter[0] = splitter[1];
			}
		}

		if (time.contains("m")) {
			splitter = splitter[0].split("m");
			minutes = new Long(splitter[0]) * 60 * 20;
			if (splitter.length == 2) {
				splitter[0] = splitter[1];
			}
		}

		if (time.contains("s")) {
			splitter = splitter[0].split("s");
			seconds = new Long(splitter[0]) * 20;
		}

		length = seconds + minutes + hours + days;
		startTimer((int) length);
	}

	@Override
	public void startTimer(int value) {
		Murge.oreChecker = new OreRegenTimer(MurgeData.getPlugin())
				.runTaskTimer(MurgeData.getPlugin(), value, value);
	}
}