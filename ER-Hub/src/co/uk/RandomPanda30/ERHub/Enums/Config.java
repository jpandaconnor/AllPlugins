package co.uk.RandomPanda30.ERHub.Enums;

import java.util.ArrayList;

@SuppressWarnings("serial")
public enum Config {

	WORLDS (new ArrayList<String>() {
		{
			add("world");
		}
	}),

	TEST ("TEST");

	public Object value;

	Config (Object value) {
		this.value = value;
	}
}