package co.uk.RandomPanda30.MWarn.Items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.MWarn.B;
import co.uk.RandomPanda30.MWarn.Methods.U;
import co.uk.RandomPanda30.MWarn.Methods.W;
import co.uk.RandomPanda30.MWarn.Methods.Y;

@SuppressWarnings("unchecked")
public class IA {

	public static ItemStack obtainPaperDetails(Player player) {
		ArrayList<String> paperLores = (ArrayList<String>) B.itemsC
				.get("PLAYERINV.PLAYERDETAILS.LORES");
		ArrayList<String> newPaperLores = new ArrayList<String>();
		for (String s : paperLores) {
			if (s.contains("'playername'")) {
				newPaperLores.add(Y.formatMessage(s.replaceAll("'playername'",
						player.getName())));
			}
			if (s.contains("'uuid'")) {
				newPaperLores.add(Y.formatMessage(s.replaceAll("'uuid'", player
						.getUniqueId().toString())));
			}
			if (s.contains("'aw'")) {
				newPaperLores.add(Y.formatMessage(s.replaceAll("'aw'",
						Integer.toString(W.getActiveWarnings(player)))));
			}
			if (s.contains("'ew'")) {
				newPaperLores.add(Y.formatMessage(s.replaceAll("'ew'",
						Integer.toString(W.getExpiredWarnings(player)))));
			}
		}
		ItemStack is = U.createItem((String) B.itemsC.get("PLAYERINV.NAME")
				.toString().replaceAll("'playername'", player.getName()),
				Material.SKULL_ITEM, 1, 0, newPaperLores, false);
		return is;
	}

	public static ItemStack obtainActiveWarnings(Player player) {
		ArrayList<String> awLores = (ArrayList<String>) B.itemsC
				.get("PLAYERINV.ACTIVEWARNINGS.LORES");
		ArrayList<String> newAwLores = new ArrayList<String>();
		for (String s : awLores) {
			s = s.replaceAll("'aw'",
					Integer.toString(W.getActiveWarnings(player)));
			newAwLores.add(Y.formatMessage(s));
		}
		ItemStack is = U.createItem(
				(String) B.itemsC.get("PLAYERINV.ACTIVEWARNINGS.NAME")
						.toString()
						.replaceAll("'playername'", player.getName()),
				Material.ENCHANTED_BOOK, 1, 0, newAwLores, false);
		return is;
	}

	public static ItemStack obtainExpiredWarnings(Player player) {
		ArrayList<String> ewLores = (ArrayList<String>) B.itemsC
				.get("PLAYERINV.EXPIREDWARNINGS.LORES");
		ArrayList<String> newEwLores = new ArrayList<String>();
		for (String s : ewLores) {
			s = s.replaceAll("'ew'",
					Integer.toString(W.getExpiredWarnings(player)));
			newEwLores.add(Y.formatMessage(s));
		}
		ItemStack is = U.createItem(
				(String) B.itemsC.get("PLAYERINV.EXPIREDWARNINGS.NAME")
						.toString()
						.replaceAll("'playername'", player.getName()),
				Material.ENCHANTED_BOOK, 1, 0, newEwLores, false);
		return is;
	}

	public static ItemStack obtainBans(Player player) {
		ArrayList<String> bcLores = (ArrayList<String>) B.itemsC
				.get("PLAYERINV.BANS.LORES");
		ArrayList<String> newBcLores = new ArrayList<String>();
		for (String s : bcLores) {
			s = s.replaceAll("'bc'", Integer.toString(W.getBans(player)));
			newBcLores.add(Y.formatMessage(s));
		}
		ItemStack is = U.createItem((String) B.itemsC
				.get("PLAYERINV.BANS.NAME").toString(), Material.PAPER, 1, 0,
				newBcLores, false);
		return is;
	}

}
