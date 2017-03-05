package co.uk.RandomPanda30.Guilds.Commands;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Guilds.Data;
import co.uk.RandomPanda30.Guilds.Guilds;
import co.uk.RandomPanda30.Guilds.Config.Config;
import co.uk.RandomPanda30.Guilds.Config.Config.ConfigValues;
import co.uk.RandomPanda30.Guilds.Config.Messages;
import co.uk.RandomPanda30.Guilds.Config.Messages.MessagesValues;
import co.uk.RandomPanda30.Guilds.Handlers.General;
import co.uk.RandomPanda30.Guilds.Handlers.JLHandler;
import co.uk.RandomPanda30.Guilds.Objects.GInvite;
import co.uk.RandomPanda30.Guilds.Objects.Invite;
import co.uk.RandomPanda30.Guilds.Objects.Rank;
import co.uk.RandomPanda30.Guilds.Plots.PlotHandler;
import co.uk.RandomPanda30.Guilds.Util.PermUtil;
import co.uk.RandomPanda30.Guilds.Util.StringUtil;
import co.uk.RandomPanda30.Guilds.Util.Basics.EUtil;
import co.uk.RandomPanda30.Guilds.Util.Basics.EUtil.Cause;
import co.uk.RandomPanda30.Guilds.Util.Basics.MUtil;

@SuppressWarnings("unchecked")
public class CommandGuild implements CommandExecutor {

	// http://whileimautomaton.net/2011/05/25002914

	/**
	 * 
	 * 
	 * Make guild chat command /g
	 */

	public Guilds plugin;

	public CommandGuild (Guilds plugin) {
		this.plugin = plugin;
		plugin.getCommand("guild").setExecutor(this);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (!(sender instanceof Player)) {
			MUtil.sendMessage((String) MessagesValues.COMMAND_PLAYERONLY.value,
					null);
			return true;
		}

		Player player = (Player) sender;

		if (args.length == 0) {
			for (String string : (List<String>) MessagesValues.COMMANDS_LIST.value) {
				MUtil.sendMessage(string, player);
			}
			return true;
		}

		General.checkSystem(player);

		if (args[0].equalsIgnoreCase("list")) {
			List<String> names = new ArrayList<>();
			ResultSet nameset;
			try {
				nameset = Data.mysql.querySQL("SELECT guild FROM "
						+ (String) ConfigValues.MYSQL_DBNAME.value + ".guilds");
				while (nameset.next()) {
					names.add(nameset.getString("guild"));
				}
			} catch (ClassNotFoundException | SQLException e) {
				new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
						"Error whilst getting names through list command");
			}

			MUtil.sendMessage("%TAG %AGuild list", player);
			for (String s : names) {
				MUtil.sendMessage(s, player);
			}
			return true;
		}

		if (args[0].equalsIgnoreCase("leave")) {
			String guild = "";

			ResultSet pguild;
			try {
				pguild = Data.mysql
						.querySQL("SELECT guild FROM data WHERE uuid = '"
								+ player.getUniqueId().toString() + "'");
				if (pguild.next()) {
					guild = pguild.getString("guild");
				}
			} catch (ClassNotFoundException | SQLException e) {
				new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
						"Error whilst getting player's guild in the invite command method thingy");
			}

			if (guild.equals("NULL")) {
				MUtil.sendMessage(
						(String) MessagesValues.GENERAL_NOTINGUILD.value,
						player);
				return true;
			}

			Rank rank = PermUtil.getPlayerRank(player, guild);

			if (rank.getName().equals("Leader")) {
				MUtil.sendMessage((String) MessagesValues.NOGUILDPERM.value
						.toString().replace("%perm", "*Not Leader*"), player);
				return true;
			}

