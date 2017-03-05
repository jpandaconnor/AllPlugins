package co.uk.RandomPanda30.DailyRewardsPlus.Files;

import java.util.ArrayList;

@SuppressWarnings("serial")
public enum Config {

	RANDOMITEMS_ENABLED (false), RANDOMITEMS_NO (5),

	COMMANDSLIST (new ArrayList<String>() {
		{
			add("test");
		}
	}),

	CONSOLECOMMANDSLIST (new ArrayList<String>() {
		{
			add("test");
		}
	});

	public Object value;

	Config (Object value) {
		this.value = value;
	}
}