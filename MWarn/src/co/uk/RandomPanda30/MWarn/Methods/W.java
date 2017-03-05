package co.uk.RandomPanda30.MWarn.Methods;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.MWarn.B;
import co.uk.RandomPanda30.MWarn.M;
import co.uk.RandomPanda30.MWarn.S;

public class W {

	public static ArrayList<ItemStack> getActiveHeads(Player player) {
		ArrayList<ItemStack> heads = new ArrayList<ItemStack>();
		B.getKeys();
		Set<String> numeralKeys = null;
		ArrayList<Integer> no = new ArrayList<Integer>();
		if (B.keys.contains(player.getUniqueId().toString())) {
			if (B.dataC.get(player.getUniqueId().toString() + ".warnings") != null) {
				numeralKeys = B.dataC.getConfigurationSection(
						player.getUniqueId().toString() + ".warnings").getKeys(
						false);
				for (String s : numeralKeys) {
					no.add(Integer.parseInt(s));
				}
			}
		}

		for (int i : no) {
			if (WB.getState(
					player.getUniqueId().toString() + ".warnings." + i
							+ ".state").equals(S.ACTIVE)) {
				ItemStack is = new ItemStack(Material.SKULL_ITEM, 1);
				ItemMeta im = is.getItemMeta();
				im.setDisplayName(Y.formatMessage((String) B.itemsC
						.get("AWARNINGSINV.NAME")
						.toString()
						.replaceAll(
								"'no'",
								B.dataC.getString(player.getUniqueId()
										.toString()
										+ ".warnings."
										+ i
										+ ".admin.lastname"))));
				ArrayList<String> lores = new ArrayList<String>();
				lores.add(Y.formatMessage((String) B.itemsC
						.get("AWARNINGSINV.REASON")
						.toString()
						.replaceAll(
								"'reason'",
								B.dataC.getString(player.getUniqueId()
										.toString()
										+ ".warnings."
										+ i
										+ ".reason"))));
				lores.add(Y.formatMessage((String) B.itemsC
						.get("AWARNINGSINV.DATE")
						.toString()
						.replaceAll(
								"'date'",
								B.dataC.getString(player.getUniqueId()
										.toString()
										+ ".warnings."
										+ i
										+ ".date"))));
				lores.add(Y.formatMessage((String) B.itemsC
						.get("AWARNINGSINV.KEY")
						.toString()
						.replaceAll(
								"'key'",
								B.dataC.getString(player.getUniqueId()
										+ ".warnings." + i + ".key"))));
				im.setLore(lores);
				is.setItemMeta(im);
				if (!heads.contains(is)) {
					heads.add(is);
				}
			}
		}
		return heads;
	}

	public static ArrayList<ItemStack> getExpiredHeads(Player player) {
		ArrayList<ItemStack> heads = new ArrayList<ItemStack>();
		B.getKeys();
		Set<String> numeralKeys = null;
		ArrayList<Integer> no = new ArrayList<Integer>();
		if (B.keys.contains(player.getUniqueId().toString())) {
			if (B.dataC.get(player.getUniqueId().toString() + ".warnings") != null) {
				numeralKeys = B.dataC.getConfigurationSection(
						player.getUniqueId().toString() + ".warnings").getKeys(
						false);
				for (String s : numeralKeys) {
					no.add(Integer.parseInt(s));
				}
			}
		}

		for (int i : no) {
			if (WB.getState(
					player.getUniqueId().toString() + ".warnings." + i
							+ ".state").equals(S.EXPIRED)) {
				ItemStack is = new ItemStack(Material.SKULL_ITEM, 1);
				ItemMeta im = is.getItemMeta();
				im.setDisplayName(Y.formatMessage((String) B.itemsC
						.get("AWARNINGSINV.NAME")
						.toString()
						.replaceAll(
								"'no'",
								B.dataC.getString(player.getUniqueId()
										.toString()
										+ ".warnings."
										+ i
										+ ".admin.lastname"))));
				ArrayList<String> lores = new ArrayList<String>();
				lores.add(Y.formatMessage((String) B.itemsC
						.get("AWARNINGSINV.REASON")
						.toString()
						.replaceAll(
								"'reason'",
								B.dataC.getString(player.getUniqueId()
										.toString()
										+ ".warnings."
										+ i
										+ ".reason"))));
				lores.add(Y.formatMessage((String) B.itemsC
						.get("AWARNINGSINV.DATE")
						.toString()
						.replaceAll(
								"'date'",
								B.dataC.getString(player.getUniqueId()
										.toString()
										+ ".warnings."
										+ i
										+ ".date"))));
				im.setLore(lores);
				is.setItemMeta(im);
				if (!heads.contains(is)) {
					heads.add(is);
				}
			}
		}
		return heads;
	}

