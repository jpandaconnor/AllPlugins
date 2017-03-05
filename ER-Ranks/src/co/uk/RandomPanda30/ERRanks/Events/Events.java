package co.uk.RandomPanda30.ERRanks.Events;

import co.uk.RandomPanda30.ERRanks.ERRanksData;

public class Events {

	public static final OnAsyncPlayerChatEvent pce = new OnAsyncPlayerChatEvent();

	public static void registerEvents() {
		ERRanksData.plugin.getServer().getPluginManager()
				.registerEvents(pce, ERRanksData.getPlugin());
	}
}