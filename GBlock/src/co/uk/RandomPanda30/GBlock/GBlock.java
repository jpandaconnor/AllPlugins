package co.uk.RandomPanda30.GBlock;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import co.uk.RandomPanda30.GBlock.Configs.Config;
import co.uk.RandomPanda30.GBlock.Configs.Messages;
import co.uk.RandomPanda30.GBlock.Configs.RPGConfig;
import co.uk.RandomPanda30.GBlock.Events.OnWorldInitListener;
import co.uk.RandomPanda30.GBlock.Plot.Plot;
import co.uk.RandomPanda30.GBlock.Plot.PlotsGenerator;

public class GBlock extends JavaPlugin {

	public GBlock plugin;
	public PluginDescriptionFile pdfFile;

	private static Config config;
	private static Messages messages;
	private static RPGConfig rpgconf;

	public void onEnable() {
		plugin = this;
		pdfFile = this.getDescription();

		config = new Config(this);
		messages = new Messages(this);
		rpgconf = new RPGConfig();

		this.plugin.getServer().getPluginManager()
				.registerEvents(new OnWorldInitListener(this), this);

		// this.plugin.getCommand("guild").setExecutor(new CommandGuild(this));

		// check if world = null

		Plot.load();
	}

	public void onDisable() {
		
		GBlockData.plots.clear();
		
		plugin = null;
	}

	public PluginDescriptionFile getPdfFile() {
		return pdfFile;
	}

	public ChunkGenerator getDefaultWorldGenerator(String worldName,
			String id) {
		return new PlotsGenerator();
	}

	public static Config getConfigFile() {
		return config;
	}

	public static Messages getMessagesFile() {
		return messages;
	}

	public static RPGConfig getRPGFile() {
		return rpgconf;
	}
}