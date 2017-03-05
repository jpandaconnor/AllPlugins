package co.uk.RandomPanda30.VShop.Methods;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import co.uk.RandomPanda30.VShop.A;

public class W {

	public static Economy econ = null;

	public static void withdrawMoney(int amount, Player player) {
		econ.withdrawPlayer(player, amount);
	}

	public static boolean hasEnough(Player player, int amount) {
		if (!econ.has(player, amount)) {
			return false;
		}
		return true;
	}

	public static void depositMoney(int amount, Player player) {
		econ.depositPlayer(player, amount);
	}

	public static boolean setupEconomy() {
		if (A.plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = A.plugin.getServer()
				.getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}
}
