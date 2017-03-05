package co.uk.RandomPanda30.ERHub;

import java.util.ArrayList;

import org.bukkit.plugin.java.JavaPlugin;

import co.uk.RandomPanda30.ERHub.Events.Events;
import co.uk.RandomPanda30.ERHub.Methods.ConfigMethods;
import co.uk.RandomPanda30.ERHub.Methods.StringMethods;

@SuppressWarnings("unchecked")
public class ERHub extends JavaPlugin {

	ArrayList<String> sm;
	ArrayList<String> shm;

	@Override
	public void onEnable() {
		ERHubData.plugin = this;
		ERHubData.initDataFile();

		ConfigMethods.initConfig();
		ConfigMethods.initMessages();

		Events.registerEvents();

		sm = (ArrayList<String>) ERHubData.messagesC.get("SM");
		shm = (ArrayList<String>) ERHubData.messagesC.get("SHM");

		for (String s : sm) {
			StringMethods.sendMessage(s, null);
		}
	}

	@Override
	public void onDisable() {
		for (String s : shm) {
			StringMethods.sendMessage(s, null);
		}
		ERHubData.plugin = null;
	}
}