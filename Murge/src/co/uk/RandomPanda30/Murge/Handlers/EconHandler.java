package co.uk.RandomPanda30.Murge.Handlers;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.plugin.RegisteredServiceProvider;

import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Collection.Player.EconCollection;

public class EconHandler {

	private static EconHandler instance = new EconHandler();

	public static EconHandler getHandler() {
		return instance;
	}

	public boolean setupEcon() {
		if (MurgeData.getPlugin().getServer().getPluginManager()
				.getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = MurgeData.getPlugin()
				.getServer().getServicesManager()
				.getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		EconCollection.econ = rsp.getProvider();
		return EconCollection.getCollection().getEcon() != null;
	}
}