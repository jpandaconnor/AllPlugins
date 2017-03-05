package co.uk.RandomPanda30.RPG.Files.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import co.uk.RandomPanda30.RPG.RPGValues;
import co.uk.RandomPanda30.RPG.Files.Value;
import co.uk.RandomPanda30.RPG.Files.Config.Enums.ConfigValues;

@SuppressWarnings("rawtypes")
public class ConfigMapping {

	private RPGValues values;

	private final HashMap<ConfigValues, Value> statValueHashMap = new HashMap<>();

	public ConfigMapping (RPGValues values) {
		this.values = values;
	}

	public void setStat(ConfigValues stat, Object object) {
		statValueHashMap.put(stat, new Value<>(object));
	}

	public Object getStat(ConfigValues stat) {
		return statValueHashMap.get(stat).getValue();
	}

	public void save() {
		for (ConfigValues stat : statValueHashMap.keySet()) {
			values.getConfigC().set("" + stat.name().replace("_", "."),
					statValueHashMap.get(stat).getValue());
		}
		try {
			values.getConfigC().save(values.getConfig());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void load() {
		ArrayList<String> nodes = new ArrayList<String>();
		for (String rawNode : values.getConfigC().getConfigurationSection("")
				.getKeys(false)) {
			if (values.getConfigC().isConfigurationSection(rawNode)) {
				if (values.getConfigC().getConfigurationSection(rawNode)
						.getKeys(true) != null) {
					for (String newRawNode : values.getConfigC()
							.getConfigurationSection(rawNode).getKeys(true)) {
						nodes.add(rawNode + "_" + newRawNode);
					}
				}
			} else {
				nodes.add(rawNode);
			}
		}

		for (String statName : nodes) {
			ConfigValues valuepl = ConfigValues.valueOf(statName);
			Object value = values.getConfigC().get(
					"" + statName.replace("_", "."));
			setStat(valuepl, value);
		}
	}
}