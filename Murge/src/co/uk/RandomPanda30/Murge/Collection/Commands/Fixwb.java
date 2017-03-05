package co.uk.RandomPanda30.Murge.Collection.Commands;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Collection.Bases.CommandBase;
import co.uk.RandomPanda30.Murge.Values.Enums.ConfigValues;

public class Fixwb implements CommandBase {

	private static Fixwb instance = new Fixwb();

	public static Fixwb getCollection() {
		return instance;
	}

	@Override
	public boolean isEnabled() {
		return (Boolean) Murge.cMap.getStat(ConfigValues.COMMANDS_FIXWB);
	}

	@Override
	public void setEnabled(boolean c) {
		Murge.cMap.setStat(ConfigValues.COMMANDS_FIXWB, c);
	}
}