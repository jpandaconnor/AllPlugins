package co.uk.RandomPanda30.GBlock.Configs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import co.uk.RandomPanda30.GBlock.GBlock;

@SuppressWarnings("rawtypes")
public class Messages {

	private GBlock plugin;

	private File messagesF;
	private FileConfiguration messagesCF;
	private ConfigurationSection messagesCS;

	private final HashMap<MessagesValues, Value> statValueHashMap = new HashMap<>();

	public Messages (GBlock plugin) {
		this.plugin = plugin;
		init();
	}

	public void init() {
		try {
			setFile(new File("plugins/" + plugin.getPdfFile().getName(),
					"messages.yml"));
			if (!getFile().exists()) {
				getFile().getParentFile().mkdirs();
				getFile().createNewFile();
			}

			setFileConfiguration(new YamlConfiguration());
			setConfigurationSection(
					getConfiguration().getConfigurationSection(""));
			getConfiguration().load(getFile());

			for (MessagesDefaults value : MessagesDefaults.values()) {
				if (getConfigurationSection()
						.get(value.name().replaceAll("_", ".")) == null) {
					getConfigurationSection().set(
							value.name().replaceAll("_", "."), value.value);
					save();
				}
				value.value = getConfigurationSection()
						.get(value.name().replaceAll("_", "."));
			}
			getConfiguration().load(getFile());
		} catch (Exception e) {
			e.printStackTrace();
		}

		ArrayList<String> nodes = new ArrayList<String>();
		for (String rawNode : getConfiguration().getConfigurationSection("")
				.getKeys(false)) {
			if (getConfiguration().isConfigurationSection(rawNode)) {
				if (getConfiguration().getConfigurationSection(rawNode)
						.getKeys(true) != null) {
					for (String newRawNode : getConfiguration()
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
			Object value = getConfiguration()
					.get("" + statName.replace("_", "."));
			setStat(values, value);
		}
	}

	public void reset() {
		remove();
		init();
	}

	public void remove() {
		messagesF.delete();
	}

	public void save() {
		try {
			messagesCF.save(messagesF);
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (MessagesValues stat : statValueHashMap.keySet()) {
			getConfiguration().set("" + stat.name().replace("_", "."),
					statValueHashMap.get(stat).getValue());
		}
	}

	public void setFile(File file) {
		messagesF = file;
	}

	public void setFileConfiguration(FileConfiguration fileConfiguration) {
		messagesCF = fileConfiguration;
	}

	public void setConfigurationSection(
			ConfigurationSection configurationSection) {
		messagesCS = configurationSection;
	}

	public File getFile() {
		return messagesF;
	}

	public FileConfiguration getConfiguration() {
		return messagesCF;
	}

	public ConfigurationSection getConfigurationSection() {
		return messagesCS;
	}

	public void setStat(MessagesValues stat, Object object) {
		statValueHashMap.put(stat, new Value<>(object));
	}

	public Object getStat(MessagesValues stat) {
		return statValueHashMap.get(stat).getValue();
	}

	public enum MessagesDefaults {

		TEST ("TEST");

		public Object value;

		MessagesDefaults (Object value) {
			this.value = value;
		}
	}

	public enum MessagesValues {
		TEST {
			public Object getValue() {
				return "TEST";
			}
		};

		public String toString() {
			return name();
		}

		public Object getValue() {
			throw new AbstractMethodError("This error should never be shown.");
		}
	}
}