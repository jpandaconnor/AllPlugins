package co.uk.RandomPanda30.DShops.Methods;

import java.util.ArrayList;
import java.util.Calendar;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.DShops.B;
import co.uk.RandomPanda30.DShops.S;

public class PA {

	public static int getRentPrice() {
		return (Integer) B.configC.get("RENT.PRICE");
	}

	public static int getRentDays() {
		return (Integer) B.configC.get("RENT.DAYS");
	}

	public static void addDoorDefaults(String location, Player player) {
		B.doorsC.set(location + ".state", S.FORRENT.toString());
		B.doorsC.set(location + ".owner.uuid", null);
		B.doorsC.set(location + ".owner.lastname", "");
		B.doorsC.set(location + ".friends", new ArrayList<String>());
		K.saveDoors();
		U.withdrawMoney(getRentPrice(), player);
	}

	public static void updateRentTime(String location, Player player) {
		B.doorsC.set(
				location + ".time",
				Long.toString(Calendar.getInstance().getTimeInMillis()
						+ (getRentDays() * 1000 * 60 * 60 * 24)));
		try {
			K.saveDoors();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sellRentDoor(String location, int amount, Player player) {
		B.doorsC.set(location + ".state", S.FORRENT.toString());
		B.doorsC.set(location + ".owner.uuid", null);
		B.doorsC.set(location + ".owner.lastname", "");
		B.doorsC.set(location + ".friends", new ArrayList<String>());
		if (player != null) {
			U.depositMoney(amount, player);
		}
		try {
			K.saveDoors();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void rentDoor(Location location, Player player) {
		String sLocation = M.compileLocation(location);
		B.doorsC.set(sLocation + ".state", S.RENTED.toString());
		B.doorsC.set(sLocation + ".owner.uuid", player.getUniqueId().toString());
		B.doorsC.set(sLocation + ".owner.lastname", player.getName());
		B.doorsC.set(
				sLocation + ".time",
				Long.toString(Calendar.getInstance().getTimeInMillis()
						+ (getRentDays() * 1000 * 60 * 60 * 24)));
		try {
			K.saveDoors();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}