package co.uk.RandomPanda30.MWarn;

import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import co.uk.RandomPanda30.MWarn.Commands.CA;
import co.uk.RandomPanda30.MWarn.Commands.CB;
import co.uk.RandomPanda30.MWarn.Commands.CC;
import co.uk.RandomPanda30.MWarn.Commands.CD;
import co.uk.RandomPanda30.MWarn.Events.E;
import co.uk.RandomPanda30.MWarn.Methods.WA;
import co.uk.RandomPanda30.MWarn.Methods.XA;
import co.uk.RandomPanda30.MWarn.Methods.Y;
import co.uk.RandomPanda30.MWarn.Methods.Z;

public class A extends JavaPlugin {

	ArrayList<String> sm;
	ArrayList<String> shm;

	BukkitTask checker;

	@SuppressWarnings("unchecked")
	@Override
	public void onEnable() {
		B.plugin = this;
		B.initDataFile();

		Z.initConfig();
		Z.initBans();
		Z.initData();
		Z.initItems();
		Z.initMessages();

		registerCommands();
		E.registerEvents();

		B.getKeys();

		sm = (ArrayList<String>) B.messagesC.get("START.MESSAGES");
		shm = (ArrayList<String>) B.messagesC.get("STOP.MESSAGES");

		checker = new WA(this).runTaskTimer(this, 0, 10 * 60 * 20);

		for (String s : sm) {
			Y.sendMessage(s, null);
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
		checker.cancel();
		for (String s : shm) {
			Y.sendMessage(s, null);
		}

		B.deinitConfigs();

		B.plugin = null;
	}

	public static void registerCommands() {
		XA handler = new XA();
		handler.register("mwarn", new CA());
		handler.register("warn", new CB());
		handler.register("clear", new CC());
		handler.register("remove", new CD());
		B.plugin.getCommand("mwarn").setExecutor(handler);
	}
}