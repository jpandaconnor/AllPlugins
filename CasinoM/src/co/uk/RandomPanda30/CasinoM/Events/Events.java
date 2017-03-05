package co.uk.RandomPanda30.CasinoM.Events;

import co.uk.RandomPanda30.CasinoM.CasinoData;

public class Events {

	public static final OnPlayerInteractEvent pie = new OnPlayerInteractEvent();

	public static void registerEvents() {
		CasinoData.getPlugin().getServer().getPluginManager()
				.registerEvents(pie, CasinoData.plugin);
	}
}