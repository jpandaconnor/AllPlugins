package co.uk.RandomPanda30.CasinoM;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;

public class CasinoData {

	private static Location pos1;
	private static Location pos2;

	public static CasinoM plugin;

	public static File config;
	public static FileConfiguration configC;
	public static ConfigurationSection configCS;

	public static File messages;
	public static FileConfiguration messagesC;
	public static ConfigurationSection messagesCS;

	public static File items;
	public static FileConfiguration itemsC;
	public static ConfigurationSection itemsCS;

	public static File slotmachines;
	public static FileConfiguration slotmachinesC;
	public static ConfigurationSection slotmachinesCS;

	public static PluginDescriptionFile pdfFile;

	public static ArrayList<UUID> drawing = new ArrayList<UUID>();
	public static ArrayList<UUID> inSlotMachine = new ArrayList<UUID>();

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

	public static CasinoM getPlugin() {
		return plugin;
	}

	public static String getAuthor(int no) {
		return getDataFile().getAuthors().get(no);
	}

	public static void setPosition(int no, Location location) {
		if (no == 1) {
			pos1 = location;
		} else {
			pos2 = location;
		}
	}

	public static Location getPosition(int no) {
		if (no == 1) {
			return pos1;
		}
		return pos2;
	}
}