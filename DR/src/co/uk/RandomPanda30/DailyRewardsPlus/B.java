package co.uk.RandomPanda30.DailyRewardsPlus;

import java.io.File;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;

public class B {

	public static A plugin;
	public static PluginDescriptionFile pdfFile;

	public static File config;
	public static FileConfiguration configC;
	public static ConfigurationSection configCS;

	public static File messages;
	public static FileConfiguration messagesC;
	public static ConfigurationSection messagesCS;

	public static File data;
	public static FileConfiguration dataC;
	public static ConfigurationSection dataCS;

	public static File items;
	public static FileConfiguration itemsC;
	public static ConfigurationSection itemsCS;

	public static void initDataFile() {
		pdfFile = plugin.getDescription();
	}

	public static void deinitConfigs() {
		config = null;
		messages = null;
	}

	public static PluginDescriptionFile getDataFile() {
		return pdfFile;
	}

	public static A getPlugin() {
		return plugin;
	}

	public static String getAuthor(int no) {
		return getDataFile().getAuthors().get(no);
	}
}