package co.uk.RandomPanda30.Handlers;

import org.bukkit.Bukkit;

public class Start_ShutHandler {
	
	public static void startup() {
		Bukkit.getConsoleSender().sendMessage(TextH.tag.toString());
		Bukkit.getConsoleSender().sendMessage(TextH.enable.toString());
		Bukkit.getConsoleSender().sendMessage(TextH.listenersRegistered.toString());
		Bukkit.getConsoleSender().sendMessage(TextH.done.toString());
	}
	
	public static void shutdown() {
		Bukkit.getConsoleSender().sendMessage(TextH.tag.toString());
		Bukkit.getConsoleSender().sendMessage(TextH.disable.toString());
	}

}
