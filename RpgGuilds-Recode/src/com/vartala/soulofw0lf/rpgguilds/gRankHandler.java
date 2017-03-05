package com.vartala.soulofw0lf.rpgguilds;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class gRankHandler implements CommandExecutor {

	RpgGuilds Rpgg;

	public gRankHandler (RpgGuilds rpgg) {
		this.Rpgg = rpgg;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (args.length <= 0) {
			return false;
		}

		if (args[0].equalsIgnoreCase("create")) {
			Player player = (Player) sender;
			if (args.length != 3) {
				player.sendMessage(
						"Improper usage! Please use /grank create rank title");
				return true;
			}

			String guildn = this.Rpgg.getConfig()
					.getString(player.getUniqueId().toString() + ".Guild.Name");
			String grank = this.Rpgg.getConfig().getString("Guilds." + guildn
					+ ".Players." + player.getUniqueId().toString() + ".Rank");
			if (!this.Rpgg.getConfig()
					.contains(player.getUniqueId().toString())) {
				player.sendMessage(
						"You are not even in a guild? how can you expect to create a rank one?");
				return true;
			}

			if (!this.Rpgg.getConfig().getBoolean(
					"Guilds." + guildn + ".Ranks." + grank + ".CreateRank")) {
				player.sendMessage(
						"You do not have permission to create a rank in this guild!");
				return true;
			}

			if (this.Rpgg.getConfig().contains("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " "))) {
				player.sendMessage("This rank already exists!");
				return true;
			}

			this.Rpgg.getConfig()
					.set("Guilds." + guildn + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".Title",
					args[2].replaceAll("_", " "));
			this.Rpgg.getConfig()
					.set("Guilds." + guildn + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".InviteGuild",
					args[2].replaceAll("_", " "));
			this.Rpgg.getConfig()
					.set("Guilds." + guildn + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".Invite",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + guildn + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".Kick",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + guildn + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".Gmotd",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + guildn + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".Disband",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + guildn + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".Gchat",
					Boolean.valueOf(true));
			this.Rpgg.getConfig()
					.set("Guilds." + guildn + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".Gbank",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + guildn + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".Addslot",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + guildn + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".Withdrawl",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + guildn + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".Deposit",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + guildn + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".GbRanks",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + guildn + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".RankSet",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + guildn + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".RankTitle",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + guildn + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".CreateRank",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + guildn + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".DeleteRank",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + guildn + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".PlayerInfo",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + guildn + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".RankPerms",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + guildn + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".Ochat",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + guildn + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".PlayerNotes",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + guildn + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".PlayerNotesView",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + guildn + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".PlayerNotesSet",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + guildn + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".TP",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + guildn + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".PlotCreate",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + guildn + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".PlotHome",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + guildn + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".PlotDelete",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + guildn + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".PlotReset",
					Boolean.valueOf(true));
			this.Rpgg.saveConfig();
			player.sendMessage("You have successfully created the rank "
					+ args[1].replaceAll("_", " ") + "!");
			return true;
		}

		Player p;
		if (args[0].equalsIgnoreCase("delete")) {
			Player player = (Player) sender;
			if (args.length <= 1) {
				player.sendMessage("You must include a rank to delete!");
				return true;
			}

			String guildn = this.Rpgg.getConfig()
					.getString(player.getUniqueId().toString() + ".Guild.Name");
			String grank = this.Rpgg.getConfig().getString("Guilds." + guildn
					+ ".Players." + player.getUniqueId().toString() + ".Rank");
			if (!this.Rpgg.getConfig()
					.contains(player.getUniqueId().toString())) {
				player.sendMessage(
						"You are not even in a guild? how can you expect to delete a rank?");
				return true;
			}

			if (!this.Rpgg.getConfig().getBoolean(
					"Guilds." + guildn + ".Ranks." + grank + ".DeleteRank")) {
				player.sendMessage(
						"You do not have permission to delete a rank in this guild!");
				return true;
			}

			if (!this.Rpgg.getConfig().contains("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " "))) {
				player.sendMessage("This rank doesn't exist!");
				return true;
			}

			String newbies = this.Rpgg.getConfig()
					.getString("Guilds." + guildn + ".DefTerm.Default");
			for (String key : this.Rpgg.getConfig()
					.getConfigurationSection("Guilds." + guildn + ".Players")
					.getKeys(false)) {
				String rank = this.Rpgg.getConfig().getString(
						"Guilds." + guildn + ".Players." + key + ".Rank");
				if (args[1].replaceAll("_", " ").equalsIgnoreCase(rank)) {
					this.Rpgg.getConfig().set(
							"Guilds." + guildn + ".Players." + key + ".Rank",
							newbies);
					if (Bukkit.getPlayer(key) != null) {
						p = Bukkit.getPlayer(key);
						p.sendMessage("&3 Your rank has been changed to "
								+ newbies + "!");
					}
				}
			}

			this.Rpgg.getConfig().set("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " "), null);
			this.Rpgg.saveConfig();
			player.sendMessage("You have successfully deleted the rank "
					+ args[1].replaceAll("_", " ") + "!");
			return true;
		}

		if (args[0].equalsIgnoreCase("set")) {
			Player player = (Player) sender;
			if (args.length != 3) {
				player.sendMessage(
						"Improper usage! Please use /grank set playername rank");
				return true;
			}

			String guildn = this.Rpgg.getConfig()
					.getString(player.getUniqueId().toString() + ".Guild.Name");
			String grank = this.Rpgg.getConfig().getString("Guilds." + guildn
					+ ".Players." + player.getUniqueId().toString() + ".Rank");

			String uuid = null;
			for (String s : this.Rpgg.getConfig()
					.getConfigurationSection("Guilds." + guildn + ".Players")
					.getKeys(false)) {
				if (this.Rpgg.getConfig()
						.getString(
								"Guilds." + guildn + ".Players." + s + ".Name")
						.equals(args[1])) {
					uuid = s;
				}
			}

			String prank = this.Rpgg.getConfig().getString(
					"Guilds." + guildn + ".Players." + uuid + ".Rank");
			if (!this.Rpgg.getConfig()
					.contains(player.getUniqueId().toString())) {
				player.sendMessage(
						"You are not even in a guild? how can you expect to delete a rank?");
				return true;
			}

			if (!this.Rpgg.getConfig().getBoolean(
					"Guilds." + guildn + ".Ranks." + grank + ".RankSet")) {
				player.sendMessage(
						"You do not have permission to set a players rank in this guild!");
				return true;
			}

			if (!this.Rpgg.getConfig().contains("Guilds." + guildn + ".Ranks."
					+ args[2].replaceAll("_", " "))) {
				player.sendMessage("This rank doesn't exist!");
				return true;
			}

			for (String s : this.Rpgg.getConfig()
					.getConfigurationSection("Guilds." + guildn + ".Players")
					.getKeys(false)) {
				if (!this.Rpgg.getConfig().contains(
						"Guilds." + guildn + ".Players." + s + ".Name")) {
					player.sendMessage(
							"That player is not a part of your guild!");
					return true;
				}
			}

			String leader = this.Rpgg.getConfig()
					.getString("Guilds." + guildn + ".DefTerm.Leader");
			String newbie = this.Rpgg.getConfig()
					.getString("Guilds." + guildn + ".DefTerm.Default");
			if ((args[2].replaceAll("_", " ").equalsIgnoreCase(leader))
					&& (!grank.equalsIgnoreCase(leader))) {
				player.sendMessage(
						"You can not promote someone to Guild Leader this way!!");
				return true;
			}

			if ((args[2].replaceAll("_", " ").equalsIgnoreCase(leader))
					&& (grank.equalsIgnoreCase(leader))) {
				this.Rpgg.getConfig().set(
						"Guilds." + guildn + ".Players." + uuid + ".Rank",
						leader);
				this.Rpgg.getConfig()
						.set("Guilds." + guildn + ".Players."
								+ player.getUniqueId().toString() + ".Rank",
						newbie);
				this.Rpgg.getConfig().set("Guilds." + guildn + ".Leader",
						args[1]);
				this.Rpgg.saveConfig();
				player.sendMessage(
						"You are no longer the leader of " + guildn + ".");
				for (String key : this.Rpgg.getConfig()
						.getConfigurationSection(
								"Guilds." + guildn + ".Players")
						.getKeys(false)) {
					if (Bukkit.getPlayer(key) != null) {
						Player p2 = Bukkit.getPlayer(key);
						if (!p2.getName().equalsIgnoreCase(args[1])) {
							p2.sendMessage(args[1] + " is the new Leader of "
									+ guildn + ".");
						} else {
							p2.sendMessage("You are the new Guild leader of "
									+ guildn + "!");
						}
					}
				}
				return true;
			}

			if (prank.equalsIgnoreCase(grank)) {
				player.sendMessage(
						"You can not change someone's rank if their rank is the same as yours!");
				return true;
			}

			if (args[2].replaceAll("_", " ").equalsIgnoreCase(grank)) {
				player.sendMessage(
						"You can not set someone to the same rank as yours!");
				return true;
			}

			if (prank.equalsIgnoreCase(leader)) {
				player.sendMessage(
						"You cannot change a guild leaders rank in this fashion!");
				return true;
			}

			for (String key : this.Rpgg.getConfig()
					.getConfigurationSection("Guilds." + guildn + ".Players")
					.getKeys(false)) {
				if (Bukkit.getPlayer(key) != null) {
					Player p2 = Bukkit.getPlayer(key);
					if (!p2.getName().equalsIgnoreCase(args[1])) {
						p2.sendMessage(
								args[1] + " has been changed to the rank "
										+ args[2].replaceAll("_", " ") + ".");
					} else {
						p2.sendMessage("You have been moved to the rank "
								+ args[2].replaceAll("_", " ") + ".");
					}
				}
			}

			this.Rpgg.getConfig().set(
					"Guilds." + guildn + ".Players." + uuid + ".Rank",
					args[2].replaceAll("_", " "));

			this.Rpgg.saveConfig();
			return true;
		}

		if (args[0].equalsIgnoreCase("title")) {
			Player player = (Player) sender;
			if (args.length != 3) {
				player.sendMessage(
						"Improper usage! Please use /grank title rankname newtitle");
				return true;
			}

			String guildn = this.Rpgg.getConfig()
					.getString(player.getUniqueId().toString() + ".Guild.Name");
			String grank = this.Rpgg.getConfig().getString("Guilds." + guildn
					+ ".Players." + player.getUniqueId().toString() + ".Rank");
			if (!this.Rpgg.getConfig()
					.contains(player.getUniqueId().toString())) {
				player.sendMessage(
						"You are not even in a guild? how can you expect to delete a rank?");
				return true;
			}

			if (!this.Rpgg.getConfig().getBoolean(
					"Guilds." + guildn + ".Ranks." + grank + ".RankTitle")) {
				player.sendMessage(
						"You do not have permission to set a rank's Title in this guild!");
				return true;
			}
			if (!this.Rpgg.getConfig().contains("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " "))) {
				player.sendMessage("This rank doesn't exist!");
				return true;
			}

			this.Rpgg.getConfig()
					.set("Guilds." + guildn + ".Ranks."
							+ args[1].replaceAll("_", " ") + ".Title",
					args[2].replaceAll("_", " "));
			this.Rpgg.saveConfig();
			player.sendMessage("You have changed "
					+ args[1].replaceAll("_", " ") + "'s title to "
					+ args[2].replaceAll("_", " ") + ".");
			return true;
		}

		if (args[0].equalsIgnoreCase("perms")) {
			Player player = (Player) sender;
			if (args.length != 4) {
				player.sendMessage(
						"Improper usage! Please use /grank perms rankname permission_name true/false");
				return true;
			}

			String guildn = this.Rpgg.getConfig()
					.getString(player.getUniqueId().toString() + ".Guild.Name");
			String grank = this.Rpgg.getConfig().getString("Guilds." + guildn
					+ ".Players." + player.getUniqueId().toString() + ".Rank");
			if (!this.Rpgg.getConfig()
					.contains(player.getUniqueId().toString())) {
				player.sendMessage(
						"You are not even in a guild? how can you expect to delete a rank?");
				return true;
			}

			if (!this.Rpgg.getConfig().getBoolean(
					"Guilds." + guildn + ".Ranks." + grank + ".RankPerms")) {
				player.sendMessage(
						"You do not have permission to set a rank's permissions in this guild!");
				return true;
			}

			if (!this.Rpgg.getConfig().contains("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " "))) {
				player.sendMessage("This rank doesn't exist!");
				return true;
			}

			String leader = this.Rpgg.getConfig()
					.getString("Guild." + guildn + ".DefTerm.Leader");
			if (args[1].replaceAll("_", " ").equalsIgnoreCase(leader)) {
				player.sendMessage(
						"You can not change a guild leaders permissions!");
				return true;
			}

			if (args[3].equalsIgnoreCase("true")) {
				Boolean permbool = Boolean.valueOf(true);
				this.Rpgg.getConfig()
						.set("Guilds." + guildn + ".Ranks."
								+ args[1].replaceAll("_", " ") + "."
								+ args[2].replaceAll("_", " "), permbool);
				this.Rpgg.saveConfig();
				player.sendMessage("Members with the rank "
						+ args[1].replaceAll("_", " ") + " now have  "
						+ args[2].replaceAll("_", " ") + " permissions.");
				return true;
			}

			Boolean permbool = Boolean.valueOf(false);
			this.Rpgg.getConfig()
					.set("Guilds." + guildn + ".Ranks."
							+ args[1].replaceAll("_", " ") + "."
							+ args[2].replaceAll("_", " "), permbool);
			this.Rpgg.saveConfig();
			player.sendMessage("Members with the rank "
					+ args[1].replaceAll("_", " ") + " no longer have  "
					+ args[2].replaceAll("_", " ") + " permissions.");
			return true;
		}

		if (args[0].equalsIgnoreCase("list")) {
			Player player = (Player) sender;
			player.sendMessage(
					"The available rank permissions are \nInvite\nKick\nGmotd\nDisband\nGchat\nGbank\nAddslot\nWithdrawl\nDeposit\nGbRanks\nRankSet\nRankTitle\nCreateRank\nDeleteRank\nPlayerInfo\nRankPerms\nOchat\nPlayerNotesView\nPlayerNotesSet\nTitle");
			return true;
		}

		if (args[0].equalsIgnoreCase("defaults")) {
			Player player = (Player) sender;

			String guildn = this.Rpgg.getConfig()
					.getString(player.getUniqueId().toString() + ".Guild.Name");
			String grank = this.Rpgg.getConfig().getString("Guilds." + guildn
					+ ".Players." + player.getUniqueId().toString() + ".Rank");
			String leader = this.Rpgg.getConfig()
					.getString("Guilds." + guildn + ".DefTerm.Leader");
			if (!grank.equalsIgnoreCase(leader)) {
				player.sendMessage(
						"Only a guild leader can change the default rank names!");
				return true;
			}

			if (args[1].equalsIgnoreCase("leader")) {
				this.Rpgg.getConfig().set(
						"Guilds." + guildn + ".DefTerm.Leader",
						args[2].replaceAll("_", " "));
				this.Rpgg.saveConfig();
				player.sendMessage("You have changed the leader rank name to "
						+ args[2].replaceAll("_", " ") + ".");
				return true;
			}

			if (args[1].equalsIgnoreCase("newbies")) {
				this.Rpgg.getConfig().set(
						"Guilds." + guildn + ".DefTerm.Default",
						args[2].replaceAll("_", " "));
				this.Rpgg.saveConfig();
				player.sendMessage("You have changed the default rank name to "
						+ args[2].replaceAll("_", " ") + ".");
				return true;
			}

			player.sendMessage(
					"Improper usage! Please use /grank defaults {leader/newbies} new_rank_name");
			return true;
		}

		if (args[0].equalsIgnoreCase("edit")) {
			Player player = (Player) sender;
			if (args.length != 2) {
				player.sendMessage("You must specify a rank to edit!");
				return true;
			}

			String guildn = this.Rpgg.getConfig()
					.getString(player.getUniqueId().toString() + ".Guild.Name");
			String grank = this.Rpgg.getConfig().getString("Guilds." + guildn
					+ ".Players." + player.getUniqueId().toString() + ".Rank");
			String leader = this.Rpgg.getConfig()
					.getString("Guilds." + guildn + ".DefTerm.Leader");
			if (!this.Rpgg.getConfig().getBoolean(
					"Guilds." + guildn + ".Ranks." + grank + ".RankPerms")) {
				player.sendMessage(
						"You do not have permission to set a rank's permissions in this guild!");
				return true;
			}

			if (!this.Rpgg.getConfig().contains("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " "))) {
				player.sendMessage("This rank doesn't exist!");
				return true;
			}

			if (grank.equalsIgnoreCase(args[1].replaceAll("_", " "))) {
				player.sendMessage(
						"You cannot change permission for your own rank!");
				return true;
			}

			if (leader.equalsIgnoreCase(args[1].replaceAll("_", " "))) {
				player.sendMessage(
						"A guild leaders default permissions cannot be changed!!");
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
			ItemStack itmStack20 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack21 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack22 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack23 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack24 = new ItemStack(Material.WOOL, 1);
			ItemStack itmStack25 = new ItemStack(Material.WOOL, 1);
			Short green = Short.valueOf((short) 5);
			Short red = Short.valueOf((short) 14);

			if (!this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " ") + ".Invite")) {
				ItemMeta invite = itmStack0.getItemMeta();
				invite.setDisplayName("Invite");
				ArrayList<String> lore = new ArrayList();
				lore.add("False");
				invite.setLore(lore);
				itmStack0.setItemMeta(invite);
				itmStack0.setDurability(red.shortValue());
				inv.setItem(0, itmStack0);
			} else {
				ItemMeta invite = itmStack0.getItemMeta();
				invite.setDisplayName("Invite");
				ArrayList<String> lore = new ArrayList();
				lore.add("True");
				invite.setLore(lore);
				itmStack0.setItemMeta(invite);
				itmStack0.setDurability(green.shortValue());
				inv.setItem(0, itmStack0);
			}

			if (!this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " ") + ".Kick")) {
				ItemMeta kick = itmStack1.getItemMeta();
				kick.setDisplayName("Kick");
				ArrayList<String> lore = new ArrayList();
				lore.add("False");
				kick.setLore(lore);
				itmStack1.setItemMeta(kick);
				itmStack1.setDurability(red.shortValue());
				inv.setItem(1, itmStack1);
			} else {
				ItemMeta kick = itmStack1.getItemMeta();
				kick.setDisplayName("Kick");
				ArrayList<String> lore = new ArrayList();
				lore.add("True");
				kick.setLore(lore);
				itmStack1.setItemMeta(kick);
				itmStack1.setDurability(green.shortValue());
				inv.setItem(1, itmStack1);
			}

			if (!this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " ") + ".Gmotd")) {
				ItemMeta gmotd = itmStack2.getItemMeta();
				gmotd.setDisplayName("Gmotd");
				ArrayList<String> lore = new ArrayList();
				lore.add("False");
				gmotd.setLore(lore);
				itmStack2.setItemMeta(gmotd);
				itmStack2.setDurability(red.shortValue());
				inv.setItem(2, itmStack2);
			} else {
				ItemMeta gmotd = itmStack2.getItemMeta();
				gmotd.setDisplayName("Gmotd");
				ArrayList<String> lore = new ArrayList();
				lore.add("True");
				gmotd.setLore(lore);
				itmStack2.setItemMeta(gmotd);
				itmStack2.setDurability(green.shortValue());
				inv.setItem(2, itmStack2);
			}

			if (!this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " ") + ".Disband")) {
				ItemMeta disband = itmStack3.getItemMeta();
				disband.setDisplayName("Disband");
				ArrayList<String> lore = new ArrayList();
				lore.add("False");
				disband.setLore(lore);
				itmStack3.setItemMeta(disband);
				itmStack3.setDurability(red.shortValue());
				inv.setItem(3, itmStack3);
			} else {
				ItemMeta disband = itmStack3.getItemMeta();
				disband.setDisplayName("Disband");
				ArrayList<String> lore = new ArrayList();
				lore.add("True");
				disband.setLore(lore);
				itmStack3.setItemMeta(disband);
				itmStack3.setDurability(green.shortValue());
				inv.setItem(3, itmStack3);
			}

			if (!this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " ") + ".Gchat")) {
				ItemMeta gchat = itmStack4.getItemMeta();
				gchat.setDisplayName("Gchat");
				ArrayList<String> lore = new ArrayList();
				lore.add("False");
				gchat.setLore(lore);
				itmStack4.setItemMeta(gchat);
				itmStack4.setDurability(red.shortValue());
				inv.setItem(4, itmStack4);
			} else {
				ItemMeta gchat = itmStack4.getItemMeta();
				gchat.setDisplayName("Gchat");
				ArrayList<String> lore = new ArrayList();
				lore.add("True");
				gchat.setLore(lore);
				itmStack4.setItemMeta(gchat);
				itmStack4.setDurability(green.shortValue());
				inv.setItem(4, itmStack4);
			}

			if (!this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " ") + ".Gbank")) {
				ItemMeta gbank = itmStack5.getItemMeta();
				gbank.setDisplayName("Gbank");
				ArrayList<String> lore = new ArrayList();
				lore.add("False");
				gbank.setLore(lore);
				itmStack5.setItemMeta(gbank);
				itmStack5.setDurability(red.shortValue());
				inv.setItem(5, itmStack5);
			} else {
				ItemMeta gbank = itmStack5.getItemMeta();
				gbank.setDisplayName("Gbank");
				ArrayList<String> lore = new ArrayList();
				lore.add("True");
				gbank.setLore(lore);
				itmStack5.setItemMeta(gbank);
				itmStack5.setDurability(green.shortValue());
				inv.setItem(5, itmStack5);
			}

			if (!this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " ") + ".Addslot")) {
				ItemMeta addslot = itmStack6.getItemMeta();
				addslot.setDisplayName("Addslot");
				ArrayList<String> lore = new ArrayList();
				lore.add("False");
				addslot.setLore(lore);
				itmStack6.setItemMeta(addslot);
				itmStack6.setDurability(red.shortValue());
				inv.setItem(6, itmStack6);
			} else {
				ItemMeta addslot = itmStack6.getItemMeta();
				addslot.setDisplayName("Addslot");
				ArrayList<String> lore = new ArrayList();
				lore.add("True");
				addslot.setLore(lore);
				itmStack6.setItemMeta(addslot);
				itmStack6.setDurability(green.shortValue());
				inv.setItem(6, itmStack6);
			}

			if (!this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " ") + ".Withdrawl")) {
				ItemMeta withdrawl = itmStack7.getItemMeta();
				withdrawl.setDisplayName("Withdrawl");
				ArrayList<String> lore = new ArrayList();
				lore.add("False");
				withdrawl.setLore(lore);
				itmStack7.setItemMeta(withdrawl);
				itmStack7.setDurability(red.shortValue());
				inv.setItem(7, itmStack7);
			} else {
				ItemMeta withdrawl = itmStack7.getItemMeta();
				withdrawl.setDisplayName("Withdrawl");
				ArrayList<String> lore = new ArrayList();
				lore.add("True");
				withdrawl.setLore(lore);
				itmStack7.setItemMeta(withdrawl);
				itmStack7.setDurability(green.shortValue());
				inv.setItem(7, itmStack7);
			}

			if (!this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " ") + ".Deposit")) {
				ItemMeta deposit = itmStack8.getItemMeta();
				deposit.setDisplayName("Deposit");
				ArrayList<String> lore = new ArrayList();
				lore.add("False");
				deposit.setLore(lore);
				itmStack8.setItemMeta(deposit);
				itmStack8.setDurability(red.shortValue());
				inv.setItem(8, itmStack8);
			} else {
				ItemMeta deposit = itmStack8.getItemMeta();
				deposit.setDisplayName("Deposit");
				ArrayList<String> lore = new ArrayList();
				lore.add("True");
				deposit.setLore(lore);
				itmStack8.setItemMeta(deposit);
				itmStack8.setDurability(green.shortValue());
				inv.setItem(8, itmStack8);
			}

			if (!this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " ") + ".GbRanks")) {
				ItemMeta GbRanks = itmStack9.getItemMeta();
				GbRanks.setDisplayName("GbRanks");
				ArrayList<String> lore = new ArrayList();
				lore.add("False");
				GbRanks.setLore(lore);
				itmStack9.setItemMeta(GbRanks);
				itmStack9.setDurability(red.shortValue());
				inv.setItem(9, itmStack9);
			} else {
				ItemMeta GbRanks = itmStack9.getItemMeta();
				GbRanks.setDisplayName("GbRanks");
				ArrayList<String> lore = new ArrayList();
				lore.add("True");
				GbRanks.setLore(lore);
				itmStack9.setItemMeta(GbRanks);
				itmStack9.setDurability(green.shortValue());
				inv.setItem(9, itmStack9);
			}

			if (!this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " ") + ".RankSet")) {
				ItemMeta Rankset = itmStack10.getItemMeta();
				Rankset.setDisplayName("RankSet");
				ArrayList<String> lore = new ArrayList();
				lore.add("False");
				Rankset.setLore(lore);
				itmStack10.setItemMeta(Rankset);
				itmStack10.setDurability(red.shortValue());
				inv.setItem(10, itmStack10);
			} else {
				ItemMeta Rankset = itmStack10.getItemMeta();
				Rankset.setDisplayName("RankSet");
				ArrayList<String> lore = new ArrayList();
				lore.add("True");
				Rankset.setLore(lore);
				itmStack10.setItemMeta(Rankset);
				itmStack10.setDurability(green.shortValue());
				inv.setItem(10, itmStack10);
			}

			if (!this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " ") + ".RankTitle")) {
				ItemMeta RankTitle = itmStack11.getItemMeta();
				RankTitle.setDisplayName("RankTitle");
				ArrayList<String> lore = new ArrayList();
				lore.add("False");
				RankTitle.setLore(lore);
				itmStack11.setItemMeta(RankTitle);
				itmStack11.setDurability(red.shortValue());
				inv.setItem(11, itmStack11);
			} else {
				ItemMeta RankTitle = itmStack11.getItemMeta();
				RankTitle.setDisplayName("RankTitle");
				ArrayList<String> lore = new ArrayList();
				lore.add("True");
				RankTitle.setLore(lore);
				itmStack11.setItemMeta(RankTitle);
				itmStack11.setDurability(green.shortValue());
				inv.setItem(11, itmStack11);
			}

			if (!this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " ") + ".CreateRank")) {
				ItemMeta CreateRank = itmStack12.getItemMeta();
				CreateRank.setDisplayName("CreateRank");
				ArrayList<String> lore = new ArrayList();
				lore.add("False");
				CreateRank.setLore(lore);
				itmStack12.setItemMeta(CreateRank);
				itmStack12.setDurability(red.shortValue());
				inv.setItem(12, itmStack12);
			} else {
				ItemMeta CreateRank = itmStack12.getItemMeta();
				CreateRank.setDisplayName("CreateRank");
				ArrayList<String> lore = new ArrayList();
				lore.add("True");
				CreateRank.setLore(lore);
				itmStack12.setItemMeta(CreateRank);
				itmStack12.setDurability(green.shortValue());
				inv.setItem(12, itmStack12);
			}

			if (!this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " ") + ".DeleteRank")) {
				ItemMeta DeleteRank = itmStack13.getItemMeta();
				DeleteRank.setDisplayName("DeleteRank");
				ArrayList<String> lore = new ArrayList();
				lore.add("False");
				DeleteRank.setLore(lore);
				itmStack13.setItemMeta(DeleteRank);
				itmStack13.setDurability(red.shortValue());
				inv.setItem(13, itmStack13);
			} else {
				ItemMeta DeleteRank = itmStack13.getItemMeta();
				DeleteRank.setDisplayName("DeleteRank");
				ArrayList<String> lore = new ArrayList();
				lore.add("True");
				DeleteRank.setLore(lore);
				itmStack13.setItemMeta(DeleteRank);
				itmStack13.setDurability(green.shortValue());
				inv.setItem(13, itmStack13);
			}

			if (!this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " ") + ".PlayerInfo")) {
				ItemMeta PlayerInfo = itmStack14.getItemMeta();
				PlayerInfo.setDisplayName("PlayerInfo");
				ArrayList<String> lore = new ArrayList();
				lore.add("False");
				PlayerInfo.setLore(lore);
				itmStack14.setItemMeta(PlayerInfo);
				itmStack14.setDurability(red.shortValue());
				inv.setItem(14, itmStack14);
			} else {
				ItemMeta PlayerInfo = itmStack14.getItemMeta();
				PlayerInfo.setDisplayName("PlayerInfo");
				ArrayList<String> lore = new ArrayList();
				lore.add("True");
				PlayerInfo.setLore(lore);
				itmStack14.setItemMeta(PlayerInfo);
				itmStack14.setDurability(green.shortValue());
				inv.setItem(14, itmStack14);
			}

			if (!this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " ") + ".RankPerms")) {
				ItemMeta RankPerms = itmStack15.getItemMeta();
				RankPerms.setDisplayName("RankPerms");
				ArrayList<String> lore = new ArrayList();
				lore.add("False");
				RankPerms.setLore(lore);
				itmStack15.setItemMeta(RankPerms);
				itmStack15.setDurability(red.shortValue());
				inv.setItem(15, itmStack15);
			} else {
				ItemMeta RankPerms = itmStack15.getItemMeta();
				RankPerms.setDisplayName("RankPerms");
				ArrayList<String> lore = new ArrayList();
				lore.add("True");
				RankPerms.setLore(lore);
				itmStack15.setItemMeta(RankPerms);
				itmStack15.setDurability(green.shortValue());
				inv.setItem(15, itmStack15);
			}

			if (!this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " ") + ".Ochat")) {
				ItemMeta Ochat = itmStack16.getItemMeta();
				Ochat.setDisplayName("Ochat");
				ArrayList<String> lore = new ArrayList();
				lore.add("False");
				Ochat.setLore(lore);
				itmStack16.setItemMeta(Ochat);
				itmStack16.setDurability(red.shortValue());
				inv.setItem(16, itmStack16);
			} else {
				ItemMeta Ochat = itmStack16.getItemMeta();
				Ochat.setDisplayName("Ochat");
				ArrayList<String> lore = new ArrayList();
				lore.add("True");
				Ochat.setLore(lore);
				itmStack16.setItemMeta(Ochat);
				itmStack16.setDurability(green.shortValue());
				inv.setItem(16, itmStack16);
			}

			if (!this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " ") + ".PlayerNotes")) {
				ItemMeta PlayerNotes = itmStack17.getItemMeta();
				PlayerNotes.setDisplayName("PlayerNotes");
				ArrayList<String> lore = new ArrayList();
				lore.add("False");
				PlayerNotes.setLore(lore);
				itmStack17.setItemMeta(PlayerNotes);
				itmStack17.setDurability(red.shortValue());
				inv.setItem(17, itmStack17);
			} else {
				ItemMeta PlayerNotes = itmStack17.getItemMeta();
				PlayerNotes.setDisplayName("PlayerNotes");
				ArrayList<String> lore = new ArrayList();
				lore.add("True");
				PlayerNotes.setLore(lore);
				itmStack17.setItemMeta(PlayerNotes);
				itmStack17.setDurability(green.shortValue());
				inv.setItem(17, itmStack17);
			}

			if (!this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " ") + ".PlayerNotesView")) {
				ItemMeta PlayerNotesView = itmStack18.getItemMeta();
				PlayerNotesView.setDisplayName("PlayerNotesView");
				ArrayList<String> lore = new ArrayList();
				lore.add("False");
				PlayerNotesView.setLore(lore);
				itmStack18.setItemMeta(PlayerNotesView);
				itmStack18.setDurability(red.shortValue());
				inv.setItem(18, itmStack18);
			} else {
				ItemMeta PlayerNotesView = itmStack18.getItemMeta();
				PlayerNotesView.setDisplayName("PlayerNotesView");
				ArrayList<String> lore = new ArrayList();
				lore.add("True");
				PlayerNotesView.setLore(lore);
				itmStack18.setItemMeta(PlayerNotesView);
				itmStack18.setDurability(green.shortValue());
				inv.setItem(18, itmStack18);
			}

			if (!this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " ") + ".TP")) {
				ItemMeta TP = itmStack19.getItemMeta();
				TP.setDisplayName("TP");
				ArrayList<String> lore = new ArrayList();
				lore.add("False");
				TP.setLore(lore);
				itmStack19.setItemMeta(TP);
				itmStack19.setDurability(red.shortValue());
				inv.setItem(19, itmStack19);
			} else {
				ItemMeta TP = itmStack19.getItemMeta();
				TP.setDisplayName("TP");
				ArrayList<String> lore = new ArrayList();
				lore.add("True");
				TP.setLore(lore);
				itmStack19.setItemMeta(TP);
				itmStack19.setDurability(green.shortValue());
				inv.setItem(19, itmStack19);
			}

			if (!this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " ") + ".InviteGuild")) {
				ItemMeta TP = itmStack20.getItemMeta();
				TP.setDisplayName("InviteGuild");
				ArrayList<String> lore = new ArrayList();
				lore.add("False");
				TP.setLore(lore);
				itmStack20.setItemMeta(TP);
				itmStack20.setDurability(red.shortValue());
				inv.setItem(20, itmStack20);
			} else {
				ItemMeta TP = itmStack20.getItemMeta();
				TP.setDisplayName("InviteGuild");
				ArrayList<String> lore = new ArrayList();
				lore.add("True");
				TP.setLore(lore);
				itmStack20.setItemMeta(TP);
				itmStack20.setDurability(green.shortValue());
				inv.setItem(20, itmStack20);
			}

			if (!this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " ") + ".PlotCreate")) {
				ItemMeta TP = itmStack21.getItemMeta();
				TP.setDisplayName("PlotCreate");
				ArrayList<String> lore = new ArrayList();
				lore.add("False");
				TP.setLore(lore);
				itmStack21.setItemMeta(TP);
				itmStack21.setDurability(red.shortValue());
				inv.setItem(21, itmStack21);
			} else {
				ItemMeta TP = itmStack21.getItemMeta();
				TP.setDisplayName("PlotCreate");
				ArrayList<String> lore = new ArrayList();
				lore.add("True");
				TP.setLore(lore);
				itmStack21.setItemMeta(TP);
				itmStack21.setDurability(green.shortValue());
				inv.setItem(21, itmStack21);
			}

			if (!this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " ") + ".PlotDelete")) {
				ItemMeta TP = itmStack22.getItemMeta();
				TP.setDisplayName("PlotDelete");
				ArrayList<String> lore = new ArrayList();
				lore.add("False");
				TP.setLore(lore);
				itmStack22.setItemMeta(TP);
				itmStack22.setDurability(red.shortValue());
				inv.setItem(22, itmStack22);
			} else {
				ItemMeta TP = itmStack22.getItemMeta();
				TP.setDisplayName("PlotDelete");
				ArrayList<String> lore = new ArrayList();
				lore.add("True");
				TP.setLore(lore);
				itmStack22.setItemMeta(TP);
				itmStack22.setDurability(green.shortValue());
				inv.setItem(22, itmStack22);
			}

			if (!this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " ") + ".PlotReset")) {
				ItemMeta TP = itmStack23.getItemMeta();
				TP.setDisplayName("PlotReset");
				ArrayList<String> lore = new ArrayList();
				lore.add("False");
				TP.setLore(lore);
				itmStack23.setItemMeta(TP);
				itmStack23.setDurability(red.shortValue());
				inv.setItem(23, itmStack23);
			} else {
				ItemMeta TP = itmStack23.getItemMeta();
				TP.setDisplayName("PlotReset");
				ArrayList<String> lore = new ArrayList();
				lore.add("True");
				TP.setLore(lore);
				itmStack23.setItemMeta(TP);
				itmStack23.setDurability(green.shortValue());
				inv.setItem(23, itmStack23);
			}

			if (!this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " ") + ".PlotHome")) {
				ItemMeta TP = itmStack24.getItemMeta();
				TP.setDisplayName("PlotHome");
				ArrayList<String> lore = new ArrayList();
				lore.add("False");
				TP.setLore(lore);
				itmStack24.setItemMeta(TP);
				itmStack24.setDurability(red.shortValue());
				inv.setItem(24, itmStack24);
			} else {
				ItemMeta TP = itmStack24.getItemMeta();
				TP.setDisplayName("PlotHome");
				ArrayList<String> lore = new ArrayList();
				lore.add("True");
				TP.setLore(lore);
				itmStack24.setItemMeta(TP);
				itmStack24.setDurability(green.shortValue());
				inv.setItem(24, itmStack24);
			}

			if (!this.Rpgg.getConfig().getBoolean("Guilds." + guildn + ".Ranks."
					+ args[1].replaceAll("_", " ") + ".PvpToggle")) {
				ItemMeta TP = itmStack25.getItemMeta();
				TP.setDisplayName("PvpToggle");
				ArrayList<String> lore = new ArrayList();
				lore.add("False");
				TP.setLore(lore);
				itmStack25.setItemMeta(TP);
				itmStack25.setDurability(red.shortValue());
				inv.setItem(24, itmStack25);
			} else {
				ItemMeta TP = itmStack25.getItemMeta();
				TP.setDisplayName("PvpToggle");
				ArrayList<String> lore = new ArrayList();
				lore.add("True");
				TP.setLore(lore);
				itmStack25.setItemMeta(TP);
				itmStack25.setDurability(green.shortValue());
				inv.setItem(24, itmStack25);
			}

			player.openInventory(inv);
			return true;
		}
		return true;
	}
}