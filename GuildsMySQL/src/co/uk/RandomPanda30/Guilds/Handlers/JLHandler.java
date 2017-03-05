package co.uk.RandomPanda30.Guilds.Handlers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Guilds.Data;
import co.uk.RandomPanda30.Guilds.Util.PermUtil;
import co.uk.RandomPanda30.Guilds.Util.StringUtil;
import co.uk.RandomPanda30.Guilds.Util.Basics.EUtil;
import co.uk.RandomPanda30.Guilds.Util.Basics.EUtil.Cause;

public class JLHandler {

	public static void addPlayerToGuild(String guild, Player player) {
		String players = PermUtil.getPlayersList(guild);
		players += "$" + StringUtil.compilePlayer(
				player.getUniqueId().toString(), player.getName(), "Rookie");

		String s = "UPDATE guilds SET players = '" + players
				+ "' WHERE guild = '" + guild + "'";
		String s2 = "UPDATE data SET guild = '" + guild + "' WHERE uuid = '"
				+ player.getUniqueId().toString() + "'";
		try {
			Data.mysql.updateSQL(s);
			Data.mysql.updateSQL(s2);
		} catch (ClassNotFoundException | SQLException e) {
			new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
					"Error whilst adding a player to a guild in the JLHandler");
		}
	}

	public static void addAllyToGuild(String guild, String ally) {
		String al = PermUtil.getAllyList(guild);
		String al2 = PermUtil.getAllyList(ally);

		al += ";" + ally;
		al2 += ";" + al;

		String s = "UPDATE guilds SET players = '" + al + "' WHERE guild = '"
				+ guild + "'";
		String s2 = "UPDATE guilds SET allies = '" + al2 + "' WHERE guild = '"
				+ ally + "'";
		try {
			Data.mysql.updateSQL(s);
			Data.mysql.updateSQL(s2);
		} catch (ClassNotFoundException | SQLException e) {
			new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
					"Error whilst adding a player to a guild in the JLHandler");
		}
	}

	public static void removePlayerFromGuild(String guild, Player player) {
		String players = PermUtil.getPlayersList(guild);

		players = players
				.replace(
						"$" + player.getUniqueId().toString() + ";"
								+ player.getName() + ";" + PermUtil
										.getPlayerRank(player, guild).getName(),
				"");

		String s = "UPDATE guilds SET players = '" + players
				+ "' WHERE guild = '" + guild + "'";
		String s2 = "UPDATE data SET guild = '-' WHERE uuid = '"
				+ player.getUniqueId().toString() + "'";
		try {
			Data.mysql.updateSQL(s);
			Data.mysql.updateSQL(s2);
		} catch (ClassNotFoundException | SQLException e) {
			new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
					"Error whilst removing a player from a guild in JLHandler");
		}
	}

	public static void removeAllyFromAlly(String guild1, String guild2) {
		String g1 = PermUtil.getAllyList(guild1);
		String g2 = PermUtil.getAllyList(guild2);

		g1.replace(";" + guild2, "");
		g2.replace(";" + guild1, "");

		try {
			Data.mysql.updateSQL("UPDATE guilds SET allies = '" + g1
					+ "' WHERE guild = '" + guild1 + "'");
			Data.mysql.updateSQL("UPDATE guilds SET allies = '" + g2
					+ "' WHERE guild = '" + guild2 + "'");
		} catch (ClassNotFoundException | SQLException e) {
			new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
					"Error whilst removing a player from a guild in JLHandler");
		}
	}

	public static boolean guildExist(String guild) {
		ArrayList<String> guildslist = new ArrayList<>();
		ResultSet guilds;
		try {
			guilds = Data.mysql.querySQL("SELECT guilds FROM guilds");
			if (guilds.next()) {
				guildslist.add(guilds.getString("guild"));
			}

			if (!guildslist.contains(guild)) {
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
					"Could not get all the guilds from the guild list for some reason");
		}
		return true;
	}

	public static boolean areAllies(String guild1, String guild2) {
		boolean bool = true;
		ResultSet allies;
		try {
			allies = Data.mysql.querySQL(
					"SELECT allies FROM guilds WHERE guild = '" + guild1 + "'");
			String al = allies.getString("allies");
			String[] alList = al.split(";");
			for (String check : alList) {
				if (check != guild2) {
					bool = false;
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
					"Could nopt check if the guilds are allies for some reason");
		}
		return bool;
	}
}