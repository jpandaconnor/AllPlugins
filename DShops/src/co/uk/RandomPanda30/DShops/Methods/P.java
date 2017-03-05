package co.uk.RandomPanda30.DShops.Methods;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.DShops.B;
import co.uk.RandomPanda30.DShops.S;

@SuppressWarnings("unchecked")
public class P {

	public static void addDoorDefaults(String location) {
		B.doorsC.set(location + ".state", S.FORSALE.toString());
		B.doorsC.set(location + ".owner.uuid", null);
		B.doorsC.set(location + ".owner.lastname", "");
		B.doorsC.set(location + ".friends", new ArrayList<String>());
		K.saveDoors();
	}

	public static boolean isCustomPrice(String location) {
		if (B.doorsC.get(location + ".cprice") == null) {
			return false;
		}
		return true;
	}

	public static int getCustomPrice(String location) {
		int amount = 0;
		if (B.doorsC.get(location + ".cprice") != null) {
			amount = (Integer) B.doorsC.get(location + ".cprice");
		} else {
			if (P.getDoorState((String) B.doorsC.get(location + ".state"))
					.equals(S.FORSALE)
					|| P.getDoorState(
							(String) B.doorsC.get(location + ".state")).equals(
							S.OWNED)) {
				amount = getDoorPrice();
			} else {
				amount = PA.getRentPrice();
			}
		}
		return amount;
	}

	public static int getPrice(String location) {
		int amount = 0;
		if (P.getDoorState((String) B.doorsC.get(location + ".state")).equals(
				S.FORSALE)
				|| P.getDoorState((String) B.doorsC.get(location + ".state"))
						.equals(S.OWNED)) {
			amount = getDoorPrice();
		} else {
			amount = PA.getRentPrice();
		}
		return amount;
	}

	public static void addToCP(String location, int amount) {
		int i = getCustomPrice(location);
		B.doorsC.set(location + ".cprice", i + amount);
		K.saveDoors();
	}

	public static void minusToCP(String location, int amount) {
		int i = getCustomPrice(location);
		if (i - amount < 0) {
			B.doorsC.set(location + ".cprice", 0);
		} else {
			B.doorsC.set(location + ".cprice", i - amount);
		}
	}

	public static void addFriend(Player player, String location) {
		ArrayList<String> list;
		if (!B.doorsC.getStringList(location + ".friends").contains(
				M.compileDetails(player.getUniqueId(), player.getName()))) {
			list = (ArrayList<String>) B.doorsC.getStringList(location
					+ ".friends");
			if (list != null) {
				list.add(M.compileDetails(player.getUniqueId(),
						player.getName()));
				B.doorsC.set(location + ".friends", list);
			} else {
				list = new ArrayList<String>();
				list.add(M.compileDetails(player.getUniqueId(),
						player.getName()));
				B.doorsC.set(location + ".friends", list);
			}
		}

		try {
			B.doorsC.save(B.doors);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void removeFriend(String path, String location) {
		ArrayList<String> list;
		if (B.doorsC.getStringList(location + ".friends") != null) {
			list = (ArrayList<String>) B.doorsC.get(location + ".friends");
		} else {
			list = new ArrayList<String>();
		}

		if (list.contains(path)) {
			list.remove(path);
		}

		B.doorsC.set(location + ".friends", list);
		try {
			K.saveDoors();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void buyDoor(Location location, Player player) {
		String sLocation = M.compileLocation(location);
		B.doorsC.set(sLocation + ".state", S.OWNED.toString());
		B.doorsC.set(sLocation + ".owner.uuid", player.getUniqueId().toString());
		B.doorsC.set(sLocation + ".owner.lastname", player.getName());
		try {
			K.saveDoors();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static S getDoorState(String string) {
		S state = null;
		switch (string) {
		case "FORSALE":
			state = S.FORSALE;
			break;
		case "FORRENT":
			state = S.FORRENT;
			break;
		case "OWNED":
			state = S.OWNED;
			break;
		case "RENTED":
			state = S.RENTED;
			break;
		}
		return state;
	}

	public static void sellDoor(Player player, String location) {
		B.doorsC.set(location + ".state", S.FORSALE.toString());
		B.doorsC.set(location + ".owner.uuid", null);
		B.doorsC.set(location + ".owner.lastname", null);
		B.doorsC.set(location + ".friends", new ArrayList<String>());
		if (player != null) {
			int price = (P.isCustomPrice(location) ? P.getCustomPrice(location)
					: P.getDoorPrice()) / 10;
			int newPrice = (P.isCustomPrice(location) ? P
					.getCustomPrice(location) : P.getDoorPrice()) - price;
			U.depositMoney(newPrice, player);
		}
		try {
			K.saveDoors();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getDoorPrice() {
		return (Integer) B.configC.get("DOOR.PRICE");
	}

	public static int getDoorThreshold() {
		return (Integer) B.configC.get("DOOR.THRESHOLD");
	}

	public static ArrayList<UUID> getFriendsUUID(String location) {
		ArrayList<String> rawUUIDs = (ArrayList<String>) B.doorsC.get(location
				+ ".friends");
		ArrayList<UUID> uuids = new ArrayList<UUID>();
		for (String uuid : rawUUIDs) {
			String[] s = M.decompileDetails(uuid);
			String newUUID = s[0];
			uuids.add(UUID.fromString(newUUID));
		}
		return uuids;
	}

	public static UUID getOwner(String location) {
		String uuid = (String) B.doorsC.get(location + ".owner.uuid");
		return UUID.fromString(uuid);
	}

	public static String getOwnerLastName(String location) {
		String name = (String) B.doorsC.get(location + ".owner.lastname");
		return name;
	}
}