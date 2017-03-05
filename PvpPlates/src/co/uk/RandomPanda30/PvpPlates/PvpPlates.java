package co.uk.RandomPanda30.PvpPlates;

import java.io.File;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import co.uk.RandomPanda30.PvpPlates.Methods.ConfigMethods;

public class PvpPlates extends JavaPlugin {

	private static PvpPlates plugin;
	private static PluginDescriptionFile pdfFile;

	private static File configF;
	private static FileConfiguration configFC;
	private static ConfigurationSection configFS;

	private static File dataF;
	private static FileConfiguration dataFC;
	private static ConfigurationSection dataFS;

	@Override
	public void onEnable() {
		setPlugin(this);
		setPdfFile(this.getDescription());

		ConfigMethods.c().initAll();
	}

	@Override
	public void onDisable() {
		setPlugin(null);
	}

	public static PvpPlates getPlugin() {
		return plugin;
	}

	public static void setPlugin(PvpPlates plugin) {
		PvpPlates.plugin = plugin;
	}

	public static PluginDescriptionFile getPdfFile() {
		return pdfFile;
	}

	public static void setPdfFile(PluginDescriptionFile pdfFile) {
		PvpPlates.pdfFile = pdfFile;
	}

	public static File getConfigF() {
		return PvpPlates.configF;
	}

	public static void setConfigF(File configF) {
		PvpPlates.configF = configF;
	}

	public static FileConfiguration getConfigFC() {
		return PvpPlates.configFC;
	}

	public static void setConfigFC(FileConfiguration configFC) {
		PvpPlates.configFC = configFC;
	}

	public static ConfigurationSection getConfigFS() {
		return PvpPlates.configFS;
	}

	public static void setConfigFS(ConfigurationSection configFS) {
		PvpPlates.configFS = configFS;
	}

	public static File getDataF() {
		return dataF;
	}

	public static void setDataF(File dataF) {
		PvpPlates.dataF = dataF;
	}

	public static FileConfiguration getDataFC() {
		return dataFC;
	}

	public static void setDataFC(FileConfiguration dataFC) {
		PvpPlates.dataFC = dataFC;
	}

	public static ConfigurationSection getDataFS() {
		return dataFS;
	}

	public static void setDataFS(ConfigurationSection dataFS) {
		PvpPlates.dataFS = dataFS;
	}
}