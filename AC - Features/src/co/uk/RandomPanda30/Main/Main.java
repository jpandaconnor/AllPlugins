package co.uk.RandomPanda30.Main;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.api.Economy;

import co.uk.RandomPanda30.CMD.CMDac;
import co.uk.RandomPanda30.CMD.CMDbet;
import co.uk.RandomPanda30.CMD.CMDt;
import co.uk.RandomPanda30.Events.OnInventoryClickEvent;
import co.uk.RandomPanda30.Text.TextH;

public class Main extends JavaPlugin {

	public static Main plugin;
	public static FileConfiguration config;

	public final OnInventoryClickEvent ic = new OnInventoryClickEvent(this);

	public static Essentials ess = (Essentials) Bukkit.getServer()
			.getPluginManager().getPlugin("Essentials");

	public static Economy econ = null;

	public void onEnable() {
		
		CMDbet.jackpotSize = 0;

		if (ess != null) {
			Bukkit.getConsoleSender().sendMessage(TextH.loadingEss.toString());
		} else {
			Bukkit.getConsoleSender().sendMessage(TextH.needsEss.toString());
			return;
		}

		Bukkit.getConsoleSender().sendMessage(TextH.enabled.toString());

		getServer().getPluginManager().registerEvents(this.ic, this);

		getCommand("ac").setExecutor(new CMDac(this));
		getCommand("t").setExecutor(new CMDt(this));
		getCommand("bet").setExecutor(new CMDbet(this));

		setupEconomy();

	}

	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(TextH.disabled.toString());
	}

	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer()
				.getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}
}
