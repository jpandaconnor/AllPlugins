package co.uk.RandomPanda30.Murge.Checkers;

import org.bukkit.scheduler.BukkitRunnable;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Checkers.Beans.DayTime;
import co.uk.RandomPanda30.Murge.Collection.World.WorldCollection;
import co.uk.RandomPanda30.Murge.Executor.PurgeExecutor;
import co.uk.RandomPanda30.Murge.Handlers.InworldHandler;
import co.uk.RandomPanda30.Murge.Methods.StringMethods;
import co.uk.RandomPanda30.Murge.Values.Enums.MessagesValues;

public class DayTimer extends BukkitRunnable {

	public DayTimer (Murge plugin) {
		MurgeData.plugin = plugin;
	}

	InworldHandler iw = new InworldHandler();
	WorldCollection wc = new WorldCollection();
	DayTime dt = new DayTime();

	PurgeExecutor pe = new PurgeExecutor();

	@Override
	public void run() {
		if (!MurgeData.isPurge()) {
			if (dt.getTickTime() != 0) {
				if (wc.getTime() != 13000) {
					if (wc.getTime() < 13000) {
						if (wc.getTime() + (dt.getTimeLeft() * 20) > 13000) {
							wc.setTime(13000);
						} else {
							wc.setTime(wc.getTime() + (dt.getTimeLeft() * 20));
						}
					} else {
						wc.setTime(13000);
					}
				}
				
				dt.decrementTickTime();
				iw.sendActionBar(StringMethods
						.formatMessage(((String) Murge.mMap
								.getStat(MessagesValues.PURGECOUNTDOWN_START))
								.replace("%time", StringMethods
										.convertTicksToTimeString(dt
												.getTickTime()))));

				switch (dt.getTickTime()) {
				case 600:
					iw.sendCountdown((String) Murge.mMap
							.getStat(MessagesValues.TOSTART_10), false);
					break;
				case 300:
					iw.sendCountdown((String) Murge.mMap
							.getStat(MessagesValues.TOSTART_5), false);
					break;
				case 60:
					iw.sendCountdown((String) Murge.mMap
							.getStat(MessagesValues.TOSTART_1), false);
					break;
				case 5:
					iw.sendCountdown((String) Murge.mMap
							.getStat(MessagesValues.STARTSIN_5), true);
					break;
				case 4:
					iw.sendCountdown((String) Murge.mMap
							.getStat(MessagesValues.STARTSIN_4), true);
					break;
				case 3:
					iw.sendCountdown((String) Murge.mMap
							.getStat(MessagesValues.STARTSIN_3), true);
					break;
				case 2:
					iw.sendCountdown((String) Murge.mMap
							.getStat(MessagesValues.STARTSIN_2), true);
					break;
				case 1:
					iw.sendCountdown((String) Murge.mMap
							.getStat(MessagesValues.STARTSIN_1), true);
					break;
				case 0:
					pe.execute();
					break;
				}
			}
		} else {
			dt.resetTime();
		}
	}
}