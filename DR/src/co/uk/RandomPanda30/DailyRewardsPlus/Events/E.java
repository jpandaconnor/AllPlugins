package co.uk.RandomPanda30.DailyRewardsPlus.Events;

import co.uk.RandomPanda30.DailyRewardsPlus.B;

public class E {

	static final EA pje = new EA();
	static final EB ice = new EB();
	static final EC ice2 = new EC();

	public static void registerEvents() {
		B.plugin.getServer().getPluginManager().registerEvents(pje, B.plugin);
		B.plugin.getServer().getPluginManager().registerEvents(ice, B.plugin);
		B.plugin.getServer().getPluginManager().registerEvents(ice2, B.plugin);
	}
}