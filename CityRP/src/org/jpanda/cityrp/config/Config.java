package org.jpanda.cityrp.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jpanda.cityrp.CityRP;

public class Config {
	
	public enum ConfigValues {
		MYSQL_SET(false),
		MYSQL_DBIP("null"),
		MYSQL_DBNAME("null"),
		MYSQL_DBUSER("null"),
		MYSQL_DBPASS("null"),
		MYSQL_DBPORT(0);
		
		public Object value;
		
		ConfigValues (Object value) {
			this.value = value;
		}
	}

	private CityRP plugin;
	
	static File config;
	static FileConfiguration configc;
	static ConfigurationSection configcs;
	
	public Config(CityRP plugin) {
		this.plugin = plugin;
		init();
	}
	
	private void init() {
		try {
			config = new File("plugins/" + plugin.getName() + "/config.yml");
			
			if(!config.exists()) {
				config.getParentFile().mkdirs();
				config.createNewFile();
			}
			
			configc = new YamlConfiguration();
			configcs = configc.getConfigurationSection("");
			configc.load(config);
		
			for(ConfigValues value : ConfigValues.values()) {
				if(configcs.get(value.name().replaceAll("_", ".")) == null) {
					configcs.set(value.name().replaceAll("_", "."), value.value);
					save();
				}
				value.value = configcs.get(value.name().replaceAll("_", "."));
			}
			
			configc.load(config);
		} catch(IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public static void save() {
		try {
			configc.save(config);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static File getFile() {
		return config;
	}
	
	public static FileConfiguration getConfiguration() {
		return configc;
	}
}