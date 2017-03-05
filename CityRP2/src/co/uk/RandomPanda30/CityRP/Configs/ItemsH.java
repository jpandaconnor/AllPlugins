package co.uk.RandomPanda30.CityRP.Configs;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import co.uk.RandomPanda30.CityRP.CityRP;
import co.uk.RandomPanda30.CityRP.Data;
import co.uk.RandomPanda30.CityRP.Configs.Enums.Items;
import co.uk.RandomPanda30.CityRP.Resources.ExceptionUtil;
import co.uk.RandomPanda30.CityRP.Resources.ExceptionUtil.Cause;

public class ItemsH {

	private CityRP plugin;

	public ItemsH (CityRP plugin) {
		this.plugin = plugin;
		init();
	}

	public void init() {
		try {
			Data.items = new File("plugins/" + plugin.getName() + "/items.yml");
			if (!Data.items.exists()) {
				Data.items.getParentFile().mkdirs();
				Data.items.createNewFile();
			}

			Data.itemsf = new YamlConfiguration();
			Data.itemss = Data.itemsf.getConfigurationSection("");
			Data.itemsf.load(Data.items);

			for (Items value : Items.values()) {
				if (Data.itemss
						.get(value.name().replaceAll("_", ".")) == null) {
					Data.itemss.set(value.name().replaceAll("_", "."),
							value.value);
					save();
				}

				value.value = Data.itemss
						.get(value.name().replaceAll("_", "."));
			}

			Data.itemsf.load(Data.items);
		} catch (IOException | InvalidConfigurationException e) {
			new ExceptionUtil(e.getStackTrace(), Cause.FILEWENTWRONG,
					"%EFailed to init the items");
		}
	}

	public void save() {
		try {
			Data.itemsf.save(Data.items);
		} catch (IOException e) {
			new ExceptionUtil(e.getStackTrace(), Cause.FILEWENTWRONG,
					"%EFailed to save the items");
		}
	}
}