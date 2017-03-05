package co.uk.RandomPanda30.ItemHandlerPlus.Events;

import co.uk.RandomPanda30.ItemHandlerPlus.B;

public class E {

	public static final EA ice = new EA();
	public static final EB ice2 = new EB();
	public static final EC ide = new EC();
	public static final ED pje = new ED();
	public static final EF pie = new EF();

	public static void registerEvents() {
		B.plugin.getServer().getPluginManager().registerEvents(ice, B.plugin);
		B.plugin.getServer().getPluginManager().registerEvents(ice2, B.plugin);
		B.plugin.getServer().getPluginManager().registerEvents(ide, B.plugin);
		B.plugin.getServer().getPluginManager().registerEvents(pje, B.plugin);
		B.plugin.getServer().getPluginManager().registerEvents(pie, B.plugin);
	}
}