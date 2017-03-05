package co.uk.RandomPanda30.Main;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import co.uk.RandomPanda30.CMD.CMDhub;
import co.uk.RandomPanda30.Events.OnEntityDamageByEntityEvent;
import co.uk.RandomPanda30.Events.OnEntityDamageEvent;
import co.uk.RandomPanda30.Events.OnFoodLevelChangeEvent;
import co.uk.RandomPanda30.Events.OnPlayerChatEvent;
import co.uk.RandomPanda30.Events.OnPlayerCommandPreprocessEvent;
import co.uk.RandomPanda30.Events.OnPlayerDropItemEvent;
import co.uk.RandomPanda30.Events.OnPlayerInteractEvent;
import co.uk.RandomPanda30.Events.OnPlayerJoinEvent;
import co.uk.RandomPanda30.Events.OnPlayerQuitEvent;
import co.uk.RandomPanda30.Events.OnWeatherChangeEvent;
import co.uk.RandomPanda30.Handlers.Start_ShutHandler;
import co.uk.RandomPanda30.Handlers.TextH;

public class Main extends JavaPlugin {

	public static Main plugin;

	public static TextH texth;

	public final OnFoodLevelChangeEvent flc = new OnFoodLevelChangeEvent(this);
	public final OnWeatherChangeEvent wc = new OnWeatherChangeEvent(this);
	public final OnPlayerDropItemEvent pdi = new OnPlayerDropItemEvent(this);
	public final OnPlayerJoinEvent pj = new OnPlayerJoinEvent(this);
	public final OnPlayerQuitEvent pq = new OnPlayerQuitEvent(this);
	public final OnPlayerInteractEvent pi = new OnPlayerInteractEvent(this);
	public final OnEntityDamageByEntityEvent edbe = new OnEntityDamageByEntityEvent(
			this);
	public final OnEntityDamageEvent ed = new OnEntityDamageEvent(this);
	public final OnPlayerChatEvent pc = new OnPlayerChatEvent(this);
	public final OnPlayerCommandPreprocessEvent pcpe = new OnPlayerCommandPreprocessEvent(
			this);

	public static HashMap<String, Player> clicked = new HashMap<String, Player>();
	public static ArrayList<String> bannedWords = new ArrayList<String>();
	public static ArrayList<String> bannedCommands = new ArrayList<String>();
	public static ArrayList<String> gamemodeOnSpawn = new ArrayList<String>();

	public void onEnable() {
		Start_ShutHandler.startup();

		FileConfiguration config = getConfig();

		config.addDefault("HungerLoss", false);
		config.addDefault("RainOn", false);
		config.addDefault("AllowDrops", false);
		config.addDefault("LeaveMessage", false);
		config.addDefault("ClearInventory", false);
		config.addDefault("ForceSpawnInHub", true);
		config.addDefault("BannedWords", bannedWords);
		config.addDefault("BannedCommands", bannedCommands);
		config.addDefault("SpecialPlayers", gamemodeOnSpawn);

		config.options().copyDefaults(true);
		this.saveDefaultConfig();
		this.saveConfig();

		getServer().getPluginManager().registerEvents(this.flc, this);
		getServer().getPluginManager().registerEvents(this.wc, this);
		getServer().getPluginManager().registerEvents(this.pdi, this);
		getServer().getPluginManager().registerEvents(this.pj, this);
		getServer().getPluginManager().registerEvents(this.pq, this);
		getServer().getPluginManager().registerEvents(this.pi, this);
		getServer().getPluginManager().registerEvents(this.edbe, this);
		getServer().getPluginManager().registerEvents(this.ed, this);
		getServer().getPluginManager().registerEvents(this.pc, this);
		getServer().getPluginManager().registerEvents(this.pcpe, this);
		
		getCommand("hub").setExecutor(new CMDhub(this));
	}

	public void onDisable() {
		Start_ShutHandler.shutdown();
	}

}
