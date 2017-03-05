package co.uk.RandomPanda30.Murge.Collection.Special;

import co.uk.RandomPanda30.Murge.Values.Enums.ConfigValues;

import co.uk.RandomPanda30.Murge.Murge;

public class BungeeCollection {

	private static BungeeCollection instance = new BungeeCollection();

	public static BungeeCollection getCollection() {
		return instance;
	}

	public boolean isEnabled() {
		return (Boolean) Murge.cMap.getStat(ConfigValues.BUNGEE_ENABLE);
	}

	public void setEnable(boolean c) {
		Murge.cMap.setStat(ConfigValues.BUNGEE_ENABLE, c);
	}

	public String getLobby() {
		return (String) Murge.cMap.getStat(ConfigValues.BUNGEE_LOBBY);
	}

	public boolean isSendOnLeave() {
		return (Boolean) Murge.cMap.getStat(ConfigValues.BUNGEE_SENDONLEAVE);
	}

	public void setSendOnLeave(boolean c) {
		Murge.cMap.setStat(ConfigValues.BUNGEE_SENDONLEAVE, c);
	}

	public boolean isSendOnDeath() {
		return (Boolean) Murge.cMap.getStat(ConfigValues.BUNGEE_SENDONDEATH);
	}

	public void setSendOnDeath(boolean c) {
		Murge.cMap.setStat(ConfigValues.BUNGEE_SENDONDEATH, c);
	}

	public int getSendDelay() {
		return (Integer) Murge.cMap.getStat(ConfigValues.BUNGEE_SENDDELAY);
	}

	public void setSendDelay(int delay) {
		Murge.cMap.setStat(ConfigValues.BUNGEE_SENDDELAY, delay);
	}
}