package co.uk.RandomPanda30.Hub;

import java.io.File;
import java.io.IOException;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import co.uk.RandomPanda30.Handlers.ConfigYML;
import co.uk.RandomPanda30.Handlers.MessageH;

public class Main extends JavaPlugin {

	public static Main plugin;
	public static ConfigYML configYML;
	public static PluginDescriptionFile pdfFile;

	public void onEnable() {
		File dataFolder;
		plugin = this;
		pdfFile = getDescription();
		dataFolder = getDataFolder();

		File config = new File(dataFolder
				+ "config.yml");

		if (!config.exists() || config == null) {
			MessageH.sendConsoleMessage("asffasasf");
			try {
				config.createNewFile();
				configYML.saveconfigYML();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			MessageH.sendBroadcastMessage("dfaadgdgadg");
		}

	}

	public void onDisable() {

	}
}