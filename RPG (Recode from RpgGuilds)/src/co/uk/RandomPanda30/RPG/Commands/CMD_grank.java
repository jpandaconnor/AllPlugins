package co.uk.RandomPanda30.RPG.Commands;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.RPG.RPG;
import co.uk.RandomPanda30.RPG.Files.Messages.Enums.MessagesValues;

public class CMD_grank implements CommandExecutor {

	public RPG plugin;

	public CMD_grank (RPG plugin) {
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

		Player player = (Player) sender;
		UUID uuid = player.getUniqueId();

		String guildName = "";
		String guildRank = "";

		if (args.length != 3) {
			plugin.sendMessage((String) plugin.getRPGConfig().getMessagesMap()
					.getStat(MessagesValues.SYNTAX_GRANKCREATE), player);
			return true;
		}

		Bukkit.broadcastMessage(uuid.toString());

		if (!plugin.getRPGValues().getConfigC().contains(uuid.toString())) {
			plugin.sendMessage((String) plugin.getRPGConfig().getMessagesMap()
					.getStat(MessagesValues.NOTINAGUILD), player);
			return true;
		}

		guildName = (String) plugin.getRPGValues().getConfigC()
				.get(uuid.toString() + ".Guild.Name");
		guildRank = (String) plugin
				.getRPGValues()
				.getConfigC()
				.get("Guilds." + guildName + ".Players." + uuid.toString()
						+ ".Rank");

		if (args[0].equalsIgnoreCase("create")) {
			if (!(Boolean) plugin
					.getRPGValues()
					.getConfigC()
					.get("Guilds." + guildName + ".Ranks." + guildRank + "."
							+ "CreateRank")) {
				plugin.sendMessage((String) plugin.getRPGConfig()
						.getMessagesMap().getStat(MessagesValues.NOPERM),
						player);
				return true;
			}

			if (plugin
					.getRPGValues()
					.getConfigC()
					.contains(
							"Guilds." + guildName + ".Ranks."
									+ args[1].replaceAll("_", " "))) {
				plugin.sendMessage(
						(String) plugin.getRPGConfig().getMessagesMap()
								.getStat(MessagesValues.RANK_ALREADYEXISTS),
						player);
				return true;
			}

			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + guildName + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".Title",
							args[2].replaceAll("_", " "));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + guildName + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".Invite", false);
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + guildName + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".Kick", false);
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + guildName + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".Gmotd", false);
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + guildName + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".Disband", false);
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + guildName + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".Gchat",
							Boolean.valueOf(true));
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + guildName + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".Gbank", false);
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + guildName + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".Addslot", false);
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + guildName + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".Withdrawl",
							false);
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + guildName + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".Deposit", false);
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + guildName + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".GbRanks", false);
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + guildName + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".RankSet", false);
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + guildName + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".RankTitle",
							false);
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + guildName + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".CreateRank",
							false);
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + guildName + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".DeleteRank",
							false);
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + guildName + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".PlayerInfo",
							false);
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + guildName + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".RankPerms",
							false);
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + guildName + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".Ochat", false);
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + guildName + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".PlayerNotes",
							false);
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + guildName + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".PlayerNotesView",
							false);
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + guildName + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".PlayerNotesSet",
							false);
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + guildName + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".TP", false);
			plugin.getRPGConfig().saveAllConfigs();
			plugin.sendMessage(
					(String) plugin
							.getRPGConfig()
							.getMessagesMap()
							.getStat(MessagesValues.RANK_CREATED)
							.toString()
							.replace("%rank",
									args[1].toString().replaceAll("_", " ")),
					player);
			return true;
		}

		if (args[0].equalsIgnoreCase("delete")) {
			if (args.length <= 1) {
				plugin.sendMessage(
						(String) plugin.getRPGConfig().getMessagesMap()
								.getStat(MessagesValues.SYNTAX_GRANKDELETE),
						player);
				return true;
			}

			if (!(Boolean) plugin
					.getRPGValues()
					.getConfigC()
					.get("Guilds." + guildName + ".Ranks." + guildRank + "."
							+ "DeleteRank")) {
				plugin.sendMessage((String) plugin.getRPGConfig()
						.getMessagesMap().getStat(MessagesValues.NOPERM),
						player);
				return true;
			}

			if (!plugin
					.getRPGValues()
					.getConfigC()
					.contains(
							"Guilds." + guildName + ".Ranks."
									+ args[1].replaceAll("_", " "))) {
				plugin.sendMessage((String) plugin.getRPGConfig()
						.getMessagesMap().getStat(MessagesValues.RANK_NOEXIST),
						player);
				return true;
			}

			String newR = plugin.getRPGValues().getConfigC()
					.getString("Guilds." + guildName + ".DefTerm.Default");
			for (String key : plugin
					.getRPGValues()
					.getConfigC()
					.getConfigurationSection("Guilds." + guildName + ".Players")
					.getKeys(false)) {
				String rank = plugin
						.getRPGValues()
						.getConfigC()
						.getString(
								"Guilds." + guildName + ".Players." + key
										+ ".Rank");
				if (args[1].replaceAll("_", " ").equalsIgnoreCase(rank)) {
					plugin.getRPGValues()
							.getConfigC()
							.set("Guilds." + guildName + ".Players." + key
									+ ".Rank", newR);
					if (Bukkit.getPlayer(key) != null) {
						Player newP = Bukkit.getPlayer(key);
						plugin.sendMessage(
								(String) plugin.getRPGConfig().getMessagesMap()
										.getStat(MessagesValues.RANK_CHANGEDTO)
										.toString().replace("%rank", newR),
								newP);
					}
				}
			}

			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + guildName + ".Ranks."
							+ args[1].replaceAll("_", " "), null);
			plugin.getRPGConfig().saveAllConfigs();
			plugin.sendMessage(
					(String) plugin.getRPGConfig().getMessagesMap()
							.getStat(MessagesValues.RANK_CHANGEDTO).toString()
							.replace("%rank", args[1].replaceAll("_", " ")),
					player);
			return true;
		}

		if (args[0].equalsIgnoreCase("set")) {
			if (args.length != 3) {
				plugin.sendMessage(
						(String) plugin.getRPGConfig().getMessagesMap()
								.getStat(MessagesValues.SYNTAX_GRANKSET),
						player);
				return true;
			}

			String newR = (String) plugin
					.getRPGValues()
					.getConfigC()
					.get("Guilds." + guildName + ".Players." + args[1]
							+ ".Rank");
			if (!(Boolean) plugin
					.getRPGValues()
					.getConfigC()
					.get("Guilds." + guildName + ".Ranks." + guildRank + "."
							+ "RankSet")) {
				plugin.sendMessage((String) plugin.getRPGConfig()
						.getMessagesMap().getStat(MessagesValues.NOPERM),
						player);
				return true;
			}

			if (!plugin
					.getRPGValues()
					.getConfigC()
					.contains(
							"Guilds." + guildName + ".Ranks."
									+ args[2].replaceAll("_", " "))) {
				plugin.sendMessage((String) plugin.getRPGConfig()
						.getMessagesMap().getStat(MessagesValues.RANK_NOEXIST),
						player);
				return true;
			}

			if (!plugin.getRPGValues().getConfigC()
					.contains("Guilds." + guildName + ".Players." + args[1])) {
				player.sendMessage("That player is not a part of your guild!");
				return true;
			}

			String leader = (String) plugin.getRPGValues().getConfigC()
					.get("Guilds." + guildName + ".DefTerm.Leader");
			String newbie = (String) plugin.getRPGValues().getConfigC()
					.get("Guilds." + guildName + ".DefTerm.Default");

			if ((args[2].replaceAll("_", " ").equalsIgnoreCase(leader))
					&& (!guildRank.equalsIgnoreCase(leader))) {
				plugin.sendMessage(
						(String) plugin
								.getRPGConfig()
								.getMessagesMap()
								.getStat(
										MessagesValues.RANK_CANNOTPROMOTETOLEADER),
						player);
				return true;
			}

			if ((args[2].replaceAll("_", " ").equalsIgnoreCase(leader))
					&& (guildRank.equalsIgnoreCase(leader))) {
				plugin.getRPGValues()
						.getConfigC()
						.set("Guilds." + guildName + ".Players." + args[1]
								+ ".Rank", leader);
				plugin.getRPGValues()
						.getConfigC()
						.set("Guilds." + guildName + ".Players."
								+ player.getUniqueId().toString() + ".Rank",
								newbie);
				plugin.getRPGValues().getConfigC()
						.set("Guilds." + guildName + ".Leader", args[1]);
				plugin.getRPGConfig().saveAllConfigs();

				plugin.sendMessage(
						(String) plugin.getRPGConfig().getMessagesMap()
								.getStat(MessagesValues.RANK_NOLONGERLEADER)
								.toString().replaceAll("%guild", guildName),
						player);

				for (String key : plugin
						.getRPGValues()
						.getConfigC()
						.getConfigurationSection(
								"Guilds." + guildName + ".Players")
						.getKeys(false)) {
					if (Bukkit.getPlayer(key) != null) {
						Player p2 = Bukkit.getPlayer(key);
						if (!p2.getName().equalsIgnoreCase(args[1])) {
							String pass1 = (String) plugin
									.getRPGConfig()
									.getMessagesMap()
									.getStat(MessagesValues.RANK_ISNOWTHELEADER)
									.toString().replaceAll("%guild", guildName);
							pass1.replaceAll("%player", player.getName());
							plugin.sendMessage(pass1, p2);
						} else {
							String pass1 = (String) plugin
									.getRPGConfig()
									.getMessagesMap()
									.getStat(
											MessagesValues.RANK_YOUARENOWTHELEADER)
									.toString().replaceAll("%guild", guildName);
							plugin.sendMessage(pass1, p2);
						}
					}
				}
				return true;
			}

			if (newR.equalsIgnoreCase(guildRank)) {
				plugin.sendMessage((String) plugin.getRPGConfig()
						.getMessagesMap().getStat(MessagesValues.NOPERM),
						player);
				return true;
			}

			if (args[2].replaceAll("_", " ").equalsIgnoreCase(guildRank)) {
				plugin.sendMessage((String) plugin.getRPGConfig()
						.getMessagesMap().getStat(MessagesValues.NOPERM),
						player);
				return true;
			}

			if (newR.equalsIgnoreCase(leader)) {
				plugin.sendMessage(
						(String) plugin
								.getRPGConfig()
								.getMessagesMap()
								.getStat(
										MessagesValues.RANK_CANNOTPROMOTETOLEADER),
						player);
				return true;
			}

			for (String key : plugin
					.getRPGValues()
					.getConfigC()
					.getConfigurationSection("Guilds." + guildName + ".Players")
					.getKeys(false)) {
				if (Bukkit.getPlayer(key) != null) {
					Player p2 = Bukkit.getPlayer(key);
					if (!p2.getName().equalsIgnoreCase(args[1])) {
						String pass = (String) plugin
								.getRPGConfig()
								.getMessagesMap()
								.getStat(MessagesValues.RANK_THEIRRANKCHANGEDTO)
								.toString().replace("%player", args[1]);
						pass = pass.replace("%rank",
								args[2].replaceAll("_", " "));
						plugin.sendMessage(pass, p2);
					} else {
						plugin.sendMessage(
								plugin.getRPGConfig()
										.getMessagesMap()
										.getStat(
												MessagesValues.RANK_YOURRANKCHANGETO)
										.toString()
										.replace("%rank",
												args[2].replaceAll("_", " ")),
								p2);
					}
				}
			}

			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + guildName + ".Players." + args[1]
							+ ".Rank", args[2].replaceAll("_", " "));

			plugin.getRPGConfig().saveAllConfigs();
			return true;
		}

		if (args[0].equalsIgnoreCase("title")) {
			if (args.length != 3) {
				plugin.sendMessage(
						(String) plugin.getRPGConfig().getMessagesMap()
								.getStat(MessagesValues.SYNTAX_GRANKTITLE),
						player);
				return true;
			}

			if (!(Boolean) plugin
					.getRPGValues()
					.getConfigC()
					.get("Guilds." + guildName + ".Ranks." + guildRank + "."
							+ "RankTitle")) {
				plugin.sendMessage((String) plugin.getRPGConfig()
						.getMessagesMap().getStat(MessagesValues.NOPERM),
						player);
				return true;
			}

			if (!plugin
					.getRPGValues()
					.getConfigC()
					.contains(
							"Guilds." + guildName + ".Ranks."
									+ args[2].replaceAll("_", " "))) {
				plugin.sendMessage((String) plugin.getRPGConfig()
						.getMessagesMap().getStat(MessagesValues.RANK_NOEXIST),
						player);
				return true;
			}

			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + guildName + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".Title",
							args[2].replaceAll("_", " "));
			plugin.getRPGConfig().saveAllConfigs();
			// player.sendMessage("You have changed "
			// + args[1].replaceAll("_", " ") + "'s title to "
			// + args[2].replaceAll("_", " ") + ".");
			return true;
		}

		if (args[0].equalsIgnoreCase("perms")) {
			if (args.length != 4) {
				plugin.sendMessage(
						(String) plugin.getRPGConfig().getMessagesMap()
								.getStat(MessagesValues.SYNTAX_GRANKPERMS),
						player);
				return true;
			}

			if (!(Boolean) plugin
					.getRPGValues()
					.getConfigC()
					.get("Guilds." + guildName + ".Ranks." + guildRank + "."
							+ "RankPerms")) {
				plugin.sendMessage((String) plugin.getRPGConfig()
						.getMessagesMap().getStat(MessagesValues.NOPERM),
						player);
				return true;
			}

			if (!plugin
					.getRPGValues()
					.getConfigC()
					.contains(
							"Guilds." + guildName + ".Ranks."
									+ args[2].replaceAll("_", " "))) {
				plugin.sendMessage((String) plugin.getRPGConfig()
						.getMessagesMap().getStat(MessagesValues.RANK_NOEXIST),
						player);
				return true;
			}

			String leader = plugin.getRPGValues().getConfigC()
					.getString("Guild." + guildName + ".DefTerm.Leader");
			if (args[1].replaceAll("_", " ").equalsIgnoreCase(leader)) {
				plugin.sendMessage((String) plugin.getRPGConfig()
						.getMessagesMap().getStat(MessagesValues.NOPERM),
						player);
				return true;
			}

			if (args[3].equalsIgnoreCase("true")) {
				Boolean permbool = Boolean.valueOf(true);
				plugin.getRPGValues()
						.getConfigC()
						.set("Guilds." + guildName + ".Ranks."
								+ args[1].replaceAll("_", " ") + "."
								+ args[2].replaceAll("_", " "), permbool);
				plugin.getRPGConfig().saveAllConfigs();
				String pass = (String) plugin.getRPGConfig().getMessagesMap()
						.getStat(MessagesValues.RANK_YESMOREPERMISSION)
						.toString()
						.replace("%rank", args[1].replaceAll("_", " "));
				pass = pass.replace("%perm", args[2].replaceAll("_", " "));
				plugin.sendMessage(pass, player);
				return true;
			}

			Boolean permbool = Boolean.valueOf(false);
			plugin.getRPGValues()
					.getConfigC()
					.set("Guilds." + guildName + ".Ranks."
							+ args[1].replaceAll("_", " ") + "."
							+ args[2].replaceAll("_", " "), permbool);
			plugin.getRPGConfig().saveAllConfigs();
			String pass = (String) plugin.getRPGConfig().getMessagesMap()
					.getStat(MessagesValues.RANK_YESMOREPERMISSION).toString()
					.replace("%rank", args[1].replaceAll("_", " "));
			pass = pass.replace("%perm", args[2].replaceAll("_", " "));
			plugin.sendMessage(pass, player);
			return true;
		}

		if (args[0].equalsIgnoreCase("list")) {
			player.sendMessage("The available rank permissions are \nInvite\nKick\nGmotd\nDisband\nGchat\nGbank\nAddslot\nWithdrawl\nDeposit\nGbRanks\nRankSet\nRankTitle\nCreateRank\nDeleteRank\nPlayerInfo\nRankPerms\nOchat\nPlayerNotesView\nPlayerNotesSet\nTitle");
			return true;
		}

		if (args[0].equalsIgnoreCase("defaults")) {
			String leader = (String) plugin.getRPGValues().getConfigC()
					.get("Guilds." + guildName + ".DefTerm.Leader");
			String newR = plugin.getRPGValues().getConfigC()
					.getString("Guilds." + guildName + ".DefTerm.Default");
			if (!newR.equalsIgnoreCase(leader)) {
				plugin.sendMessage((String) plugin.getRPGConfig()
						.getMessagesMap().getStat(MessagesValues.NOPERM),
						player);
				return true;
			}

			if (args[1].equalsIgnoreCase("leader")) {
				plugin.getRPGValues()
						.getConfigC()
						.set("Guilds." + guildName + ".DefTerm.Leader",
								args[2].replaceAll("_", " "));
				plugin.getRPGConfig().saveAllConfigs();
				plugin.sendMessage(
						(String) plugin
								.getRPGConfig()
								.getMessagesMap()
								.getStat(
										MessagesValues.RANK_CHANGEDLEADERRANKNAME)
								.toString()
								.replace("%rank", args[2].replaceAll("_", " ")),
						player);
				return true;
			}

			if (args[1].equalsIgnoreCase("newbies")) {
				plugin.getRPGValues()
						.getConfigC()
						.set("Guilds." + guildName + ".DefTerm.Default",
								args[2].replaceAll("_", " "));
				plugin.getRPGConfig().saveAllConfigs();
				plugin.sendMessage(
						(String) plugin
								.getRPGConfig()
								.getMessagesMap()
								.getStat(
										MessagesValues.RANK_CHANGEDDEFAULTRANKNAME)
								.toString()
								.replace("%rank", args[2].replaceAll("_", " ")),
						player);
				return true;
			}

			player.sendMessage("Improper usage! Please use /grank defaults {leader/newbies} new_rank_name");
			return true;
		}

		if (args[0].equalsIgnoreCase("edit")) {
			if (args.length != 2) {
				player.sendMessage("You must specify a rank to edit!");
				return true;
			}

			String leader = (String) plugin.getRPGValues().getConfigC()
					.get("Guilds." + guildName + ".DefTerm.Leader");
			if (!plugin
					.getRPGValues()
					.getConfigC()
					.getBoolean(
							"Guilds." + guildName + ".Ranks." + guildRank
									+ ".RankPerms")) {
				plugin.sendMessage((String) plugin.getRPGConfig()
						.getMessagesMap().getStat(MessagesValues.NOPERM),
						player);
				return true;
			}

			if (!plugin
					.getRPGValues()
					.getConfigC()
					.contains(
							"Guilds." + guildName + ".Ranks."
									+ args[1].replaceAll("_", " "))) {
				plugin.sendMessage((String) plugin.getRPGConfig()
						.getMessagesMap().getStat(MessagesValues.RANK_NOEXIST),
						player);
				return true;
			}

			if (guildRank.equalsIgnoreCase(args[1].replaceAll("_", " "))) {
				plugin.sendMessage((String) plugin.getRPGConfig()
						.getMessagesMap().getStat(MessagesValues.NOPERM),
						player);
				return true;
			}

			if (leader.equalsIgnoreCase(args[1].replaceAll("_", " "))) {
				plugin.sendMessage((String) plugin.getRPGConfig()
						.getMessagesMap().getStat(MessagesValues.NOPERM),
						player);
				return true;
			}

			Inventory inv = Bukkit.createInventory(null, 27,
					args[1].replaceAll("_", " "));
			ItemStack itmStack0 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack1 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack2 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack3 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack4 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack5 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack6 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack7 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack8 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack9 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack10 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack11 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack12 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack13 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack14 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack15 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack16 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack17 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack18 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack19 = new ItemStack(Material.WOOL, 1);
			Short green = Short.valueOf((short) 5);
			Short red = Short.valueOf((short) 14);

			if (!plugin
					.getRPGValues()
					.getConfigC()
					.getBoolean(
							"Guilds." + guildName + ".Ranks."
									+ args[1].replaceAll("_", " ") + ".Invite")) {
				ItemMeta invite = itmStack0.getItemMeta();
				invite.setDisplayName("Invite");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				invite.setLore(lore);
				itmStack0.setItemMeta(invite);
				itmStack0.setDurability(red.shortValue());
				inv.setItem(0, itmStack0);
			} else {
				ItemMeta invite = itmStack0.getItemMeta();
				invite.setDisplayName("Invite");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				invite.setLore(lore);
				itmStack0.setItemMeta(invite);
				itmStack0.setDurability(green.shortValue());
				inv.setItem(0, itmStack0);
			}

			if (!plugin
					.getRPGValues()
					.getConfigC()
					.getBoolean(
							"Guilds." + guildName + ".Ranks."
									+ args[1].replaceAll("_", " ") + ".Kick")) {
				ItemMeta kick = itmStack1.getItemMeta();
				kick.setDisplayName("Kick");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				kick.setLore(lore);
				itmStack1.setItemMeta(kick);
				itmStack1.setDurability(red.shortValue());
				inv.setItem(1, itmStack1);
			} else {
				ItemMeta kick = itmStack1.getItemMeta();
				kick.setDisplayName("Kick");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				kick.setLore(lore);
				itmStack1.setItemMeta(kick);
				itmStack1.setDurability(green.shortValue());
				inv.setItem(1, itmStack1);
			}

			if (!plugin
					.getRPGValues()
					.getConfigC()
					.getBoolean(
							"Guilds." + guildName + ".Ranks."
									+ args[1].replaceAll("_", " ") + ".Gmotd")) {
				ItemMeta gmotd = itmStack2.getItemMeta();
				gmotd.setDisplayName("Gmotd");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				gmotd.setLore(lore);
				itmStack2.setItemMeta(gmotd);
				itmStack2.setDurability(red.shortValue());
				inv.setItem(2, itmStack2);
			} else {
				ItemMeta gmotd = itmStack2.getItemMeta();
				gmotd.setDisplayName("Gmotd");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				gmotd.setLore(lore);
				itmStack2.setItemMeta(gmotd);
				itmStack2.setDurability(green.shortValue());
				inv.setItem(2, itmStack2);
			}

			if (!plugin
					.getRPGValues()
					.getConfigC()
					.getBoolean(
							"Guilds." + guildName + ".Ranks."
									+ args[1].replaceAll("_", " ") + ".Disband")) {
				ItemMeta disband = itmStack3.getItemMeta();
				disband.setDisplayName("Disband");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				disband.setLore(lore);
				itmStack3.setItemMeta(disband);
				itmStack3.setDurability(red.shortValue());
				inv.setItem(3, itmStack3);
			} else {
				ItemMeta disband = itmStack3.getItemMeta();
				disband.setDisplayName("Disband");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				disband.setLore(lore);
				itmStack3.setItemMeta(disband);
				itmStack3.setDurability(green.shortValue());
				inv.setItem(3, itmStack3);
			}

			if (!plugin
					.getRPGValues()
					.getConfigC()
					.getBoolean(
							"Guilds." + guildName + ".Ranks."
									+ args[1].replaceAll("_", " ") + ".Gchat")) {
				ItemMeta gchat = itmStack4.getItemMeta();
				gchat.setDisplayName("Gchat");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				gchat.setLore(lore);
				itmStack4.setItemMeta(gchat);
				itmStack4.setDurability(red.shortValue());
				inv.setItem(4, itmStack4);
			} else {
				ItemMeta gchat = itmStack4.getItemMeta();
				gchat.setDisplayName("Gchat");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				gchat.setLore(lore);
				itmStack4.setItemMeta(gchat);
				itmStack4.setDurability(green.shortValue());
				inv.setItem(4, itmStack4);
			}

			if (!plugin
					.getRPGValues()
					.getConfigC()
					.getBoolean(
							"Guilds." + guildName + ".Ranks."
									+ args[1].replaceAll("_", " ") + ".Gbank")) {
				ItemMeta gbank = itmStack5.getItemMeta();
				gbank.setDisplayName("Gbank");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				gbank.setLore(lore);
				itmStack5.setItemMeta(gbank);
				itmStack5.setDurability(red.shortValue());
				inv.setItem(5, itmStack5);
			} else {
				ItemMeta gbank = itmStack5.getItemMeta();
				gbank.setDisplayName("Gbank");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				gbank.setLore(lore);
				itmStack5.setItemMeta(gbank);
				itmStack5.setDurability(green.shortValue());
				inv.setItem(5, itmStack5);
			}

			if (!plugin
					.getRPGValues()
					.getConfigC()
					.getBoolean(
							"Guilds." + guildName + ".Ranks."
									+ args[1].replaceAll("_", " ") + ".Addslot")) {
				ItemMeta addslot = itmStack6.getItemMeta();
				addslot.setDisplayName("Addslot");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				addslot.setLore(lore);
				itmStack6.setItemMeta(addslot);
				itmStack6.setDurability(red.shortValue());
				inv.setItem(6, itmStack6);
			} else {
				ItemMeta addslot = itmStack6.getItemMeta();
				addslot.setDisplayName("Addslot");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				addslot.setLore(lore);
				itmStack6.setItemMeta(addslot);
				itmStack6.setDurability(green.shortValue());
				inv.setItem(6, itmStack6);
			}

			if (!plugin
					.getRPGValues()
					.getConfigC()
					.getBoolean(
							"Guilds." + guildName + ".Ranks."
									+ args[1].replaceAll("_", " ")
									+ ".Withdrawl")) {
				ItemMeta withdrawl = itmStack7.getItemMeta();
				withdrawl.setDisplayName("Withdrawl");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				withdrawl.setLore(lore);
				itmStack7.setItemMeta(withdrawl);
				itmStack7.setDurability(red.shortValue());
				inv.setItem(7, itmStack7);
			} else {
				ItemMeta withdrawl = itmStack7.getItemMeta();
				withdrawl.setDisplayName("Withdrawl");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				withdrawl.setLore(lore);
				itmStack7.setItemMeta(withdrawl);
				itmStack7.setDurability(green.shortValue());
				inv.setItem(7, itmStack7);
			}

			if (!plugin
					.getRPGValues()
					.getConfigC()
					.getBoolean(
							"Guilds." + guildName + ".Ranks."
									+ args[1].replaceAll("_", " ") + ".Deposit")) {
				ItemMeta deposit = itmStack8.getItemMeta();
				deposit.setDisplayName("Deposit");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				deposit.setLore(lore);
				itmStack8.setItemMeta(deposit);
				itmStack8.setDurability(red.shortValue());
				inv.setItem(8, itmStack8);
			} else {
				ItemMeta deposit = itmStack8.getItemMeta();
				deposit.setDisplayName("Deposit");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				deposit.setLore(lore);
				itmStack8.setItemMeta(deposit);
				itmStack8.setDurability(green.shortValue());
				inv.setItem(8, itmStack8);
			}

			if (!plugin
					.getRPGValues()
					.getConfigC()
					.getBoolean(
							"Guilds." + guildName + ".Ranks."
									+ args[1].replaceAll("_", " ") + ".GbRanks")) {
				ItemMeta GbRanks = itmStack9.getItemMeta();
				GbRanks.setDisplayName("GbRanks");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				GbRanks.setLore(lore);
				itmStack9.setItemMeta(GbRanks);
				itmStack9.setDurability(red.shortValue());
				inv.setItem(9, itmStack9);
			} else {
				ItemMeta GbRanks = itmStack9.getItemMeta();
				GbRanks.setDisplayName("GbRanks");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				GbRanks.setLore(lore);
				itmStack9.setItemMeta(GbRanks);
				itmStack9.setDurability(green.shortValue());
				inv.setItem(9, itmStack9);
			}

			if (!plugin
					.getRPGValues()
					.getConfigC()
					.getBoolean(
							"Guilds." + guildName + ".Ranks."
									+ args[1].replaceAll("_", " ") + ".RankSet")) {
				ItemMeta Rankset = itmStack10.getItemMeta();
				Rankset.setDisplayName("RankSet");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				Rankset.setLore(lore);
				itmStack10.setItemMeta(Rankset);
				itmStack10.setDurability(red.shortValue());
				inv.setItem(10, itmStack10);
			} else {
				ItemMeta Rankset = itmStack10.getItemMeta();
				Rankset.setDisplayName("RankSet");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				Rankset.setLore(lore);
				itmStack10.setItemMeta(Rankset);
				itmStack10.setDurability(green.shortValue());
				inv.setItem(10, itmStack10);
			}

			if (!plugin
					.getRPGValues()
					.getConfigC()
					.getBoolean(
							"Guilds." + guildName + ".Ranks."
									+ args[1].replaceAll("_", " ")
									+ ".RankTitle")) {
				ItemMeta RankTitle = itmStack11.getItemMeta();
				RankTitle.setDisplayName("RankTitle");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				RankTitle.setLore(lore);
				itmStack11.setItemMeta(RankTitle);
				itmStack11.setDurability(red.shortValue());
				inv.setItem(11, itmStack11);
			} else {
				ItemMeta RankTitle = itmStack11.getItemMeta();
				RankTitle.setDisplayName("RankTitle");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				RankTitle.setLore(lore);
				itmStack11.setItemMeta(RankTitle);
				itmStack11.setDurability(green.shortValue());
				inv.setItem(11, itmStack11);
			}

			if (!plugin
					.getRPGValues()
					.getConfigC()
					.getBoolean(
							"Guilds." + guildName + ".Ranks."
									+ args[1].replaceAll("_", " ")
									+ ".CreateRank")) {
				ItemMeta CreateRank = itmStack12.getItemMeta();
				CreateRank.setDisplayName("CreateRank");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				CreateRank.setLore(lore);
				itmStack12.setItemMeta(CreateRank);
				itmStack12.setDurability(red.shortValue());
				inv.setItem(12, itmStack12);
			} else {
				ItemMeta CreateRank = itmStack12.getItemMeta();
				CreateRank.setDisplayName("CreateRank");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				CreateRank.setLore(lore);
				itmStack12.setItemMeta(CreateRank);
				itmStack12.setDurability(green.shortValue());
				inv.setItem(12, itmStack12);
			}

			if (!plugin
					.getRPGValues()
					.getConfigC()
					.getBoolean(
							"Guilds." + guildName + ".Ranks."
									+ args[1].replaceAll("_", " ")
									+ ".DeleteRank")) {
				ItemMeta DeleteRank = itmStack13.getItemMeta();
				DeleteRank.setDisplayName("DeleteRank");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				DeleteRank.setLore(lore);
				itmStack13.setItemMeta(DeleteRank);
				itmStack13.setDurability(red.shortValue());
				inv.setItem(13, itmStack13);
			} else {
				ItemMeta DeleteRank = itmStack13.getItemMeta();
				DeleteRank.setDisplayName("DeleteRank");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				DeleteRank.setLore(lore);
				itmStack13.setItemMeta(DeleteRank);
				itmStack13.setDurability(green.shortValue());
				inv.setItem(13, itmStack13);
			}

			if (!plugin
					.getRPGValues()
					.getConfigC()
					.getBoolean(
							"Guilds." + guildName + ".Ranks."
									+ args[1].replaceAll("_", " ")
									+ ".PlayerInfo")) {
				ItemMeta PlayerInfo = itmStack14.getItemMeta();
				PlayerInfo.setDisplayName("PlayerInfo");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				PlayerInfo.setLore(lore);
				itmStack14.setItemMeta(PlayerInfo);
				itmStack14.setDurability(red.shortValue());
				inv.setItem(14, itmStack14);
			} else {
				ItemMeta PlayerInfo = itmStack14.getItemMeta();
				PlayerInfo.setDisplayName("PlayerInfo");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				PlayerInfo.setLore(lore);
				itmStack14.setItemMeta(PlayerInfo);
				itmStack14.setDurability(green.shortValue());
				inv.setItem(14, itmStack14);
			}

			if (!plugin
					.getRPGValues()
					.getConfigC()
					.getBoolean(
							"Guilds." + guildName + ".Ranks."
									+ args[1].replaceAll("_", " ")
									+ ".RankPerms")) {
				ItemMeta RankPerms = itmStack15.getItemMeta();
				RankPerms.setDisplayName("RankPerms");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				RankPerms.setLore(lore);
				itmStack15.setItemMeta(RankPerms);
				itmStack15.setDurability(red.shortValue());
				inv.setItem(15, itmStack15);
			} else {
				ItemMeta RankPerms = itmStack15.getItemMeta();
				RankPerms.setDisplayName("RankPerms");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				RankPerms.setLore(lore);
				itmStack15.setItemMeta(RankPerms);
				itmStack15.setDurability(green.shortValue());
				inv.setItem(15, itmStack15);
			}

			if (!plugin
					.getRPGValues()
					.getConfigC()
					.getBoolean(
							"Guilds." + guildName + ".Ranks."
									+ args[1].replaceAll("_", " ") + ".Ochat")) {
				ItemMeta Ochat = itmStack16.getItemMeta();
				Ochat.setDisplayName("Ochat");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				Ochat.setLore(lore);
				itmStack16.setItemMeta(Ochat);
				itmStack16.setDurability(red.shortValue());
				inv.setItem(16, itmStack16);
			} else {
				ItemMeta Ochat = itmStack16.getItemMeta();
				Ochat.setDisplayName("Ochat");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				Ochat.setLore(lore);
				itmStack16.setItemMeta(Ochat);
				itmStack16.setDurability(green.shortValue());
				inv.setItem(16, itmStack16);
			}

			if (!plugin
					.getRPGValues()
					.getConfigC()
					.getBoolean(
							"Guilds." + guildName + ".Ranks."
									+ args[1].replaceAll("_", " ")
									+ ".PlayerNotes")) {
				ItemMeta PlayerNotes = itmStack17.getItemMeta();
				PlayerNotes.setDisplayName("PlayerNotes");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				PlayerNotes.setLore(lore);
				itmStack17.setItemMeta(PlayerNotes);
				itmStack17.setDurability(red.shortValue());
				inv.setItem(17, itmStack17);
			} else {
				ItemMeta PlayerNotes = itmStack17.getItemMeta();
				PlayerNotes.setDisplayName("PlayerNotes");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				PlayerNotes.setLore(lore);
				itmStack17.setItemMeta(PlayerNotes);
				itmStack17.setDurability(green.shortValue());
				inv.setItem(17, itmStack17);
			}

			if (!plugin
					.getRPGValues()
					.getConfigC()
					.getBoolean(
							"Guilds." + guildName + ".Ranks."
									+ args[1].replaceAll("_", " ")
									+ ".PlayerNotesView")) {
				ItemMeta PlayerNotesView = itmStack18.getItemMeta();
				PlayerNotesView.setDisplayName("PlayerNotesView");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				PlayerNotesView.setLore(lore);
				itmStack18.setItemMeta(PlayerNotesView);
				itmStack18.setDurability(red.shortValue());
				inv.setItem(18, itmStack18);
			} else {
				ItemMeta PlayerNotesView = itmStack18.getItemMeta();
				PlayerNotesView.setDisplayName("PlayerNotesView");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				PlayerNotesView.setLore(lore);
				itmStack18.setItemMeta(PlayerNotesView);
				itmStack18.setDurability(green.shortValue());
				inv.setItem(18, itmStack18);
			}

			if (!plugin
					.getRPGValues()
					.getConfigC()
					.getBoolean(
							"Guilds." + guildName + ".Ranks."
									+ args[1].replaceAll("_", " ") + ".TP")) {
				ItemMeta TP = itmStack19.getItemMeta();
				TP.setDisplayName("TP");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("False");
				TP.setLore(lore);
				itmStack19.setItemMeta(TP);
				itmStack19.setDurability(red.shortValue());
				inv.setItem(19, itmStack19);
			} else {
				ItemMeta TP = itmStack19.getItemMeta();
				TP.setDisplayName("TP");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("True");
				TP.setLore(lore);
				itmStack19.setItemMeta(TP);
				itmStack19.setDurability(green.shortValue());
				inv.setItem(19, itmStack19);
			}
			player.openInventory(inv);
			return true;
		}

		return true;
	}
}