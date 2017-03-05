package co.uk.RandomPanda30.Phones.Methods;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

import co.uk.RandomPanda30.Phones.Phones;
import co.uk.RandomPanda30.Phones.Files.Config;
import co.uk.RandomPanda30.Phones.Files.Messages;

public class ConfigurationMethods {

	public static void initConfig() {
		try {
			Phones.config = new File("plugins/" + Phones.pdfFile.getName(),
					"config.yml");
			if (!Phones.config.exists()) {
				Phones.config.getParentFile().mkdirs();
				Phones.config.createNewFile();
			}

			Phones.configC = new YamlConfiguration();
			Phones.configCS = Phones.configC.getConfigurationSection("");
			Phones.configC.load(Phones.config);

			for (Config value : Config.values()) {
				if (Phones.configCS.get(value.name().replaceAll("_", ".")) == null) {
					Phones.configCS.set(value.name().replaceAll("_", "."),
							value.value);
					Phones.configC.save(Phones.config);
				}

				value.value = Phones.configCS.get(value.name().replaceAll("_",
						"."));
			}

			Phones.configC.load(Phones.config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void initMessages() {
		try {
			Phones.messages = new File("plugins/" + Phones.pdfFile.getName(),
					"messages.yml");
			if (!Phones.messages.exists()) {
				Phones.messages.getParentFile().mkdirs();
				Phones.messages.createNewFile();
			}

			Phones.messagesC = new YamlConfiguration();
			Phones.messagesCS = Phones.messagesC.getConfigurationSection("");
			Phones.messagesC.load(Phones.messages);

			for (Messages value : Messages.values()) {
				if (Phones.messagesCS.get(value.name().replaceAll("_", ".")) == null) {
					Phones.messagesCS.set(value.name().replaceAll("_", "."),
							value.value);
					Phones.messagesC.save(Phones.messages);
				}

				value.value = Phones.messagesCS.get(value.name().replaceAll(
						"_", "."));
			}

			Phones.messagesC.load(Phones.messages);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
