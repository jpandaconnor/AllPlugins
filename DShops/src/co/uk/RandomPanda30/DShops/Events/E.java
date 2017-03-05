package co.uk.RandomPanda30.DShops.Events;

import co.uk.RandomPanda30.DShops.B;

public class E {

	public static final EA pie = new EA();
	public static final EB ice = new EB();
	public static final EC ice2 = new EC();

	public static void registerEvents() {
		B.plugin.getServer().getPluginManager().registerEvents(pie, B.plugin);
		B.plugin.getServer().getPluginManager().registerEvents(ice, B.plugin);
		B.plugin.getServer().getPluginManager().registerEvents(ice2, B.plugin);
	}
}