			JLHandler.removePlayerFromGuild(guild, player);
			MUtil.sendMessage((String) MessagesValues.COMMAND_LEAVE_LEFT.value,
					player);
			return true;
		}

		if (args[0].equalsIgnoreCase("create")) {
			if (args.length != 3) {
				MUtil.sendMessage(
						(String) MessagesValues.COMMAND_CREATE_SYNTAX.value,
						player);
				return true;
			}

			if (MUtil.format(args[2]).length() != 4) {
				MUtil.sendMessage((String) MessagesValues.GENERAL_TAG.value,
						player);
				return true;
			}

			if (args[1].contains(" ")) {
				MUtil.sendMessage(
						(String) MessagesValues.COMMAND_CREATE_NOSPACES.value,
						player);
				return true;
			}

			if (!player.hasPermission("guild.create")) {
				MUtil.sendMessage((String) MessagesValues.NOPERM.value, player);
				return true;
			}

			List<String> names = new ArrayList<>();
			ResultSet nameset;
			try {
				nameset = Data.mysql.querySQL("SELECT guild FROM "
						+ (String) ConfigValues.MYSQL_DBNAME.value + ".guilds");
				while (nameset.next()) {
					names.add(nameset.getString("guild"));
				}
			} catch (ClassNotFoundException | SQLException e) {
				new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
						"Error whilst getting names through create command");
			}

			if (names.contains(args[1])) {
				MUtil.sendMessage(
						(String) MessagesValues.COMMAND_CREATE_NAMEEXISTS.value,
						player);
				return true;
			}

			List<String> tags = new ArrayList<>();
			ResultSet set;
			try {
				set = Data.mysql.querySQL("SELECT tag FROM "
						+ (String) ConfigValues.MYSQL_DBNAME.value + ".guilds");
				while (set.next()) {
					tags.add(set.getString("tag"));
				}
			} catch (ClassNotFoundException | SQLException e) {
				new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
						"Error whilst getting tags through create command");
			}

			if (tags.contains(args[2])) {
				MUtil.sendMessage(
						(String) MessagesValues.COMMAND_CREATE_TAGEXISTS.value,
						player);
				return true;
			}

			ResultSet inGuild;
			try {
				inGuild = Data.mysql
						.querySQL("SELECT guild FROM data WHERE uuid = '"
								+ player.getUniqueId().toString() + "'");
				if (inGuild.next()) {
					if (!inGuild.getString("guild").equals("NULL")) {
						MUtil.sendMessage(
								(String) MessagesValues.GENERAL_ALREADYINAGUILD.value,
								player);
						return true;
					}
				}
			} catch (ClassNotFoundException | SQLException e) {
				new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
						"Error whilst checking if a is already in a guild");
			}

			try {
				String sql = "INSERT INTO guilds (guild, tag, leader_uuid, leader_name, ranks, players)"
						+ "VALUES (?, ?, ?, ?, ?, ?)";
				PreparedStatement ps = Data.mysql.getConnection()
						.prepareStatement(sql);
				ps.setString(1, args[1]);
				ps.setString(2, args[2]);
				ps.setString(3, player.getUniqueId().toString());
				ps.setString(4, player.getName());
				ps.setString(5,
						(String) Config.ConfigValues.RANK_LEADER_DEFAULT.value
								+ "$"
								+ (String) ConfigValues.RANK_ROOKIE_DEFAULT.value);
				ps.setString(6, StringUtil.compilePlayer(player.getUniqueId(),
						player.getName(), "Leader"));
				ps.executeUpdate();

				Data.mysql.updateSQL("UPDATE data SET guild = '" + args[1]
						+ "' WHERE uuid ='" + player.getUniqueId().toString()
						+ "'");
				MUtil.sendMessage(
						(String) MessagesValues.COMMAND_CREATE_DONE.value,
						player);
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
						"Error whilst inserting default stuff into the guilds and data table");
			}
			return true;
		}

		if (args[0].equalsIgnoreCase("disband")) {
			String guild = "";

			ResultSet pguild;
			try {
				pguild = Data.mysql
						.querySQL("SELECT guild FROM data WHERE uuid = '"
								+ player.getUniqueId().toString() + "'");
				if (pguild.next()) {
					guild = pguild.getString("guild");
				}
			} catch (ClassNotFoundException | SQLException e) {
				new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
						"Error whilst getting player's guild in the invite command method thingy");
			}

			if (guild.equals("NULL")) {
				MUtil.sendMessage(
						(String) MessagesValues.GENERAL_NOTINGUILD.value,
						player);
				return true;
			}

			Rank rank = PermUtil.getPlayerRank(player, guild);

			if (!rank.getName().equals("Leader")) {
				MUtil.sendMessage((String) MessagesValues.NOGUILDPERM.value
						.toString().replace("%perm", "*Not Leader*"), player);
				return true;
			}

			String prePlayers = PermUtil.getPlayersList(guild);
			String[] prePlayersArray = prePlayers.split("\\$");

			for (String prePlayersUUID : prePlayersArray) {
				String[] ppuuid2 = prePlayersUUID.split(";");
				try {
					Data.mysql.updateSQL(
							"UPDATE data SET guild = 'NULL' WHERE uuid = '"
									+ ppuuid2[0] + "'");
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}

			try {
				Data.mysql.updateSQL(
						"DELETE FROM guilds WHERE guild = '" + guild + "'");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

			MUtil.sendMessage((String) MessagesValues.COMMAND_DISBANEDED.value,
					player);
			return true;
		}

		if (args[0].equalsIgnoreCase("invite")) {
			String guild = "";

			ResultSet pguild;
			try {
				pguild = Data.mysql
						.querySQL("SELECT guild FROM data WHERE uuid = '"
								+ player.getUniqueId().toString() + "'");
				if (pguild.next()) {
					guild = pguild.getString("guild");
				}
			} catch (ClassNotFoundException | SQLException e) {
				new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
						"Error whilst getting player's guild in the invite command method thingy");
			}

			if (guild.equals("NULL")) {
				MUtil.sendMessage(
						(String) MessagesValues.GENERAL_NOTINGUILD.value,
						player);
				return true;
			}

			Rank rank = PermUtil.getPlayerRank(player, guild);

			if (!rank.isInvite()) {
				MUtil.sendMessage((String) MessagesValues.NOGUILDPERM.value
						.toString().replace("%perm", "Invite"), player);
				return true;
			}

			if (args.length != 2) {
				MUtil.sendMessage(
						(String) Messages.MessagesValues.COMMAND_INVITE_SYNTAX.value,
						player);
				return true;
			}

			if (args[1].equals(player.getName())) {
				MUtil.sendMessage(
						(String) MessagesValues.COMMAND_INVITE_WHYINVITESELF.value,
						player);
				return true;
			}

			if (Bukkit.getPlayer(args[1]) == null) {
				MUtil.sendMessage(
						(String) Messages.MessagesValues.GENERAL_PLAYERNOTFOUND.value,
						player);
				return true;
			}

			Player invitep = Bukkit.getServer().getPlayer(args[1]);
			UUID inviteUUID = invitep.getUniqueId();

			ResultSet fGuild;
			try {
				fGuild = Data.mysql
						.querySQL("SELECT guild FROM data WHERE uuid = '"
								+ inviteUUID.toString() + "'");
				if (fGuild.next()) {
					if (!fGuild.getString("guild").equals("NULL")) {
						MUtil.sendMessage(
								(String) MessagesValues.GENERAL_PLAYERALREADYINGUILD.value,
								player);
						return true;
					}
				}
			} catch (ClassNotFoundException | SQLException e) {
				new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
						"Error whilst trying to find out if another player is in a guild in the invite method");
			}

			if (Data.invites.containsKey(invitep)) {
				MUtil.sendMessage(
						(String) MessagesValues.COMMAND_INVITE_ALREADYINVITED.value,
						player);
				return true;
			}

			new Invite(guild, player, invitep, plugin);
			return true;
		}

		if (args[0].equalsIgnoreCase("accept")) {
			String guild = "";

			if (!Data.invites.containsKey(player)) {
				MUtil.sendMessage(
						(String) MessagesValues.COMMAND_ACCEPT_NOINVITE.value,
						player);
				return true;
			}

			Invite invite = Data.invites.get(player);
			String invitee = invite.guild;

			JLHandler.addPlayerToGuild(invitee, player);
			MUtil.sendMessage(
					(String) MessagesValues.COMMAND_ACCEPT_JOINED_YOU.value
							.toString().replace("%guild", invite.guild),
					player);

			String prePlayers = PermUtil.getPlayersList(guild);
			String[] prePlayersArray = prePlayers.split("\\$");

			for (String prePlayersUUID : prePlayersArray) {
				String[] ppuuid2 = prePlayersUUID.split(";");

				if (Bukkit.getPlayer(UUID.fromString(ppuuid2[0])) == null) {
					if (Bukkit.getPlayer(UUID.fromString(ppuuid2[0]))
							.isOnline()) {
						MUtil.sendMessage(
								(String) MessagesValues.COMMAND_ACCEPT_JOINED_THEY.value,
								Bukkit.getPlayer(UUID.fromString(ppuuid2[0])));
					}
				}
			}

			Data.invites.remove(player);
			return true;
		}

		if (args[0].equalsIgnoreCase("deny")
				|| args[0].equalsIgnoreCase("decline")) {
			if (!Data.invites.containsKey(player)) {
				MUtil.sendMessage(
						(String) MessagesValues.COMMAND_ACCEPT_NOINVITE.value,
						player);
				return true;
			}

			MUtil.sendMessage(
					(String) MessagesValues.COMMAND_DENY_DENIED_YOU.value,
					player);

			Data.invites.remove(player);

			String prePlayers = PermUtil
					.getPlayersList(Data.invites.get(player).guild);
			String[] prePlayersArray = prePlayers.split("\\$");
			for (String prePlayersUUID : prePlayersArray) {
				String[] ppuuid2 = prePlayersUUID.split(";");

				if (Bukkit.getPlayer(ppuuid2[0]) == null) {
					if (Bukkit.getPlayer(ppuuid2[0]).isOnline()) {
						MUtil.sendMessage(
								(String) MessagesValues.COMMAND_DENY_DENIED_THEY.value,
								Bukkit.getPlayer(ppuuid2[0]));
					}
				}
			}
			return true;
		}

		if (args[0].equalsIgnoreCase("ginvite")) {
			String guild = "";

			ResultSet pguild;
			try {
				pguild = Data.mysql
						.querySQL("SELECT guild FROM data WHERE uuid = '"
								+ player.getUniqueId().toString() + "'");
				if (pguild.next()) {
					guild = pguild.getString("guild");
				}
			} catch (ClassNotFoundException | SQLException e) {
				new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
						"Error whilst getting player's guild in the invite command method thingy");
			}

			if (guild.equals("NULL")) {
				MUtil.sendMessage(
						(String) MessagesValues.GENERAL_NOTINGUILD.value,
						player);
				return true;
			}

			Rank rank = PermUtil.getPlayerRank(player, guild);

			if (!rank.isInviteguild()) {
				MUtil.sendMessage((String) MessagesValues.NOGUILDPERM.value
						.toString().replace("%perm", "InviteGuild"), player);
				return true;
			}

			if (args.length != 2) {
				MUtil.sendMessage(
						(String) Messages.MessagesValues.COMMAND_GINVITE_SYTAX.value,
						player);
				return true;
			}

			if (args[1].equals(player.getName())) {
				MUtil.sendMessage(
						(String) MessagesValues.COMMAND_GINVITE_WHYINVITESELF.value,
						player);
				return true;
			}

			ArrayList<String> guildNames = new ArrayList<>();
			ResultSet guildslist;
			try {
				guildslist = Data.mysql.querySQL("SELECT guild FROM guilds");
				if (guildslist.next()) {
					guildNames.add(guildslist.getString("guild"));
				}
			} catch (ClassNotFoundException | SQLException e) {
				new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
						"Error whilst getting all the guild names in the ginvite method");
			}

			if (!guildNames.contains(args[1])) {
				MUtil.sendMessage(
						(String) MessagesValues.COMMAND_GINVITE_GUILDNOTEXIST.value,
						player);
				return true;
			}

			if (args[1].equals(guild)) {
				MUtil.sendMessage(
						(String) MessagesValues.COMMAND_GINVITE_WHYINVITESELF.value,
						player);
				return true;
			}

			ResultSet allyList;
			try {
				allyList = Data.mysql
						.querySQL("SELECT allies FROM guilds WHERE guild = '"
								+ guild + "'");
				if (allyList.next()) {
					String list = allyList.getString("allies");
					String[] al = list.split(";");
					for (String alCheck : al) {
						if (alCheck.equals(args[1])) {
							MUtil.sendMessage(
									(String) MessagesValues.COMMAND_GINVITE_ALREADYALLY.value,
									player);
							return true;
						}
					}
				}
			} catch (ClassNotFoundException | SQLException e) {
				new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
						"Error whilst getting a guilds alliances");
			}

			ResultSet atWar;
			try {
				atWar = Data.mysql
						.querySQL("SELECT warWtih FROM guilds WHERE guild = '"
								+ guild + "'");
				if (atWar.next()) {
					if (args[1].equals(atWar.getString("warWith"))) {
						MUtil.sendMessage(
								(String) MessagesValues.COMMAND_GINVITE_ATWAR.value,
								player);
						return true;
					}
				}
			} catch (ClassNotFoundException | SQLException e) {
				new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
						"Error whilst getting a guilds alliances in the ginvite command section");
			}

			if (Data.ginvites.containsKey(args[1])) {
				MUtil.sendMessage(
						(String) MessagesValues.COMMAND_GINVITE_ALREADYINVITED.value,
						player);
				return true;
			}
			new GInvite(guild, args[1], plugin);
			return true;
		}

		if (args[0].equalsIgnoreCase("gaccept")) {
			String guild = "";

			ResultSet pguild;
			try {
				pguild = Data.mysql
						.querySQL("SELECT guild FROM data WHERE uuid = '"
								+ player.getUniqueId().toString() + "'");
				if (pguild.next()) {
					guild = pguild.getString("guild");
				}
			} catch (ClassNotFoundException | SQLException e) {
				new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
						"Error whilst getting player's guild in the invite command method thingy");
			}

			if (guild.equals("NULL")) {
				MUtil.sendMessage(
						(String) MessagesValues.GENERAL_NOTINGUILD.value,
						player);
				return true;
			}

			Rank rank = PermUtil.getPlayerRank(player, guild);

			if (!rank.getName().equals("Leader")) {
				MUtil.sendMessage((String) MessagesValues.NOGUILDPERM.value
						.toString().replace("%perm", "*Not Leader*"), player);
				return true;
			}

			if (!Data.ginvites.containsKey(guild)) {
				MUtil.sendMessage(
						(String) MessagesValues.COMMAND_GACCEPT_NOINVITE.value,
						player);
				return true;
			}

			GInvite invite = Data.ginvites.get(guild);

			JLHandler.addAllyToGuild(guild, invite.guildInvitee);

			MUtil.sendMessage(
					(String) MessagesValues.COMMAND_GACCEPT_JOINED_YOU.value,
					player);

			String prePlayers = PermUtil.getPlayersList(guild);
			String[] prePlayersArray = prePlayers.split("\\$");
			for (String prePlayersUUID : prePlayersArray) {
				String[] ppuuid2 = prePlayersUUID.split(";");

				if (Bukkit.getPlayer(ppuuid2[0]) == null) {
					if (Bukkit.getPlayer(ppuuid2[0]).isOnline()) {
						MUtil.sendMessage(
								(String) MessagesValues.COMMAND_GACCEPT_JOINED_YOU.value,
								Bukkit.getPlayer(ppuuid2[0]));
					}
				}
			}
			return true;
		}

		if (args[0].equalsIgnoreCase("gdeny")
				|| args[0].equalsIgnoreCase("gdecline")) {
			String guild = "";

			ResultSet pguild;
			try {
				pguild = Data.mysql
						.querySQL("SELECT guild FROM data WHERE uuid = '"
								+ player.getUniqueId().toString() + "'");
				if (pguild.next()) {
					guild = pguild.getString("guild");
				}
			} catch (ClassNotFoundException | SQLException e) {
				new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
						"Error whilst getting player's guild in the invite command method thingy");
			}

			if (guild.equals("NULL")) {
				MUtil.sendMessage(
						(String) MessagesValues.GENERAL_NOTINGUILD.value,
						player);
				return true;
			}

			Rank rank = PermUtil.getPlayerRank(player, guild);

			if (!rank.getName().equals("Leader")) {
				MUtil.sendMessage((String) MessagesValues.NOGUILDPERM.value
						.toString().replace("%perm", "*Not Leader*"), player);
				return true;
			}

			if (!Data.ginvites.containsKey(guild)) {
				MUtil.sendMessage(
						(String) MessagesValues.COMMAND_GACCEPT_NOINVITE.value,
						player);
				return true;
			}

			MUtil.sendMessage(
					(String) MessagesValues.COMMAND_DENY_DENIED_YOU.value,
					player);

			Data.invites.remove(guild);

			String prePlayers = PermUtil
					.getPlayersList(Data.invites.get(player).guild);
			String[] prePlayersArray = prePlayers.split("\\$");
			for (String prePlayersUUID : prePlayersArray) {
				String[] ppuuid2 = prePlayersUUID.split(";");

				if (Bukkit.getPlayer(ppuuid2[0]) == null) {
					if (Bukkit.getPlayer(ppuuid2[0]).isOnline()) {
						MUtil.sendMessage(
								(String) MessagesValues.COMMAND_DENY_DENIED_THEY.value,
								Bukkit.getPlayer(ppuuid2[0]));
					}
				}
			}
			return true;
		}

		if (args[0].equalsIgnoreCase("kick")) {
			String guild = "";

			ResultSet pguild;
			try {
				pguild = Data.mysql
						.querySQL("SELECT guild FROM data WHERE uuid = '"
								+ player.getUniqueId().toString() + "'");
				if (pguild.next()) {
					guild = pguild.getString("guild");
				}
			} catch (ClassNotFoundException | SQLException e) {
				new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
						"Error whilst getting player's guild in the invite command method thingy");
			}

			if (guild.equals("NULL")) {
				MUtil.sendMessage(
						(String) MessagesValues.GENERAL_NOTINGUILD.value,
						player);
				return true;
			}

			Rank rank = PermUtil.getPlayerRank(player, guild);

			if (!rank.isKick()) {
				MUtil.sendMessage((String) MessagesValues.NOGUILDPERM.value
						.toString().replace("%perm", "Kick"), player);
				return true;
			}

			if (args.length != 2) {
				MUtil.sendMessage(
						(String) Messages.MessagesValues.COMMAND_KICK_SYNTAX.value,
						player);
				return true;
			}

			if (Bukkit.getPlayer(args[1]) == null) {
				MUtil.sendMessage(
						(String) MessagesValues.COMMAND_KICK_NOTFOUND.value,
						player);
				return true;
			}

			if (!PermUtil.getPlayerGuild(Bukkit.getPlayer(args[1]))
					.equals(guild)) {
				MUtil.sendMessage(
						(String) MessagesValues.COMMAND_KICK_NOTINGUILD.value,
						player);
				return true;
			}

			MUtil.sendMessage(
					(String) MessagesValues.COMMAND_KICK_YOUHAVE.value, player);

			if (Bukkit.getPlayer(args[1]).isOnline()) {
				MUtil.sendMessage(
						(String) MessagesValues.COMMAND_KICK_KICKED.value,
						Bukkit.getPlayer(args[1]));
			}

			JLHandler.removePlayerFromGuild(guild, Bukkit.getPlayer(args[1]));
			return true;
		}

		if (args[0].equalsIgnoreCase("gkick")) {
			String guild = "";

			ResultSet pguild;
			try {
				pguild = Data.mysql
						.querySQL("SELECT guild FROM data WHERE uuid = '"
								+ player.getUniqueId().toString() + "'");
				if (pguild.next()) {
					guild = pguild.getString("guild");
				}
			} catch (ClassNotFoundException | SQLException e) {
				new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
						"Error whilst getting player's guild in the invite command method thingy");
			}

			if (guild.equals("NULL")) {
				MUtil.sendMessage(
						(String) MessagesValues.GENERAL_NOTINGUILD.value,
						player);
				return true;
			}

			Rank rank = PermUtil.getPlayerRank(player, guild);

			if (!rank.isKickguild()) {
				MUtil.sendMessage((String) MessagesValues.NOGUILDPERM.value
						.toString().replace("%perm", "KickGuild"), player);
				return true;
			}

			if (args.length != 2) {
				MUtil.sendMessage(
						(String) Messages.MessagesValues.COMMAND_GKICK_SYNTAX.value,
						player);
				return true;
			}

			if (!JLHandler.guildExist(args[1])) {
				MUtil.sendMessage(
						(String) MessagesValues.COMMAND_GKICK_NOTFOUND.value,
						player);
				return true;
			}

			if (!JLHandler.areAllies(guild, args[1])) {
				MUtil.sendMessage(
						(String) MessagesValues.COMMAND_GKICK_NOTALLIES.value,
						player);
				return true;
			}

			ResultSet atWar;
			try {
				atWar = Data.mysql
						.querySQL("SELECT warWtih FROM guilds WHERE guild = '"
								+ guild + "'");
				if (atWar.next()) {
					if (args[1].equals(atWar.getString("warWith"))) {
						MUtil.sendMessage(
								(String) MessagesValues.COMMAND_GKICK_ATWAR.value,
								player);
						return true;
					}
				}
			} catch (ClassNotFoundException | SQLException e) {
				new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
						"Error whilst getting a guilds alliances in the ginvite command section");
			}

			MUtil.sendMessage(
					(String) MessagesValues.COMMAND_GKICK_YOUHAVE.value,
					player);

			JLHandler.removeAllyFromAlly(guild, args[1]);
			return true;
		}

		if (args[0].equalsIgnoreCase("gmotd")) {
			String guild = "";

			ResultSet pguild;
			try {
				pguild = Data.mysql
						.querySQL("SELECT guild FROM data WHERE uuid = '"
								+ player.getUniqueId().toString() + "'");
				if (pguild.next()) {
					guild = pguild.getString("guild");
				}
			} catch (ClassNotFoundException | SQLException e) {
				new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
						"Error whilst getting player's guild in the invite command method thingy");
			}

			if (guild.equals("NULL")) {
				MUtil.sendMessage(
						(String) MessagesValues.GENERAL_NOTINGUILD.value,
						player);
				return true;
			}

			Rank rank = PermUtil.getPlayerRank(player, guild);

			if (!rank.isGmotd()) {
				MUtil.sendMessage((String) MessagesValues.NOGUILDPERM.value
						.toString().replace("%perm", "GMOTD"), player);
				return true;
			}

			String finalString = "";

			for (int i = 1; i < args.length; i++) {
				String arg = args[i];
				finalString = finalString + arg;
			}

			try {
				Data.mysql.updateSQL("UPDATE guilds SET GMOTD = '" + finalString
						+ "' WHERE guild = '" + guild + "'");
			} catch (ClassNotFoundException | SQLException e) {
				new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
						"Error whilst getting player's guild in the invite command method thingy");
			}

			MUtil.sendMessage((String) MessagesValues.COMMAND_GMOTD.value,
					player);
			return true;
		}

		if (args[0].equalsIgnoreCase("rank")) {
			String guild = "";

			ResultSet pguild;
			try {
				pguild = Data.mysql
						.querySQL("SELECT guild FROM data WHERE uuid = '"
								+ player.getUniqueId().toString() + "'");
				if (pguild.next()) {
					guild = pguild.getString("guild");
				}
			} catch (ClassNotFoundException | SQLException e) {
				new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
						"Error whilst getting player's guild in the invite command method thingy");
			}

			if (guild.equals("NULL")) {
				MUtil.sendMessage(
						(String) MessagesValues.GENERAL_NOTINGUILD.value,
						player);
				return true;
			}

			Rank rank = PermUtil.getPlayerRank(player, guild);

			if (args[1].equalsIgnoreCase("perms")) {
				if (!rank.isRankperms()) {
					MUtil.sendMessage((String) MessagesValues.NOGUILDPERM.value
							.toString().replace("%perm", "RankPerms"), player);
					return true;
				}

				if (args.length != 3) {
					MUtil.sendMessage(
							(String) MessagesValues.COMMAND_RANK_PERMS_SYNTAX.value,
							player);
					return true;
				}

				if (args[2].equalsIgnoreCase("Leader")) {
					MUtil.sendMessage(
							(String) MessagesValues.COMMAND_RANK_PERMS_NOEDITLEASDER.value,
							player);
					return true;
				}

				String[] split = PermUtil.getRanksList(guild).split("\\$");
				ArrayList<Rank> ranks = new ArrayList<>();

				for (String s : split) {
					ranks.add(new Rank(s));
				}

				Rank rankr = null;

				for (Rank r : ranks) {
					if (r.getName().equals(args[2])) {
						rankr = r;
					}
				}

				if (rankr == null) {
					MUtil.sendMessage(
							(String) MessagesValues.COMMAND_RANK_DELETE_RANKNULL.value,
							player);
					return true;
				}

				General.permsInv(player, rankr);
			}

			if (args[1].equalsIgnoreCase("create")) {
				if (!rank.isRankcreate()) {
					MUtil.sendMessage((String) MessagesValues.NOGUILDPERM.value
							.toString().replace("%perm", "RankCreate"), player);
					return true;
				}

				if (args.length != 4) {
					MUtil.sendMessage(
							(String) MessagesValues.COMMAND_RANK_CREATE_SYNTAX.value,
							player);
					return true;
				}

				String[] split = PermUtil.getRanksList(guild).split("\\$");
				ArrayList<Rank> ranks = new ArrayList<>();

				for (String s : split) {
					ranks.add(new Rank(s));
				}

				for (Rank r : ranks) {
					if (r.getName().equals(args[2])) {
						MUtil.sendMessage(
								(String) MessagesValues.COMMAND_RANK_CREATE_ALREADYEXISTS.value,
								player);
						return true;
					}
				}

				String r = PermUtil.getRanksList(guild);
				r += "$" + (String) ConfigValues.RANK_ROOKIE_DEFAULT.value
						.toString().replaceAll("Rookie", args[2]);

				try {
					Data.mysql.updateSQL("UPDATE guilds SET ranks = '" + r
							+ "' WHERE guild = '" + guild + "'");
				} catch (ClassNotFoundException | SQLException e) {
					new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
							"Error whilst updating new rank");
				}

				// Message when created here
				return true;
			}

			if (args[1].equalsIgnoreCase("delete")) {
				// PEOPLE IN RANK< MAKE SURE THEY'RE REMOVED

				if (!rank.isRankdelete()) {
					MUtil.sendMessage((String) MessagesValues.NOGUILDPERM.value
							.toString().replace("%perm", "RankDelete"), player);
					return true;
				}

				if (args.length != 3) {
					MUtil.sendMessage(
							(String) MessagesValues.COMMAND_RANK_DELETE_SYNTAX.value,
							player);
					return true;
				}

				String[] split = PermUtil.getRanksList(guild).split("\\$");
				ArrayList<Rank> ranks = new ArrayList<>();

				for (String s : split) {
					ranks.add(new Rank(s));
				}

				boolean exist = false;

				Rank deleteRank = null;

				for (Rank r : ranks) {
					if (r.getName().equals(args[2])) {
						exist = true;
						deleteRank = r;
					}
				}

				if (!exist) {
					MUtil.sendMessage(
							(String) MessagesValues.COMMAND_RANK_DELETE_RANKNULL.value,
							player);
					return true;
				}

				String ranksList = PermUtil.getRanksList(guild);
				String newRanks = ranksList
						.replace("$" + StringUtil.compileRankForReplace(
								deleteRank.getName(), deleteRank.isInvite(),
								deleteRank.isInviteguild(), deleteRank.isKick(),
								deleteRank.isKickguild(), deleteRank.isGmotd(),
								deleteRank.isGchat(), deleteRank.isRankset(),
								deleteRank.isRanktitle(),
								deleteRank.isRankcreate(),
								deleteRank.isRankdelete(),
								deleteRank.isRankperms(),
								deleteRank.isPlotcreate(),
								deleteRank.isPlotdelete(),
								deleteRank.isPlotreset(),
								deleteRank.isPlothome(),
								deleteRank.isWardeclare(),
								deleteRank.isWarsurrender(), deleteRank.isPvp(),
								deleteRank.getTitle()), "");

				String players = PermUtil.getPlayersList(guild);
				players.replaceAll(args[2], "Rookie");

				try {
					Data.mysql.updateSQL("UPDATE guilds SET ranks = '"
							+ newRanks + "' WHERE guild = '" + guild + "'");
					Data.mysql.updateSQL("UPDATE guilds SET players = '"
							+ players + "' WHERE guild = '" + guild + "'");
				} catch (ClassNotFoundException | SQLException e) {
					new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
							"Error whilst updating new ranks list");
				}

				// Message to delete
			}

			if (args[1].equalsIgnoreCase("set")) {
				if (!rank.isRankset()) {
					MUtil.sendMessage((String) MessagesValues.NOGUILDPERM.value
							.toString().replace("%perm", "RankSet"), player);
					return true;
				}

				if (args.length != 4) {
					MUtil.sendMessage(
							(String) MessagesValues.COMMAND_RANK_SET_SYNTAX.value,
							player);
					return true;
				}

				if (args[3].equals("Leader")) {
					MUtil.sendMessage(
							(String) MessagesValues.COMMAND_RANK_SET_NOTTOLEADER.value,
							player);
					return true;
				}

				if (Bukkit.getPlayer(args[2]) == null) {
					MUtil.sendMessage(
							(String) MessagesValues.COMMAND_RANK_SET_PLAYERNOTFOUND.value,
							player);
					return true;
				}

				Player pl = Bukkit.getPlayer(args[2]);
				Rank plrank;

				if (PermUtil.getPlayerGuild(player).equals("NULL")
						|| !PermUtil.getPlayerGuild(pl).equals(guild)) {
					MUtil.sendMessage(
							(String) MessagesValues.COMMAND_RANK_SET_PLAYERNOTINGUILD.value,
							player);
					return true;
				}

				plrank = PermUtil.getPlayerRank(pl, guild);

				String[] split = PermUtil.getRanksList(guild).split("\\$");
				ArrayList<Rank> ranks = new ArrayList<>();

				for (String s : split) {
					ranks.add(new Rank(s));
				}

				boolean exist = false;

				for (Rank r : ranks) {
					if (r.getName().equals(args[3])) {
						exist = true;
					}
				}

				if (!exist) {
					MUtil.sendMessage(
							(String) MessagesValues.COMMAND_RANK_SET_RANKNULL.value,
							player);
					return true;
				}

				String newList = "";
				String[] playersList = PermUtil.getPlayersList(guild)
						.split("\\$");

				for (String s : playersList) {
					String newS = "";
					if (s.contains(pl.getUniqueId().toString())) {
						newS = s.replace(plrank.getName(), args[3]);
					} else {
						newS = s;
					}
					newList += "$" + newS;
				}

				newList.replaceFirst("$", "");

				try {
					Data.mysql.updateSQL("UPDATE guilds SET players = '"
							+ newList + "' WHERE guild = '" + guild + "'");
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}

			if (args[1].equalsIgnoreCase("list")) {
				String[] ranks = PermUtil.getRanksList(guild).split("\\$");

				MUtil.sendMessage("%TAG %ARank list", player);

				for (String s : ranks) {
					String name = s.split(":")[0];
					MUtil.sendMessage(name, player);
				}
				return true;
			}

			return true;
		}

		if (args[0].equalsIgnoreCase("plot")) {
			String guild = "";

			ResultSet pguild;
			try {
				pguild = Data.mysql
						.querySQL("SELECT guild FROM data WHERE uuid = '"
								+ player.getUniqueId().toString() + "'");
				if (pguild.next()) {
					guild = pguild.getString("guild");
				}
			} catch (ClassNotFoundException | SQLException e) {
				new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
						"Error whilst getting player's guild in the invite command method thingy");
			}

			if (guild.equals("NULL")) {
				MUtil.sendMessage(
						(String) MessagesValues.GENERAL_NOTINGUILD.value,
						player);
				return true;
			}

			Rank rank = PermUtil.getPlayerRank(player, guild);
			PlotHandler ph = new PlotHandler(guild);

			if (args[1].equalsIgnoreCase("create")) {
				if (!rank.isPlotcreate()) {
					MUtil.sendMessage((String) MessagesValues.NOGUILDPERM.value
							.toString().replace("%perm", "PlotCreate"), player);
					return true;
				}

				ph.createPlot(player);
				return true;
			}

			if (args[1].equalsIgnoreCase("delete")
					|| args[1].equalsIgnoreCase("remove")) {
				if (!rank.isPlotdelete()) {
					MUtil.sendMessage((String) MessagesValues.NOGUILDPERM.value
							.toString().replace("%perm", "PlotCreate"), player);
					return true;
				}

				ph.deletePlot(player);
				return true;
			}

			if (args[1].equalsIgnoreCase("home")) {
				if (!rank.isPlothome()) {
					MUtil.sendMessage((String) MessagesValues.NOGUILDPERM.value
							.toString().replace("%perm", "PlotCreate"), player);
					return true;
				}

				ph.sendHome(player);
				return true;
			}
			return true;
		}

		return true;
	}
}