package co.uk.RandomPanda30.Murge.Collection;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Values.Enums.ConfigValues;

public class StringCollection {

	private static StringCollection instance = new StringCollection();

	public static StringCollection getCollection() {
		return instance;
	}

	public String getMoneyString() {
		return (String) Murge.cMap.getStat(ConfigValues.CURRENCYSTRING);
	}

	public void setMoneyString(String string) {
		Murge.cMap.setStat(ConfigValues.CURRENCYSTRING, string);
	}
}