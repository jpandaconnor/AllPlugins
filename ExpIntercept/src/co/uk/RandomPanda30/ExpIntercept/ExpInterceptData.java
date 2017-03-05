package co.uk.RandomPanda30.ExpIntercept;

import java.io.File;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;

public class ExpInterceptData {
	
	public static ExpIntercept plugin;

	public static File config;
	public static FileConfiguration configC;
	public static ConfigurationSection configCS;

	public static File messages;
	public static FileConfiguration messagesC;
	public static ConfigurationSection messagesCS;
	
	public static PluginDescriptionFile pdfFile;
	
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

	public static ExpIntercept getPlugin() {
		return plugin;
	}

	public static String getAuthor(int no) {
		return getDataFile().getAuthors().get(no);
	}
}