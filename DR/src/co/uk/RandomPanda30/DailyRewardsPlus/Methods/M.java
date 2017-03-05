package co.uk.RandomPanda30.DailyRewardsPlus.Methods;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

import co.uk.RandomPanda30.DailyRewardsPlus.B;
import co.uk.RandomPanda30.DailyRewardsPlus.Files.Config;
import co.uk.RandomPanda30.DailyRewardsPlus.Files.Items;
import co.uk.RandomPanda30.DailyRewardsPlus.Files.Messages;

public class M {

	public static void initConfig() {
		try {
			B.config = new File("plugins/" + B.getDataFile().getName(),
					"config.yml");
			if (!B.config.exists()) {
				B.config.getParentFile().mkdirs();
				B.config.createNewFile();
			}

			B.configC = new YamlConfiguration();
			B.configCS = B.configC.getConfigurationSection("");
			B.configC.load(B.config);

			for (Config value : Config.values()) {
				if (B.configCS.get(value.name().replaceAll("_", ".")) == null) {
					B.configCS.set(value.name().replaceAll("_", "."),
							value.value);
					B.configC.save(B.config);
				}

				value.value = B.configCS.get(value.name().replaceAll("_", "."));
			}

			B.configC.load(B.config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void initMessages() {
		try {
			B.messages = new File("plugins/" + B.getDataFile().getName(),
					"messages.yml");
			if (!B.messages.exists()) {
				B.messages.getParentFile().mkdirs();
				B.messages.createNewFile();
			}

			B.messagesC = new YamlConfiguration();
			B.messagesCS = B.messagesC.getConfigurationSection("");
			B.messagesC.load(B.messages);

			for (Messages value : Messages.values()) {
				if (B.messagesCS.get(value.name().replaceAll("_", ".")) == null) {
					B.messagesCS.set(value.name().replaceAll("_", "."),
							value.value);
					B.messagesC.save(B.messages);
				}

				value.value = B.messagesCS.get(value.name()
						.replaceAll("_", "."));
			}

			B.messagesC.load(B.messages);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void initItems() {
		try {
			B.items = new File("plugins/" + B.getDataFile().getName(),
					"items.yml");
			if (!B.items.exists()) {
				B.items.getParentFile().mkdirs();
				B.items.createNewFile();
			}

			B.itemsC = new YamlConfiguration();
			B.itemsCS = B.itemsC.getConfigurationSection("");
			B.itemsC.load(B.items);

			for (Items value : Items.values()) {
				if (B.itemsCS.get(value.name().replaceAll("_", ".")) == null) {
					B.itemsCS.set(value.name().replaceAll("_", "."),
							value.value);
					B.itemsC.save(B.items);
				}

				value.value = B.itemsCS.get(value.name().replaceAll("_", "."));
			}

			B.itemsC.load(B.items);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void initData() {
		try {
			B.data = new File("plugins/" + B.getDataFile().getName(),
					"data.yml");
			if (!B.data.exists()) {
				B.data.getParentFile().mkdirs();
				B.data.createNewFile();
			}

			B.dataC = new YamlConfiguration();
			B.dataCS = B.dataC.getConfigurationSection("");
			B.dataC.load(B.data);

			B.dataC.load(B.data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}