package co.uk.RandomPanda30.Markets;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import co.uk.RandomPanda30.Commands.Command_Wand;
import co.uk.RandomPanda30.Handlers.ConfigH;
import co.uk.RandomPanda30.Handlers.MessageH;
import co.uk.RandomPanda30.Listeners.OnPlayerInteract;

public class Main extends JavaPlugin implements Listener {

	public static HashMap<String, Location> pos1 = new HashMap<String, Location>();
	public static HashMap<String, Location> pos2 = new HashMap<String, Location>();

	public static Main plugin;
	public static PluginDescriptionFile desc;

	public void onEnable() {
		plugin = this;
		Main.desc = getDescription();

		ConfigH.checkConfig();
		ConfigH.checkShops();
		
		MessageH.sendCMDMessage(MessageH.loadingMarkets);
		MessageH.sendCMDMessage(MessageH.madeBy);

		getCommand("mk").setExecutor(new Command_Wand(this));

		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(
				new OnPlayerInteract(this), this);

		this.saveDefaultConfig();
		this.saveConfig();
	}

	public void onDisable() {
		MessageH.sendCMDMessage(MessageH.closingMarkets);
	}
}
