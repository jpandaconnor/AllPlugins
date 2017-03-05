package co.uk.RandomPanda30.Murge.Values;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Values.Enums.MessagesValues;

@SuppressWarnings("rawtypes")
public class MessagesMapping {

	private final FileConfiguration fileConfiguration;
	private final HashMap<MessagesValues, Value> statValueHashMap = new HashMap<>();

	public MessagesMapping (FileConfiguration config) {
		this.fileConfiguration = config;
	}

	public void setStat(MessagesValues stat, Object object) {
		statValueHashMap.put(stat, new Value<>(object));
	}

	public Object getStat(MessagesValues stat) {
		return statValueHashMap.get(stat).getValue();
	}

	public void save() {
		for (MessagesValues stat : statValueHashMap.keySet()) {
			fileConfiguration.set("" + stat.name().replace("_", "."),
					statValueHashMap.get(stat).getValue());
		}
		try {
			fileConfiguration.save(Murge.cso.getMessages());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void load() {
		ArrayList<String> nodes = new ArrayList<String>();
		for (String rawNode : fileConfiguration.getConfigurationSection("")
				.getKeys(false)) {
			if (fileConfiguration.isConfigurationSection(rawNode)) {
				if (fileConfiguration.getConfigurationSection(rawNode).getKeys(
						true) != null) {
					for (String newRawNode : fileConfiguration
							.getConfigurationSection(rawNode).getKeys(true)) {
						nodes.add(rawNode + "_" + newRawNode);
					}
				}
			} else {
				nodes.add(rawNode);
			}
		}
		for (String statName : nodes) {
			MessagesValues values = MessagesValues.valueOf(statName);
			Object value = fileConfiguration.get(""
					+ statName.replace("_", "."));
			setStat(values, value);
		}
	}
}