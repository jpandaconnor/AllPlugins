package co.uk.RandomPanda30.Murge.Collection.Commands;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Collection.Bases.CommandBase;
import co.uk.RandomPanda30.Murge.Values.Enums.ConfigValues;

public class Stop implements CommandBase {

	private static Stop instance = new Stop();

	public static Stop getCollection() {
		return instance;
	}

	@Override
	public boolean isEnabled() {
		return (Boolean) Murge.cMap.getStat(ConfigValues.COMMANDS_STOP);
	}

	@Override
	public void setEnabled(boolean c) {
		Murge.cMap.setStat(ConfigValues.COMMANDS_STOP, c);
	}
}