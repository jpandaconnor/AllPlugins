package co.uk.RandomPanda30.Files;

import java.util.ArrayList;

public enum ConfigF {

	@SuppressWarnings("serial")
	BANNED_PLAYERS (new ArrayList<String>() {
		{
			add("");
		}
	});

	public Object value;

	ConfigF (Object value) {
		this.value = value;
	}

}
