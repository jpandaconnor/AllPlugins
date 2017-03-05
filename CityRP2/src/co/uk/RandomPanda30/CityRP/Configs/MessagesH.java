package co.uk.RandomPanda30.CityRP.Configs;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import co.uk.RandomPanda30.CityRP.CityRP;
import co.uk.RandomPanda30.CityRP.Data;
import co.uk.RandomPanda30.CityRP.Configs.Enums.Messages;
import co.uk.RandomPanda30.CityRP.Resources.ExceptionUtil;
import co.uk.RandomPanda30.CityRP.Resources.ExceptionUtil.Cause;

public class MessagesH {

	private CityRP plugin;

	public MessagesH (CityRP plugin) {
		this.plugin = plugin;
		init();
	}

	public void init() {
		try {
			Data.messages = new File(
					"plugins/" + plugin.getName() + "/messages.yml");
			if (!Data.messages.exists()) {
				Data.messages.getParentFile().mkdirs();
				Data.messages.createNewFile();
			}

			Data.messagesf = new YamlConfiguration();
			Data.messagess = Data.messagesf.getConfigurationSection("");
			Data.messagesf.load(Data.messages);

			for (Messages value : Messages.values()) {
				if (Data.messagess
						.get(value.name().replaceAll("_", ".")) == null) {
					Data.messagess.set(value.name().replaceAll("_", "."),
							value.value);
					save();
				}

				value.value = Data.messagess
						.get(value.name().replaceAll("_", "."));
			}
			Data.messagesf.load(Data.messages);
		} catch (IOException | InvalidConfigurationException e) {
			new ExceptionUtil(e.getStackTrace(), Cause.FILEWENTWRONG,
					"%EFailed to init the messages file");
		}
	}

	public void save() {
		try {
			Data.messagesf.save(Data.messages);
		} catch (IOException e) {
			new ExceptionUtil(e.getStackTrace(), Cause.FILEWENTWRONG,
					"%EFailed to save the messages file");
		}
	}
}