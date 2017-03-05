package co.uk.RandomPanda30.RPG.Economy;

import java.util.UUID;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.RPG.RPG;
import co.uk.RandomPanda30.RPG.RPGValues;
import co.uk.RandomPanda30.RPG.Files.Bank.Bank;
import co.uk.RandomPanda30.RPG.Files.Config.Enums.ConfigValues;

public class RPGEconomy implements IEconomy {

	public RPG plugin;
	public RPGValues values;

	public Bank bank;

	public Vault vault;
	public Economy econ;

	public boolean usingVault;

	public RPGEconomy (RPG plugin, RPGValues values, Bank bank, Vault vault) {
		this.plugin = plugin;
		this.vault = vault;
		this.bank = bank;
		this.values = values;
	}

	@Override
	public void init() {
		if ((Boolean) plugin.getRPGConfig().getConfigMap()
				.getStat(ConfigValues.VAULT)) {
			if (!vault.setupEconomy()) {
				plugin.getServer().getPluginManager().disablePlugin(plugin);
				// Error message here
				return;
			} else {
				usingVault = true;
				// Message here
			}
		} else {
			usingVault = false;
			// Message here
		}
	}

	@Override
	public void deposit(UUID uuid, double amount) {
		if (!usingVault) {
			values.getBankC().set(uuid.toString() + ".money",
					getBalance(uuid) + amount);
			bank.save();
		} else {
			if (Bukkit.getPlayer(uuid).isOnline()
					&& Bukkit.getPlayer(uuid) != null) {
				Player player = Bukkit.getPlayer(uuid);
				econ.depositPlayer(player, amount);
			} else {
				econ.depositPlayer(Bukkit.getOfflinePlayer(uuid), amount);
			}
		}
	}

	@Override
	public void withdraw(UUID uuid, double amount) {
		if (!usingVault) {
			values.getBankC().set(
					uuid.toString() + ".money",
					(getBalance(uuid) - amount < 0 ? 0 : getBalance(uuid)
							- amount));
			bank.save();
		} else {
			if (Bukkit.getPlayer(uuid).isOnline()
					&& Bukkit.getPlayer(uuid) != null) {
				Player player = Bukkit.getPlayer(uuid);
				econ.withdrawPlayer(player, amount);
			} else {
				econ.withdrawPlayer(Bukkit.getOfflinePlayer(uuid), amount);
			}
		}
	}

	@Override
	public double getBalance(UUID uuid) {
		if (!usingVault) {
			return (values.getBankC().get(uuid.toString()) != null ? (Double) values
					.getBankC().get(uuid.toString() + ".money") : 0);
		} else {
			if (Bukkit.getPlayer(uuid).isOnline()
					&& Bukkit.getPlayer(uuid) != null) {
				return econ.getBalance(Bukkit.getPlayer(uuid));
			}
		}
		return econ.getBalance(Bukkit.getOfflinePlayer(uuid));
	}

	// TODO add player to file on start
}