package co.uk.RandomPanda30.IAFK;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import co.uk.RandomPanda30.CMD.CMDiafk;
import co.uk.RandomPanda30.Events.OnPlayerMoveEvent;
import co.uk.RandomPanda30.Handlers.ConsoleSender;
import co.uk.RandomPanda30.Text.TextH;
import co.uk.mcstats.Metrics;

public class Main extends JavaPlugin {

	public static Main plugin;
	
	public static List<Player> player = new ArrayList<Player>();
	
	public static HashMap<String, Location> location = new HashMap<String, Location>();

	public final OnPlayerMoveEvent pm = new OnPlayerMoveEvent(this);

	public void onEnable() {

		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
			FileConfiguration metrics_fc = new YamlConfiguration();
			metrics_fc.load(metrics.getConfigFile());
			if (!metrics_fc.getBoolean("opt-out", false)) {
				Bukkit.getConsoleSender().sendMessage(
						"Sending MCStats to their servers!");
			} else {
				Bukkit.getConsoleSender()
						.sendMessage(
								"Error sending MCStats to their servers. Is MCStats disabled?");
			}
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage(
					"Could not send stats MCStats to their servers!");
		} catch (InvalidConfigurationException e) {
			Bukkit.getConsoleSender().sendMessage(
					"Could not send stats MCStats to their servers!");
		}

		ConsoleSender.consoleM(TextH.loading);

		getServer().getPluginManager().registerEvents(this.pm, this);

		getCommand("iafk").setExecutor(new CMDiafk(this));

	}

	public void onDisable() {
		ConsoleSender.consoleM(TextH.closing);
	}
}