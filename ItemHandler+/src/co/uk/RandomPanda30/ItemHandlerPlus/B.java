package co.uk.RandomPanda30.ItemHandlerPlus;

import java.io.File;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;

public class B {
	
	public static A plugin;

	private static PluginDescriptionFile pdfFile;

	public static File config;
	public static FileConfiguration configC;
	public static ConfigurationSection configCS;

	public static File messages;
	public static FileConfiguration messagesC;
	public static ConfigurationSection messagesCS;

	public static File items;
	public static FileConfiguration itemsC;
	public static ConfigurationSection itemsCS;

	public static Set<String> keys;

	public static String getAuthor(int no) {
		return getDataFile().getAuthors().get(no);
	}

	public static void initDataFile() {
		pdfFile = plugin.getDescription();
	}

	public static PluginDescriptionFile getDataFile() {
		return pdfFile;
	}

	public static A getPlugin() {
		return plugin;
	}
}