package co.uk.RandomPanda30.RPG.Commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import co.uk.RandomPanda30.RPG.RPG;
import co.uk.RandomPanda30.RPG.Files.Config.Enums.ConfigValues;
import co.uk.RandomPanda30.RPG.Files.Messages.Enums.MessagesValues;
import co.uk.RandomPanda30.RPG.Handlers.GuildH;

public class CMD_guild implements CommandExecutor {

	public RPG plugin;

	public CMD_guild (RPG plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (!(sender instanceof Player)) {
			plugin.sendMessage((String) plugin.getRPGConfig().getMessagesMap()
					.getStat(MessagesValues.ONLYCONSOLE), null);
			return true;
		}

		if (args.length < 0) {

			return true;
		}

		Player player = (Player) sender;
		UUID uuid = player.getUniqueId();

		if (args[0].equalsIgnoreCase("create")) {
			if (args.length != 3) {
				// player.sendMessage("Please use /guild create guildname tag");
				return true;
			}

			if (args[2].length() != 4) {
				// player.sendMessage("Guild tags must be 4 letters!");
				return true;
			}

			if (plugin.getRPGValues().getConfigC().contains("Guilds")) {
				for (String guilds : plugin.getRPGValues().getConfigC()
						.getConfigurationSection("Guilds").getKeys(false)) {
					if (args[2].equalsIgnoreCase(plugin.getRPGValues()
							.getConfigC()
							.getString("Guilds." + guilds + ".Tag"))) {
						// player.sendMessage("This guild tag already exists!");
						return true;
					}
				}
			}

			if (!player.hasPermission("guild.create")) {
				plugin.sendMessage((String) plugin.getRPGConfig().getMessagesMap()
						.getStat(MessagesValues.NOPERM), player);
				return true;
			}

			if (plugin.getRPGValues().getConfigC()
					.contains(player.getUniqueId().toString())) {
				player.sendMessage("You are already in a guild! you can't create a new one!");
				return true;
			}

			if (plugin.getRPGValues().getConfigC()
					.contains(args[1].replaceAll("_", " "))) {
				player.sendMessage("A guild named "
						+ args[1].replaceAll("_", " ") + " already exists!");
				return true;
			}

			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ") + ".Players."
							+ uuid.toString() + ".Rank", "Leader");
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ") + ".Leader",
							player.getUniqueId().toString());
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ") + ".Tag",
							args[2]);
			plugin.getRPGValues()
					.getConfigC()
					.set(player.getUniqueId().toString() + ".Guild.Name",
							args[1].replaceAll("_", " "));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".DefTerm.Leader", "Leader");
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Leader.Invite", Boolean.valueOf(true));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Leader.Ochat", Boolean.valueOf(true));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Leader.Kick", Boolean.valueOf(true));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Leader.Gmotd", Boolean.valueOf(true));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Leader.Disband", Boolean.valueOf(true));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Leader.Gchat", Boolean.valueOf(true));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Leader.Gbank", Boolean.valueOf(true));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Leader.Addslot", Boolean.valueOf(true));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Leader.Withdrawl", Boolean.valueOf(true));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Leader.Deposit", Boolean.valueOf(true));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Leader.GbRanks", Boolean.valueOf(true));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Leader.RankSet", Boolean.valueOf(true));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Leader.RankTitle", Boolean.valueOf(true));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Leader.CreateRank", Boolean.valueOf(true));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Leader.DeleteRank", Boolean.valueOf(true));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Leader.PlayerInfo", Boolean.valueOf(true));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Leader.RankPerms", Boolean.valueOf(true));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Leader.PlayerNotes",
							Boolean.valueOf(true));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Leader.PlayerNotesView",
							Boolean.valueOf(true));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Leader.PlayerNotesSet",
							Boolean.valueOf(true));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Leader.TP", Boolean.valueOf(true));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Leader.Title", "&4Guild Master");
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.Invite", Boolean.valueOf(false));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.Ochat", Boolean.valueOf(false));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.Kick", Boolean.valueOf(false));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.Gmotd", Boolean.valueOf(false));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.Disband", Boolean.valueOf(false));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.Gchat", Boolean.valueOf(true));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.Gbank", Boolean.valueOf(false));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.Addslot", Boolean.valueOf(false));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.Withdrawl",
							Boolean.valueOf(false));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.Deposit", Boolean.valueOf(false));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.GbRanks", Boolean.valueOf(false));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.RankSet", Boolean.valueOf(false));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.RankTitle",
							Boolean.valueOf(false));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.CreateRank",
							Boolean.valueOf(false));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.DeleteRank",
							Boolean.valueOf(false));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.PlayerInfo",
							Boolean.valueOf(false));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.RankPerms",
							Boolean.valueOf(false));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.PlayerNotes",
							Boolean.valueOf(false));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.PlayerNotesView",
							Boolean.valueOf(false));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.PlayerNotesSet",
							Boolean.valueOf(false));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.TP", Boolean.valueOf(false));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.Title", "&2Newbies");
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".DefTerm.Default", "Newbies");
			plugin.getRPGConfig().saveAllConfigs();
			// player.sendMessage("Congratulations " + player.getName()
			// + " you are now the leader of the newly created guild "
			// + args[1].replaceAll("_", " "));

			return true;
		}

		if (args[0].equalsIgnoreCase("reward")) {
			if (!player.hasPermission("cts.reward")) {
				plugin.sendMessage((String) plugin.getRPGConfig().getMessagesMap()
						.getStat(MessagesValues.NOPERM), player);
				return true;
			}

			Inventory inventory = Bukkit.createInventory(null, 54,
					"Drag and drop items into here");

			if (GuildH.rewards != null) {
				inventory.addItem(GuildH.rewards);
			}

			player.openInventory(inventory);
			return true;
		}

		if (args[0].equalsIgnoreCase("invite")) {
			if (args.length != 2) {
				// player.sendMessage("Improper usage of the guild invite command, please just use /guild invite playername");
				return true;
			}

			if (Bukkit.getPlayer(args[1]) == null) {
				// player.sendMessage("This player cannot be found");
				return true;
			}

			Player p = Bukkit.getPlayer(args[1]);
			if (player != null) {
				String guildName = plugin
						.getRPGValues()
						.getConfigC()
						.getString(
								player.getUniqueId().toString() + ".Guild.Name");
				if (!plugin.getRPGValues().getConfigC()
						.contains(player.getUniqueId().toString())) {
					plugin.sendMessage(
							(String) plugin.getRPGConfig().getMessagesMap()
									.getStat(MessagesValues.NOTINAGUILD),
							player);
					return true;
				}

				if (!plugin.getRPGValues().getConfigC()
						.getString("Guilds." + guildName + ".Leader")
						.equals(player.getUniqueId().toString())) {
					// player.sendMessage("You do not have permission to invite people to this guild!");
					return true;
				}

				if (plugin.getRPGValues().getConfigC()
						.contains(p.getUniqueId().toString())) {
					// player.sendMessage(p.getName() +
					// " is already in a guild!");
					return true;
				}

				if (plugin.getRPGValues().getConfigC()
						.contains("Pending." + p.getUniqueId().toString())) {
					// player.sendMessage("This player already has a pending guild invite from "
					// + this.Rpgg.getConfig().getString(
					// new StringBuilder("Pending.")
					// .append(p.getUniqueId().toString())
					// .append(".Guild").toString()));
					return true;
				}

				plugin.getRPGValues()
						.getConfigC()
						.set("Pending." + p.getUniqueId().toString() + ".Guild",
								guildName);
				plugin.getRPGConfig().saveAllConfigs();
				// player.sendMessage("You have invited "
				// + p.getUniqueId().toString() + " To join " + guildn);
				// p.sendMessage("You have a pending guild invite from '"
				// + guildn
				// +
				// "' type </guild accept> to join this guild. or </guild deny> to turn it down.");
				return true;
			}
		}

		if (args[0].equalsIgnoreCase("accept")) {
			if (!plugin.getRPGValues().getConfigC()
					.contains("Pending." + player.getUniqueId().toString())) {
				// player.sendMessage("You do not have any pending guild invites!");
				return true;
			}

			String guildName = plugin
					.getRPGValues()
					.getConfigC()
					.getString(
							"Pending." + player.getUniqueId().toString()
									+ ".Guild");
			if (!plugin.getRPGValues().getConfigC()
					.contains("Guilds." + guildName)) {
				// player.sendMessage("This guild no longer exists!");
				plugin.getRPGValues()
						.getConfigC()
						.set("Pending." + player.getUniqueId().toString(), null);
				return true;
			}

			String newbies = plugin.getRPGValues().getConfigC()
					.getString("Guilds." + guildName + ".DefTerm.Default");
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + guildName + ".Players."
							+ player.getUniqueId().toString() + ".Rank",
							newbies);
			plugin.getRPGValues()
					.getConfigC()
					.set(player.getUniqueId().toString() + ".Guild.Name",
							guildName);
			plugin.getRPGValues().getConfigC()
					.set("Pending." + player.getUniqueId().toString(), null);
			plugin.getRPGConfig().saveAllConfigs();
			if ((Boolean) plugin.getRPGConfig().getConfigMap()
					.getStat(ConfigValues.Chat)) {
				player.setDisplayName("&F[&2" + guildName + "&f]"
						+ player.getDisplayName());
			}

			for (String key : plugin
					.getRPGValues()
					.getConfigC()
					.getConfigurationSection("Guilds." + guildName + ".Players")
					.getKeys(false)) {
				if (Bukkit.getPlayer(key) != null) {
					if (Bukkit.getPlayer(key).getName() == player.getName()) {
						// player.sendMessage("You Have Joined  " + guildn +
						// "!!!");
					} else {
						Player p = Bukkit.getPlayer(key);
						// p.sendMessage("&3" + player.getName()
						// + "&2 Has Joined The Guild!");
					}
				}
			}
			return true;
		}

		if (args[0].equalsIgnoreCase("deny")) {
			if (!plugin.getRPGValues().getConfigC()
					.contains("Pending." + player.getUniqueId().toString())) {
				player.sendMessage("You do not have any pending guild invites!");
				return true;
			}

			String guildName = plugin
					.getRPGValues()
					.getConfigC()
					.getString(
							"Pending." + player.getUniqueId().toString()
									+ ".Guild");
			String lead = plugin.getRPGValues().getConfigC()
					.getString("Guilds." + guildName + ".Leader");
			if (Bukkit.getPlayer(lead) != null) {
				Player gleader = Bukkit.getPlayer(lead);
				// gleader.sendMessage(player.getName()
				// + " has declined your guild invite.");
			}

			// player.sendMessage("You have declined joining " + guildn + ".");
			plugin.getRPGValues().getConfigC()
					.set("Pending." + player.getUniqueId().toString(), null);
			plugin.getRPGConfig().saveAllConfigs();
			return true;
		}

		if (args[0].equalsIgnoreCase("gmotd")) {
			StringBuilder buffer = new StringBuilder();
			for (int i = 1; i < args.length; i++) {
				buffer.append(' ').append(args[i]);
			}

			String s = buffer.toString();
			String guildName = plugin.getRPGValues().getConfigC()
					.getString(player.getUniqueId().toString() + ".Guild.Name");
			String grank2 = plugin
					.getRPGValues()
					.getConfigC()
					.getString(
							"Guilds." + guildName + ".Players."
									+ player.getUniqueId().toString() + ".Rank");
			if (!plugin.getRPGValues().getConfigC()
					.contains(player.getUniqueId().toString())) {
				plugin.sendMessage((String) plugin.getRPGConfig()
						.getMessagesMap().getStat(MessagesValues.NOTINAGUILD),
						player);
				return true;
			}

			if (!plugin
					.getRPGValues()
					.getConfigC()
					.getBoolean(
							"Guilds." + guildName + ".Ranks." + grank2
									+ ".Gmotd")) {
				// player.sendMessage("You do not have permission to set the Gmotd!");
				return true;
			}

			plugin.getRPGValues().getConfigC()
					.set("Guilds." + guildName + ".Gmotd", s);
			plugin.getRPGConfig().saveAllConfigs();
			// player.sendMessage("You have saved the gmotd as &2" + s);
			return true;
		}

		if (args[0].equalsIgnoreCase("Kick")) {
			String guildName = plugin.getRPGValues().getConfigC()
					.getString(player.getUniqueId().toString() + ".Guild.Name");
			String grank2 = plugin
					.getRPGValues()
					.getConfigC()
					.getString(
							"Guilds." + guildName + ".Players."
									+ player.getUniqueId().toString() + ".Rank");
			if (args.length != 2) {
				// player.sendMessage("Please include the name of the person you want to kick.");
				return true;
			}

			if (!plugin.getRPGValues().getConfigC()
					.contains(player.getUniqueId().toString())) {
				plugin.sendMessage((String) plugin.getRPGConfig()
						.getMessagesMap().getStat(MessagesValues.NOTINAGUILD),
						player);
				return true;
			}

			if (!plugin
					.getRPGValues()
					.getConfigC()
					.getBoolean(
							"Guilds." + guildName + ".Ranks." + grank2
									+ ".Kick")) {
				// player.sendMessage("You do not have permission to kick someone from the guild!");
				return true;
			}

			if (!plugin
					.getRPGValues()
					.getConfigC()
					.contains(
							"Guilds."
									+ guildName
									+ ".Players."
									+ Bukkit.getPlayer(args[1]).getUniqueId()
											.toString())) {
				// player.sendMessage("This player is not a member of your guild!");
				return true;
			}

			String prank = plugin
					.getRPGValues()
					.getConfigC()
					.getString(
							"Guilds." + guildName + ".Players." + args[1]
									+ ".Rank");
			if (prank.equalsIgnoreCase("Leader")) {
				player.sendMessage("You cannot kick a guild leader!");
				return true;
			}

			plugin.getRPGValues().getConfigC()
					.set("Guilds." + guildName + ".Players." + args[1], null);
			plugin.getRPGValues().getConfigC().set(args[1], null);
			if (Bukkit.getPlayer(args[1]) != null) {
				Player newPlayer = Bukkit.getPlayer(args[1]);
				newPlayer = Bukkit.getPlayer(args[1]);
				// p.sendMessage("You have been removed from " + guildn + ".");
				newPlayer.setDisplayName(newPlayer.getName());
			}

			// player.sendMessage("You have removed " + args[1] + " from "
			// + guildn + ".");
			plugin.getRPGConfig().saveAllConfigs();
			return true;
		}

		if (args[0].equalsIgnoreCase("disband")) {
			String guildName = plugin.getRPGValues().getConfigC()
					.getString(player.getUniqueId().toString() + ".Guild.Name");

			if (args.length != 2) {
				player.sendMessage("Command syntax: /guild disband <Guild name>");
				return true;
			}

			if (!plugin.getRPGValues().getConfigC()
					.contains(player.getUniqueId().toString())) {
				player.sendMessage("You are not even in a guild? How can you disband it?");
				return true;
			}

			try {
				if (!plugin.getRPGValues().getConfigC()
						.get("Guilds." + args[1] + ".Leader")
						.equals(player.getUniqueId().toString())) {
					player.sendMessage("You do not have permission to disband the guild!");
					return true;
				}

				// player.sendMessage(ChatColor.translateAlternateColorCodes('&',
				// "&4You Have Disbanded " + args[1] + "!"));
				// player.setDisplayName(player.getName());

				plugin.getRPGValues().getConfigC()
						.set("Guilds." + guildName, null);
				plugin.getRPGValues().getConfigC()
						.set(player.getUniqueId().toString(), null);
				plugin.getRPGConfig().saveAllConfigs();
			} catch (NullPointerException e) {
				// player.sendMessage(ChatColor
				// .translateAlternateColorCodes('&',
				// "&CSomething went wrong. The guid name is case sensitive; try again"));
			}
			return true;
		}

		if (args[0].equalsIgnoreCase("leave")) {
			String guildName = plugin.getRPGValues().getConfigC()
					.getString(player.getUniqueId().toString() + ".Guild.Name");
			if (!plugin.getRPGValues().getConfigC()
					.contains(player.getUniqueId().toString())) {
				// player.sendMessage("You are not even in a guild? How can you quit?");
				return true;
			}

			for (String key2 : plugin
					.getRPGValues()
					.getConfigC()
					.getConfigurationSection("Guilds." + guildName + ".Players")
					.getKeys(false)) {
				if (Bukkit.getPlayer(key2).getUniqueId().toString() == player
						.getUniqueId().toString()) {
					// player.sendMessage("&4You Have Left  " + guildName +
					// "!!!");
				} else if (Bukkit.getPlayer(key2) != null) {
					Player p2 = Bukkit.getPlayer(key2);
					// p2.sendMessage("&3" + player.getUniqueId().toString()
					// + "&4 Has Quit The Guild!");
				}
			}

			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + guildName + ".Players."
							+ player.getUniqueId().toString(), null);
			plugin.getRPGValues().getConfigC()
					.set(player.getUniqueId().toString(), null);
			player.setDisplayName(player.getName());
			return true;
		}

		if (args[0].equalsIgnoreCase("List")) {
			if (args.length != 1) {
				// player.sendMessage("Improper usage! Please use /guild list");
				return true;
			}

			String guildName = plugin.getRPGValues().getConfigC()
					.getString(player.getUniqueId().toString() + ".Guild.Name");
			if (!plugin.getRPGValues().getConfigC()
					.contains(player.getUniqueId().toString())) {
				plugin.sendMessage((String) plugin.getRPGConfig()
						.getMessagesMap().getStat(MessagesValues.NOTINAGUILD),
						player);
				return true;
			}

			for (String key2 : plugin
					.getRPGValues()
					.getConfigC()
					.getConfigurationSection("Guilds." + guildName + ".Players")
					.getKeys(false)) {
				if (Bukkit.getPlayer(key2) != null) {
					Player p2 = Bukkit.getPlayer(key2);
					String rank = plugin
							.getRPGValues()
							.getConfigC()
							.getString(
									"Guilds." + guildName + ".Players."
											+ p2.getUniqueId().toString()
											+ ".Rank");
					String title = plugin
							.getRPGValues()
							.getConfigC()
							.getString(
									"Guilds." + guildName + ".Ranks." + rank
											+ ".Title");
					player.sendMessage("&F[" + title + "&F]" + " "
							+ p2.getUniqueId().toString()
							+ " - Status &2Online\n");
				} else {
					String rank = plugin
							.getRPGValues()
							.getConfigC()
							.getString(
									"Guilds." + guildName + ".Players." + key2
											+ ".Rank");
					String title = plugin
							.getRPGValues()
							.getConfigC()
							.getString(
									"Guilds." + guildName + ".Ranks." + rank
											+ ".Title");
					// player.sendMessage("&F[" + title + "&F]" + " " + key2
					// + " - Status &8Offline\n");
				}
			}
			// player.sendMessage("List complete");
			return true;
		}

		if (args[0].equalsIgnoreCase("TP")) {
			if (!player.isOp()) {
				// player.sendMessage("Only ops can change the default server teleport behavior!");
				return true;
			}

			if (args.length != 2) {
				// player.sendMessage("wrong usage! please use /guild tp on  or guild tp off!");
				return true;
			}

			if (args[1].equalsIgnoreCase("on")) {
				plugin.getRPGValues().getConfigC()
						.set("TP", Boolean.valueOf(true));
				// player.sendMessage("Teleporting guild members is now allowed on your server!");
				return true;
			}

			if (args[1].equalsIgnoreCase("off")) {
				plugin.getRPGValues().getConfigC()
						.set("TP", Boolean.valueOf(false));
				// player.sendMessage("Teleporting guild members is no longer allowed on your server!");
				return true;
			}
		}

		return true;
	}
}