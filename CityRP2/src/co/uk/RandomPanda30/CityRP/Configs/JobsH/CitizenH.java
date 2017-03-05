package co.uk.RandomPanda30.CityRP.Configs.JobsH;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import co.uk.RandomPanda30.CityRP.CityRP;
import co.uk.RandomPanda30.CityRP.Data;
import co.uk.RandomPanda30.CityRP.Configs.Enums.Citizen.Citizen;
import co.uk.RandomPanda30.CityRP.Configs.Enums.Citizen.CitizenV;
import co.uk.RandomPanda30.CityRP.Misc.Value;
import co.uk.RandomPanda30.CityRP.Resources.ExceptionUtil;
import co.uk.RandomPanda30.CityRP.Resources.ExceptionUtil.Cause;

public class CitizenH {

	private CityRP plugin;

	public CitizenH (CityRP plugin) {
		this.plugin = plugin;
		init();
	}

	public void init() {
		try {
			Data.citizen = new File(
					"plugins/" + plugin.getName() + "/Jobs/citizen.yml");
			if (!Data.citizen.exists()) {
				Data.citizen.getParentFile().mkdirs();
				Data.citizen.createNewFile();
			}

			Data.citizenf = new YamlConfiguration();
			Data.citizens = Data.citizenf.getConfigurationSection("");
			Data.citizenf.load(Data.citizen);

			for (Citizen value : Citizen.values()) {
				if (Data.citizens
						.get(value.name().replaceAll("_", ".")) == null) {
					Data.citizens.set(value.name().replaceAll("_", "."),
							value.value);
					save();
				}

				value.value = Data.citizens
						.get(value.name().replaceAll("_", "."));
			}

			Data.citizenf.load(Data.citizen);
		} catch (IOException | InvalidConfigurationException e) {
			new ExceptionUtil(e.getStackTrace(), Cause.FILEWENTWRONG,
					"%EFailed to init the citizen config");
		}

		ArrayList<String> nodes = new ArrayList<String>();
		for (String rawNode : Data.citizens.getKeys(false)) {
			if (Data.citizenf.isConfigurationSection(rawNode)) {
				if (Data.citizenf.getConfigurationSection(rawNode)
						.getKeys(true) != null) {
					for (String newRawNode : Data.citizenf
							.getConfigurationSection(rawNode).getKeys(true)) {
						nodes.add(rawNode + "_" + newRawNode);
					}
				}
			} else {
				nodes.add(rawNode);
			}
		}

		for (String statName : nodes) {
			CitizenV values = CitizenV.valueOf(statName);
			Object value = Data.citizenf.get("" + statName.replace("_", "."));
			setStat(values, value);
		}
	}

	public void save() {
		try {
			Data.citizenf.save(Data.citizen);
		} catch (IOException e) {
			new ExceptionUtil(e.getStackTrace(), Cause.FILEWENTWRONG,
					"%EFailed to save the citizen config");
		}
	}

	public static void setStat(CitizenV stat, Object object) {
		Data.citizenMap.put(stat, new Value<>(object));
	}

	public static Object getStat(CitizenV stat) {
		return Data.citizenMap.get(stat).getValue();
	}
}