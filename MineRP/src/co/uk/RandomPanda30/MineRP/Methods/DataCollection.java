package co.uk.RandomPanda30.MineRP.Methods;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import co.uk.RandomPanda30.MineRP.Job;
import co.uk.RandomPanda30.MineRP.MineRPData;
import co.uk.RandomPanda30.MineRP.Handlers.ConfigMethods;
import co.uk.RandomPanda30.MineRP.Jobs.Citizen;

@SuppressWarnings("unchecked")
public class DataCollection {

	// Get player data

	/*
	 * Don't know what the fuck this is doing here. Still haven't thought out a
	 * decent class sytem
	 */

	public static boolean firstTime(Player player) {
		if (!MineRPData.dataC.contains(player.getUniqueId().toString())) {
			return true;
		}
		return false;
	}

	public static String getJobColour(Job job) {
		String colour = "";
		switch (job) {
		case CITIZEN:
			colour = (String) MineRPData.citizenC.get("COLOUR");
			break;
		}
		return colour;
	}

	public static int getJobPay(Job job) {
		int pay = 0;
		switch (job) {
		case CITIZEN:
			pay = Citizen.getSalary();
			break;
		}
		return pay;
	}

	public static ArrayList<String> getJobDescription(Job job) {
		ArrayList<String> des = new ArrayList<String>();
		switch (job) {
		case CITIZEN:
			des = (ArrayList<String>) MineRPData.citizenC.get("DESCRIPTION");
			break;
		}
		return des;
	}

	public static void addNewPlayer(Player player) {
		MineRPData.dataC.set(player.getUniqueId().toString() + ".lastname",
				player.getName());
		MineRPData.dataC.set(player.getUniqueId().toString() + ".rpname", null);
		MineRPData.dataC.set(player.getUniqueId().toString() + ".money", 500);
		ConfigMethods.saveData();
	}

	public static String getName(Player player) {
		if (MineRPData.dataC.get(player.getUniqueId().toString() + ".rpname") != null) {
			return (String) MineRPData.dataC.get(player.getUniqueId()
					.toString() + ".rpname");
		} else {
			if (!MineRPData.dataC.get(
					player.getUniqueId().toString() + ".lastname").equals(
					player.getName())) {
				MineRPData.dataC.set(player.getUniqueId().toString()
						+ ".lastname", player.getName());
				ConfigMethods.saveData();
			}
		}
		return (String) MineRPData.dataC.get(player.getUniqueId().toString()
				+ ".lastname");
	}

	public static int getMoney(Player player) {
		return (Integer) MineRPData.dataC.get(player.getUniqueId().toString()
				+ ".money");
	}

	public static int getPayInterval() {
		return (Integer) MineRPData.configC.get("PAY.INTERVAL");
	}
}