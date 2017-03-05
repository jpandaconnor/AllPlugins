package co.uk.RandomPanda30.Murge.Values;

import java.io.File;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigurationSectionObjects {

	private File config;
	private FileConfiguration configC;
	private ConfigurationSection configCS;

	private File messages;
	private FileConfiguration messagesC;
	private ConfigurationSection messagesCS;

	private File items;
	private FileConfiguration itemsC;
	private ConfigurationSection itemsCS;

	private File data;
	private FileConfiguration dataC;
	private ConfigurationSection dataCS;

	private File dump;
	private FileConfiguration dumpC;
	private ConfigurationSection dumpCS;

	private File kits;
	private FileConfiguration kitsC;
	private ConfigurationSection kitsCS;

	private static ConfigurationSectionObjects instance = new ConfigurationSectionObjects();

	public static ConfigurationSectionObjects getObjects() {
		return instance;
	}

	public File getConfig() {
		return config;
	}

	public void setConfig(File config) {
		this.config = config;
	}

	public FileConfiguration getConfigC() {
		return configC;
	}

	public void setConfigC(FileConfiguration configC) {
		this.configC = configC;
	}

	public ConfigurationSection getConfigCS() {
		return configCS;
	}

	public void setConfigCS(ConfigurationSection configCS) {
		this.configCS = configCS;
	}

	public File getMessages() {
		return messages;
	}

	public void setMessages(File messages) {
		this.messages = messages;
	}

	public FileConfiguration getMessagesC() {
		return messagesC;
	}

	public void setMessagesC(FileConfiguration messagesC) {
		this.messagesC = messagesC;
	}

	public ConfigurationSection getMessagesCS() {
		return messagesCS;
	}

	public void setMessagesCS(ConfigurationSection messagesCS) {
		this.messagesCS = messagesCS;
	}

	public File getItems() {
		return items;
	}

	public void setItems(File items) {
		this.items = items;
	}

	public FileConfiguration getItemsC() {
		return itemsC;
	}

	public void setItemsC(FileConfiguration itemsC) {
		this.itemsC = itemsC;
	}

	public ConfigurationSection getItemsCS() {
		return itemsCS;
	}

	public void setItemsCS(ConfigurationSection itemsCS) {
		this.itemsCS = itemsCS;
	}

	public File getData() {
		return data;
	}

	public void setData(File data) {
		this.data = data;
	}

	public FileConfiguration getDataC() {
		return dataC;
	}

	public void setDataC(FileConfiguration dataC) {
		this.dataC = dataC;
	}

	public ConfigurationSection getDataCS() {
		return dataCS;
	}

	public void setDataCS(ConfigurationSection dataCS) {
		this.dataCS = dataCS;
	}

	public File getDump() {
		return dump;
	}

	public void setDump(File dump) {
		this.dump = dump;
	}

	public FileConfiguration getDumpC() {
		return dumpC;
	}

	public void setDumpC(FileConfiguration dumpC) {
		this.dumpC = dumpC;
	}

	public ConfigurationSection getDumpCS() {
		return dumpCS;
	}

	public void setDumpCS(ConfigurationSection dumpCS) {
		this.dumpCS = dumpCS;
	}

	public File getKits() {
		return kits;
	}

	public void setKits(File kits) {
		this.kits = kits;
	}

	public FileConfiguration getKitsC() {
		return kitsC;
	}

	public void setKitsC(FileConfiguration kitsC) {
		this.kitsC = kitsC;
	}

	public ConfigurationSection getKitsCS() {
		return kitsCS;
	}

	public void setKitsCS(ConfigurationSection kitsCS) {
		this.kitsCS = kitsCS;
	}
}