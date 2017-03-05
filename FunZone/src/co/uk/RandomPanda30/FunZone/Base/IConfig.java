package co.uk.RandomPanda30.FunZone.Base;

import java.io.File;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public interface IConfig {

	public void init();

	public void reset();

	public void remove();

	public void save();

	public void setFile(File file);

	public void setFileConfiguration(FileConfiguration fileConfiguration);

	public void setConfigurationSection(
			ConfigurationSection configurationSection);

	public File getFile();

	public FileConfiguration getConfiguration();

	public ConfigurationSection getConfigurationSection();

}