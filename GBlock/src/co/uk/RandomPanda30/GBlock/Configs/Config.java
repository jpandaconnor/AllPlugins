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
public class Config {

	private GBlock plugin;

	private File configF;
	private FileConfiguration configCF;
	private ConfigurationSection configCS;

	private final HashMap<ConfigValues, Value> statValueHashMap = new HashMap<>();

	public Config (GBlock plugin) {
		this.plugin = plugin;
		init();
	}

	public void init() {
		try {
			setFile(new File("plugins/" + plugin.getPdfFile().getName(),
					"config.yml"));
			if (!getFile().exists()) {
				getFile().getParentFile().mkdirs();
				getFile().createNewFile();
			}

			setFileConfiguration(new YamlConfiguration());
			setConfigurationSection(
					getConfiguration().getConfigurationSection(""));
			getConfiguration().load(getFile());

			for (ConfigDefaults value : ConfigDefaults.values()) {
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
			ConfigValues values = ConfigValues.valueOf(statName);
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
		configF.delete();
	}

	public void save() {
		try {
			configCF.save(configF);
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (ConfigValues stat : statValueHashMap.keySet()) {
			getConfiguration().set("" + stat.name().replace("_", "."),
					statValueHashMap.get(stat).getValue());
		}
	}

	public void setFile(File file) {
		configF = file;
	}

	public void setFileConfiguration(FileConfiguration fileConfiguration) {
		configCF = fileConfiguration;
	}

	public void setConfigurationSection(
			ConfigurationSection configurationSection) {
		configCS = configurationSection;
	}

	public File getFile() {
		return configF;
	}

	public FileConfiguration getConfiguration() {
		return configCF;
	}

	public ConfigurationSection getConfigurationSection() {
		return configCS;
	}

	public void setStat(ConfigValues stat, Object object) {
		statValueHashMap.put(stat, new Value<>(object));
	}

	public Object getStat(ConfigValues stat) {
		return statValueHashMap.get(stat).getValue();
	}

	public enum ConfigDefaults {

		TEST ("TEST"), PLOTWORLD ("plots"), FALLBACKWORLD ("world");

		public Object value;

		ConfigDefaults (Object value) {
			this.value = value;
		}
	}

	public enum ConfigValues {
		TEST {
			public Object getValue() {
				return "TEST";
			}
		},

		PLOTWORLD {
			public Object getValue() {
				return "plots";
			}
		},

		FALLBACKWORLD {
			public Object getValue() {
				return "world";
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