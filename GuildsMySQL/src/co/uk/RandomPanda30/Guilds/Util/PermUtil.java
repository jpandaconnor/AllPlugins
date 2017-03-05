package co.uk.RandomPanda30.Guilds.Util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Guilds.Data;
import co.uk.RandomPanda30.Guilds.Objects.Rank;
import co.uk.RandomPanda30.Guilds.Util.Basics.EUtil;
import co.uk.RandomPanda30.Guilds.Util.Basics.EUtil.Cause;

public class PermUtil {

	public enum Ranks {
		INVITE,
		INVITEGUILD,
		KICK,
		KICKGUILD,
		GMOTD,
		GCHAT,
		RANKSET,
		RANKTITLE,
		RANKCREATE,
		RANKDELETE,
		RANKPERMS,
		PLOTCREATE,
		PLOTDELETE,
		PLOTHOME,
		PLOTRESET,
		WARDECLARE,
		WARSURRENDER,
		PVP;
	}

	public static String getPlayerGuild(Player player) {
		String g = "";
		ResultSet inGuild;
		try {
			inGuild = Data.mysql
					.querySQL("SELECT guild FROM data WHERE uuid = '"
							+ player.getUniqueId().toString() + "'");
			if (inGuild.next()) {
				g = inGuild.getString("guild");
			}
		} catch (ClassNotFoundException | SQLException e) {
			new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
					"Error getting guild in perm util");
		}
		return g;
	}

	public static String getPlayersList(String guild) {
		String g = "";
		ResultSet inGuild;
		try {
			inGuild = Data.mysql.querySQL(
					"SELECT players FROM guilds WHERE guild = '" + guild + "'");
			if (inGuild.next()) {
				g = inGuild.getString("players");
			}
		} catch (ClassNotFoundException | SQLException e) {
			new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
					"Error getting players string from the players list getter, in perm util");
		}
		return g;
	}

	public static String getRanksList(String guild) {
		String g = "";
		ResultSet inGuild;
		try {
			inGuild = Data.mysql.querySQL(
					"SELECT ranks FROM guilds WHERE guild = '" + guild + "'");
			if (inGuild.next()) {
				g = inGuild.getString("ranks");
			}
		} catch (ClassNotFoundException | SQLException e) {
			new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
					"Error getting ranks string from the guilds list getter, in perm util");
		}
		return g;
	}

	public static String getAllyList(String guild) {
		String g = "";
		ResultSet inGuild;
		try {
			inGuild = Data.mysql.querySQL(
					"SELECT allies FROM guilds WHERE guild = '" + guild + "'");
			if (inGuild.next()) {
				g = inGuild.getString("allies");
			}
		} catch (ClassNotFoundException | SQLException e) {
			new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
					"Error getting alies string from the ally list getter, in perm util");
		}
		return g;
	}

	public static Rank getPlayerRank(Player player, String guild) {
		String list = getPlayersList(guild);
		String rlist = getRanksList(guild);

		String[] s1 = list.split("\\$"); // uuid;playername;rank

		String mainPlayer = "";
		String mainRank = "";
		
		for (String s : s1) {
			if ((s.split(";")[0]).equals(player.getUniqueId().toString())) {
				mainPlayer = s;
			}
		}
		
		for(String s : rlist.split("\\$")) {
			if(s.split(":")[0].equals(mainPlayer.split(";")[2])) {
				mainRank = s;
			}
		}
		return new Rank(mainRank);
	}
}