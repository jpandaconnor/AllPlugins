package co.uk.RandomPanda30.RPG.Files.Messages;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import co.uk.RandomPanda30.RPG.RPG;
import co.uk.RandomPanda30.RPG.RPGValues;
import co.uk.RandomPanda30.RPG.Files.IConfig;
import co.uk.RandomPanda30.RPG.Files.Messages.Enums.MessagesDefault;

public class Messages implements IConfig {

	public RPG plugin;
	public RPGValues values;

	public Messages (RPG plugin, RPGValues values) {
		this.plugin = plugin;
		this.values = values;
	}

	@Override
	public void init() {
		try {
			values.setMessages(new File("plugins/"
					+ plugin.getPdfFile().getName(), "messages.yml"));
			if (!values.getMessages().exists()) {
				values.getMessages().getParentFile().mkdirs();
				values.getMessages().createNewFile();
			}

			values.setMessagesC(new YamlConfiguration());
			values.setMessagesCS(values.getMessagesC().getConfigurationSection(
					""));
			values.getMessagesC().load(values.getMessages());

			for (MessagesDefault value : MessagesDefault.values()) {
				if (values.getMessagesCS().get(
						value.name().replaceAll("_", ".")) == null) {
					values.getMessagesCS().set(
							value.name().replaceAll("_", "."), value.value);
					values.getMessagesC().save(values.getMessages());
				}

				value.value = values.getMessagesCS().get(
						value.name().replaceAll("_", "."));
			}
			values.getMessagesC().load(values.getMessages());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void save() {
		try {
			values.getMessagesC().save(values.getMessages());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remove() {
		values.getMessages().delete();
	}

	@Override
	public void reset() {
		remove();
		init();
	}
}