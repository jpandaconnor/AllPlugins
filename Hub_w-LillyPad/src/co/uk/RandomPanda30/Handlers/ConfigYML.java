package co.uk.RandomPanda30.Handlers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import co.uk.RandomPanda30.Hub.Main;

public class ConfigYML {

	public ConfigYML (Main plugin) {
		Main.plugin = plugin;
	}

	private FileConfiguration configYML = null;
	private File configYMLFile = null;

	public void reloadconfigYML() {
		if (configYMLFile == null) {
			configYMLFile = new File("plugins/"
					+ Main.pdfFile.getFullName().toString() + "/config.yml");
		}
		configYML = YamlConfiguration.loadConfiguration(configYMLFile);

		InputStream defContactsStream = Main.plugin.getResource("config.yml");
		if (defContactsStream != null) {
			YamlConfiguration defContacts = YamlConfiguration
					.loadConfiguration(defContactsStream);
			configYML.setDefaults(defContacts);
		}
	}

	public FileConfiguration getconfigYML() {
		if (configYML == null) {
			reloadconfigYML();
		}
		return configYML;
	}

	public void saveconfigYML() {
		if (configYML == null || configYMLFile == null) {
			return;
		}
		try {
			getconfigYML().save(configYMLFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void defaultSaveconfigYML() {
		if (configYMLFile == null) {
			configYMLFile = new File("plugins/"
					+ Main.pdfFile.getFullName().toString() + "/config.yml");
		}
		if (!configYMLFile.exists()) {
			Main.plugin.saveResource("configYMLFile", false);
		}
	}

}