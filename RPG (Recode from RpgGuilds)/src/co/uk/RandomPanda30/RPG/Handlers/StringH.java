package co.uk.RandomPanda30.RPG.Handlers;

import net.md_5.bungee.api.ChatColor;

public class StringH {

	private static StringH instance = new StringH();
	
	public static StringH getInstance() {
		return instance;
	}
	
	public String formatMessage(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}	
}