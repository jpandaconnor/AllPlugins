package co.uk.RandomPanda30.Phones;

import java.io.File;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import co.uk.RandomPanda30.Phones.Commands.CommandHandler;
import co.uk.RandomPanda30.Phones.Commands.PhoneCMD;
import co.uk.RandomPanda30.Phones.Commands.PhonesCMD;
import co.uk.RandomPanda30.Phones.Files.Config;
import co.uk.RandomPanda30.Phones.Files.Messages;
import co.uk.RandomPanda30.Phones.Listeners.OnPlayerInteractEvent;
import co.uk.RandomPanda30.Phones.Methods.ConfigurationMethods;
import co.uk.RandomPanda30.Phones.Methods.PhonesMethods;

public class Phones extends JavaPlugin {

	public static Phones plugin;
	public static PluginDescriptionFile pdfFile;

	public static File config;
	public static FileConfiguration configC;
	public static ConfigurationSection configCS;

	public static File messages;
	public static FileConfiguration messagesC;
	public static ConfigurationSection messagesCS;

	public final OnPlayerInteractEvent pie = new OnPlayerInteractEvent(this);

	public void onEnable() {
		plugin = this;
		pdfFile = this.getDescription();
		if ((Boolean) Config.PHONES_ENABLED.value == false) {
			PhonesMethods
					.sendConsoleMessage((String) Messages.CRITICAL_PLUGINDISABLED.value);
			return;
		}

		ConfigurationMethods.initConfig();
		ConfigurationMethods.initMessages();

		PhonesMethods.announceStartUpMessages();

		// Fucked up command here. The register method doesn't like it...
		getCommand("phone").setExecutor(new PhoneCMD());

		getServer().getPluginManager().registerEvents(pie, plugin);

		registerCommands();
	}

	public void onDisable() {

		PhonesMethods.announceShutDownMessages();
	}

	public void registerCommands() {
		CommandHandler handler = new CommandHandler();
		handler.register("phones", new PhonesCMD());

		getCommand("phones").setExecutor(handler);
	}

}
