package co.uk.RandomPanda30.ERHub.Methods;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

import co.uk.RandomPanda30.ERHub.ERHubData;
import co.uk.RandomPanda30.ERHub.Enums.Config;
import co.uk.RandomPanda30.ERHub.Enums.Messages;

public class ConfigMethods {

	public static void initConfig() {
		try {
			ERHubData.config = new File("plugins/"
					+ ERHubData.getDataFile().getName(), "config.yml");
			if (!ERHubData.config.exists()) {
				ERHubData.config.getParentFile().mkdirs();
				ERHubData.config.createNewFile();
			}

			ERHubData.configC = new YamlConfiguration();
			ERHubData.configCS = ERHubData.configC
					.getConfigurationSection("");
			ERHubData.configC.load(ERHubData.config);

			for (Config value : Config.values()) {
				if (ERHubData.configCS.get(value.name().replaceAll("_", ".")) == null) {
					ERHubData.configCS.set(value.name().replaceAll("_", "."),
							value.value);
					ERHubData.configC.save(ERHubData.config);
				}

				value.value = ERHubData.configCS.get(value.name().replaceAll(
						"_", "."));
			}

			ERHubData.configC.load(ERHubData.config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void initMessages() {
		try {
			ERHubData.messages = new File("plugins/"
					+ ERHubData.getDataFile().getName(), "messages.yml");
			if (!ERHubData.messages.exists()) {
				ERHubData.messages.getParentFile().mkdirs();
				ERHubData.messages.createNewFile();
			}

			ERHubData.messagesC = new YamlConfiguration();
			ERHubData.messagesCS = ERHubData.messagesC
					.getConfigurationSection("");
			ERHubData.messagesC.load(ERHubData.messages);

			for (Messages value : Messages.values()) {
				if (ERHubData.messagesCS.get(value.name()
						.replaceAll("_", ".")) == null) {
					ERHubData.messagesCS.set(value.name()
							.replaceAll("_", "."), value.value);
					ERHubData.messagesC.save(ERHubData.messages);
				}

				value.value = ERHubData.messagesCS.get(value.name()
						.replaceAll("_", "."));
			}

			ERHubData.messagesC.load(ERHubData.messages);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveConfig() {
		try {
			ERHubData.configC.save(ERHubData.config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveMessages() {
		try {
			ERHubData.messagesC.save(ERHubData.messages);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}