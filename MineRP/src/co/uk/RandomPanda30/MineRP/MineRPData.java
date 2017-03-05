package co.uk.RandomPanda30.MineRP;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;

import co.uk.RandomPanda30.MineRP.Handlers.PlayerHandler;

public class MineRPData {

	public static MineRP plugin;
	private static PluginDescriptionFile pdfFile;

	public static File config;
	public static FileConfiguration configC;
	public static ConfigurationSection configCS;

	public static File messages;
	public static FileConfiguration messagesC;
	public static ConfigurationSection messagesCS;

	public static File data;
	public static FileConfiguration dataC;
	public static ConfigurationSection dataCS;

	public static File doors;
	public static FileConfiguration doorsC;
	public static ConfigurationSection doorsCS;

	public static File citizen;
	public static FileConfiguration citizenC;
	public static ConfigurationSection citizenCS;

	public static World world;

	public static ArrayList<PlayerHandler> players = new ArrayList<PlayerHandler>();

	public static MineRP getPlugin() {
		return plugin;
	}

	public static void initDataFile() {
		pdfFile = plugin.getDescription();
	}

	public static PluginDescriptionFile getDataFile() {
		return pdfFile;
	}

	public static String getAuthor(int no) {
		return getDataFile().getAuthors().get(no);
	}
}