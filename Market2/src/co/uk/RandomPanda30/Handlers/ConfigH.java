package co.uk.RandomPanda30.Handlers;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import co.uk.RandomPanda30.Markets.Main;

public class ConfigH {

	static File config = new File("plugins/" + Main.desc.getName()
			+ "config.yml");
	static File shops = new File("plugins/" + Main.desc.getName() + "shops.yml");

	public static void checkConfig() {
		if (!config.exists()) {
			try {
				config.createNewFile();
				YamlConfiguration.loadConfiguration(config);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			YamlConfiguration.loadConfiguration(config);
		}
	}
	
	public static void checkShops() {
		if (!shops.exists()) {
			try {
				shops.createNewFile();
				YamlConfiguration.loadConfiguration(shops);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			YamlConfiguration.loadConfiguration(shops);
		}
	}

	public static void saveConfig() {
		try {
			YamlConfiguration.loadConfiguration(config).save(config);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveShops() {
		try {
			YamlConfiguration.loadConfiguration(shops).save(shops);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadShops() {
		if (shops == null) {
			checkShops();
		} else {
			YamlConfiguration.loadConfiguration(shops);
		}
	}
	
	public static void loadConfig() {
		if (config == null) {
			checkConfig();
		} else {
			YamlConfiguration.loadConfiguration(config);
		}
	}
}
