package org.jpanda.cityrp.plugin;

import static org.jpanda.cityrp.plugin.Status.*;

public class I {

	static final String NAME = "CityRP";
	static final String VERSION = "1.0";
	static final Status STATUS = BETA;
	
	static final String[] AUTHORS = { "RandomPanda30" };
	
	public static String getName() {
		return NAME;
	}
	
	public static String getVersion() {
		return VERSION;
	}
	
	public static Status getStatus() {
		return STATUS;
	}
	
	public static String[] getAuthors() {
		return AUTHORS;
	}
	
	public static String getAuthor(int index) {
		return AUTHORS[index] != null ?  AUTHORS[index] : "Null";
	}
}