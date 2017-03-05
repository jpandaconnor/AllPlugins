package co.uk.RandomPanda30.RPG.Economy;

import java.util.UUID;

public interface IEconomy {

	public void init();

	public void deposit(UUID uuid, double amount);

	public void withdraw(UUID uuid, double amount);

	public double getBalance(UUID uuid);

}