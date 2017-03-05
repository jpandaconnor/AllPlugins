package co.uk.RandomPanda30.Murge.Events;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Kits.KitEditor;
import co.uk.RandomPanda30.Murge.Kits.KitListeners;

public class Events {

	public Murge plugin;

	public Events (Murge plugin) {
		this.plugin = plugin;
	}

	public static final OnPlayerJoinEvent pje = new OnPlayerJoinEvent();
	public static final OnPlayerLoginEvent ple = new OnPlayerLoginEvent();
	public static final OnPlayerInteractEvent pie = new OnPlayerInteractEvent();
	public static final OnInventoryClickEvent ice = new OnInventoryClickEvent();
	public static final OnBlockPlaceEvent bpe = new OnBlockPlaceEvent();
	public static final OnBlockBreakEvent bbe = new OnBlockBreakEvent();
	public static final OnWeatherChangeEvent wce = new OnWeatherChangeEvent();
	public static final OnPlayerDropItemEvent pdie = new OnPlayerDropItemEvent();
	public static final OnEntityDamageByEntityEvent edbee = new OnEntityDamageByEntityEvent();
	public static final OnPlayerDeathEvent pde = new OnPlayerDeathEvent();
	public static final OnEntityDeathEvent ede = new OnEntityDeathEvent();
	public static final OnPlayerPickupItemEvent ppie = new OnPlayerPickupItemEvent();
	public static final OnBlockIgniteEvent bie = new OnBlockIgniteEvent();
	public static final OnEntityDamageEvent ede2 = new OnEntityDamageEvent();
	public static final OnAsyncPlayerChatEvent pce = new OnAsyncPlayerChatEvent();

	public void registerEvents() {
		MurgeData.getPlugin().getServer().getPluginManager()
				.registerEvents(pje, MurgeData.getPlugin());
		MurgeData.getPlugin().getServer().getPluginManager()
				.registerEvents(ple, MurgeData.getPlugin());
		MurgeData.getPlugin().getServer().getPluginManager()
				.registerEvents(pie, MurgeData.getPlugin());
		MurgeData.getPlugin().getServer().getPluginManager()
				.registerEvents(ice, MurgeData.getPlugin());
		MurgeData.getPlugin().getServer().getPluginManager()
				.registerEvents(bpe, MurgeData.getPlugin());
		MurgeData.getPlugin().getServer().getPluginManager()
				.registerEvents(bbe, MurgeData.getPlugin());
		MurgeData.getPlugin().getServer().getPluginManager()
				.registerEvents(wce, MurgeData.getPlugin());
		MurgeData.getPlugin().getServer().getPluginManager()
				.registerEvents(pdie, MurgeData.getPlugin());
		MurgeData.getPlugin().getServer().getPluginManager()
				.registerEvents(edbee, MurgeData.getPlugin());
		MurgeData.getPlugin().getServer().getPluginManager()
				.registerEvents(pde, MurgeData.getPlugin());
		MurgeData.getPlugin().getServer().getPluginManager()
				.registerEvents(ede, MurgeData.getPlugin());
		MurgeData.getPlugin().getServer().getPluginManager()
				.registerEvents(ppie, MurgeData.getPlugin());
		MurgeData.getPlugin().getServer().getPluginManager()
				.registerEvents(bie, MurgeData.getPlugin());
		MurgeData.getPlugin().getServer().getPluginManager()
				.registerEvents(ede2, MurgeData.getPlugin());
		MurgeData.getPlugin().getServer().getPluginManager()
				.registerEvents(pce, MurgeData.getPlugin());
		MurgeData.getPlugin().getServer().getPluginManager()
				.registerEvents(new KitListeners(plugin), plugin);
		MurgeData.getPlugin().getServer().getPluginManager()
				.registerEvents(new KitEditor(plugin), plugin);
	}
}