package co.uk.RandomPanda30.VShop.Methods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.VShop.B;
import co.uk.RandomPanda30.VShop.S;
import co.uk.RandomPanda30.VShop.Files.Messages;

public class U {

	public static void createSafeZone(Location pos1, Location pos2) {
		B.shopsC.set("safezone", true);
		B.shopsC.set("safezone.pos1", X.compileLocation(pos1));
		B.shopsC.set("safezone.pos2", X.compileLocation(pos2));
		Z.saveShops();
	}

	public static Location getPosition(String plotName, int positionNo) {
		Location loc = null;
		if (positionNo == 1) {
			loc = X.decompileLocation(B.shopsC.getString(plotName + ".pos1"));
		} else {
			loc = X.decompileLocation(B.shopsC.getString(plotName + ".pos2"));
		}
		return loc;
	}

	public static S getPlotState(String plotName) {
		S state = null;
		if (B.shopsC.contains(plotName)) {
			switch (B.shopsC.getString(plotName + ".state")) {
			case "OWNED":
				state = S.OWNED;
				break;
			case "FORSALE":
				state = S.FORSALE;
				break;
			case "NENABLED":
				state = S.NENABLED;
				break;
			}
		}
		return state;
	}

	public static int getPlotPrice(String plotName) {
		int price = 0;
		if (B.shopsC.contains(plotName)) {
			price = B.shopsC.getInt(plotName + ".price");
		}
		return price;
	}

	public static String getLocationString(String plotName) {
		String s = "";
		s = B.shopsC.getString(plotName + ".sign");
		return s;
	}

	public static String getLastKnowName(String plotName) {
		String n = "";
		if (B.shopsC.contains(plotName)) {
			n = B.shopsC.getString(plotName + ".owner.lastname");
		}
		return n;
	}

	public static UUID getLastUUID(String plotName) {
		UUID uuid = null;
		if (B.shopsC.contains(plotName)) {
			uuid = UUID
					.fromString(B.shopsC.getString(plotName + ".owner.uuid"));
		}
		return uuid;
	}

	public static long getTime(String plotName) {
		long time = 0;
		if (B.shopsC.contains(plotName)) {
			time = Long.parseLong(B.shopsC.getString(plotName + ".owner.time")
					.replaceAll("", ""));
		}
		return time;
	}

	public static void resetTime(String plotName) {
		if (B.shopsC.contains(plotName)) {
			B.shopsC.set(
					plotName + ".owner.time",
					Long.toString(Calendar.getInstance().getTimeInMillis()
							+ (24 * 1000 * 60 * 60)));
		}
	}

	public static void setShopSign(String plotName, Location location) {
		if (B.shopsC.contains(plotName)) {
			B.shopsC.set(plotName + ".sign", X.compileLocation(location));
		}
		EBC.signLocations.put(plotName, location);
	}

	public static void buyPlot(String plotName, Player player, int amount) {
		if (B.shopsC.contains(plotName)) {
			if (W.hasEnough(player, amount)) {
				B.shopsC.set(plotName + ".state", S.OWNED.toString());
				B.shopsC.set(plotName + ".owner.uuid", player.getUniqueId()
						.toString());
				B.shopsC.set(plotName + ".owner.lastname", player.getName());
				B.shopsC.set(
						plotName + ".owner.time",
						Long.toString(Calendar.getInstance().getTimeInMillis()
								+ (24 * 1000 * 60 * 60)));
				try {
					B.shopsC.save(B.shops);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				X.sendMessage((String) Messages.CRITICAL_CANNOTAFFORD.value,
						player);
			}
		}

		B.getKeys();
		EBC.loadSigns();
		updateSigns();
	}

	public static void sellPlot(String plotName, Player player) {
		if (B.shopsC.contains(plotName)) {
			B.shopsC.set(plotName + ".state", S.FORSALE.toString());
			B.shopsC.set(plotName + ".owner.uuid", "n/a");
			B.shopsC.set(plotName + ".owner.lastname", "n/a");
			B.shopsC.set(plotName + ".owner.time", "n/a");
		}
		try {
			B.shopsC.save(B.shops);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int sellPrice = U.getPlotPrice(plotName) / 10;
		int newSellPrice = U.getPlotPrice(plotName) - sellPrice;
		W.depositMoney(newSellPrice, player);
		B.getKeys();
		EBC.loadSigns();
		updateSigns();
	}

	public static void setLastName(String plotName, String lastName) {
		if (B.shopsC.contains(plotName)) {
			B.shopsC.set(plotName + ".owner.lastname", lastName);
		}
		try {
			B.shopsC.save(B.shops);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked" })
	public static void updateSigns() {
		for (Entry<String, Location> m : EBC.signLocations.entrySet()) {
			Block block = m.getValue().getBlock();
			if (ECA.isSign(block)) {
				Sign sign = (Sign) block.getState();
				int lc = 0;
				ArrayList<String> newSignLines = new ArrayList<String>();

				if (U.getPlotState(m.getKey()).equals(S.OWNED)) {
					ArrayList<String> signLines = (ArrayList<String>) Messages.SIGNS_OWNED.value;
					String playerName = U.getLastKnowName(m.getKey());
					for (String s : signLines) {
						newSignLines.add(X.formatMessage(s));
					}
					for (String line : newSignLines) {
						if (lc <= 3) {
							String fm = X.formatMessage(line);
							if (playerName == "n/a" || playerName == null) {
								fm = fm.replace("'player'", "-");
							} else {
								fm = fm.replace("'player'", playerName);
							}
							fm = fm.replace("'price'", Integer.toString(U
									.getPlotPrice(m.getKey())));
							if (sign == null) {
								Bukkit.broadcastMessage(ChatColor.AQUA
										+ "null....");
							}
							sign.setLine(lc, fm);
							sign.update();
						}
						lc = lc + 1;
					}
				} else if (U.getPlotState(m.getKey()).equals(S.FORSALE)) {
					ArrayList<String> signLines = (ArrayList<String>) Messages.SIGNS_FORSALE.value;
					String price = "$"
							+ Integer.toString(U.getPlotPrice(m.getKey()));
					for (String s : signLines) {
						newSignLines.add(X.formatMessage(s));
					}
					for (String line : newSignLines) {
						if (lc <= 3) {
							String fm = X.formatMessage(line);
							fm = fm.replace("'price'", price);
							sign.setLine(lc, fm);
							sign.update();
						}
						lc = lc + 1;
					}
				} else if (U.getPlotState(m.getKey()).equals(S.NENABLED)) {
					ArrayList<String> signLines = (ArrayList<String>) Messages.SIGNS_NENABLED.value;
					for (String s : signLines) {
						newSignLines.add(X.formatMessage(s));
					}
					for (String line : newSignLines) {
						if (lc <= 3) {
							String fm = X.formatMessage(line);
							sign.setLine(lc, fm);
							sign.update();
						}
						lc = lc + 1;
					}
				}
			}
		}
	}
}