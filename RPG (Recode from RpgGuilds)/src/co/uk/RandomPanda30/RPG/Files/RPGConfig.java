package co.uk.RandomPanda30.RPG.Files;

import co.uk.RandomPanda30.RPG.RPG;
import co.uk.RandomPanda30.RPG.RPGValues;
import co.uk.RandomPanda30.RPG.Files.Bank.Bank;
import co.uk.RandomPanda30.RPG.Files.Config.Config;
import co.uk.RandomPanda30.RPG.Files.Config.ConfigMapping;
import co.uk.RandomPanda30.RPG.Files.Messages.Messages;
import co.uk.RandomPanda30.RPG.Files.Messages.MessagesMapping;

public class RPGConfig {

	public RPG plugin;
	public RPGValues values;
	public Config config;
	public Messages messages;
	public Bank bank;

	public ConfigMapping cMap;
	public MessagesMapping mMap;

	public RPGConfig (RPG plugin, RPGValues values, Config config,
			Messages messages, Bank bank) {
		this.plugin = plugin;
		this.values = values;
		this.config = config;
		this.messages = messages;
		this.bank = bank;
	}

	public void start() {
		initAllConfigs();
		this.setConfigMap(new ConfigMapping(values));
		this.setMessagesMap(new MessagesMapping(values));
		
		cMap.load();
		mMap.load();
	}
	
	public void stop() {
		saveAllConfigs();
		cMap.save();
		mMap.save();
	}

	public void initAllConfigs() {
		config.init();
		messages.init();
		
		bank.init();
	}

	public void saveAllConfigs() {
		config.save();
		messages.save();
	}

	public void removeAllConfigs() {
		config.remove();
		messages.remove();
		bank.remove();
	}

	public void resetAllConfigs() {
		config.reset();
		messages.reset();
	}

	public void setConfigMap(ConfigMapping cMap) {
		this.cMap = cMap;
	}

	public ConfigMapping getConfigMap() {
		return cMap;
	}

	public void setMessagesMap(MessagesMapping mMap) {
		this.mMap = mMap;
	}

	public MessagesMapping getMessagesMap() {
		return mMap;
	}
}