package co.uk.RandomPanda30.Main;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static Main plugin;

	public static PluginDescriptionFile pdfFile;

	@Override
	public void onEnable() {
		plugin = this;
		pdfFile = plugin.getDescription();
	}

	@Override
	public void onDisable() {

	}
}
