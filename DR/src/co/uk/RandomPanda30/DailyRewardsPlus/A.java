package co.uk.RandomPanda30.DailyRewardsPlus;

import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import co.uk.RandomPanda30.DailyRewardsPlus.Commands.X;
import co.uk.RandomPanda30.DailyRewardsPlus.Commands.XA;
import co.uk.RandomPanda30.DailyRewardsPlus.Events.E;
import co.uk.RandomPanda30.DailyRewardsPlus.Methods.M;
import co.uk.RandomPanda30.DailyRewardsPlus.Methods.P;

@SuppressWarnings("unchecked")
public class A extends JavaPlugin {

	ArrayList<String> sm;
	ArrayList<String> shm;

	@Override
	public void onEnable() {
		B.plugin = this;
		B.initDataFile();

		M.initConfig();
		M.initMessages();
		M.initData();
		M.initItems();

		registerCommands();
		E.registerEvents();

		sm = (ArrayList<String>) B.messagesC.get("START.MESSAGES");
		shm = (ArrayList<String>) B.messagesC.get("STOP.MESSAGES");

		for (String s : sm) {
			P.sendMessage(s, null);
		}

		if (!B.dataC.contains("Inventory")) {
			B.dataC.set("Inventory", new ArrayList<String>());
		}

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
	}

	@Override
	public void onDisable() {
		for (String s : shm) {
			P.sendMessage(s, null);
		}
		B.plugin = null;
	}

	public void registerCommands() {
		X handler = new X();
		handler.register("rewards", new XA());
		getCommand("rewards").setExecutor(handler);
	}
}