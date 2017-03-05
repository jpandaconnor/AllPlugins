package co.uk.RandomPanda30.ExpIntercept.Events;

import co.uk.RandomPanda30.ExpIntercept.ExpInterceptData;

public class Events {

	public static final OnPlayerDeathEvent pde = new OnPlayerDeathEvent();
	public static final OnPlayerExpChangeEvent ede = new OnPlayerExpChangeEvent();
	public static final OnBlockBreakEvent bbe = new OnBlockBreakEvent();
	public static final OnExpBottleEvent ebe = new OnExpBottleEvent();

	public static void registerEvents() {
		// ExpInterceptData.getPlugin().getServer().getPluginManager()
		// .registerEvents(pde, ExpInterceptData.getPlugin());
		ExpInterceptData.getPlugin().getServer().getPluginManager()
				.registerEvents(ede, ExpInterceptData.getPlugin());
		// ExpInterceptData.getPlugin().getServer().getPluginManager()
		// .registerEvents(bbe, ExpInterceptData.getPlugin());
		// ExpInterceptData.getPlugin().getServer().getPluginManager()
		// .registerEvents(ebe, ExpInterceptData.getPlugin());
	}
}