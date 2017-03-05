package co.uk.RandomPanda30.GBlock.Configs;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class RPGConfig {
	
	Plugin rpg = Bukkit.getPluginManager().getPlugin("RpgGuilds");
	String path = rpg.getDataFolder().getPath();

	public RPGConfig () {
	}
	
	public FileConfiguration getConfiguration() {
		return rpg.getConfig();
	}
	
	public void saveConfig() {
		rpg.saveConfig();
	}
}
