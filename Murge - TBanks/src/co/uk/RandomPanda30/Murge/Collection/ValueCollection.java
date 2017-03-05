package co.uk.RandomPanda30.Murge.Collection;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Values.Enums.ConfigValues;

public class ValueCollection {

	private static ValueCollection instance = new ValueCollection();

	public static ValueCollection getCollection() {
		return instance;
	}

	public int getValueOnKill() {
		return (Integer) Murge.cMap.getStat(ConfigValues.MONEY_ONKILL);
	}

	public void setValueOnKill(int value) {
		Murge.cMap.setStat(ConfigValues.MONEY_ONKILL, value);
	}

	public int getValueOnPurgeSurvive() {
		return (Integer) Murge.cMap.getStat(ConfigValues.MONEY_ONPURGESURVIVE);
	}

	public void setValueOnPurgeSurvive(int value) {
		Murge.cMap.setStat(ConfigValues.MONEY_ONPURGESURVIVE, value);
	}
}