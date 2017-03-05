package co.uk.RandomPanda30.DShops.Methods;

import java.util.Calendar;

import org.bukkit.scheduler.BukkitRunnable;

import co.uk.RandomPanda30.DShops.A;
import co.uk.RandomPanda30.DShops.B;
import co.uk.RandomPanda30.DShops.S;

public class V extends BukkitRunnable {

	public V (A plugin) {
		B.plugin = plugin;
	}

	public void run() {
		for (String s : B.doorsC.getConfigurationSection("").getKeys(false)) {
			try {
				if (B.doorsC.get(s + ".time") != null) {
					if (Calendar.getInstance().getTimeInMillis() > B.doorsC
							.getLong(s + ".time")) {
						if (P.getDoorState(s).equals(S.RENTED)
								|| P.getDoorState(s).equals(S.FORRENT)) {
							PA.sellRentDoor(s, 0, null);
						} else {
							P.sellDoor(null, s);
						}
					}
				}
			} catch (NullPointerException e) {

			}
		}
	}
}