package co.uk.RandomPanda30.CityRP.Misc;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class SetupFilter implements Filter {

	@Override
	public boolean isLoggable(LogRecord string) {
		return !string.getMessage().contains("INFO");
	}
}