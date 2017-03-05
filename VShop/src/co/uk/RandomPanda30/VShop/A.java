package co.uk.RandomPanda30.VShop;

import java.util.ArrayList;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import co.uk.RandomPanda30.VShop.Commands.CA;
import co.uk.RandomPanda30.VShop.Commands.CB;
import co.uk.RandomPanda30.VShop.Commands.CC;
import co.uk.RandomPanda30.VShop.Events.E;
import co.uk.RandomPanda30.VShop.Files.Messages;
import co.uk.RandomPanda30.VShop.Methods.EBC;
import co.uk.RandomPanda30.VShop.Methods.U;
import co.uk.RandomPanda30.VShop.Methods.UB;
import co.uk.RandomPanda30.VShop.Methods.X;
import co.uk.RandomPanda30.VShop.Methods.YA;
import co.uk.RandomPanda30.VShop.Methods.Z;

public class A extends JavaPlugin {

	public static A plugin;
	public static PluginDescriptionFile pdfFile;

	ArrayList<String> startM;
	ArrayList<String> shutM;

	BukkitTask checker;

	@SuppressWarnings("unchecked")
	@Override
	public void onEnable() {
		plugin = this;
		pdfFile = this.getDescription();

		Z.initShops();
		Z.initItems();
		Z.initMessages();
		Z.initConfig();

		if (this.getServer().getPluginManager().getPlugin("Vault") == null) {
			X.sendMessage((String) Messages.CRITICAL_NOVAULT.value, null);
			this.getServer().getPluginManager().disablePlugin(this);
			return;
		}

		E.registerEvents();

		registerCommands();

		B.getKeys();
		EBC.loadSigns();
		U.updateSigns();

		checker = new UB(this).runTaskTimer(this, 0, 10 * 60 * 20);

		startM = (ArrayList<String>) Messages.STARTUP_MESSAGES_LIST.value;
		shutM = (ArrayList<String>) Messages.SHUTDOWN_MESSAGES_LIST.value;

		for (String m : startM) {
			X.sendMessage(m, null);
		}
	}

	@Override
	public void onDisable() {
		checker.cancel();
		Z.saveConfig();
		Z.saveMessages();
		Z.saveItems();
		Z.saveShops();

		for (String m : shutM) {
			X.sendMessage(m, null);
		}

		B.keys.clear();
		EBC.signLocations.clear();

		plugin = null;
	}

	public void registerCommands() {
		YA handler = new YA();
		handler.register("vshop", new CA());
		handler.register("wand", new CB());
		handler.register("create", new CC());
		// handler.register("help", new CD());
		getCommand("vshop").setExecutor(handler);
	}
}