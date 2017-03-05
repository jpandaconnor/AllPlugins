package co.uk.RandomPanda30.MWarn.Events;

import co.uk.RandomPanda30.MWarn.B;

public class E {

	public static final EA ice = new EA();
	public static final EB ple = new EB();
	public static final EC ice2 = new EC();

	public static void registerEvents() {
		B.plugin.getServer().getPluginManager().registerEvents(ice, B.plugin);
		B.plugin.getServer().getPluginManager().registerEvents(ple, B.plugin);
		B.plugin.getServer().getPluginManager().registerEvents(ice2, B.plugin);
	}
}