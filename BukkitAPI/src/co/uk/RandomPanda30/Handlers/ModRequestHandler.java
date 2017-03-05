package co.uk.RandomPanda30.Handlers;

import co.uk.RandomPanda30.Main.Main;

public class ModRequestHandler {

	public ModRequestHandler (Main plugin) {
		Main.plugin = plugin;
	}

	public static void ModRequest() {
		Main.plugin.getServer().getScheduler()
				.scheduleSyncDelayedTask(Main.plugin, new Runnable() {
					public void run() {
						Main.alreadyRequested.clear();
					}
				}, 2000L);
	}
}
