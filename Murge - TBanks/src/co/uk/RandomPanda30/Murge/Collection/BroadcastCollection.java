package co.uk.RandomPanda30.Murge.Collection;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Values.Enums.ConfigValues;

public class BroadcastCollection {

	public static boolean broadcastLoggedOut() {
		return (Boolean) Murge.cMap
				.getStat(ConfigValues.BROADCAST_PLAYERLEAVESCOMBAT);
	}

	public static void setBroadcastLoggedOut(boolean c) {
		Murge.cMap.setStat(ConfigValues.BROADCAST_PLAYERLEAVESCOMBAT, c);
	}
}