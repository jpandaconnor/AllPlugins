package co.uk.RandomPanda30.AdminPlus;

import java.util.ArrayList;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import co.uk.RandomPanda30.Events.OnInventoryClickEvent;
import co.uk.RandomPanda30.Events.OnPlayerInteractEntityEvent;
import co.uk.RandomPanda30.Handlers.Messages;
import co.uk.RandomPanda30.Handlers.Methods;

public class AdminPlus extends JavaPlugin {

	public static AdminPlus plugin;
	public static PluginDescriptionFile pdfFile;

	public static ArrayList<String> lookAtPlayer = new ArrayList<String>();

	final OnPlayerInteractEntityEvent pie = new OnPlayerInteractEntityEvent(
			this);
	final OnInventoryClickEvent ice = new OnInventoryClickEvent(this);

	@Override
	public void onEnable() {
		plugin = this;
		pdfFile = this.getDescription();
		Methods.startupMessages(Messages.startupM);

		getServer().getPluginManager().registerEvents(pie, this);
		getServer().getPluginManager().registerEvents(ice, this);
	}

	@Override
	public void onDisable() {
		plugin = null;

		Methods.shutdownMessages(Messages.shutdownM);
	}

}
