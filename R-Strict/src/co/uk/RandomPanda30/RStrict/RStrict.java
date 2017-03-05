package co.uk.RandomPanda30.RStrict;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class RStrict extends JavaPlugin implements Listener {

	public RStrict plugin;
	public PluginDescriptionFile pdfFile;

	public File config;
	public FileConfiguration configC;
	public ConfigurationSection configCS;

	public String tag = "&F[&CR-Strict&F]";

	@Override
	public void onEnable() {
		plugin = this;
		pdfFile = this.getDescription();

		sendMessage(tag + " &CLoading", null);

		getServer().getPluginManager().registerEvents(this, this);
		initConfig();
	}

	@Override
	public void onDisable() {
		sendMessage(tag + " &CClosing", null);

		plugin = null;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onSignChangeEvent(SignChangeEvent event) {
		String[] lines = event.getLines();
		Block block = event.getBlock();

		Player player = event.getPlayer();

		for (String line : lines) {
			if (line.length() > 16 || line.contains("|")) {
				block.breakNaturally();

				sendMessage(tag + " " + configC.get("MESSAGE.ONRULEBREAK"),
						player);

				if (configC.get("COMMANDS.ONRULEBREAK.ASCONSOLE") != null) {
					@SuppressWarnings("unchecked")
					ArrayList<String> cmds = (ArrayList<String>) configC
							.get("COMMANDS.ONRULEBREAK.ASCONSOLE");
					for (String cmd : cmds) {
						Bukkit.getServer().dispatchCommand(
								Bukkit.getConsoleSender(),
								cmd.replaceAll("%player", player.getName()));
					}
				}

				if (configC.get("COMMANDS.ONRULEBREAK.ASPLAYER") != null) {
					@SuppressWarnings("unchecked")
					ArrayList<String> cmds = (ArrayList<String>) configC
							.get("COMMANDS.ONRULEBREAK.ASPLAYER");
					for (String cmd : cmds) {
						player.performCommand(cmd);
					}
				}

				break;
			}
		}
	}

	@SuppressWarnings("serial")
	public enum Config {
		MESSAGE_ONRULEBREAK ("&CYou cannot do this!"),

		COMMANDS_ONRULEBREAK_ASCONSOLE (new ArrayList<String>() {
			{

			}
		}),

		COMMANDS_ONRULEBREAK_ASPLAYER (new ArrayList<String>() {
			{

			}
		});

		public Object value;

		Config (Object value) {
			this.value = value;
		}
	}

	public void initConfig() {
		try {
			config = new File("plugins/" + "RStrict", "config.yml");
			if (!config.exists()) {
				config.getParentFile().mkdirs();
				config.createNewFile();
			}

			configC = new YamlConfiguration();
			configCS = configC.getConfigurationSection("");
			configC.load(config);

			for (Config value : Config.values()) {
				if (configCS.get(value.name().replaceAll("_", ".")) == null) {
					configCS.set(value.name().replaceAll("_", "."), value.value);
					configC.save(config);
				}

				value.value = configCS.get(value.name().replaceAll("_", "."));
			}

			configC.load(config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String message, Player player) {
		if (player == null) {
			Bukkit.getConsoleSender().sendMessage(
					ChatColor.translateAlternateColorCodes('&', message));
		} else {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&',
					message));
		}
	}

}