	public static ArrayList<ItemStack> getBansHeads(Player player) {
		ArrayList<ItemStack> heads = new ArrayList<ItemStack>();
		B.getKeys();
		Set<String> numeralKeys = null;
		ArrayList<Integer> no = new ArrayList<Integer>();
		if (B.keys.contains(player.getUniqueId().toString())) {
			if (B.dataC.get(player.getUniqueId().toString() + ".bans") != null) {
				numeralKeys = B.dataC.getConfigurationSection(
						player.getUniqueId().toString() + ".bans").getKeys(
						false);
				for (String s : numeralKeys) {
					no.add(Integer.parseInt(s));
				}
			}
		}

		for (int i : no) {
			ItemStack is = new ItemStack(Material.SKULL_ITEM, 1);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName(Y.formatMessage((String) B.itemsC
					.get("AWARNINGSINV.NAME")
					.toString()
					.replaceAll(
							"'no'",
							B.dataC.getString(player.getUniqueId().toString()
									+ ".bans." + i + ".admin.lastname"))));
			ArrayList<String> lores = new ArrayList<String>();
			lores.add(Y.formatMessage((String) B.itemsC
					.get("AWARNINGSINV.REASON")
					.toString()
					.replaceAll(
							"'reason'",
							B.dataC.getString(player.getUniqueId().toString()
									+ ".bans." + i + ".reason"))));
			lores.add(Y.formatMessage((String) B.itemsC
					.get("AWARNINGSINV.DATE")
					.toString()
					.replaceAll(
							"'date'",
							B.dataC.getString(player.getUniqueId().toString()
									+ ".bans." + i + ".date"))));
			lores.add(Y.formatMessage((String) B.itemsC
					.get("AWARNINGSINV.KEY")
					.toString()
					.replaceAll(
							"'key'",
							B.dataC.getString(player.getUniqueId() + ".bans."
									+ i + ".key"))));
			im.setLore(lores);
			is.setItemMeta(im);
			if (!heads.contains(is)) {
				heads.add(is);
			}
		}
		return heads;
	}

