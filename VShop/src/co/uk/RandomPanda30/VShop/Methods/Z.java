package co.uk.RandomPanda30.VShop.Methods;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import co.uk.RandomPanda30.VShop.A;
import co.uk.RandomPanda30.VShop.B;
import co.uk.RandomPanda30.VShop.Files.Config;
import co.uk.RandomPanda30.VShop.Files.Items;
import co.uk.RandomPanda30.VShop.Files.Messages;

public class Z {

	// Config methods

	public static void initConfig() {
		try {
			B.config = new File("plugins/" + A.pdfFile.getName(), "config.yml");
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
			B.messages = new File("plugins/" + A.pdfFile.getName(),
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
			B.items = new File("plugins/" + A.pdfFile.getName(), "items.yml");
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

	public static void initShops() {
		try {
			B.shops = new File("plugins/" + A.pdfFile.getName(), "shops.yml");
			if (!B.shops.exists()) {
				B.shops.getParentFile().mkdirs();
				B.shops.createNewFile();
			}

			B.shopsC = new YamlConfiguration();
			B.shopsCS = B.shopsC.getConfigurationSection("");
			B.shopsC.load(B.shops);

			B.shopsC.load(B.shops);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveConfig() {
		try {
			B.configC.save(B.config);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveMessages() {
		try {
			B.messagesC.save(B.messages);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveItems() {
		try {
			B.itemsC.save(B.items);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveShops() {
		try {
			B.shopsC.save(B.shops);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}