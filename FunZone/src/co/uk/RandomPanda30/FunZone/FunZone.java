package co.uk.RandomPanda30.FunZone;

import java.io.File;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import co.uk.RandomPanda30.FunZone.Configs.Config;
import co.uk.RandomPanda30.FunZone.Configs.Messages;
import co.uk.RandomPanda30.FunZone.Register.EventRegister;

public class FunZone extends JavaPlugin {

	public FunZone plugin;
	public PluginDescriptionFile pdfFile;

	private PValues pvalues;
	public Config config;
	public Messages messages;

	public File configF;
	public FileConfiguration configCF;
	public ConfigurationSection configCSF;

	@Override
	public void onEnable() {
		plugin = this;
		pdfFile = this.getDescription();

		pvalues = new PValues(this);
		config = new Config(this);
		messages = new Messages(this);
		
		EventRegister er = new EventRegister(this);
		er.registerEvents();
		
		
	}

	@Override
	public void onDisable() {

	}

	@Override
	public FileConfiguration getConfig() {
		return configCF;
	}

	public FileConfiguration getMessages() {
		return null;
	}

	public PluginDescriptionFile getPdfFile() {
		return pdfFile;
	}
	
	public PValues getPValues() {
		return pvalues;
	}
}
