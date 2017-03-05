package co.uk.RandomPanda30.ERRanks.Methods;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

import co.uk.RandomPanda30.ERRanks.ERRanksData;
import co.uk.RandomPanda30.ERRanks.Enums.Config;
import co.uk.RandomPanda30.ERRanks.Enums.Messages;

public class ConfigMethods {

	public static void initConfig() {
		try {
			ERRanksData.config = new File("plugins/"
					+ ERRanksData.getDataFile().getName(), "config.yml");
			if (!ERRanksData.config.exists()) {
				ERRanksData.config.getParentFile().mkdirs();
				ERRanksData.config.createNewFile();
			}

			ERRanksData.configC = new YamlConfiguration();
			ERRanksData.configCS = ERRanksData.configC
					.getConfigurationSection("");
			ERRanksData.configC.load(ERRanksData.config);

			for (Config value : Config.values()) {
				if (ERRanksData.configCS.get(value.name().replaceAll("_", ".")) == null) {
					ERRanksData.configCS.set(value.name().replaceAll("_", "."),
							value.value);
					ERRanksData.configC.save(ERRanksData.config);
				}

				value.value = ERRanksData.configCS.get(value.name().replaceAll(
						"_", "."));
			}

			ERRanksData.configC.load(ERRanksData.config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void initMessages() {
		try {
			ERRanksData.messages = new File("plugins/"
					+ ERRanksData.getDataFile().getName(), "messages.yml");
			if (!ERRanksData.messages.exists()) {
				ERRanksData.messages.getParentFile().mkdirs();
				ERRanksData.messages.createNewFile();
			}

			ERRanksData.messagesC = new YamlConfiguration();
			ERRanksData.messagesCS = ERRanksData.messagesC
					.getConfigurationSection("");
			ERRanksData.messagesC.load(ERRanksData.messages);

			for (Messages value : Messages.values()) {
				if (ERRanksData.messagesCS.get(value.name()
						.replaceAll("_", ".")) == null) {
					ERRanksData.messagesCS.set(value.name()
							.replaceAll("_", "."), value.value);
					ERRanksData.messagesC.save(ERRanksData.messages);
				}

				value.value = ERRanksData.messagesCS.get(value.name()
						.replaceAll("_", "."));
			}

			ERRanksData.messagesC.load(ERRanksData.messages);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveConfig() {
		try {
			ERRanksData.configC.save(ERRanksData.config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveMessages() {
		try {
			ERRanksData.messagesC.save(ERRanksData.messages);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}