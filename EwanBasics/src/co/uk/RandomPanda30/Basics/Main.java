package co.uk.RandomPanda30.Basics;

import java.util.ArrayList;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import co.uk.RandomPanda30.Commands.Command_apply;
import co.uk.RandomPanda30.Commands.Command_donate;
import co.uk.RandomPanda30.Commands.Command_ad;
import co.uk.RandomPanda30.Commands.Command_fb;
import co.uk.RandomPanda30.Commands.Command_twitter;
import co.uk.RandomPanda30.Commands.Command_vote;
import co.uk.RandomPanda30.Handler.MessageH;
import co.uk.RandomPanda30.Listeners.OnPlayerInteractEvent;
import co.uk.RandomPanda30.Listeners.OnSignChangeEvent;

public class Main extends JavaPlugin {

	public static Main plugin;
	public static PluginDescriptionFile pdfFile;
	
	OnSignChangeEvent sce = new OnSignChangeEvent(this);
	OnPlayerInteractEvent pie = new OnPlayerInteractEvent(this);
	
	@Override
	public void onEnable() {
		plugin = this;
		pdfFile = this.getDescription();

		// OnPlayerTeleportEvent pte = new OnPlayerTeleportEvent(this);

		MessageH.sendCMDMessage(MessageH.loadingBasics);
		MessageH.sendCMDMessage(MessageH.madeBy);

		getCommand("ad").setExecutor(new Command_ad(this));
		getCommand("apply").setExecutor(new Command_apply(this));
		getCommand("donate").setExecutor(new Command_donate(this));
		getCommand("vote").setExecutor(new Command_vote(this));
		getCommand("facebook").setExecutor(new Command_fb(this));
		getCommand("twitter").setExecutor(new Command_twitter(this));

		// getServer().getPluginManager().registerEvents(pte, this);
		
		getServer().getPluginManager().registerEvents(sce, this);
		getServer().getPluginManager().registerEvents(pie, this);

		checkConfigOptions();
		this.saveDefaultConfig();
		this.saveConfig();
	}

	public void onDisable() {
		MessageH.sendCMDMessage(MessageH.closingBasics);
	}

	public void checkConfigOptions() {
		ArrayList<String> adminNames = (ArrayList<String>) this.getConfig()
				.getStringList("adminNames");
		ArrayList<String> randomSpawnWorlds = (ArrayList<String>) this
				.getConfig().getStringList("randomSpawnWorlds");

		if (!adminNames.contains("RandomPanda30")) {
			adminNames.add("RandomPanda30");
		}

		if (!adminNames.contains("Zeptars")) {
			adminNames.add("Zeptars");
		}

		if (!adminNames.contains("fishf12")) {
			adminNames.add("fishf12");
		}

		if (!adminNames.contains("rcjoyce")) {
			adminNames.add("rcjoyce");
		}

		if (!randomSpawnWorlds.contains("wilderness")) {
			randomSpawnWorlds.add("wilderness");
		}

		this.getConfig().set("adminNames", adminNames);
		this.getConfig().set("randomSpawnWorlds", randomSpawnWorlds);
	}
}
