package co.uk.RandomPanda30.PvpPlates.Methods;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

import co.uk.RandomPanda30.PvpPlates.PvpPlates;
import co.uk.RandomPanda30.PvpPlates.Files.Config;

public class ConfigMethods {

	private static ConfigMethods instance = new ConfigMethods();

	public static ConfigMethods c() {
		return instance;
	}

	public void initConfig() {
		try {
			PvpPlates.setConfigF(new File("plugins/"
					+ PvpPlates.getPdfFile().getName(), "config.yml"));
			if (!PvpPlates.getConfigF().exists()) {
				PvpPlates.getConfigF().getParentFile().mkdirs();
				PvpPlates.getConfigF().createNewFile();
			}

			PvpPlates.setConfigFC(new YamlConfiguration());
			PvpPlates.setConfigFS(PvpPlates.getConfigFC()
					.getConfigurationSection(""));
			PvpPlates.getConfigFC().load(PvpPlates.getConfigF());

			for (Config value : Config.values()) {
				if (PvpPlates.getConfigFS().get(
						value.name().replaceAll("_", ".")) == null) {
					PvpPlates.getConfigFS().set(
							value.name().replaceAll("_", "."), value.value);
					PvpPlates.getConfigFC().save(PvpPlates.getConfigF());
				}

				value.value = PvpPlates.getConfigFS().get(
						value.name().replaceAll("_", "."));
			}

			PvpPlates.getConfigFC().load(PvpPlates.getConfigF());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initData() {
		try {
			PvpPlates.setDataF(new File("plugins/"
					+ PvpPlates.getPdfFile().getName(), "data.yml"));
			if (!PvpPlates.getDataF().exists()) {
				PvpPlates.getDataF().getParentFile().mkdirs();
				PvpPlates.getDataF().createNewFile();
			}

			PvpPlates.setDataFC(new YamlConfiguration());
			PvpPlates.setDataFS(PvpPlates.getDataFC().getConfigurationSection(
					""));

			PvpPlates.getDataFC().load(PvpPlates.getDataF());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initAll() {
		initConfig();
		initData();
	}
}