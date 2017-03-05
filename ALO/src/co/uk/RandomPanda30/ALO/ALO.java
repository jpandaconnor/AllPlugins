package co.uk.RandomPanda30.ALO;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import co.uk.RandomPanda30.ALO.Files.Config;

public class ALO extends JavaPlugin implements Listener, CommandExecutor {

	private ALO instance;

	private String permPrefix = "alo.";

	private File configF;
	private FileConfiguration configC;
	private ConfigurationSection configCS;

	/*
	 * 
	 * Perms -
	 * 
	 * alo.pro - 3 coins alo.ultimate - 5 coins
	 */

	@Override
	public void onEnable() {
		setPlugin(this);
		initConfig();

		sendMessage("&FLoading &5ALO", null);

		getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable() {
		sendMessage("&FClosing &5ALO", null);
		setPlugin(null);
	}

	public ALO getPlugin() {
		return instance;
	}

	public void setPlugin(ALO instance) {
		this.instance = instance;
	}

	public String formatMessage(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}

	public void sendMessage(String string, Player player) {
		if (player != null) {
			player.sendMessage(formatMessage(string));
		} else {
			Bukkit.getConsoleSender().sendMessage(formatMessage(string));
		}
	}

	@SuppressWarnings("unchecked")
	@EventHandler
	public void onPlayerDeathEvent(PlayerDeathEvent event) {
		if (event.getEntity().getKiller() instanceof Player) {
			Player player = event.getEntity().getKiller();

			ArrayList<String> commands;
			if (player.hasPermission(permPrefix + "pro")) {
				commands = (ArrayList<String>) getConfigC().get(
						"KILLS.COMMANDS.PRO");
				for (String s : commands) {
					instance.getPlugin()
							.getServer()
							.dispatchCommand(Bukkit.getConsoleSender(),
									s.replace("%name", player.getName()));
				}
			} else if (player.hasPermission(permPrefix + "ultimate")) {
				commands = (ArrayList<String>) getConfigC().get(
						"KILLS.COMMANDS.ULTIMATE");
				for (String s : commands) {
					instance.getPlugin()
							.getServer()
							.dispatchCommand(Bukkit.getConsoleSender(),
									s.replace("%name", player.getName()));
				}
			} else {
				commands = (ArrayList<String>) getConfigC().get(
						"KILLS.COMMANDS.DEFAULT");
				for (String s : commands) {
					instance.getPlugin()
							.getServer()
							.dispatchCommand(Bukkit.getConsoleSender(),
									s.replace("%name", player.getName()));
				}
			}
		}
	}

	public void initConfig() {
		try {
			setConfigF(new File("plugins/"
					+ instance.getPlugin().getDescription().getName(),
					"config.yml"));
			if (!getConfigF().exists()) {
				getConfigF().getParentFile().mkdirs();
				getConfigF().createNewFile();
			}

			setConfigC(new YamlConfiguration());
			setConfigCS(getConfigC().getConfigurationSection(""));
			getConfigC().load(getConfigF());

			for (Config value : Config.values()) {
				if (getConfigCS().get(value.name().replaceAll("_", ".")) == null) {
					getConfigCS().set(value.name().replaceAll("_", "."),
							value.value);
					getConfigC().save(getConfigF());
				}

				value.value = getConfigCS().get(
						value.name().replaceAll("_", "."));
			}

			getConfigC().load(getConfigF());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public File getConfigF() {
		return configF;
	}

	public void setConfigF(File configF) {
		this.configF = configF;
	}

	public FileConfiguration getConfigC() {
		return configC;
	}

	public void setConfigC(FileConfiguration configC) {
		this.configC = configC;
	}

	public ConfigurationSection getConfigCS() {
		return configCS;
	}

	public void setConfigCS(ConfigurationSection configCS) {
		this.configCS = configCS;
	}
}