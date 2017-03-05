package co.uk.RandomPanda30.CasinoM;

import java.util.ArrayList;

import org.bukkit.plugin.java.JavaPlugin;

import co.uk.RandomPanda30.CasinoM.Commands.CommandHandler;
import co.uk.RandomPanda30.CasinoM.Commands.CommandList.CommandCasino;
import co.uk.RandomPanda30.CasinoM.Commands.CommandList.CommandCreate;
import co.uk.RandomPanda30.CasinoM.Commands.CommandList.CommandWand;
import co.uk.RandomPanda30.CasinoM.Events.Events;
import co.uk.RandomPanda30.CasinoM.Methods.ConfigMethods;
import co.uk.RandomPanda30.CasinoM.Methods.FireworkMethods;

public class CasinoM extends JavaPlugin {

	ArrayList<String> sm;
	ArrayList<String> shm;

	@Override
	public void onEnable() {
		CasinoData.plugin = this;
		CasinoData.initDataFile();

		ConfigMethods.initConfig();
		ConfigMethods.initMessages();
		ConfigMethods.initItems();
		ConfigMethods.initSlotMachines();
		
		registerCommands();
		
		Events.registerEvents();
		
		FireworkMethods.getManager().addColors();
		FireworkMethods.getManager().addTypes();
	}

	@Override
	public void onDisable() {

	}
	
	public void registerCommands() {
		CommandHandler handler = new CommandHandler();
		handler.register("casino", new CommandCasino());
		handler.register("wand", new CommandWand());
		handler.register("create", new CommandCreate());
		
		getCommand("casino").setExecutor(handler);
	}
}