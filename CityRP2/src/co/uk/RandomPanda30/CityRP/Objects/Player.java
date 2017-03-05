package co.uk.RandomPanda30.CityRP.Objects;

import java.util.UUID;

import co.uk.RandomPanda30.CityRP.CityRPData;
import co.uk.RandomPanda30.CityRP.Misc.Job;
import co.uk.RandomPanda30.CityRP.Objects.Temp.Data_Editing;

public class Player {

	private String name;
	private String rpName;
	private UUID uuid;

	private org.bukkit.entity.Player player;
	private Job job;

	private int money;

	private Data_Editing editingData;
	
	public Player (org.bukkit.entity.Player rplayer, String rpName, int money,
			Job job, boolean toList) {
		this.setName(rplayer.getName());
		this.setRpName(""); // Work on getter for RP names ;P Collection methods

		this.setUuid(rplayer.getUniqueId());

		this.setPlayer(rplayer);
		this.setJob(job);

		this.setMoney(money);

		if (toList)
			addToList();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRpName() {
		return rpName;
	}

	public void setRpName(String rpName) {
		this.rpName = rpName;
	}

	public org.bukkit.entity.Player getPlayer() {
		return player;
	}

	public void setPlayer(org.bukkit.entity.Player player) {
		this.player = player;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	private void addToList() {
		CityRPData.players.add(this);
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
	public Data_Editing getEditingData() {
		return editingData;
	}
	
	public void setEditingData(Data_Editing editingData) {
		this.editingData = editingData;
	}
}