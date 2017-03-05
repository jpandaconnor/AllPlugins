package co.uk.RandomPanda30.Guilds.Util;

import java.util.ArrayList;
import java.util.UUID;

import co.uk.RandomPanda30.Guilds.Objects.Rank;

public class StringUtil {

	/*
	 * This method will compile the ranks into a string list and stuffs
	 * 
	 * String, string goes in lists, list is then compiled into a big string
	 */

	public static String compileRank(String rankname, boolean invite,
			boolean ginvite, boolean gmotd, boolean kick, boolean gkick,
			boolean gchat, boolean rankset, boolean ranktitle,
			boolean createrank, boolean deleterank, boolean permsrank,
			boolean plotcreate, boolean plotdelete, boolean plotreset,
			boolean plothome, boolean wardeclare, boolean warsurrender,
			boolean pvp, String title) {
		return rankname + ":Invite;" + invite + "!InviteGuild;" + ginvite
				+ "!Kick;" + kick + "!KickGuild;" + gkick + "!Gmotd;" + gmotd
				+ "!GChat;" + gchat + "!RankSet;" + rankset + "!RankTitle;"
				+ ranktitle + "!RankCreate;" + createrank + "!RankDelete;"
				+ deleterank + "!RankPerms;" + permsrank + "!PlotCreate;"
				+ plotcreate + "!PlotDelete;" + plotdelete + "!PlotHome;"
				+ plothome + "!PlotReset;" + plotreset + "!WarDeclare;"
				+ wardeclare + "!WarSurrender;" + warsurrender + "!Title;["
				+ title + "]" + "!PVP;" + pvp;
	}

	public static String compileRankForReplace(String rankname, boolean invite,
			boolean ginvite, boolean gmotd, boolean kick, boolean gkick,
			boolean gchat, boolean rankset, boolean ranktitle,
			boolean createrank, boolean deleterank, boolean permsrank,
			boolean plotcreate, boolean plotdelete, boolean plotreset,
			boolean plothome, boolean wardeclare, boolean warsurrender,
			boolean pvp, String title) {
		return rankname + ":Invite;" + invite + "!InviteGuild;" + ginvite
				+ "!Kick;" + kick + "!KickGuild;" + gkick + "!Gmotd;" + gmotd
				+ "!GChat;" + gchat + "!RankSet;" + rankset + "!RankTitle;"
				+ ranktitle + "!RankCreate;" + createrank + "!RankDelete;"
				+ deleterank + "!RankPerms;" + permsrank + "!PlotCreate;"
				+ plotcreate + "!PlotDelete;" + plotdelete + "!PlotHome;"
				+ plothome + "!PlotReset;" + plotreset + "!WarDeclare;"
				+ wardeclare + "!WarSurrender;" + warsurrender + "!Title;"
				+ title + "!PVP;" + pvp;
	}

	public static Rank decompileRank(String rankInfo) {
		return new Rank(rankInfo);
	}

	public static String compilePlayer(UUID uuid, String playername,
			String rank) {
		return uuid.toString() + ";" + playername + ";" + rank;
	}

	public static String compilePlayer(String uuid, String playername,
			String rank) {
		return uuid + ";" + playername + ";" + rank;
	}

	public static String compileRankToList(ArrayList<String> strings) {
		String s = "";
		for (String string : strings) {
			s += string + "$";
		}
		return s;
	}

	public static String compilePlayerToList(ArrayList<String> strings) {
		String s = "";
		for (String string : strings) {
			if (!string.equals(strings.get(strings.size() - 1))) {
				s += string + "$";
			} else {
				s += string;
			}
		}
		return s;
	}
}