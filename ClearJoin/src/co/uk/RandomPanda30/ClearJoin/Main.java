package co.uk.RandomPanda30.ClearJoin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static Main plugin;
	public static InventoryHandler inv;

	public static String tag = "ClearJoin ";
	
	public final InventoryHandler ih = new InventoryHandler(this);

	public void onEnable() {

		Bukkit.getConsoleSender().sendMessage(tag + "Is being enabled");
		
		getServer().getPluginManager().registerEvents(this.ih, this);
	}

	public void onDisable() {

		Bukkit.getConsoleSender().sendMessage(tag + "Is being disabled");
	}

}
