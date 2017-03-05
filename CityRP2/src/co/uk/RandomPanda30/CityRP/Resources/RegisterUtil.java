package co.uk.RandomPanda30.CityRP.Resources;

import co.uk.RandomPanda30.CityRP.CityRP;
import co.uk.RandomPanda30.CityRP.Setup.LocationSetup;

public class RegisterUtil {

	/*
	 * All events will be registered in here
	 * Just stops shit cluttering up the whole package
	 */
	
	public CityRP plugin;
	
	// private final DatabaseSetup databaseSetup = new DatabaseSetup(true);
	
	public RegisterUtil(CityRP plugin) {
		this.plugin = plugin;
		register();
	}
	
	public void register() {
		new LocationSetup(plugin);
	}	
}