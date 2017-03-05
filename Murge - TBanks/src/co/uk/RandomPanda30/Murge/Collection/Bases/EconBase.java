package co.uk.RandomPanda30.Murge.Collection.Bases;

import java.util.UUID;

import net.milkbowl.vault.economy.Economy;

public interface EconBase {
	
	public Economy getEcon();

	public double getBalance(UUID uuid);

	public void setBalance(UUID uuid, double amount);

	public void addBalance(UUID uuid, double amount);

	public void removeBalance(UUID uuid, double amount);

	public boolean hasEnough(UUID uuid, double amount);

	public boolean usingVault();

	public void setVault(boolean value);

}