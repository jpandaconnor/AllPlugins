package co.uk.RandomPanda30.CityRP.Configs;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import co.uk.RandomPanda30.CityRP.CityRP;
import co.uk.RandomPanda30.CityRP.Data;
import co.uk.RandomPanda30.CityRP.Configs.Enums.Config;
import co.uk.RandomPanda30.CityRP.Resources.ExceptionUtil;
import co.uk.RandomPanda30.CityRP.Resources.ExceptionUtil.Cause;

public class ConfigH {

	private CityRP plugin;

	public ConfigH (CityRP plugin) {
		this.plugin = plugin;
		init();
	}

	public void init() {
		try {
			Data.config = new File(
					"plugins/" + plugin.getName() + "/config.yml");
			if (!Data.config.exists()) {
				Data.config.getParentFile().mkdirs();
				Data.config.createNewFile();
			}

			Data.configf = new YamlConfiguration();
			Data.configs = Data.configf.getConfigurationSection("");
			Data.configf.load(Data.config);

			for (Config value : Config.values()) {
				if (Data.configs
						.get(value.name().replaceAll("_", ".")) == null) {
					Data.configs.set(value.name().replaceAll("_", "."),
							value.value);
					save();
				}

				value.value = Data.configs
						.get(value.name().replaceAll("_", "."));
			}

			Data.configf.load(Data.config);
		} catch (IOException | InvalidConfigurationException e) {
			new ExceptionUtil(e.getStackTrace(), Cause.FILEWENTWRONG,
					"%EFailed to init the config");
		}
	}

	public void save() {
		try {
			Data.configf.save(Data.config);
		} catch (IOException e) {
			new ExceptionUtil(e.getStackTrace(), Cause.FILEWENTWRONG,
					"%EFailed to save the config");
		}
	}
}