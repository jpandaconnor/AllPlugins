package com.vartala.soulofw0lf.rpgguilds;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.GBlock.Plot.PlotHandler;

public class GuildHandler implements CommandExecutor {

	RpgGuilds Rpgg;

	public static ItemStack[] rewards;
	public static ArrayList<InviteRequests> requests = new ArrayList<InviteRequests>();

	public GuildHandler (RpgGuilds rpgg) {
		this.Rpgg = rpgg;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player = (Player) sender;

		if (args.length == 0) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&BCommand options:"));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&d/guild create <Guild name> <Guild tag> &e- &5Creates a guild"));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&d/guild invite <Player name> &e- &5Invites a player to a guild"));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&d/guild leave - &e- &5Lets you leave your guild"));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&d/guild gmotd <Message of the day> &e- &5Sets the guild MOTD"));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&d/guild kick <Player name> &e- &5Kicks a player from youe guild"));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&d/guild disband &e- &5Disbands your guild"));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&d/guild plot create &e- &5Creates a plot"));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&d/guild plot delete &e- &5Deletes your plot"));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&d/guild create home &e- &5Teleports you to your plot"));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&d/guild plot reset &e- &5Resets your plot"));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&d/guild pvp &e- &5Toggles PVP between guild members"));
			return true;
		}

		if (args[0].equalsIgnoreCase("create")) {
			if (args.length != 3) {
				player.sendMessage("Please use /guild create guildname tag");
				return true;
			}

			if (args[2].length() != 4) {
				player.sendMessage("Guild tags must be 4 letters!");
				return true;
			}

			if (args[1].contains(" ")) {
				player.sendMessage("You cannot have spaces in guild names");
				return true;
			}

			if (this.Rpgg.getConfig().contains("Guilds")) {
				for (String guilds : this.Rpgg.getConfig()
						.getConfigurationSection("Guilds").getKeys(false)) {
					if (args[2].equalsIgnoreCase(this.Rpgg.getConfig()
							.getString("Guilds." + guilds + ".Tag"))) {
						player.sendMessage("This guild tag already exists!");
						return true;
					}
				}
			}

			if (!player.hasPermission("guild.create")) {
				player.sendMessage(
						"You do not have permission to use this command!");
				return true;
			}

			if (this.Rpgg.getConfig()
					.contains(player.getUniqueId().toString())) {
				player.sendMessage(
						"You are already in a guild! you can't create a new one!");
				return true;
			}

			if (this.Rpgg.getConfig().contains(args[1].replaceAll("_", " "))) {
				player.sendMessage("A guild named "
						+ args[1].replaceAll("_", " ") + " already exists!");
				return true;
			}

			this.Rpgg.getConfig()
					.set("Guilds." + args[1].replaceAll("_", " ") + ".Players."
							+ player.getUniqueId().toString() + ".Rank",
					"Leader");
			this.Rpgg.getConfig()
					.set("Guilds." + args[1].replaceAll("_", " ") + ".Players."
							+ player.getUniqueId().toString() + ".Name",
					player.getName());
			this.Rpgg.getConfig().set(
					"Guilds." + args[1].replaceAll("_", " ") + ".Leader",
					player.getUniqueId().toString());
			this.Rpgg.getConfig().set(
					"Guilds." + args[1].replaceAll("_", " ") + ".Tag", args[2]);
			this.Rpgg.getConfig().set(
					player.getUniqueId().toString() + ".Guild.Name",
					args[1].replaceAll("_", " "));
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".DefTerm.Leader", "Leader");
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Leader.Invite", Boolean.valueOf(true));
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Leader.Ochat", Boolean.valueOf(true));
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Leader.Kick", Boolean.valueOf(true));
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Leader.Gmotd", Boolean.valueOf(true));
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Leader.Disband", Boolean.valueOf(true));
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Leader.Gchat", Boolean.valueOf(true));
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Leader.Gbank", Boolean.valueOf(true));
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Leader.Addslot", Boolean.valueOf(true));
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Leader.Withdrawl", Boolean.valueOf(true));
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Leader.Deposit", Boolean.valueOf(true));
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Leader.GbRanks", Boolean.valueOf(true));
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Leader.RankSet", Boolean.valueOf(true));
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Leader.RankTitle", Boolean.valueOf(true));
			this.Rpgg.getConfig()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Leader.CreateRank",
					Boolean.valueOf(true));
			this.Rpgg.getConfig()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Leader.DeleteRank",
					Boolean.valueOf(true));
			this.Rpgg.getConfig()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Leader.PlayerInfo",
					Boolean.valueOf(true));
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Leader.RankPerms", Boolean.valueOf(true));
			this.Rpgg.getConfig()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Leader.PlayerNotes",
					Boolean.valueOf(true));
			this.Rpgg.getConfig()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Leader.PlayerNotesView",
					Boolean.valueOf(true));
			this.Rpgg.getConfig()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Leader.PlayerNotesSet",
					Boolean.valueOf(true));
			this.Rpgg.getConfig()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Leader.InviteGuild",
					Boolean.valueOf(true));

			// 4 permissions here. Create, delete, reset, home

			this.Rpgg.getConfig()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Leader.PlotCreate",
					Boolean.valueOf(true));
			this.Rpgg.getConfig()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Leader.PlotDelete",
					Boolean.valueOf(true));
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Leader.PlotReset", Boolean.valueOf(true));
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Leader.PlotHome", Boolean.valueOf(true));

			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Leader.TP", Boolean.valueOf(true));
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Leader.Title", "&4Guild Master");
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Newbies.Invite", Boolean.valueOf(false));
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Newbies.Ochat", Boolean.valueOf(false));
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Newbies.Kick", Boolean.valueOf(false));
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Newbies.Gmotd", Boolean.valueOf(false));
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Newbies.Disband", Boolean.valueOf(false));
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Newbies.Gchat", Boolean.valueOf(true));
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Newbies.Gbank", Boolean.valueOf(false));
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Newbies.Addslot", Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.InviteGuil",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.Withdrawl",
					Boolean.valueOf(false));
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Newbies.Deposit", Boolean.valueOf(false));
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Newbies.GbRanks", Boolean.valueOf(false));
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Newbies.RankSet", Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.RankTitle",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.CreateRank",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.DeleteRank",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.PlayerInfo",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.RankPerms",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.PlayerNotes",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.PlayerNotesView",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.PlayerNotesSet",
					Boolean.valueOf(false));
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Newbies.TP", Boolean.valueOf(false));
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Newbies.Title", "&2Newbies");
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".DefTerm.Default", "Newbies");

			this.Rpgg.getConfig()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.PlotCreate",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.PlotDelete",
					Boolean.valueOf(false));
			this.Rpgg.getConfig()
					.set("Guilds." + args[1].replaceAll("_", " ")
							+ ".Ranks.Newbies.PlotReset",
					Boolean.valueOf(false));
			this.Rpgg.getConfig().set("Guilds." + args[1].replaceAll("_", " ")
					+ ".Ranks.Newbies.PlotHome", Boolean.valueOf(true));
			this.Rpgg.saveConfig();

			player.sendMessage("Congratulations " + player.getName()
					+ " you are now the leader of the newly created guild "
					+ args[1].replaceAll("_", " "));
			return true;
		}

		if (args[0].equalsIgnoreCase("reward")) {
			if (!player.hasPermission("cts.reward")) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&CYou do not have permission to do this"));
				return true;
			}

			Inventory inventory = Bukkit.createInventory(null, 54,
					"Drag and drop items into here");

			if (rewards != null) {
				inventory.addItem(rewards);
			}

			player.openInventory(inventory);
			return true;
		}

		String grank;
		if (args[0].equalsIgnoreCase("invite")) {
			if (args.length != 2) {
				player.sendMessage(
						"Improper usage of the guild invite command, please just use /guild invite playername");
				return true;
			}

			if (Bukkit.getPlayer(args[1]) == null) {
				player.sendMessage("This player cannot be found");
				return true;
			}

			Player p = Bukkit.getPlayer(args[1]);
			String guildn = this.Rpgg.getConfig()
					.getString(player.getUniqueId().toString() + ".Guild.Name");
			grank = this.Rpgg.getConfig().getString("Guilds." + guildn
					+ ".Players." + player.getUniqueId().toString() + ".Rank");
			if (!this.Rpgg.getConfig()
					.contains(player.getUniqueId().toString())) {
				player.sendMessage(
						"You are not even in a guild? how can you invite someone?");
				return true;
			}

			if (!this.Rpgg.getConfig().getBoolean(
					"Guilds." + guildn + ".Ranks." + grank + ".Invite")) {
				player.sendMessage(
						"You do not have permission to invite people to this guild!");
				return true;
			}

			if (this.Rpgg.getConfig().contains(p.getUniqueId().toString())) {
				player.sendMessage(p.getName() + " is already in a guild!");
				return true;
			}

			if (this.Rpgg.getConfig()
					.contains("Pending." + p.getUniqueId().toString())) {
				player.sendMessage(
						"This player already has a pending guild invite from "
								+ this.Rpgg.getConfig()
										.getString(new StringBuilder("Pending.")
												.append(p.getUniqueId()
														.toString())
												.append(".Guild").toString()));
				return true;
			}

			this.Rpgg.getConfig().set(
					"Pending." + p.getUniqueId().toString() + ".Guild", guildn);
			this.Rpgg.saveConfig();
			player.sendMessage(
					"You have invited " + p.getName() + " to join " + guildn);
			p.sendMessage("You have a pending guild invite from '" + guildn
					+ "' type </guild accept> to join this guild. or </guild deny> to turn it down.");

			new InviteRequests(player.getUniqueId(), p.getUniqueId(), Rpgg);

			return true;
		}

		if (args[0].equalsIgnoreCase("ginvite")) {
			if (args.length != 2) {
				player.sendMessage(
						"Improper usage of the guild invite command, please just use /guild ginvite guild name");
				return true;
			}

			if (!this.Rpgg.getConfig().contains("Guilds." + args[1])) {
				player.sendMessage("A guild with this name cannot be found");
				return true;
			}

			String guildn = this.Rpgg.getConfig()
					.getString(player.getUniqueId().toString() + ".Guild.Name");

			grank = this.Rpgg.getConfig().getString("Guilds." + guildn
					+ ".Players." + player.getUniqueId().toString() + ".Rank");

			if (!this.Rpgg.getConfig()
					.contains(player.getUniqueId().toString())) {
				player.sendMessage(
						"You are not even in a guild? how can you invite someone?");
				return true;
			}

			if (!this.Rpgg.getConfig().getBoolean(
					"Guilds." + guildn + ".Ranks." + grank + ".InviteGuild")) {
				player.sendMessage(
						"You do not have permission to invite people to this guild!");
				return true;
			}

			if (this.Rpgg.getConfig().contains("Pending.Guild" + args[1])) {
				player.sendMessage(
						"This guild already has a pending guild invite");
				return true;
			}

			if (this.Rpgg.getConfig()
					.getStringList("Guilds." + guildn + ".Ally") != null) {
				List<String> allies = this.Rpgg.getConfig()
						.getStringList("Guilds." + guildn + ".Ally");
				if (allies.contains(args[1])) {
					player.sendMessage(
							"You are already allied with this player");
					return true;
				}
			}

			this.Rpgg.getConfig().set("Pending.Guilds." + args[1] + ".Guild",
					guildn);
			this.Rpgg.saveConfig();

			player.sendMessage("You have invited " + args[1] + " to ally");

			for (String key : this.Rpgg.getConfig()
					.getConfigurationSection("Guilds." + args[1] + ".Players")
					.getKeys(false)) {
				if (Bukkit.getPlayer(UUID.fromString(key)) != null) {
					Player fp = Bukkit.getPlayer(UUID.fromString(key));
					fp.sendMessage("Your guild has been to invited to ally with"
							+ guildn + "!");
					fp.sendMessage(
							"Leaders or persons with the appropriate rank can type </guild gaccept> to join or </guild gdeny> to turn it down");
				}
			}
			return true;
		}

		if (args[0].equalsIgnoreCase("accept")) {
			if (!this.Rpgg.getConfig()
					.contains("Pending." + player.getUniqueId().toString())) {
				player.sendMessage(
						"You do not have any pending guild invites!");
				return true;
			}

			String guildn = this.Rpgg.getConfig().getString(
					"Pending." + player.getUniqueId().toString() + ".Guild");
			if (!this.Rpgg.getConfig().contains("Guilds." + guildn)) {
				player.sendMessage("This guild no longer exists!");
				this.Rpgg.getConfig().set(
						"Pending." + player.getUniqueId().toString(), null);
				return true;
			}

			String newbies = this.Rpgg.getConfig()
					.getString("Guilds." + guildn + ".DefTerm.Default");
			this.Rpgg.getConfig()
					.set("Guilds." + guildn + ".Players."
							+ player.getUniqueId().toString() + ".Rank",
					newbies);
			this.Rpgg.getConfig()
					.set("Guilds." + guildn + ".Players."
							+ player.getUniqueId().toString() + ".Name",
					player.getName());
			this.Rpgg.getConfig().set(
					player.getUniqueId().toString() + ".Guild.Name", guildn);
			this.Rpgg.getConfig().set(player.getUniqueId().toString() + ".Name",
					player.getName());
			this.Rpgg.getConfig()
					.set("Pending." + player.getUniqueId().toString(), null);
			this.Rpgg.saveConfig();
			if (this.Rpgg.getConfig().getBoolean("Chat")) {
				player.setDisplayName(ChatColor.translateAlternateColorCodes(
						'&',
						"&F[&2" + guildn + "&f]" + player.getDisplayName()));
			}

			for (String key : this.Rpgg.getConfig()
					.getConfigurationSection("Guilds." + guildn + ".Players")
					.getKeys(false)) {
				if (Bukkit.getPlayer(UUID.fromString(key)) != null) {
					if (Bukkit.getPlayer(UUID.fromString(key)).getUniqueId()
							.equals(player.getUniqueId())) {
						player.sendMessage("You have joined " + guildn + "!");
					} else {
						Player p = Bukkit.getPlayer(UUID.fromString(key));
						p.sendMessage(ChatColor.translateAlternateColorCodes(
								'&', "&3" + player.getName()
										+ "&2 Has Joined The Guild!"));
					}
				}
			}
			return true;
		}

		if (args[0].equalsIgnoreCase("gaccept")) {
			String guildn = this.Rpgg.getConfig()
					.getString(player.getUniqueId().toString() + ".Guild.Name");

			if (!this.Rpgg.getConfig().contains("Pending.Guilds." + guildn)) {
				player.sendMessage(
						"You do not have any pending guild invites!");
				return true;
			}

			String whoInvited = this.Rpgg.getConfig()
					.getString("Pending.Guilds." + guildn + ".Guild");

			if (!this.Rpgg.getConfig().contains("Guilds." + whoInvited)) {
				player.sendMessage("This guild no longer exists!");
				this.Rpgg.getConfig().set("Pending.Guilds" + guildn, null);
				return true;
			}

			// Set for both thingies

			/*
			 * This is for the person who sent the invite
			 */

			List<String> allies = null;
			if (this.Rpgg.getConfig()
					.getStringList("Guilds." + whoInvited + ".Ally") != null) {
				allies = this.Rpgg.getConfig()
						.getStringList("Guilds." + whoInvited + ".Ally");
			} else {
				allies = new ArrayList<String>();
			}

			allies.add(guildn);
			this.Rpgg.getConfig().set("Guilds." + whoInvited + ".Ally", allies);
			this.Rpgg.saveConfig();

			List<String> who = null;
			if (this.Rpgg.getConfig()
					.getStringList("Guilds." + guildn + ".Ally") != null) {
				who = this.Rpgg.getConfig()
						.getStringList("Guilds." + guildn + ".Ally");
			} else {
				who = new ArrayList<String>();
			}

			who.add(whoInvited);
			this.Rpgg.getConfig().set("Guilds." + guildn + ".Ally", who);

			this.Rpgg.getConfig().set("Pending.Guilds." + guildn, null);
			this.Rpgg.saveConfig();

			// Who invited

			for (String key : this.Rpgg.getConfig()
					.getConfigurationSection("Guilds." + guildn + ".Players")
					.getKeys(false)) {
				if (Bukkit.getPlayer(UUID.fromString(key)) != null) {
					Player fP = Bukkit.getPlayer(UUID.fromString(key));
					fP.sendMessage(ChatColor.translateAlternateColorCodes('&',
							"&3You are now allied with &2" + whoInvited));
				}
			}

			for (String key : this.Rpgg.getConfig()
					.getConfigurationSection(
							"Guilds." + whoInvited + ".Players")
					.getKeys(false)) {
				if (Bukkit.getPlayer(UUID.fromString(key)) != null) {
					Player fP = Bukkit.getPlayer(UUID.fromString(key));
					fP.sendMessage(ChatColor.translateAlternateColorCodes('&',
							"&3You are now allied with &2" + guildn));
				}
			}
			return true;
		}

		if (args[0].equalsIgnoreCase("deny")) {
			if (!this.Rpgg.getConfig()
					.contains("Pending." + player.getUniqueId().toString())) {
				player.sendMessage(
						"You do not have any pending guild invites!");
				return true;
			}

			String guildn = this.Rpgg.getConfig().getString(
					"Pending." + player.getUniqueId().toString() + ".Guild");

			if (!this.Rpgg.getConfig().contains("Guilds." + guildn)) {
				player.sendMessage("This guild no longer exists!");
				this.Rpgg.getConfig().set(
						"Pending." + player.getUniqueId().toString(), null);
				return true;
			}

			String lead = this.Rpgg.getConfig()
					.getString("Guilds." + guildn + ".Leader");
			if (Bukkit.getPlayer(UUID.fromString(lead)) != null) {
				Player gleader = Bukkit.getPlayer(UUID.fromString(lead));
				gleader.sendMessage(
						player.getName() + " has declined your guild invite.");
			}

			player.sendMessage("You have declined joining " + guildn + ".");
			this.Rpgg.getConfig()
					.set("Pending." + player.getUniqueId().toString(), null);
			this.Rpgg.saveConfig();
			return true;
		}

		if (args[0].equalsIgnoreCase("gdeny")) {
			String guildn = this.Rpgg.getConfig()
					.getString(player.getUniqueId().toString() + ".Guild.Name");

			if (!this.Rpgg.getConfig().contains("Pending.Guilds." + guildn)) {
				player.sendMessage(
						"You do not have any pending guild invites!");
				return true;
			}

			String whoInvited = this.Rpgg.getConfig()
					.getString("Pending.Guilds." + guildn + ".Guild");

			String lead = this.Rpgg.getConfig()
					.getString("Guilds." + whoInvited + ".Leader");
			if (Bukkit.getPlayer(UUID.fromString(lead)) != null) {
				Player gleader = Bukkit.getPlayer(UUID.fromString(lead));
				gleader.sendMessage(
						guildn + " has declined your guild invite.");
			}

			player.sendMessage("You have declined joining " + whoInvited + ".");
			this.Rpgg.getConfig().set("Pending.Guilds." + guildn, null);
			this.Rpgg.saveConfig();
			return true;
		}

		if (args[0].equalsIgnoreCase("gmotd")) {
			StringBuilder buffer = new StringBuilder();
			for (int i = 1; i < args.length; i++) {
				buffer.append(' ').append(args[i]);
			}
			String s = buffer.toString();
			String guildn = this.Rpgg.getConfig()
					.getString(player.getUniqueId().toString() + ".Guild.Name");
			String grank2 = this.Rpgg.getConfig().getString("Guilds." + guildn
					+ ".Players." + player.getUniqueId().toString() + ".Rank");
			if (!this.Rpgg.getConfig()
					.contains(player.getUniqueId().toString())) {
				player.sendMessage(
						"You are not even in a guild? how can you set the Gmotd?");
				return true;
			}

			if (!this.Rpgg.getConfig().getBoolean(
					"Guilds." + guildn + ".Ranks." + grank2 + ".Gmotd")) {
				player.sendMessage(
						"You do not have permission to set the Gmotd!");
				return true;
			}

			this.Rpgg.getConfig().set("Guilds." + guildn + ".Gmotd", s);
			this.Rpgg.saveConfig();
			player.sendMessage(ChatColor.translateAlternateColorCodes('&',
					"You have saved the gmotd as&2" + s));
			return true;
		}

		Player p;
		if (args[0].equalsIgnoreCase("Kick")) {
			String guildn = this.Rpgg.getConfig()
					.getString(player.getUniqueId().toString() + ".Guild.Name");
			String grank2 = this.Rpgg.getConfig().getString("Guilds." + guildn
					+ ".Players." + player.getUniqueId().toString() + ".Rank");
			if (args.length != 2) {
				player.sendMessage(
						"Please include the name of the person you want to kick.");
				return true;
			}

			if (!this.Rpgg.getConfig()
					.contains(player.getUniqueId().toString())) {
				player.sendMessage(
						"You are not even in a guild? How can you kick someone?");
				return true;
			}

			if (!this.Rpgg.getConfig().getBoolean(
					"Guilds." + guildn + ".Ranks." + grank2 + ".Kick")) {
				player.sendMessage(
						"You do not have permission to kick someone from the guild!");
				return true;
			}

			String fUUID = "";
			String fName = "";

			for (String key : this.Rpgg.getConfig()
					.getConfigurationSection("Guilds." + guildn + ".Players")
					.getKeys(false)) {
				if (this.Rpgg.getConfig().getString(
						"Guilds." + guildn + ".Players." + key + ".Name")
						.equals(args[1])) {
					fName = args[1];
					fUUID = key;
				}
			}

			/*
			 * if (!this.Rpgg.getConfig().contains( "Guilds." + guildn +
			 * ".Players." + Bukkit.getPlayer(args[1]).getUniqueId()
			 * .toString())) {
			 */
			if (fUUID == null) {
				player.sendMessage(
						"This player is not a member of your guild!");
				return true;
			}

			String prank = this.Rpgg.getConfig().getString(
					"Guilds." + guildn + ".Players." + fUUID + ".Rank");
			if (prank.equalsIgnoreCase("Leader")) {
				player.sendMessage("You cannot kick a guild leader!");
				return true;
			}

			this.Rpgg.getConfig().set("Guilds." + guildn + ".Players." + fUUID,
					null);
			this.Rpgg.getConfig().set(fUUID, null);
			if (Bukkit.getPlayer(fUUID) != null) {
				p = Bukkit.getPlayer(fUUID);
				p.sendMessage("You have been removed from " + guildn + ".");
				p.setDisplayName(p.getName());
			}

			player.sendMessage(
					"You have removed " + fName + " from " + guildn + ".");
			this.Rpgg.saveConfig();
			return true;
		}

		if (args[0].equalsIgnoreCase("pvp")) {
			if (!this.Rpgg.getConfig()
					.contains(player.getUniqueId().toString())) {
				player.sendMessage(
						"You are not even in a guild? How can you disband it?");
				return true;
			}

			String guildn = this.Rpgg.getConfig()
					.getString(player.getUniqueId().toString() + ".Guild.Name");
			grank = this.Rpgg.getConfig().getString("Guilds." + guildn
					+ ".Players." + player.getUniqueId().toString() + ".Rank");

			if (!this.Rpgg.getConfig().getBoolean(
					"Guilds." + guildn + ".Ranks." + grank + ".PvpToggle")) {
				player.sendMessage("You do not have permission to toggle PVP!");
				return true;
			}

			boolean a = Rpgg.getConfig().getBoolean("Guilds."
					+ (player.getUniqueId().toString() + ".Guild.") + ".Pvp");

			if (!a) {
				Rpgg.getConfig().set("Guilds." + guildn + ".Pvp", true);
				Rpgg.saveConfig();
			} else {
				Rpgg.getConfig().set("Guilds." + guildn + ".Pvp", false);
				Rpgg.saveConfig();
			}
		}

		if (args[0].equalsIgnoreCase("war")) {
			if (!this.Rpgg.getConfig()
					.contains(player.getUniqueId().toString())) {
				player.sendMessage(
						"You are not even in a guild? How can you declare war on another guild?");
				return true;
			}

			String guildn = this.Rpgg.getConfig()
					.getString(player.getUniqueId().toString() + ".Guild.Name");
			grank = this.Rpgg.getConfig().getString("Guilds." + guildn
					+ ".Players." + player.getUniqueId().toString() + ".Rank");

			if (!this.Rpgg.getConfig().get("Guilds." + guildn + ".Leader")
					.equals(player.getUniqueId().toString())) {
				player.sendMessage(
						"You do not have permission to declare war on this guild!");
				return true;
			}

			
		}

		if (args[0].equalsIgnoreCase("disband")) {
			if (!this.Rpgg.getConfig()
					.contains(player.getUniqueId().toString())) {
				player.sendMessage(
						"You are not even in a guild? How can you disband it?");
				return true;
			}

			String guildn = this.Rpgg.getConfig()
					.getString(player.getUniqueId().toString() + ".Guild.Name");

			try {
				if (!this.Rpgg.getConfig().get("Guilds." + guildn + ".Leader")
						.equals(player.getUniqueId().toString())) {
					player.sendMessage(
							"You do not have permission to disband the guild!");
					return true;
				}

				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&4You Have Disbanded " + guildn + "!"));
				player.setDisplayName(player.getName());

				for (String s : this.Rpgg.getConfig()
						.getConfigurationSection(
								"Guilds." + guildn + ".Players")
						.getKeys(false)) {
					if (this.Rpgg.getConfig().contains(s)) {
						this.Rpgg.getConfig().set(s, null);
						if (Bukkit.getPlayer(UUID.fromString(s)) != null) {
							Player newP = Bukkit.getPlayer(UUID.fromString(s));
							newP.setDisplayName(newP.getName());
						}
						this.Rpgg.saveConfig();
					}
				}

				this.Rpgg.getConfig().set("Guilds." + guildn, null);
				this.Rpgg.getConfig().set(player.getUniqueId().toString(),
						null);
				this.Rpgg.saveConfig();
			} catch (NullPointerException e) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&CSomething went wrong. The guid name is case sensitive; try again"));
			}
			return true;
		}

		if (args[0].equalsIgnoreCase("leave")) {
			if (!this.Rpgg.getConfig()
					.contains(player.getUniqueId().toString())) {
				player.sendMessage(
						"You are not even in a guild? How can you quit?");
				return true;
			}

			String guildn = this.Rpgg.getConfig()
					.getString(player.getUniqueId().toString() + ".Guild.Name");

			if (this.Rpgg.getConfig()
					.getString("Guilds." + guildn + ".Leader") != null) {
				String leader = this.Rpgg.getConfig()
						.getString("Guilds." + guildn + ".Leader");

				if (leader.equals(player.getUniqueId().toString())) {
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&',
							"&CYou cannot use /guild leave as leader. Instead use /guild disband"));
					return true;
				}
			}

			for (String key2 : this.Rpgg.getConfig()
					.getConfigurationSection("Guilds." + guildn + ".Players")
					.getKeys(false)) {
				if (key2.equals(player.getUniqueId().toString())) {
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&', "&4You have left " + guildn + "."));
					player.setDisplayName(player.getName());
				} else {
					if (Bukkit.getPlayer(UUID.fromString(key2)) != null
							&& Bukkit.getPlayer(UUID.fromString(key2))
									.isOnline()) {
						Player p2 = Bukkit.getPlayer(UUID.fromString(key2));
						p2.sendMessage(ChatColor.translateAlternateColorCodes(
								'&', "&3" + player.getName()
										+ "&4 has quit the guild!"));
					}
				}
			}

			this.Rpgg.getConfig().set("Guilds." + guildn + ".Players."
					+ player.getUniqueId().toString(), null);
			this.Rpgg.getConfig().set(player.getUniqueId().toString(), null);
			this.Rpgg.saveConfig();
			return true;
		}

		if (args[0].equalsIgnoreCase("List")) {
			if (args.length != 1) {
				player.sendMessage("Improper usage! Please use /guild list");
				return true;
			}

			String guildn = this.Rpgg.getConfig()
					.getString(player.getUniqueId().toString() + ".Guild.Name");
			if (!this.Rpgg.getConfig()
					.contains(player.getUniqueId().toString())) {
				player.sendMessage(
						"You are not even in a guild? how can you expect to list online members?");
				return true;
			}

			for (String key2 : this.Rpgg.getConfig()
					.getConfigurationSection("Guilds." + guildn + ".Players")
					.getKeys(false)) {
				if (Bukkit.getPlayer(UUID.fromString(key2)) != null
						&& Bukkit.getPlayer(UUID.fromString(key2)).isOnline()) {
					Player p2 = Bukkit.getPlayer(UUID.fromString(key2));
					String rank = this.Rpgg.getConfig()
							.getString("Guilds." + guildn + ".Players."
									+ p2.getUniqueId().toString() + ".Rank");
					String title = this.Rpgg.getConfig().getString(
							"Guilds." + guildn + ".Ranks." + rank + ".Title");
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&', "&F[" + title + "&F]" + " " + p2.getName()
									+ " - Status &2Online\n"));
				} else {
					String rank = this.Rpgg.getConfig().getString(
							"Guilds." + guildn + ".Players." + key2 + ".Rank");
					String title = this.Rpgg.getConfig().getString(
							"Guilds." + guildn + ".Ranks." + rank + ".Title");
					String name = this.Rpgg.getConfig().getString(
							"Guilds." + guildn + ".Players." + key2 + ".Name");
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&', "&F[" + title + "&F]" + " " + name
									+ " - Status &8Offline\n"));
				}
			}
			player.sendMessage("List complete");
			return true;
		}

		if (args[0].equalsIgnoreCase("TP")) {
			if (!player.isOp()) {
				player.sendMessage(
						"Only ops can change the default server teleport behavior!");
				return true;
			}

			if (args.length != 2) {
				player.sendMessage(
						"wrong usage! please use /guild tp on  or guild tp off!");
				return true;
			}

			if (args[1].equalsIgnoreCase("on")) {
				this.Rpgg.getConfig().set("TP", Boolean.valueOf(true));
				player.sendMessage(
						"Teleporting guild members is now allowed on your server!");
				return true;
			}

			if (args[1].equalsIgnoreCase("off")) {
				this.Rpgg.getConfig().set("TP", Boolean.valueOf(false));
				player.sendMessage(
						"Teleporting guild members is no longer allowed on your server!");
				return true;
			}
		}

		if (args[0].equalsIgnoreCase("plot")) {

			if (args.length != 1) {
				// return here blah blah blah
			}

			// Add null guild check here

			if (!this.Rpgg.getConfig()
					.contains(player.getUniqueId().toString())) {
				// must be in guild lol
				return true;
			}

			String guild = this.Rpgg.getConfig()
					.getString(player.getUniqueId().toString() + ".Guild.Name");
			grank = this.Rpgg.getConfig().getString("Guilds." + guild
					+ ".Players." + player.getUniqueId().toString() + ".Rank");

			PlotHandler ph = new PlotHandler(guild);

			// Permissions

			if (args[1].equalsIgnoreCase("create")) {
				if (!this.Rpgg.getConfig().getBoolean("Guilds." + guild
						+ ".Ranks." + grank + ".PlotCreate")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&',
							"&CYou do not have permission to create a plot!"));
					return true;
				}

				if (this.Rpgg.getConfig()
						.contains("Guilds." + guild + ".Plot")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&',
							"&CYou already have a plot. To remove it do /guild plot remove or to reset it, do /guild plot reset"));
					return true;
				}

				ph.createPlot(player);
				return true;
			}

			if (args[1].equalsIgnoreCase("remove")
					|| args[1].equalsIgnoreCase("delete")) {

				if (!this.Rpgg.getConfig().getBoolean("Guilds." + guild
						+ ".Ranks." + grank + ".PlotDelete")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&',
							"&CYou do not have permission to delete a plot!"));
					return true;
				}

				ph.deletePlot(player);
				return true;
			}

			if (args[1].equalsIgnoreCase("reset")) {
				// ph.resetPlot(player);
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&C&lDidn't quite finish this command yet...#BlamePanda"));
				return true;
			}

			if (args[1].equalsIgnoreCase("home")) {

				if (!this.Rpgg.getConfig().getBoolean(
						"Guilds." + guild + ".Ranks." + grank + ".PlotHome")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&',
							"&CYou do not have permission to teleport to your plot!"));
					return true;
				}
				ph.sendHome(player);
			}

			return true;
		}

		return false;
	}
}