package co.uk.RandomPanda30.MWarn;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;

public class B {

	// Very naughty
	public static A plugin;

	public static Set<String> keys;

	private static PluginDescriptionFile pdfFile;

	public static File bans;
	public static FileConfiguration bansC;
	public static ConfigurationSection bansCS;

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

	public static ArrayList<UUID> sam = new ArrayList<UUID>();
	public static HashMap<UUID, UUID> samUUID = new HashMap<UUID, UUID>();

	public static ArrayList<UUID> srm = new ArrayList<UUID>();
	public static HashMap<UUID, UUID> srmUUID = new HashMap<UUID, UUID>();

	public static ArrayList<UUID> scm = new ArrayList<UUID>();
	public static HashMap<UUID, UUID> scmUUID = new HashMap<UUID, UUID>();

	public static ArrayList<UUID> sbm = new ArrayList<UUID>();
	public static HashMap<UUID, UUID> sbmUUID = new HashMap<UUID, UUID>();

	public static ArrayList<UUID> subm = new ArrayList<UUID>();
	public static HashMap<UUID, UUID> submUUID = new HashMap<UUID, UUID>();

	public static ArrayList<ItemStack> ei = new ArrayList<ItemStack>();

	public static void initDataFile() {
		pdfFile = plugin.getDescription();
	}

	public static void deinitConfigs() {
		config = null;
		messages = null;
		data = null;
		items = null;
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

	public static void getKeys() {
		keys = dataC.getConfigurationSection("").getKeys(false);
	}
}