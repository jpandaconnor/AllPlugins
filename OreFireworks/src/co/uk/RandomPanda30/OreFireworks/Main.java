package co.uk.RandomPanda30.OreFireworks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static Main plugin;

	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage("OreFireworks has been Enabled!");

		getCommand("test").setExecutor(new MinedListener(this));

		Bukkit.getServer().getPluginManager()
				.registerEvents(new MinedListener(plugin), this);
	}

	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("OreFireworks has been Disabled");
	}
}
