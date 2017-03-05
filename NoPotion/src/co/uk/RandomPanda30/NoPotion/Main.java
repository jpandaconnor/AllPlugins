package co.uk.RandomPanda30.NoPotion;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import co.uk.RandomPanda30.Listeners.OnPlayerInteractEvent;

public class Main extends JavaPlugin {

	public static Main plugin;
	public static PluginDescriptionFile pdfFile;

	public final OnPlayerInteractEvent pie = new OnPlayerInteractEvent(this);

	@Override
	public void onEnable() {
		plugin = this;
		pdfFile = this.getDescription();

		Bukkit.getConsoleSender().sendMessage(
				ChatColor.GOLD + "NoPotions is being enabled");

		this.getServer().getPluginManager().registerEvents(pie, this);
	}

	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(
				ChatColor.GOLD + "NoPotions is being disabled");
	}

}
