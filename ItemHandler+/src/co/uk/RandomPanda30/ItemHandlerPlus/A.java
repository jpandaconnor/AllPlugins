package co.uk.RandomPanda30.ItemHandlerPlus;

import java.util.ArrayList;

import org.bukkit.plugin.java.JavaPlugin;

import co.uk.RandomPanda30.ItemHandlerPlus.Commands.X;
import co.uk.RandomPanda30.ItemHandlerPlus.Commands.XA;
import co.uk.RandomPanda30.ItemHandlerPlus.Events.E;
import co.uk.RandomPanda30.ItemHandlerPlus.Methods.Y;
import co.uk.RandomPanda30.ItemHandlerPlus.Methods.Z;

@SuppressWarnings("unchecked")
public class A extends JavaPlugin {

	ArrayList<String> sm;
	ArrayList<String> shm;

	@Override
	public void onEnable() {
		B.plugin = this;
		B.initDataFile();

		Z.initConfig();
		Z.initMessages();
		Z.initItems();

		registerCommands();
		E.registerEvents();

		sm = (ArrayList<String>) B.messagesC.get("SM");
		shm = (ArrayList<String>) B.messagesC.get("SHM");

		for (String s : sm) {
			Y.sendMessage(s, null);
		}
	}

	@Override
	public void onDisable() {
		for (String s : shm) {
			Y.sendMessage(s, null);
		}

		B.plugin = null;
	}

	public void registerCommands() {
		X handler = new X();
		handler.register("ihp", new XA());
		getCommand("ihp").setExecutor(handler);
	}
}