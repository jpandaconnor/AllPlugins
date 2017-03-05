package co.uk.RandomPanda30.CityRP;

import java.io.File;
import java.util.HashMap;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import co.uk.RandomPanda30.CityRP.Configs.Enums.Citizen.CitizenV;
import co.uk.RandomPanda30.CityRP.Misc.Value;

@SuppressWarnings("rawtypes")
public class Data {

	public static final HashMap<CitizenV, Value> citizenMap = new HashMap<>();

	public static File config;
	public static FileConfiguration configf;
	public static ConfigurationSection configs;

	public static File messages;
	public static FileConfiguration messagesf;
	public static ConfigurationSection messagess;

	public static File items;
	public static FileConfiguration itemsf;
	public static ConfigurationSection itemss;

	/* Jobs */

	public static File citizen;
	public static FileConfiguration citizenf;
	public static ConfigurationSection citizens;

}