package co.uk.RandomPanda30.ExpIntercept.Methods;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

import co.uk.RandomPanda30.ExpIntercept.ExpInterceptData;
import co.uk.RandomPanda30.ExpIntercept.Enums.Config;
import co.uk.RandomPanda30.ExpIntercept.Enums.Messages;

public class ConfigMethods {

	public static void initConfig() {
		try {
			ExpInterceptData.config = new File("plugins/"
					+ ExpInterceptData.getDataFile().getName(), "config.yml");
			if (!ExpInterceptData.config.exists()) {
				ExpInterceptData.config.getParentFile().mkdirs();
				ExpInterceptData.config.createNewFile();
			}

			ExpInterceptData.configC = new YamlConfiguration();
			ExpInterceptData.configCS = ExpInterceptData.configC
					.getConfigurationSection("");
			ExpInterceptData.configC.load(ExpInterceptData.config);

			for (Config value : Config.values()) {
				if (ExpInterceptData.configCS.get(value.name().replaceAll("_",
						".")) == null) {
					ExpInterceptData.configCS.set(
							value.name().replaceAll("_", "."), value.value);
					ExpInterceptData.configC.save(ExpInterceptData.config);
				}

				value.value = ExpInterceptData.configCS.get(value.name()
						.replaceAll("_", "."));
			}

			ExpInterceptData.configC.load(ExpInterceptData.config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void initMessages() {
		try {
			ExpInterceptData.messages = new File("plugins/"
					+ ExpInterceptData.getDataFile().getName(), "messages.yml");
			if (!ExpInterceptData.messages.exists()) {
				ExpInterceptData.messages.getParentFile().mkdirs();
				ExpInterceptData.messages.createNewFile();
			}

			ExpInterceptData.messagesC = new YamlConfiguration();
			ExpInterceptData.messagesCS = ExpInterceptData.messagesC
					.getConfigurationSection("");
			ExpInterceptData.messagesC.load(ExpInterceptData.messages);

			for (Messages value : Messages.values()) {
				if (ExpInterceptData.messagesCS.get(value.name().replaceAll(
						"_", ".")) == null) {
					ExpInterceptData.messagesCS.set(
							value.name().replaceAll("_", "."), value.value);
					ExpInterceptData.messagesC.save(ExpInterceptData.messages);
				}

				value.value = ExpInterceptData.messagesCS.get(value.name()
						.replaceAll("_", "."));
			}

			ExpInterceptData.messagesC.load(ExpInterceptData.messages);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}