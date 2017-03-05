package co.uk.RandomPanda30.Murge.Methods;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Files.Config;
import co.uk.RandomPanda30.Murge.Files.Items;
import co.uk.RandomPanda30.Murge.Files.Messages;

public class ConfigMethods {

	public static void initConfig() {
		try {
			Murge.cso.setConfig(new File("plugins/"
					+ MurgeData.getDataFile().getName(), "config.yml"));
			if (!Murge.cso.getConfig().exists()) {
				Murge.cso.getConfig().getParentFile().mkdirs();
				Murge.cso.getConfig().createNewFile();
			}

			Murge.cso.setConfigC(new YamlConfiguration());
			Murge.cso.setConfigCS(Murge.cso.getConfigC()
					.getConfigurationSection(""));
			Murge.cso.getConfigC().load(Murge.cso.getConfig());

			for (Config value : Config.values()) {
				if (Murge.cso.getConfigCS().get(
						value.name().replaceAll("_", ".")) == null) {
					Murge.cso.getConfigCS().set(
							value.name().replaceAll("_", "."), value.value);
					Murge.cso.getConfigC().save(Murge.cso.getConfig());
				}

				value.value = Murge.cso.getConfigCS().get(
						value.name().replaceAll("_", "."));
			}

			Murge.cso.getConfigC().load(Murge.cso.getConfig());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void initMessages() {
		try {
			Murge.cso.setMessages(new File("plugins/"
					+ MurgeData.getDataFile().getName(), "messages.yml"));
			if (!Murge.cso.getMessages().exists()) {
				Murge.cso.getMessages().getParentFile().mkdirs();
				Murge.cso.getMessages().createNewFile();
			}

			Murge.cso.setMessagesC(new YamlConfiguration());
			Murge.cso.setMessagesCS(Murge.cso.getMessagesC()
					.getConfigurationSection(""));
			Murge.cso.getMessagesC().load(Murge.cso.getMessages());

			for (Messages value : Messages.values()) {
				if (Murge.cso.getMessagesCS().get(
						value.name().replaceAll("_", ".")) == null) {
					Murge.cso.getMessagesCS().set(
							value.name().replaceAll("_", "."), value.value);
					Murge.cso.getMessagesC().save(Murge.cso.getMessages());
				}

				value.value = Murge.cso.getMessagesCS().get(
						value.name().replaceAll("_", "."));
			}

			Murge.cso.getMessagesC().load(Murge.cso.getMessages());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void initItems() {
		try {
			Murge.cso.setItems(new File("plugins/"
					+ MurgeData.getDataFile().getName(), "items.yml"));
			if (!Murge.cso.getItems().exists()) {
				Murge.cso.getItems().getParentFile().mkdirs();
				Murge.cso.getItems().createNewFile();
			}

			Murge.cso.setItemsC(new YamlConfiguration());
			Murge.cso.setItemsCS(Murge.cso.getItemsC().getConfigurationSection(
					""));
			Murge.cso.getItemsC().load(Murge.cso.getItems());

			for (Items value : Items.values()) {
				if (Murge.cso.getItemsCS().get(
						value.name().replaceAll("_", ".")) == null) {
					Murge.cso.getItemsCS().set(
							value.name().replaceAll("_", "."), value.value);
					Murge.cso.getItemsC().save(Murge.cso.getItems());
				}

				value.value = Murge.cso.getItemsCS().get(
						value.name().replaceAll("_", "."));
			}

			Murge.cso.getItemsC().load(Murge.cso.getItems());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void initDump() {
		try {
			Murge.cso.setDump(new File("plugins/"
					+ MurgeData.getDataFile().getName(), "dump.yml"));
			if (!Murge.cso.getDump().exists()) {
				Murge.cso.getDump().getParentFile().mkdirs();
				Murge.cso.getDump().createNewFile();
			}

			Murge.cso.setDumpC(new YamlConfiguration());
			Murge.cso.setDumpCS(Murge.cso.getDumpC()
					.getConfigurationSection(""));

			Murge.cso.getDumpC().load(Murge.cso.getDump());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void initData() {
		try {
			Murge.cso.setData(new File("plugins/"
					+ MurgeData.getDataFile().getName(), "data.yml"));
			if (!Murge.cso.getData().exists()) {
				Murge.cso.getData().getParentFile().mkdirs();
				Murge.cso.getData().createNewFile();
			}

			Murge.cso.setDataC(new YamlConfiguration());
			Murge.cso.setDataCS(Murge.cso.getDataC()
					.getConfigurationSection(""));

			Murge.cso.getDataC().load(Murge.cso.getData());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void initKits() {
		try {
			Murge.cso.setKits(new File("plugins/"
					+ MurgeData.getDataFile().getName(), "kits.yml"));
			if (!Murge.cso.getKits().exists()) {
				Murge.cso.getKits().getParentFile().mkdirs();
				Murge.cso.getKits().createNewFile();
			}

			Murge.cso.setKitsC(new YamlConfiguration());
			Murge.cso.setKitsCS(Murge.cso.getKitsC()
					.getConfigurationSection(""));

			Murge.cso.getKitsC().load(Murge.cso.getKits());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveConfig() {
		try {
			Murge.cso.getConfigC().save(Murge.cso.getConfig());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveMessages() {
		try {
			Murge.cso.getMessagesC().save(Murge.cso.getMessages());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveItems() {
		try {
			Murge.cso.getItemsC().save(Murge.cso.getItems());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveDump() {
		try {
			Murge.cso.getDumpC().save(Murge.cso.getDump());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveData() {
		try {
			Murge.cso.getDataC().save(Murge.cso.getData());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void saveKits() {
		try {
			Murge.cso.getKitsC().save(Murge.cso.getKits());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveAllConfigs() {
		saveConfig();
		saveMessages();
		saveItems();
		saveDump();
		saveData();
		saveKits();
	}

	public static void initAllConfigs() {
		initConfig();
		initMessages();
		initItems();
		initDump();
		initData();
		initKits();
	}
}