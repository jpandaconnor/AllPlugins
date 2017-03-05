package co.uk.RandomPanda30.DailyRewards;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import co.uk.RandomPanda30.DailyRewards.Commands.CMD_DRP;
import co.uk.RandomPanda30.DailyRewards.Menus.MainMenu;

public class DR extends JavaPlugin {

	private static DR plugin;
	private static PluginDescriptionFile pdfFile;

	private static String tag = "";
	
	/*
	 * 
	 * Random items by percent - Relative to fixed amount of items
	 * 
	 * Fixed amount of items
	 * 
	 * Commands through console
	 * Commands by player
	 * 
	 * Option to reset everyone's reward time
	 * 
	 * Titles and subtitles
	 * 
	 * Player effects on claim
	 * 
	 * Multi groups
	 * 
	 */

	@Override
	public void onEnable() {
		plugin = this;
		pdfFile = this.getDescription();

		saveConfig();
		
		if(!getConfig().contains("tag")) {
			getConfig().set("tag", "&F[&BDR+&F]");
			saveConfig();
		}
		
		tag = getConfig().getString("tag") + " ";
		
		getServer().getPluginManager().registerEvents(new MainMenu(), this);
		
		getCommand("drp").setExecutor(new CMD_DRP(this));

	}

	@Override
	public void onDisable() {

	}

	public static DR getPlugin() {
		return plugin;
	}

	public static PluginDescriptionFile getDes() {
		return pdfFile;
	}

	public static String getTag() {
		return tag;
	}
}
