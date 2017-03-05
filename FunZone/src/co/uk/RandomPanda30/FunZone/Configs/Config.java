package co.uk.RandomPanda30.FunZone.Configs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import co.uk.RandomPanda30.FunZone.FunZone;
import co.uk.RandomPanda30.FunZone.Base.IConfig;
import co.uk.RandomPanda30.FunZone.Misc.Value;

@SuppressWarnings("rawtypes")
public class Config implements IConfig {

	public FunZone plugin;

	private final HashMap<ConfigValues, Value> statValueHashMap = new HashMap<>();

	public Config (FunZone plugin) {
		this.plugin = plugin;
		init();
	}

	@Override
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

	@Override
	public void reset() {
		remove();
		init();
	}

	@Override
	public void remove() {
		plugin.configF.delete();
	}

	@Override
	public void save() {
		try {
			plugin.configCF.save(plugin.configF);
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (ConfigValues stat : statValueHashMap.keySet()) {
			getConfiguration().set("" + stat.name().replace("_", "."),
					statValueHashMap.get(stat).getValue());
		}
	}

	@Override
	public void setFile(File file) {
		plugin.configF = file;
	}

	@Override
	public void setFileConfiguration(FileConfiguration fileConfiguration) {
		plugin.configCF = fileConfiguration;
	}

	@Override
	public void setConfigurationSection(
			ConfigurationSection configurationSection) {
		plugin.configCSF = configurationSection;
	}

	@Override
	public File getFile() {
		return plugin.configF;
	}

	@Override
	public FileConfiguration getConfiguration() {
		return plugin.configCF;
	}

	@Override
	public ConfigurationSection getConfigurationSection() {
		return plugin.configCSF;
	}

	public void setStat(ConfigValues stat, Object object) {
		statValueHashMap.put(stat, new Value<>(object));
	}

	public Object getStat(ConfigValues stat) {
		return statValueHashMap.get(stat).getValue();
	}

	@SuppressWarnings("serial")
	public enum ConfigDefaults {

		TEST ("TEST"),

		DOUBLEJUMP_WORLDDISABLE (new ArrayList<String>() {
			{

			}
		});

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

		DOUBLEJUMP_WORLDDISABLE {
			public Object getValue() {
				return new ArrayList<String>();
			}
		};

		@Override
		public String toString() {
			return name();
		}

		public Object getValue() {
			throw new AbstractMethodError("This error should never be shown.");
		}
	}
}