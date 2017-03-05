package co.uk.RandomPanda30.NoCMDS;

import java.util.ArrayList;

public enum Config {

	@SuppressWarnings("serial")
	COMMANDS (new ArrayList<String>() {
		{
			add("help");
		}
	});
	
	public Object value;
	
	Config(Object value) {
		this.value = value;
	}
}