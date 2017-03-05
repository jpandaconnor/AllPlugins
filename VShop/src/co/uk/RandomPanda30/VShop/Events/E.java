package co.uk.RandomPanda30.VShop.Events;

import co.uk.RandomPanda30.VShop.A;

public class E {

	public static final EA pie = new EA();
	public static final EB sce = new EB();
	public static final EC bpe = new EC();
	public static final ED ice = new ED();
	public static final EE bbe = new EE();
	public static final EF pje = new EF();

	public static void registerEvents() {
		A.plugin.getServer().getPluginManager().registerEvents(pie, A.plugin);
		A.plugin.getServer().getPluginManager().registerEvents(sce, A.plugin);
		A.plugin.getServer().getPluginManager().registerEvents(bpe, A.plugin);
		A.plugin.getServer().getPluginManager().registerEvents(ice, A.plugin);
		A.plugin.getServer().getPluginManager().registerEvents(bbe, A.plugin);
		A.plugin.getServer().getPluginManager().registerEvents(pje, A.plugin);
	}
}