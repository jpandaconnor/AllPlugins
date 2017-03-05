package co.uk.RandomPanda30.CityRP.Console;

import java.util.HashMap;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.bukkit.Bukkit;

public class ConsoleOutput {

	HashMap<Integer, String> messages = new HashMap<Integer, String>();

	boolean log;
	
	public ConsoleOutput (boolean store) {
		this.log = store;
	}

	public void stop(boolean log) {
		Bukkit.getServer().getLogger().addHandler(new Handler() {
			@Override
			public void close() throws SecurityException {};
			
			@Override
			public void flush() {};
			
			@Override
			public void publish(LogRecord logRecord) {
				logRecord.setLevel(Level.OFF);
			}
		});
	}

	public void resume(boolean useLog) {

	}
}