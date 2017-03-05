package co.uk.RandomPanda30.MineRP.Events;

import co.uk.RandomPanda30.MineRP.MineRPData;

public class Events {

	public static final OnPlayerJoinEvent pje = new OnPlayerJoinEvent();
	public static final OnPlayerLoginEvent ple = new OnPlayerLoginEvent();
	public static final OnPlayerQuitEvent pqe = new OnPlayerQuitEvent();
	public static final OnPlayerDropItemEvent pdie = new OnPlayerDropItemEvent();

	public static void registerEvents() {
		MineRPData.getPlugin().getServer().getPluginManager()
				.registerEvents(pje, MineRPData.plugin);
		MineRPData.getPlugin().getServer().getPluginManager()
				.registerEvents(ple, MineRPData.plugin);
		MineRPData.getPlugin().getServer().getPluginManager()
				.registerEvents(pqe, MineRPData.plugin);
		MineRPData.getPlugin().getServer().getPluginManager()
				.registerEvents(pdie, MineRPData.plugin);
	}
}