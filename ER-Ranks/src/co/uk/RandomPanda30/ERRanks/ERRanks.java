package co.uk.RandomPanda30.ERRanks;

import java.util.ArrayList;

import org.bukkit.plugin.java.JavaPlugin;

import co.uk.RandomPanda30.ERRanks.Events.Events;
import co.uk.RandomPanda30.ERRanks.Methods.ConfigMethods;
import co.uk.RandomPanda30.ERRanks.Methods.StringMethods;

@SuppressWarnings("unchecked")
public class ERRanks extends JavaPlugin {

	ArrayList<String> sm;
	ArrayList<String> shm;

	@Override
	public void onEnable() {
		ERRanksData.plugin = this;
		ERRanksData.initDataFile();

		ConfigMethods.initConfig();
		ConfigMethods.initMessages();

		Events.registerEvents();

		sm = (ArrayList<String>) ERRanksData.messagesC.get("SM");
		shm = (ArrayList<String>) ERRanksData.messagesC.get("SHM");

		for (String s : sm) {
			StringMethods.sendMessage(s, null);
		}
	}

	@Override
	public void onDisable() {
		for (String s : shm) {
			StringMethods.sendMessage(s, null);
		}
		ERRanksData.plugin = null;
	}
}