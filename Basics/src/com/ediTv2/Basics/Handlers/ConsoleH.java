package com.ediTv2.Basics.Handlers;

import org.bukkit.Bukkit;

public class ConsoleH {

	public static void sendConsoleM(String message) {
		Bukkit.getConsoleSender().sendMessage(message);
	}

}
