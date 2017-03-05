package co.uk.RandomPanda30.Murge.Values;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Values.Enums.ItemsValues;

@SuppressWarnings("rawtypes")
public class ItemsMapping {

	private final FileConfiguration fileConfiguration;
	private final HashMap<ItemsValues, Value> statValueHashMap = new HashMap<>();

	public ItemsMapping (FileConfiguration items) {
		this.fileConfiguration = items;
	}

	public void setStat(ItemsValues stat, Object object) {
		statValueHashMap.put(stat, new Value<>(object));
	}

	public Object getStat(ItemsValues stat) {
		return statValueHashMap.get(stat).getValue();
	}

	public void save() {
		for (ItemsValues stat : statValueHashMap.keySet()) {
			fileConfiguration.set("" + stat.name().replace("_", "."),
					statValueHashMap.get(stat).getValue());
		}
		try {
			fileConfiguration.save(Murge.cso.getItems());
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
			ItemsValues values = ItemsValues.valueOf(statName);
			Object value = fileConfiguration.get(""
					+ statName.replace("_", "."));
			setStat(values, value);
		}
	}
}