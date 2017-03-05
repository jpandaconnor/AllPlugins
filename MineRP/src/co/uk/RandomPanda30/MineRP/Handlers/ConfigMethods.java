package co.uk.RandomPanda30.MineRP.Handlers;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

import co.uk.RandomPanda30.MineRP.MineRPData;
import co.uk.RandomPanda30.MineRP.Files.Config;
import co.uk.RandomPanda30.MineRP.Files.Messages;

public class ConfigMethods {

	public static void initConfig() {
		try {
			MineRPData.config = new File("plugins/"
					+ MineRPData.getDataFile().getName(), "config.yml");
			if (!MineRPData.config.exists()) {
				MineRPData.config.getParentFile().mkdirs();
				MineRPData.config.createNewFile();
			}

			MineRPData.configC = new YamlConfiguration();
			MineRPData.configCS = MineRPData.configC
					.getConfigurationSection("");
			MineRPData.configC.load(MineRPData.config);

			for (Config value : Config.values()) {
				if (MineRPData.configCS.get(value.name().replaceAll("_", ".")) == null) {
					MineRPData.configCS.set(value.name().replaceAll("_", "."),
							value.value);
					MineRPData.configC.save(MineRPData.config);
				}

				value.value = MineRPData.configCS.get(value.name().replaceAll(
						"_", "."));
			}

			MineRPData.configC.load(MineRPData.config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void initMessages() {
		try {
			MineRPData.messages = new File("plugins/"
					+ MineRPData.getDataFile().getName(), "messages.yml");
			if (!MineRPData.messages.exists()) {
				MineRPData.messages.getParentFile().mkdirs();
				MineRPData.messages.createNewFile();
			}

			MineRPData.messagesC = new YamlConfiguration();
			MineRPData.messagesCS = MineRPData.messagesC
					.getConfigurationSection("");
			MineRPData.messagesC.load(MineRPData.messages);

			for (Messages value : Messages.values()) {
				if (MineRPData.messagesCS
						.get(value.name().replaceAll("_", ".")) == null) {
					MineRPData.messagesCS.set(
							value.name().replaceAll("_", "."), value.value);
					MineRPData.messagesC.save(MineRPData.messages);
				}

				value.value = MineRPData.messagesCS.get(value.name()
						.replaceAll("_", "."));
			}

			MineRPData.messagesC.load(MineRPData.messages);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void initData() {
		try {
			MineRPData.data = new File("plugins/"
					+ MineRPData.getDataFile().getName(), "data.yml");
			if (!MineRPData.data.exists()) {
				MineRPData.data.getParentFile().mkdirs();
				MineRPData.data.createNewFile();
			}

			MineRPData.dataC = new YamlConfiguration();
			MineRPData.dataCS = MineRPData.dataC.getConfigurationSection("");
			MineRPData.dataC.load(MineRPData.data);

			MineRPData.dataC.load(MineRPData.data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveConfig() {
		try {
			MineRPData.configC.save(MineRPData.config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveMessages() {
		try {
			MineRPData.messagesC.save(MineRPData.messages);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveData() {
		try {
			MineRPData.dataC.save(MineRPData.data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}