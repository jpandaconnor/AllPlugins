package co.uk.RandomPanda30.VShop;

import java.io.File;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class B {

	public static Set<String> keys;

	public static File config;
	public static FileConfiguration configC;
	public static ConfigurationSection configCS;

	public static File messages;
	public static FileConfiguration messagesC;
	public static ConfigurationSection messagesCS;

	public static File shops;
	public static FileConfiguration shopsC;
	public static ConfigurationSection shopsCS;

	public static File items;
	public static FileConfiguration itemsC;
	public static ConfigurationSection itemsCS;

	public static void getKeys() {
		keys = shopsC.getConfigurationSection("").getKeys(false);
		keys.remove("safezone");
	}

}
