package co.uk.RandomPanda30.RPG.Economy;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.plugin.RegisteredServiceProvider;

import co.uk.RandomPanda30.RPG.RPG;

public class Vault {

	public RPG plugin;
	public RPGEconomy econ;

	public Vault (RPG plugin, RPGEconomy econ) {
		this.plugin = plugin;
		this.econ = econ;
	}

	public boolean setupEconomy() {
		if (plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}

		RegisteredServiceProvider<Economy> rsp = plugin.getServer()
				.getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}

		econ.econ = (Economy) rsp.getProvider();
		return econ != null;
	}
}