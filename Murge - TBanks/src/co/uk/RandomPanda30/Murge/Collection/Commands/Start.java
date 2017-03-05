package co.uk.RandomPanda30.Murge.Collection.Commands;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Collection.Bases.CommandBase;
import co.uk.RandomPanda30.Murge.Values.Enums.ConfigValues;

public class Start implements CommandBase {

	private static Start instance = new Start();

	public static Start getCollection() {
		return instance;
	}

	@Override
	public boolean isEnabled() {
		return (Boolean) Murge.cMap.getStat(ConfigValues.COMMANDS_START);
	}

	@Override
	public void setEnabled(boolean c) {
		Murge.cMap.setStat(ConfigValues.COMMANDS_START, c);
	}
}