package co.uk.RandomPanda30.Murge;

import java.util.List;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import co.uk.RandomPanda30.Murge.Configs.ConfigurationFile;
import co.uk.RandomPanda30.Murge.Configs.ConfigurationFile.FileType;

public class Murge extends JavaPlugin {
	
	private static Murge murge;
	private static PluginDescriptionFile pdfFile;
	
	@Override
	public void onEnable() {
		murge = this;
		pdfFile = this.getDescription();
		
		new ConfigurationFile(FileType.ALL);
	}
	
	@Override
	public void onDisable() {
		
		murge = null;
	}
	
	/*
	 * All details + information here
	 */
	
	public static Murge getPlugin() {
		return murge;
	}
	
	public static String getPluginName() {
		return pdfFile.getName();
	}
	
	public static String getVersion() {
		return pdfFile.getVersion();
	}
	
	public static List<String> getAuthors() {
		return pdfFile.getAuthors();
	}
}