	public static void addWarning(Player target, Player player, String reason) {
		B.getKeys();
		int max = 0;

		Set<String> numeralKeys = null;
		ArrayList<Integer> no = new ArrayList<Integer>();

		if (B.keys.contains(target.getUniqueId().toString())) {
			if (B.dataC.get(target.getUniqueId().toString() + ".warnings") != null) {
				numeralKeys = B.dataC.getConfigurationSection(
						target.getUniqueId().toString() + ".warnings").getKeys(
						false);
			}
		} else {
			addDefaults(target);
		}

		if (B.keys.contains(target.getUniqueId().toString())) {
			if (B.dataC.get(target.getUniqueId().toString() + ".warnings") != null) {
				for (String s : numeralKeys) {
					no.add(Integer.parseInt(s));
				}
				max = Collections.max(no);
			} else {
				max = 0;
			}
		}

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();

		max = max + 1;
		B.dataC.set(target.getUniqueId().toString() + ".warnings." + max
				+ ".date", dateFormat.format(date));
		try {
			B.dataC.set(target.getUniqueId().toString() + ".warnings." + max
					+ ".key", Q.generateRandomString(10, M.ALPHANUMERIC));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		B.dataC.set(target.getUniqueId().toString() + ".warnings." + max
				+ ".reason", reason);
		B.dataC.set(
				target.getUniqueId().toString() + ".warnings." + max + ".time",
				Long.toString(Calendar.getInstance().getTimeInMillis()
						+ WB.getWarningExpirery() * (24 * 1000 * 60 * 60)));
		B.dataC.set(target.getUniqueId().toString() + ".warnings." + max
				+ ".admin.uuid", player.getUniqueId().toString());
		B.dataC.set(target.getUniqueId().toString() + ".warnings." + max
				+ ".admin.lastname", player.getName());
		B.dataC.set(target.getUniqueId().toString() + ".warnings." + max
				+ ".state", S.ACTIVE.toString());

		try {
			B.dataC.save(B.data);
		} catch (IOException e) {
			e.printStackTrace();
		}

		checkAmountOfWarnings(player, target);
	}

	public static void unbanPlayer(Player player, String uuid) {
		B.getKeys();
		Set<String> numeralKeys = null;
		ArrayList<Integer> no = new ArrayList<Integer>();
		if (B.keys.contains(uuid)) {
			if (B.dataC.get(uuid + ".bans") != null) {
				numeralKeys = B.dataC.getConfigurationSection(uuid + ".bans")
						.getKeys(false);
				for (String s : numeralKeys) {
					no.add(Integer.parseInt(s));
				}
			}
		}

		for (int i : no) {
			if (WB.getState(uuid + ".bans." + i + ".state").equals(S.ACTIVE)) {
				B.dataC.set(uuid + ".bans." + i + ".state",
						S.EXPIRED.toString());
			}
		}

		B.bansC.set(uuid, null);

		try {
			B.dataC.save(B.data);
			B.bansC.save(B.bans);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void removeWarning(Player player, int warning) {
		if (B.dataC.get(player.getUniqueId().toString() + ".warnings."
				+ Integer.toString(warning)) != null) {
			B.dataC.set(player.getUniqueId().toString() + ".warnings."
					+ Integer.toString(warning) + ".state",
					S.REMOVED.toString());
		}
		try {
			B.dataC.save(B.data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void removeAllWarnings(Player player) {
		B.dataC.set(player.getUniqueId().toString() + ".warnings", null);
		try {
			B.dataC.save(B.data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void removeAllActiveWarnings(Player player) {
		B.getKeys();
		Set<String> numeralKeys = null;
		ArrayList<Integer> no = new ArrayList<Integer>();
		if (B.keys.contains(player.getUniqueId().toString())) {
			if (B.dataC.get(player.getUniqueId().toString() + ".warnings") != null) {
				numeralKeys = B.dataC.getConfigurationSection(
						player.getUniqueId().toString() + ".warnings").getKeys(
						false);
				for (String s : numeralKeys) {
					no.add(Integer.parseInt(s));
				}
			}
		}

		for (int i : no) {
			if (WB.getState(
					player.getUniqueId().toString() + ".warnings." + i
							+ ".state").equals(S.ACTIVE)) {
				B.dataC.set(player.getUniqueId().toString() + ".warnings." + i
						+ ".state", S.EXPIRED.toString());
			}
		}
	}

	public static void checkWarnings(Player player) {
		B.getKeys();
		int max = 0;
		Set<String> numeralKeys = null;
		ArrayList<Integer> no = new ArrayList<Integer>();

		if (B.keys.contains(player.getUniqueId().toString())) {
			if (B.dataC.get(player.getUniqueId().toString() + ".warnings") != null) {
				numeralKeys = B.dataC.getConfigurationSection(
						player.getUniqueId().toString() + ".warnings").getKeys(
						false);
				for (String s : numeralKeys) {
					no.add(Integer.parseInt(s));
				}
				max = Collections.max(no);
				if (max == 0) {
					return;
				}
			}
		} else {
			return;
		}
		int warnings = 0;
		for (int i : no) {
			if (WB.getState(
					player.getUniqueId().toString() + ".warnings." + i
							+ ".state").equals(S.ACTIVE)) {
				String time = B.dataC.getString(player.getUniqueId().toString()
						+ ".warnings." + i + ".time");
				if (Long.parseLong(time) < Calendar.getInstance()
						.getTimeInMillis()) {
					B.dataC.set(player.getUniqueId().toString() + ".warnings."
							+ i + ".state", S.EXPIRED.toString());
					warnings = warnings + 1;
				}
			}
		}
		Y.sendMessage((String) B.messagesC.get("MWARN.WARNINGSEXPIRED")
				.toString()
				.replaceAll("'warnings'", Integer.toString(warnings)), player);
		try {
			B.dataC.save(B.data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void checkBans(Player player) {
		B.getKeys();
		Set<String> numeralKeys = null;
		int max = 0;
		ArrayList<Integer> no = new ArrayList<Integer>();

		if (B.keys.contains(player.getUniqueId().toString())) {
			if (B.dataC.get(player.getUniqueId().toString() + ".bans") != null) {
				numeralKeys = B.dataC.getConfigurationSection(
						player.getUniqueId().toString() + ".bans").getKeys(
						false);
				for (String s : numeralKeys) {
					no.add(Integer.parseInt(s));
				}
				max = Collections.max(no);
				if (max == 0) {
					return;
				}
			}
		} else {
			return;
		}

		for (int i : no) {
			if (WB.getState(
					player.getUniqueId().toString() + ".bans." + i + ".state")
					.equals(S.ACTIVE)) {
				String time = B.dataC.getString(player.getUniqueId().toString()
						+ ".bans." + i + ".time");
				if (Long.parseLong(time) < Calendar.getInstance()
						.getTimeInMillis()) {
					B.dataC.set(player.getUniqueId().toString() + ".bans." + i
							+ ".state", S.EXPIRED.toString());
				}
			}
		}

		try {
			B.dataC.save(B.data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean isBanned(Player player) {
		boolean banned = false;
		Set<String> numeralKeys = null;
		ArrayList<Integer> no = new ArrayList<Integer>();

		if (B.dataC.contains(player.getUniqueId().toString())) {
			if (B.dataC.get(player.getUniqueId().toString() + ".bans") != null) {
				numeralKeys = B.dataC.getConfigurationSection(
						player.getUniqueId().toString() + ".bans").getKeys(
						false);
				for (String s : numeralKeys) {
					no.add(Integer.parseInt(s));
				}
			}
		}

		for (int i : no) {
			String time = B.dataC.getString(player.getUniqueId().toString()
					+ ".bans." + i + ".time");
			if (Calendar.getInstance().getTimeInMillis() > Long.parseLong(time)) {
				B.dataC.set(player.getUniqueId().toString() + ".bans." + i
						+ ".state", S.EXPIRED.toString());
				return false;
			}

			if (WB.getState(
					player.getUniqueId().toString() + ".bans." + i + ".state")
					.equals(S.ACTIVE)) {
				banned = true;
			}
		}
		return banned;
	}

	public static int getActiveWarnings(Player player) {
		B.getKeys();
		Set<String> numeralKeys = null;
		ArrayList<Integer> no = new ArrayList<Integer>();

		if (B.keys.contains(player.getUniqueId().toString())) {
			if (B.dataC.get(player.getUniqueId().toString() + ".warnings") != null) {
				numeralKeys = B.dataC.getConfigurationSection(
						player.getUniqueId().toString() + ".warnings").getKeys(
						false);
				for (String s : numeralKeys) {
					no.add(Integer.parseInt(s));
				}
			}
		}

		int warnings = 0;
		for (int i : no) {
			if (WB.getState(
					player.getUniqueId().toString() + ".warnings." + i
							+ ".state").equals(S.ACTIVE)) {
				warnings = warnings + 1;
			}
		}
		return warnings;
	}

	public static int getExpiredWarnings(Player player) {
		B.getKeys();
		Set<String> numeralKeys = null;
		ArrayList<Integer> no = new ArrayList<Integer>();

		if (B.keys.contains(player.getUniqueId().toString())) {
			if (B.dataC.get(player.getUniqueId().toString() + ".warnings") != null) {
				numeralKeys = B.dataC.getConfigurationSection(
						player.getUniqueId().toString() + ".warnings").getKeys(
						false);
				for (String s : numeralKeys) {
					no.add(Integer.parseInt(s));
				}
			}
		}

		int warnings = 0;
		for (int i : no) {
			if (WB.getState(
					player.getUniqueId().toString() + ".warnings." + i
							+ ".state").equals(S.EXPIRED)) {
				warnings = warnings + 1;
			}
		}
		return warnings;
	}

	public static int getBans(Player player) {
		B.getKeys();
		Set<String> numeralKeys = null;
		ArrayList<Integer> no = new ArrayList<Integer>();

		if (B.keys.contains(player.getUniqueId().toString())) {
			if (B.dataC.get(player.getUniqueId().toString() + ".bans") != null) {
				numeralKeys = B.dataC.getConfigurationSection(
						player.getUniqueId().toString() + ".bans").getKeys(
						false);
				for (String s : numeralKeys) {
					no.add(Integer.parseInt(s));
				}
			}
		}

		int bans = 0;
		for (int i : no) {
			if (WB.getState(
					player.getUniqueId().toString() + ".bans." + i + ".state")
					.equals(S.EXPIRED)) {
				bans = bans + 1;
			}
		}
		return bans;
	}

	public static void checkAmountOfWarnings(Player player, Player target) {
		B.getKeys();
		Set<String> numeralKeys = null;
		ArrayList<Integer> no = new ArrayList<Integer>();
		if (B.keys.contains(target.getUniqueId().toString())) {
			if (B.dataC.get(target.getUniqueId().toString() + ".warnings") != null) {
				numeralKeys = B.dataC.getConfigurationSection(
						target.getUniqueId().toString() + ".warnings").getKeys(
						false);
				for (String s : numeralKeys) {
					no.add(Integer.parseInt(s));
				}
			}
		}

		int warnings = 0;
		for (int i : no) {
			if (WB.getState(
					target.getUniqueId().toString() + ".warnings." + i
							+ ".state").equals(S.ACTIVE)) {
				warnings = warnings + 1;
			}
		}

		if (warnings >= WB.getWarningThreshold()) {
			W.banPlayer(player, target, B.configC.get("CONSOLE.BANNED")
					.toString());
		}
	}

	public static void banPlayer(Player player, Player target, String reason) {
		B.getKeys();
		int max = 0;
		Set<String> numeralKeys = null;
		ArrayList<Integer> no = new ArrayList<Integer>();
		String finalReason = (String) B.messagesC.get("BAN.REASON")
				+ (reason == null ? (String) B.messagesC.get("BAN.NOREASON")
						+ "%N." : reason + "%N.");
		int days = WB.getBanLength();

		String cleanReason = reason;

		long length = 0;
		String finalTime = "";

		if (days != 0) {
			finalTime += days + (String) B.messagesC.get("BAN.DAYS");
		}

		finalReason += "&u" + (String) B.messagesC.get("BAN.LENGTH") + "%A"
				+ finalTime + "%N.";
		length += Calendar.getInstance().getTimeInMillis()
				+ (days * 1000 * 60 * 60 * 24);

		if (B.keys.contains(player.getUniqueId().toString())) {
			if (B.dataC.get(player.getUniqueId().toString() + ".bans") != null) {
				numeralKeys = B.dataC.getConfigurationSection(
						player.getUniqueId().toString() + ".bans").getKeys(
						false);
			}
		} else {
			addBanDefaults(target);
		}

		if (B.keys.contains(target.getUniqueId().toString())) {
			if (B.dataC.get(player.getUniqueId().toString() + ".bans") != null) {
				for (String s : numeralKeys) {
					no.add(Integer.parseInt(s));
				}
				max = Collections.max(no);
			} else {
				max = 0;
			}
		}

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();

		max = max + 1;
		B.dataC.set(target.getUniqueId().toString() + ".bans." + max + ".date",
				dateFormat.format(date));
		B.bansC.set(target.getUniqueId().toString() + ".date",
				dateFormat.format(date));
		try {
			B.dataC.set(target.getUniqueId().toString() + ".bans." + max
					+ ".key", Q.generateRandomString(10, M.ALPHANUMERIC));
		} catch (Exception e) {
			e.printStackTrace();
		}
		B.dataC.set(target.getUniqueId().toString() + ".bans." + max
				+ ".reason", cleanReason);
		B.dataC.set(target.getUniqueId().toString() + ".bans." + max + ".time",
				Long.toString(length));
		B.dataC.set(target.getUniqueId().toString() + ".bans." + max
				+ ".admin.uuid", (player == null ? "CONSOLE" : player
				.getUniqueId().toString()));
		B.dataC.set(target.getUniqueId().toString() + ".bans." + max
				+ ".admin.lastname",
				(player == null ? "CONSOLE" : player.getName()));
		B.dataC.set(
				target.getUniqueId().toString() + ".bans." + max + ".state",
				S.ACTIVE.toString());

		B.bansC.set(target.getUniqueId().toString() + ".reason", cleanReason);
		B.bansC.set(target.getUniqueId().toString() + ".time",
				Long.toString(length));
		B.bansC.set(target.getUniqueId().toString() + ".lastname",
				target.getName());
		B.bansC.set(target.getUniqueId().toString() + ".admin.uuid",
				(player == null ? "CONSOLE" : player.getUniqueId().toString()));
		B.bansC.set(target.getUniqueId().toString() + ".admin.lastname",
				(player == null ? "CONSOLE" : player.getName()));
		B.bansC.set(target.getUniqueId().toString() + ".state",
				S.ACTIVE.toString());

		try {
			B.dataC.save(B.data);
			B.bansC.save(B.bans);
		} catch (IOException e) {
			e.printStackTrace();
		}

		target.kickPlayer(Y.formatMessage(finalReason));
	}

	private static void addDefaults(Player player) {
		B.dataC.set(player.getUniqueId().toString() + ".warnings", "");
		try {
			B.dataC.save(B.data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void addBanDefaults(Player player) {
		B.dataC.set(player.getUniqueId().toString() + ".bans", "");
		try {
			B.dataC.save(B.data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}