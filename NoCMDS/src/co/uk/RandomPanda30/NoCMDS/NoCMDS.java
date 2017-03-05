package co.uk.RandomPanda30.NoCMDS;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class NoCMDS extends JavaPlugin implements Listener {

	public NoCMDS plugin;
	public static PluginDescriptionFile pdfFile;

	public static File config;
	public static FileConfiguration configC;
	public static ConfigurationSection configCS;

	@Override
	public void onEnable() {
		plugin = this;
		pdfFile = this.getDescription();

		CM.initConfig();

		Bukkit.getConsoleSender().sendMessage(ChatColor
				.translateAlternateColorCodes('&', "&BNo commands..."));

		getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor
				.translateAlternateColorCodes('&', "&BNo commands..."));
		plugin = null;
	}

	@SuppressWarnings("unchecked")
	@EventHandler
	public void onPreprocess(PlayerCommandPreprocessEvent event) {
		ArrayList<String> commands = (ArrayList<String>) configC
				.get("COMMANDS");
		for (String s : commands) {
			if (event.getMessage().equals(s)) {
				event.getPlayer()
						.sendMessage(ChatColor.translateAlternateColorCodes('&',
								"&CThis command has been disabled in the config"));
				event.setCancelled(true);
			}
		}
	}
}