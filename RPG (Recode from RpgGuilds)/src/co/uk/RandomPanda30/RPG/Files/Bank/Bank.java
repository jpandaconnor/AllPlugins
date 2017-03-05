package co.uk.RandomPanda30.RPG.Files.Bank;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import co.uk.RandomPanda30.RPG.RPG;
import co.uk.RandomPanda30.RPG.RPGValues;
import co.uk.RandomPanda30.RPG.Files.IConfig;

public class Bank implements IConfig {

	public RPG plugin;
	public RPGValues values;

	public Bank (RPG plugin, RPGValues values) {
		this.plugin = plugin;
		this.values = values;
	}

	@Override
	public void init() {
		try {
			values.setBank(new File("plugins/" + plugin.getPdfFile().getName(),
					"bank.yml"));
			if (!values.getBank().exists()) {
				values.getBank().getParentFile().mkdirs();
				values.getBank().createNewFile();
			}

			values.setBankC(new YamlConfiguration());
			values.setBankCS(values.getBankC().getConfigurationSection(""));
			values.getBankC().load(values.getBank());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void save() {
		try {
			values.getBankC().save(values.getBank());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remove() {
		values.getBank().delete();
	}

	@Override
	public void reset() {
		remove();
		init();
	}
}