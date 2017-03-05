package co.uk.RandomPanda30.DShops.Files;

import java.util.ArrayList;

@SuppressWarnings("serial")
public enum Config {

	DOOR_PRICE (3000), RENT_PRICE (300), RENT_DAYS (5), DOOR_THRESHOLD (1),

	WORLDS (new ArrayList<String>() {
		{
			add("world");
		}
	});

	public Object value;

	Config (Object value) {
		this.value = value;
	}
}
