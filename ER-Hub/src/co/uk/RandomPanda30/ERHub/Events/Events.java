package co.uk.RandomPanda30.ERHub.Events;

import co.uk.RandomPanda30.ERHub.ERHubData;

public class Events {

	public static final OnPlayerJoinEvent pje = new OnPlayerJoinEvent();

	public static void registerEvents() {
		ERHubData.getPlugin().getServer().getPluginManager()
				.registerEvents(pje, ERHubData.getPlugin())
		;
	}
}