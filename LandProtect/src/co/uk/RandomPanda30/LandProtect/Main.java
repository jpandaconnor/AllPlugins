package co.uk.RandomPanda30.LandProtect;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import co.uk.RandomPanda30.LandProtect.Handlers.TextH;

public class Main extends JavaPlugin {
	
	public static Main plugin;

	public void onEnable() {
		plugin = this;
		
		File plotYml = new File(plugin.getDataFolder() + "/plots.yml");
		YamlConfiguration plotsYml = YamlConfiguration.loadConfiguration(plotYml);
		

		if (!plotYml.exists()) {
			try {
				plotYml.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			TextH.sendConsoleMessage("gay");
		}
	}

	public void onDisable() {

	}

}
