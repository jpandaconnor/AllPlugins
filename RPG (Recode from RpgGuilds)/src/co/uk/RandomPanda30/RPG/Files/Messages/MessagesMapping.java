package co.uk.RandomPanda30.RPG.Files.Messages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import co.uk.RandomPanda30.RPG.RPGValues;
import co.uk.RandomPanda30.RPG.Files.Value;
import co.uk.RandomPanda30.RPG.Files.Messages.Enums.MessagesValues;

@SuppressWarnings("rawtypes")
public class MessagesMapping {

	private RPGValues values;

	private final HashMap<MessagesValues, Value> statValueHashMap = new HashMap<>();

	public MessagesMapping (RPGValues values) {
		this.values = values;
	}

	public void setStat(MessagesValues stat, Object object) {
		statValueHashMap.put(stat, new Value<>(object));
	}

	public Object getStat(MessagesValues stat) {
		return statValueHashMap.get(stat).getValue();
	}

	public void save() {
		for (MessagesValues stat : statValueHashMap.keySet()) {
			values.getMessagesC().set("" + stat.name().replace("_", "."),
					statValueHashMap.get(stat).getValue());
		}
		try {
			values.getMessagesC().save(values.getMessages());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void load() {
		ArrayList<String> nodes = new ArrayList<String>();
		for (String rawNode : values.getMessagesC().getConfigurationSection("")
				.getKeys(false)) {
			if (values.getMessagesC().isConfigurationSection(rawNode)) {
				if (values.getMessagesC().getConfigurationSection(rawNode)
						.getKeys(true) != null) {
					for (String newRawNode : values.getMessagesC()
							.getConfigurationSection(rawNode).getKeys(true)) {
						nodes.add(rawNode + "_" + newRawNode);
					}
				}
			} else {
				nodes.add(rawNode);
			}
		}
		for (String statName : nodes) {
			MessagesValues valuepl = MessagesValues.valueOf(statName);
			Object value = values.getMessagesC().get(
					"" + statName.replace("_", "."));
			setStat(valuepl, value);
		}
	}
}