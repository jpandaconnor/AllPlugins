package co.uk.RandomPanda30.Handlers;

import org.bukkit.Bukkit;

public class ConsoleSender {

	public static void consoleM(String message) {
		Bukkit.getConsoleSender().sendMessage(message);
	}

}
