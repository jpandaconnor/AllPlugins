package co.uk.RandomPanda30.MineRP.Handlers;

import org.bukkit.entity.Player;

import co.uk.RandomPanda30.MineRP.Job;
import co.uk.RandomPanda30.MineRP.Methods.DataCollection;

public class PlayerHandler {

	private String rpName;

	private int money;

	private Player player;
	private Job job;

	public PlayerHandler (Player player, String rpName, int money, Job job) {
		this.player = player;
		this.rpName = rpName;
		this.money = money;
		this.job = job;
	}

	public Player getPlayer() {
		return player;
	}

	public String getName() {
		if (rpName != null) {
			return getRpName();
		}
		return player.getName();
	}

	public String getRpName() {
		return rpName;
	}

	public int getMoney() {
		return money;
	}

	public Job getJob() {
		return job;
	}

	public void setRpName(String name) {
		this.rpName = name;
		// Update here ;P
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void addMoney(int amount) {
		this.money = this.money + amount;
	}

	public void removeMoney(int amount) {
		this.money = this.money - amount;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public void pay() {
		EconomyHandler.depositPlayer(this.player.getUniqueId(),
				DataCollection.getJobPay(this.job));
	}
}