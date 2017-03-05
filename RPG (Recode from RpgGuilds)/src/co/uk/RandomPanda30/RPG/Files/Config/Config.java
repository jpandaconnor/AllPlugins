package co.uk.RandomPanda30.RPG.Files.Config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import co.uk.RandomPanda30.RPG.RPG;
import co.uk.RandomPanda30.RPG.RPGValues;
import co.uk.RandomPanda30.RPG.Files.IConfig;
import co.uk.RandomPanda30.RPG.Files.Config.Enums.ConfigDefault;

public class Config implements IConfig {

	public RPG plugin;
	public RPGValues values;

	public Config (RPG plugin, RPGValues values) {
		this.plugin = plugin;
		this.values = values;
	}

	@Override
	public void init() {
		try {
			values.setConfig(new File("plugins/"
					+ plugin.getPdfFile().getName(), "config.yml"));
			if (!values.getConfig().exists()) {
				values.getConfig().getParentFile().mkdirs();
				values.getConfig().createNewFile();
			}

			values.setConfigC(new YamlConfiguration());
			values.setConfigCS(values.getConfigC().getConfigurationSection(""));
			values.getConfigC().load(values.getConfig());

			for (ConfigDefault value : ConfigDefault.values()) {
				if (values.getConfigCS().get(value.name().replaceAll("_", ".")) == null) {
					values.getConfigCS().set(value.name().replaceAll("_", "."),
							value.value);
					values.getConfigC().save(values.getConfig());
				}
				value.value = values.getConfigCS().get(
						value.name().replaceAll("_", "."));
			}
			values.getConfigC().load(values.getConfig());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void save() {
		try {
			values.getConfigC().save(values.getConfig());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remove() {
		values.getConfig().delete();
	}

	@Override
	public void reset() {
		remove();
		init();
	}
}