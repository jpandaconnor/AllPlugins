package co.uk.RandomPanda30.CasinoM.Methods;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

import co.uk.RandomPanda30.CasinoM.CasinoData;
import co.uk.RandomPanda30.CasinoM.Files.Config;
import co.uk.RandomPanda30.CasinoM.Files.Items;
import co.uk.RandomPanda30.CasinoM.Files.Messages;

public class ConfigMethods {

	public static void initConfig() {
		try {
			CasinoData.config = new File("plugins/"
					+ CasinoData.getDataFile().getName(), "config.yml");
			if (!CasinoData.config.exists()) {
				CasinoData.config.getParentFile().mkdirs();
				CasinoData.config.createNewFile();
			}

			CasinoData.configC = new YamlConfiguration();
			CasinoData.configCS = CasinoData.configC
					.getConfigurationSection("");
			CasinoData.configC.load(CasinoData.config);

			for (Config value : Config.values()) {
				if (CasinoData.configCS.get(value.name().replaceAll("_", ".")) == null) {
					CasinoData.configCS.set(value.name().replaceAll("_", "."),
							value.value);
					CasinoData.configC.save(CasinoData.config);
				}

				value.value = CasinoData.configCS.get(value.name().replaceAll(
						"_", "."));
			}

			CasinoData.configC.load(CasinoData.config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void initMessages() {
		try {
			CasinoData.messages = new File("plugins/"
					+ CasinoData.getDataFile().getName(), "messages.yml");
			if (!CasinoData.messages.exists()) {
				CasinoData.messages.getParentFile().mkdirs();
				CasinoData.messages.createNewFile();
			}

			CasinoData.messagesC = new YamlConfiguration();
			CasinoData.messagesCS = CasinoData.messagesC
					.getConfigurationSection("");
			CasinoData.messagesC.load(CasinoData.messages);

			for (Messages value : Messages.values()) {
				if (CasinoData.messagesCS
						.get(value.name().replaceAll("_", ".")) == null) {
					CasinoData.messagesCS.set(
							value.name().replaceAll("_", "."), value.value);
					CasinoData.messagesC.save(CasinoData.messages);
				}

				value.value = CasinoData.messagesCS.get(value.name()
						.replaceAll("_", "."));
			}

			CasinoData.messagesC.load(CasinoData.messages);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void initItems() {
		try {
			CasinoData.items = new File("plugins/"
					+ CasinoData.getDataFile().getName(), "items.yml");
			if (!CasinoData.items.exists()) {
				CasinoData.items.getParentFile().mkdirs();
				CasinoData.items.createNewFile();
			}

			CasinoData.itemsC = new YamlConfiguration();
			CasinoData.itemsCS = CasinoData.itemsC.getConfigurationSection("");
			CasinoData.itemsC.load(CasinoData.items);

			for (Items value : Items.values()) {
				if (CasinoData.itemsCS.get(value.name().replaceAll("_", ".")) == null) {
					CasinoData.itemsCS.set(value.name().replaceAll("_", "."),
							value.value);
					CasinoData.itemsC.save(CasinoData.items);
				}

				value.value = CasinoData.itemsCS.get(value.name().replaceAll(
						"_", "."));
			}

			CasinoData.itemsC.load(CasinoData.items);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void initSlotMachines() {
		try {
			CasinoData.slotmachines = new File("plugins/"
					+ CasinoData.getDataFile().getName() + "/data",
					"slotmachines.yml");
			if (!CasinoData.slotmachines.exists()) {
				CasinoData.slotmachines.getParentFile().mkdirs();
				CasinoData.slotmachines.createNewFile();
			}

			CasinoData.slotmachinesC = new YamlConfiguration();
			CasinoData.slotmachinesCS = CasinoData.slotmachinesC
					.getConfigurationSection("");
			CasinoData.slotmachinesC.load(CasinoData.slotmachines);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveConfig() {
		try {
			CasinoData.configC.save(CasinoData.config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveMessages() {
		try {
			CasinoData.messagesC.save(CasinoData.messages);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveItems() {
		try {
			CasinoData.itemsC.save(CasinoData.items);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveSlotMachines() {
		try {
			CasinoData.slotmachinesC.save(CasinoData.slotmachines);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}