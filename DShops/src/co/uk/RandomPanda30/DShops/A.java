package co.uk.RandomPanda30.DShops;

import java.util.ArrayList;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import co.uk.RandomPanda30.DShops.Events.E;
import co.uk.RandomPanda30.DShops.Methods.K;
import co.uk.RandomPanda30.DShops.Methods.L;
import co.uk.RandomPanda30.DShops.Methods.U;
import co.uk.RandomPanda30.DShops.Methods.V;

public class A extends JavaPlugin {

	ArrayList<String> sm;
	ArrayList<String> shm;

	BukkitTask checker;

	@SuppressWarnings("unchecked")
	@Override
	public void onEnable() {
		B.plugin = this;
		B.initDataFile();
		K.initConfigs();
		B.getKeys();
		E.registerEvents();

		sm = (ArrayList<String>) B.messagesC.get("SM");
		shm = (ArrayList<String>) B.messagesC.get("SHM");

		for (String s : sm) {
			L.sendMessage(s, null);
		}

		checker = new V(this).runTaskTimer(this, 0, 10 * 60 * 20);

		if (!U.setupEconomy()) {
			L.sendMessage((String) B.messagesC.get("CRITICAL.NOVAULT"), null);
			return;
		}
	}

	@Override
	public void onDisable() {
		for (String s : shm) {
			L.sendMessage(s, null);
		}

		B.plugin = null;
	}
}