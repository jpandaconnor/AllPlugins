package co.uk.RandomPanda30.Murge.Collection;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Values.Enums.ConfigValues;

public class MiscCollection {

	public static boolean isPVPAllowed() {
		return (Boolean) Murge.cMap.getStat(ConfigValues.PVPDURINGGRACE);
	}

	public static void setIsPVPAllowed(boolean c) {
		Murge.cMap.setStat(ConfigValues.PVPDURINGGRACE, c);
	}

	public static boolean allowBlockBreak() {
		return (Boolean) Murge.cMap.getStat(ConfigValues.BLOCKBREAKDURINGPURGE);
	}

	public static void setAllowBlockBreak(boolean c) {
		Murge.cMap.setStat(ConfigValues.BLOCKBREAKDURINGPURGE, c);
	}

	public static boolean allowBlockPlace() {
		return (Boolean) Murge.cMap.getStat(ConfigValues.BLOCKPLACEURINGPURGE);
	}

	public static void setAllowBlockPlace(boolean c) {
		Murge.cMap.setStat(ConfigValues.BLOCKPLACEURINGPURGE, c);
	}

	public static boolean mobDamageCountAsCombat() {
		return (Boolean) Murge.cMap.getStat(ConfigValues.MOBDAMAGEASCOMBAT);
	}

	public static void setMobDamageCountAsCombat(boolean c) {
		Murge.cMap.setStat(ConfigValues.MOBDAMAGEASCOMBAT, c);
	}

	public static boolean isStrikeOnDeath() {
		return (Boolean) Murge.cMap.getStat(ConfigValues.LIGHTNINGONDEATH);
	}

	public static void setStrikeOnDeath(boolean c) {
		Murge.cMap.setStat(ConfigValues.LIGHTNINGONDEATH, c);
	}

	public static boolean shouldRespawnAsSpectator() {
		return (Boolean) Murge.cMap
				.getStat(ConfigValues.MOVETOSPECTATEWHENNOPURGE);
	}

	public static void setShouldRespawnAsSpectator(boolean c) {
		Murge.cMap.setStat(ConfigValues.MOVETOSPECTATEWHENNOPURGE, c);
	}
}