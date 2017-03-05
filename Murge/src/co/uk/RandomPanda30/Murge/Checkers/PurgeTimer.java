package co.uk.RandomPanda30.Murge.Checkers;

import org.bukkit.scheduler.BukkitRunnable;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Checkers.Beans.PurgeTime;
import co.uk.RandomPanda30.Murge.Collection.World.WorldBorderCollection;
import co.uk.RandomPanda30.Murge.Collection.World.WorldCollection;
import co.uk.RandomPanda30.Murge.Executor.DayExecutor;
import co.uk.RandomPanda30.Murge.Handlers.InworldHandler;
import co.uk.RandomPanda30.Murge.Handlers.WorldBorderHandler;
import co.uk.RandomPanda30.Murge.Methods.StringMethods;
import co.uk.RandomPanda30.Murge.Values.Enums.MessagesValues;

public class PurgeTimer extends BukkitRunnable {

	public PurgeTimer (Murge plugin) {
		MurgeData.plugin = plugin;
	}

	public PurgeTime handle = new PurgeTime();
	public final InworldHandler general = new InworldHandler();
	public final WorldCollection wc = new WorldCollection();
	public final DayExecutor de = new DayExecutor();
	public final WorldBorderHandler wbh = new WorldBorderHandler();

	@Override
	public void run() {
		if (handle.getTickTime() != 0) {

			if (WorldBorderCollection.getCollection().closes()) {
				if (!wbh.closing) {
					wbh.closing = true;
					WorldBorderHandler.closeWorldBorder();
				}
			}

			if (wc.getTime() != 1000) {
				if (wc.getTime() >= 13000) {
					wc.setTime(wc.getTime() + (handle.getTimeLeft() * 20));
				} else {
					if (wc.getTime() < 1000) {
						if (wc.getTime() + (handle.getTimeLeft() * 20) > 1000) {
							wc.setTime(1000);
						} else {
							wc.setTime(wc.getTime()
									+ (handle.getTimeLeft() * 20));
						}
					} else {
						wc.setTime(1000);
					}
				}
			}

			handle.decrementTickTime();
			general.sendActionBar(StringMethods
					.formatMessage(((String) Murge.mMap
							.getStat(MessagesValues.PURGECOUNTDOWN_END))
							.replace("%time", StringMethods
									.convertTicksToTimeString(handle
											.getTickTime()))));

			switch (handle.getTickTime()) {
			case 600:
				general.sendCountdown(
						(String) Murge.mMap.getStat(MessagesValues.TOEND_10),
						false);
				break;
			case 300:
				general.sendCountdown(
						(String) Murge.mMap.getStat(MessagesValues.TOEND_5),
						false);
				break;
			case 60:
				general.sendCountdown(
						(String) Murge.mMap.getStat(MessagesValues.TOEND_1),
						false);
				break;
			case 5:
				general.sendCountdown(
						(String) Murge.mMap.getStat(MessagesValues.ENDSIN_5),
						true);
				break;
			case 4:
				general.sendCountdown(
						(String) Murge.mMap.getStat(MessagesValues.ENDSIN_4),
						true);
				break;
			case 3:
				general.sendCountdown(
						(String) Murge.mMap.getStat(MessagesValues.ENDSIN_3),
						true);
				break;
			case 2:
				general.sendCountdown(
						(String) Murge.mMap.getStat(MessagesValues.ENDSIN_2),
						true);
				break;
			case 1:
				general.sendCountdown(
						(String) Murge.mMap.getStat(MessagesValues.ENDSIN_1),
						true);
				break;
			case 0:
				de.execute();
				wbh.closing = false;
				if (WorldBorderCollection.getCollection().isEnabled()) {
					WorldBorderHandler.createWorldBorder(WorldBorderCollection
							.getCollection().getCentre(), WorldBorderCollection
							.getCollection().getSize(), WorldBorderCollection
							.getCollection().getDamage());
				}
				this.cancel();
				break;
			}
		} else {
			handle.resetTime();
		}
	}
}