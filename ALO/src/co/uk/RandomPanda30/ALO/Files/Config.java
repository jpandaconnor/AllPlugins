package co.uk.RandomPanda30.ALO.Files;

import java.util.ArrayList;

@SuppressWarnings("serial")
public enum Config {

	KILLS_COMMANDS_DEFAULT (new ArrayList<String>() {
		{
			add("coins give %name 1");
		}
	}),

	KILLS_COMMANDS_PRO (new ArrayList<String>() {
		{
			add("coins give %name 3");
		}
	}),

	KILLS_COMMANDS_ULTIMATE (new ArrayList<String>() {
		{
			add("coins give %name 5");
		}
	});

	public Object value;

	Config (Object value) {
		this.value = value;
	}
}