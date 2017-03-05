package co.uk.RandomPanda30.DShops;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;

public class B {

	public static HashMap<UUID, String> inInv = new HashMap<UUID, String>();

	public static A plugin;

	private static PluginDescriptionFile pdfFile;

	public static File config;
	public static FileConfiguration configC;
	public static ConfigurationSection configCS;

	public static File messages;
	public static FileConfiguration messagesC;
	public static ConfigurationSection messagesCS;

	public static File doors;
	public static FileConfiguration doorsC;
	public static ConfigurationSection doorsCS;

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

	public static void getKeys() {
		keys = doorsC.getConfigurationSection("").getKeys(false);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<String> getWorlds() {
		return (ArrayList<String>) configC.get("WORLDS");
	}
}
