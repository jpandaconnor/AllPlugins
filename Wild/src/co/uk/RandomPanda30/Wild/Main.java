package co.uk.RandomPanda30.Wild;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import co.uk.RandomPanda30.Wild.Commands.Command_wild;
import co.uk.RandomPanda30.Wild.Handler.MessageH;
import co.uk.RandomPanda30.Wild.Listeners.OnPlayerInteractEvent;
import co.uk.RandomPanda30.Wild.Listeners.OnSignChangeEvent;

public class Main extends JavaPlugin {

	public static Main plugin;
	public static PluginDescriptionFile pdfFile;

	OnSignChangeEvent sce = new OnSignChangeEvent(this);
	OnPlayerInteractEvent pie = new OnPlayerInteractEvent(this);

	public static HashMap<UUID, Long> inmap = new HashMap<>();

	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		plugin = this;
		pdfFile = this.getDescription();

		// OnPlayerTeleportEvent pte = new OnPlayerTeleportEvent(this);

		MessageH.sendCMDMessage(MessageH.loadingBasics);
		MessageH.sendCMDMessage(MessageH.madeBy);

		getCommand("wild").setExecutor(new Command_wild(this));

		// getCommand("ad").setExecutor(new Command_ad(this));
		// getCommand("apply").setExecutor(new Command_apply(this));
		// getCommand("donate").setExecutor(new Command_donate(this));
		// getCommand("vote").setExecutor(new Command_vote(this));
		// ge tCommand("facebook").setExecutor(new Command_fb(this));
		// getCommand("twitter").setExecutor(new Command_twitter(this));

		// getServer().getPluginManager().registerEvents(pte, this);

		getServer().getPluginManager().registerEvents(sce, this);
		getServer().getPluginManager().registerEvents(pie, this);

		this.getServer().getScheduler().scheduleAsyncRepeatingTask(this,
				new Runnable() {
					@Override
					public void run() {
						Iterator<Map.Entry<UUID, Long>> iter = inmap.entrySet()
								.iterator();
						while (iter.hasNext()) {
							Map.Entry<UUID, Long> entry = iter.next();
							if (Calendar.getInstance().getTimeInMillis() < entry
									.getValue()) {
								iter.remove();
							}
						}
					}
				}, 0, 1200);
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
