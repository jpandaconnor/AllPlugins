package co.uk.RandomPanda30.DShops.Methods;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import co.uk.RandomPanda30.DShops.B;

public class U {

	public static Economy econ = null;

	public static void withdrawMoney(int amount, Player player) {
		econ.withdrawPlayer(player, amount);
	}

	public static void depositMoney(int amount, Player player) {
		econ.depositPlayer(player, amount);
	}

	public static boolean setupEconomy() {
		if (B.plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = B.plugin.getServer()
				.getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}

}
