package co.uk.RandomPanda30.RPG;

import java.io.File;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class RPGValues {

	public RPG plugin;

	public RPGValues (RPG plugin) {
		this.plugin = plugin;
	}

	private File config;
	private FileConfiguration configC;
	private ConfigurationSection configCS;

	private File messages;
	private FileConfiguration messagesC;
	private ConfigurationSection messagesCS;

	private File bank;
	private FileConfiguration bankC;
	private ConfigurationSection bankCS;

	public File getConfig() {
		return config;
	}

	public FileConfiguration getConfigC() {
		return configC;
	}

	public ConfigurationSection getConfigCS() {
		return configCS;
	}

	public void setConfig(File config) {
		this.config = config;
	}

	public void setConfigC(FileConfiguration configuration) {
		this.configC = configuration;
	}

	public void setConfigCS(ConfigurationSection section) {
		this.configCS = section;
	}

	public File getMessages() {
		return messages;
	}

	public FileConfiguration getMessagesC() {
		return messagesC;
	}

	public ConfigurationSection getMessagesCS() {
		return messagesCS;
	}

	public void setMessages(File Messages) {
		this.messages = Messages;
	}

	public void setMessagesC(FileConfiguration configuration) {
		this.messagesC = configuration;
	}

	public void setMessagesCS(ConfigurationSection section) {
		this.messagesCS = section;
	}
	
	public File getBank() {
		return bank;
	}
	
	public FileConfiguration getBankC() {
		return bankC;
	}
	
	public ConfigurationSection getBankCS() {
		return bankCS;
	}
	
	public void setBank(File bank) {
		this.bank = bank;
	}
	
	public void setBankC(FileConfiguration configuration) {
		this.bankC = configuration;
	}
	
	public void setBankCS(ConfigurationSection section) {
		this.bankCS = section;
	}
}