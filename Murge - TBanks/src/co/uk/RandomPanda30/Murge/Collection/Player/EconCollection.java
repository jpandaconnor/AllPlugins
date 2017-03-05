package co.uk.RandomPanda30.Murge.Collection.Player;

import java.util.UUID;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Collection.Bases.EconBase;
import co.uk.RandomPanda30.Murge.Methods.ConfigMethods;
import co.uk.RandomPanda30.Murge.MySQL.MySQL;
import co.uk.RandomPanda30.Murge.Values.Enums.ConfigValues;

public class EconCollection implements EconBase {

	private static EconCollection instance = new EconCollection();

	public static EconCollection getCollection() {
		return instance;
	}

	public static Economy econ = null;
	MySQL mysql = Murge.getMysql();

	@Override
	public Economy getEcon() {
		return econ;
	}

	@Override
	public double getBalance(UUID uuid) {
		if (usingVault()) {
			return (double) getEcon().getBalance(Bukkit.getPlayer(uuid));
		}
		return (Integer) Murge.cso.getDataC().get(uuid.toString() + ".money");
	}

	@Override
	public void setBalance(UUID uuid, double amount) {
		if (usingVault()) {
			getEcon().withdrawPlayer(Bukkit.getPlayer(uuid), getBalance(uuid));
			getEcon().depositPlayer(Bukkit.getPlayer(uuid), amount);
		} else {
			Murge.cso.getDataC().set(uuid.toString() + ".money", amount);
			ConfigMethods.saveData();
		}
	}

	@Override
	public void addBalance(UUID uuid, double amount) {
		if (usingVault()) {
			getEcon().depositPlayer(Bukkit.getPlayer(uuid), amount);
		} else {
			Murge.cso.getDataC().set(uuid.toString() + ".money",
					getBalance(uuid) + amount);
			ConfigMethods.saveData();
		}
	}

	@Override
	public void removeBalance(UUID uuid, double amount) {
		if (usingVault()) {
			getEcon().withdrawPlayer(Bukkit.getPlayer(uuid), amount);
		} else {
			if (!(getBalance(uuid) - amount < 0)) {
				Murge.cso.getDataC().set(uuid.toString() + ".money",
						getBalance(uuid) - amount);
				ConfigMethods.saveData();
			} else {
				Murge.cso.getDataC().set(uuid.toString() + ".money", 0);
				ConfigMethods.saveData();
			}
		}
	}

	@Override
	public boolean hasEnough(UUID uuid, double amount) {
		if ((getBalance(uuid) - amount) < 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean usingVault() {
		return (Boolean) Murge.cMap.getStat(ConfigValues.USINGVAULT);
	}

	@Override
	public void setVault(boolean value) {
		Murge.cMap.setStat(ConfigValues.USINGVAULT, value);
	}
}