package co.uk.RandomPanda30.Guilds.Events;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.Guilds.Data;
import co.uk.RandomPanda30.Guilds.Guilds;
import co.uk.RandomPanda30.Guilds.Handlers.General;
import co.uk.RandomPanda30.Guilds.Objects.Rank;
import co.uk.RandomPanda30.Guilds.Util.PermUtil;
import co.uk.RandomPanda30.Guilds.Util.StringUtil;
import co.uk.RandomPanda30.Guilds.Util.Basics.EUtil;
import co.uk.RandomPanda30.Guilds.Util.Basics.EUtil.Cause;

public class OnInventoryClickEvent implements Listener {

	/*
	 * Inventory close
	 */

	Guilds plugin;

	public OnInventoryClickEvent (Guilds plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		ItemStack item = event.getCurrentItem();

		if (item == null) {
			return;
		}

		if (item.getType() == Material.AIR) {
			return;
		}

		if (event.getInventory().getName().equals("Edit permissions")) {

			Rank rank = Data.editing.get(player);
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

			String ranksList = PermUtil.getRanksList(guild);
			String original = "$" + StringUtil.compileRankForReplace(
					rank.getName(), rank.isInvite(), rank.isInviteguild(),
					rank.isKick(), rank.isKickguild(), rank.isGmotd(),
					rank.isGchat(), rank.isRankset(), rank.isRanktitle(),
					rank.isRankcreate(), rank.isRankdelete(),
					rank.isRankperms(), rank.isPlotcreate(),
					rank.isPlotdelete(), rank.isPlotreset(), rank.isPlothome(),
					rank.isWardeclare(), rank.isWarsurrender(), rank.isPvp(),
					rank.getTitle());

			if (event.getInventory().getName().equals("Edit permissions")) {
				event.setCancelled(true);
				String nr = null;
				switch (item.getItemMeta().getDisplayName()) {
				case "Invite":
					if (item.getType().getId() == 5) {
						rank.setInvite(false);
					} else {
						rank.setInvite(true);
					}

					nr = ranksList.replace(original,
							"$" + StringUtil.compileRankForReplace(
									rank.getName(), rank.isInvite(),
									rank.isInviteguild(), rank.isKick(),
									rank.isKickguild(), rank.isGmotd(),
									rank.isGchat(), rank.isRankset(),
									rank.isRanktitle(), rank.isRankcreate(),
									rank.isRankdelete(), rank.isRankperms(),
									rank.isPlotcreate(), rank.isPlotdelete(),
									rank.isPlotreset(), rank.isPlothome(),
									rank.isWardeclare(), rank.isWarsurrender(),
									rank.isPvp(), rank.getTitle()));
					General.closeAndOpen(player, rank);

					try {
						Data.mysql.updateSQL("UPDATE guilds SET ranks = '" + nr
								+ "' WHERE guild = '" + guild + "'");
					} catch (ClassNotFoundException | SQLException e) {
						new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
								"Error whilst updating new ranks list");
					}
					break;
				case "Guild invite":
					if (item.getType().getId() == 5) {
						rank.setInviteguid(false);
					} else {
						rank.setInviteguid(true);
					}

					nr = ranksList.replace(original,
							"$" + StringUtil.compileRankForReplace(
									rank.getName(), rank.isInvite(),
									rank.isInviteguild(), rank.isKick(),
									rank.isKickguild(), rank.isGmotd(),
									rank.isGchat(), rank.isRankset(),
									rank.isRanktitle(), rank.isRankcreate(),
									rank.isRankdelete(), rank.isRankperms(),
									rank.isPlotcreate(), rank.isPlotdelete(),
									rank.isPlotreset(), rank.isPlothome(),
									rank.isWardeclare(), rank.isWarsurrender(),
									rank.isPvp(), rank.getTitle()));
					General.closeAndOpen(player, rank);

					try {
						Data.mysql.updateSQL("UPDATE guilds SET ranks = '" + nr
								+ "' WHERE guild = '" + guild + "'");
					} catch (ClassNotFoundException | SQLException e) {
						new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
								"Error whilst updating new ranks list");
					}
					break;
				case "Kick":
					if (item.getType().getId() == 5) {
						rank.setKick(false);
					} else {
						rank.setKick(true);
					}

					nr = ranksList.replace(original,
							"$" + StringUtil.compileRankForReplace(
									rank.getName(), rank.isInvite(),
									rank.isInviteguild(), rank.isKick(),
									rank.isKickguild(), rank.isGmotd(),
									rank.isGchat(), rank.isRankset(),
									rank.isRanktitle(), rank.isRankcreate(),
									rank.isRankdelete(), rank.isRankperms(),
									rank.isPlotcreate(), rank.isPlotdelete(),
									rank.isPlotreset(), rank.isPlothome(),
									rank.isWardeclare(), rank.isWarsurrender(),
									rank.isPvp(), rank.getTitle()));
					General.closeAndOpen(player, rank);

					try {
						Data.mysql.updateSQL("UPDATE guilds SET ranks = '" + nr
								+ "' WHERE guild = '" + guild + "'");
					} catch (ClassNotFoundException | SQLException e) {
						new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
								"Error whilst updating new ranks list");
					}
					break;
				case "Kick guild":
					if (item.getType().getId() == 5) {
						rank.setKickguild(false);
					} else {
						rank.setKickguild(true);
					}

					nr = ranksList.replace(original,
							"$" + StringUtil.compileRankForReplace(
									rank.getName(), rank.isInvite(),
									rank.isInviteguild(), rank.isKick(),
									rank.isKickguild(), rank.isGmotd(),
									rank.isGchat(), rank.isRankset(),
									rank.isRanktitle(), rank.isRankcreate(),
									rank.isRankdelete(), rank.isRankperms(),
									rank.isPlotcreate(), rank.isPlotdelete(),
									rank.isPlotreset(), rank.isPlothome(),
									rank.isWardeclare(), rank.isWarsurrender(),
									rank.isPvp(), rank.getTitle()));
					General.closeAndOpen(player, rank);

					try {
						Data.mysql.updateSQL("UPDATE guilds SET ranks = '" + nr
								+ "' WHERE guild = '" + guild + "'");
					} catch (ClassNotFoundException | SQLException e) {
						new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
								"Error whilst updating new ranks list");
					}
					break;
				case "GMOTD":
					if (item.getType().getId() == 5) {
						rank.setGmotd(false);
					} else {
						rank.setGmotd(true);
					}

					nr = ranksList.replace(original,
							"$" + StringUtil.compileRankForReplace(
									rank.getName(), rank.isInvite(),
									rank.isInviteguild(), rank.isKick(),
									rank.isKickguild(), rank.isGmotd(),
									rank.isGchat(), rank.isRankset(),
									rank.isRanktitle(), rank.isRankcreate(),
									rank.isRankdelete(), rank.isRankperms(),
									rank.isPlotcreate(), rank.isPlotdelete(),
									rank.isPlotreset(), rank.isPlothome(),
									rank.isWardeclare(), rank.isWarsurrender(),
									rank.isPvp(), rank.getTitle()));
					General.closeAndOpen(player, rank);

					try {
						Data.mysql.updateSQL("UPDATE guilds SET ranks = '" + nr
								+ "' WHERE guild = '" + guild + "'");
					} catch (ClassNotFoundException | SQLException e) {
						new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
								"Error whilst updating new ranks list");
					}
					break;
				case "GChat":
					if (item.getType().getId() == 5) {
						rank.setGchat(false);
					} else {
						rank.setGchat(true);
					}

					nr = ranksList.replace(original,
							"$" + StringUtil.compileRankForReplace(
									rank.getName(), rank.isInvite(),
									rank.isInviteguild(), rank.isKick(),
									rank.isKickguild(), rank.isGmotd(),
									rank.isGchat(), rank.isRankset(),
									rank.isRanktitle(), rank.isRankcreate(),
									rank.isRankdelete(), rank.isRankperms(),
									rank.isPlotcreate(), rank.isPlotdelete(),
									rank.isPlotreset(), rank.isPlothome(),
									rank.isWardeclare(), rank.isWarsurrender(),
									rank.isPvp(), rank.getTitle()));
					General.closeAndOpen(player, rank);

					try {
						Data.mysql.updateSQL("UPDATE guilds SET ranks = '" + nr
								+ "' WHERE guild = '" + guild + "'");
					} catch (ClassNotFoundException | SQLException e) {
						new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
								"Error whilst updating new ranks list");
					}
					break;
				case "Rank set":
					if (item.getType().getId() == 5) {
						rank.setRankset(false);
					} else {
						rank.setRankset(true);
					}

					nr = ranksList.replace(original,
							"$" + StringUtil.compileRankForReplace(
									rank.getName(), rank.isInvite(),
									rank.isInviteguild(), rank.isKick(),
									rank.isKickguild(), rank.isGmotd(),
									rank.isGchat(), rank.isRankset(),
									rank.isRanktitle(), rank.isRankcreate(),
									rank.isRankdelete(), rank.isRankperms(),
									rank.isPlotcreate(), rank.isPlotdelete(),
									rank.isPlotreset(), rank.isPlothome(),
									rank.isWardeclare(), rank.isWarsurrender(),
									rank.isPvp(), rank.getTitle()));
					General.closeAndOpen(player, rank);

					try {
						Data.mysql.updateSQL("UPDATE guilds SET ranks = '" + nr
								+ "' WHERE guild = '" + guild + "'");
					} catch (ClassNotFoundException | SQLException e) {
						new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
								"Error whilst updating new ranks list");
					}
					break;
				case "Rank title":
					if (item.getType().getId() == 5) {
						rank.setRanktitle(false);
					} else {
						rank.setRanktitle(true);
					}

					nr = ranksList.replace(original,
							"$" + StringUtil.compileRankForReplace(
									rank.getName(), rank.isInvite(),
									rank.isInviteguild(), rank.isKick(),
									rank.isKickguild(), rank.isGmotd(),
									rank.isGchat(), rank.isRankset(),
									rank.isRanktitle(), rank.isRankcreate(),
									rank.isRankdelete(), rank.isRankperms(),
									rank.isPlotcreate(), rank.isPlotdelete(),
									rank.isPlotreset(), rank.isPlothome(),
									rank.isWardeclare(), rank.isWarsurrender(),
									rank.isPvp(), rank.getTitle()));
					General.closeAndOpen(player, rank);

					try {
						Data.mysql.updateSQL("UPDATE guilds SET ranks = '" + nr
								+ "' WHERE guild = '" + guild + "'");
					} catch (ClassNotFoundException | SQLException e) {
						new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
								"Error whilst updating new ranks list");
					}
					break;
				case "Rank create":
					if (item.getType().getId() == 5) {
						rank.setRankcreate(false);
					} else {
						rank.setRankcreate(true);
					}

					nr = ranksList.replace(original,
							"$" + StringUtil.compileRankForReplace(
									rank.getName(), rank.isInvite(),
									rank.isInviteguild(), rank.isKick(),
									rank.isKickguild(), rank.isGmotd(),
									rank.isGchat(), rank.isRankset(),
									rank.isRanktitle(), rank.isRankcreate(),
									rank.isRankdelete(), rank.isRankperms(),
									rank.isPlotcreate(), rank.isPlotdelete(),
									rank.isPlotreset(), rank.isPlothome(),
									rank.isWardeclare(), rank.isWarsurrender(),
									rank.isPvp(), rank.getTitle()));
					General.closeAndOpen(player, rank);

					try {
						Data.mysql.updateSQL("UPDATE guilds SET ranks = '" + nr
								+ "' WHERE guild = '" + guild + "'");
					} catch (ClassNotFoundException | SQLException e) {
						new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
								"Error whilst updating new ranks list");
					}
					break;
				case "Rank delete":
					if (item.getType().getId() == 5) {
						rank.setRankdelete(false);
					} else {
						rank.setRankdelete(true);
					}

					nr = ranksList.replace(original,
							"$" + StringUtil.compileRankForReplace(
									rank.getName(), rank.isInvite(),
									rank.isInviteguild(), rank.isKick(),
									rank.isKickguild(), rank.isGmotd(),
									rank.isGchat(), rank.isRankset(),
									rank.isRanktitle(), rank.isRankcreate(),
									rank.isRankdelete(), rank.isRankperms(),
									rank.isPlotcreate(), rank.isPlotdelete(),
									rank.isPlotreset(), rank.isPlothome(),
									rank.isWardeclare(), rank.isWarsurrender(),
									rank.isPvp(), rank.getTitle()));
					General.closeAndOpen(player, rank);

					try {
						Data.mysql.updateSQL("UPDATE guilds SET ranks = '" + nr
								+ "' WHERE guild = '" + guild + "'");
					} catch (ClassNotFoundException | SQLException e) {
						new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
								"Error whilst updating new ranks list");
					}
					break;
				case "Rank perms":
					if (item.getType().getId() == 5) {
						rank.setRankperms(false);
					} else {
						rank.setRankperms(true);
					}

					nr = ranksList.replace(original,
							"$" + StringUtil.compileRankForReplace(
									rank.getName(), rank.isInvite(),
									rank.isInviteguild(), rank.isKick(),
									rank.isKickguild(), rank.isGmotd(),
									rank.isGchat(), rank.isRankset(),
									rank.isRanktitle(), rank.isRankcreate(),
									rank.isRankdelete(), rank.isRankperms(),
									rank.isPlotcreate(), rank.isPlotdelete(),
									rank.isPlotreset(), rank.isPlothome(),
									rank.isWardeclare(), rank.isWarsurrender(),
									rank.isPvp(), rank.getTitle()));
					General.closeAndOpen(player, rank);

					try {
						Data.mysql.updateSQL("UPDATE guilds SET ranks = '" + nr
								+ "' WHERE guild = '" + guild + "'");
					} catch (ClassNotFoundException | SQLException e) {
						new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
								"Error whilst updating new ranks list");
					}
					break;
				case "Plot create":
					if (item.getType().getId() == 5) {
						rank.setPlotcreate(false);
					} else {
						rank.setPlotcreate(true);
					}

					nr = ranksList.replace(original,
							"$" + StringUtil.compileRankForReplace(
									rank.getName(), rank.isInvite(),
									rank.isInviteguild(), rank.isKick(),
									rank.isKickguild(), rank.isGmotd(),
									rank.isGchat(), rank.isRankset(),
									rank.isRanktitle(), rank.isRankcreate(),
									rank.isRankdelete(), rank.isRankperms(),
									rank.isPlotcreate(), rank.isPlotdelete(),
									rank.isPlotreset(), rank.isPlothome(),
									rank.isWardeclare(), rank.isWarsurrender(),
									rank.isPvp(), rank.getTitle()));
					General.closeAndOpen(player, rank);

					try {
						Data.mysql.updateSQL("UPDATE guilds SET ranks = '" + nr
								+ "' WHERE guild = '" + guild + "'");
					} catch (ClassNotFoundException | SQLException e) {
						new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
								"Error whilst updating new ranks list");
					}
					break;
				case "Plot delete":
					if (item.getType().getId() == 5) {
						rank.setPlotdelete(false);
					} else {
						rank.setPlotdelete(true);
					}

					nr = ranksList.replace(original,
							"$" + StringUtil.compileRankForReplace(
									rank.getName(), rank.isInvite(),
									rank.isInviteguild(), rank.isKick(),
									rank.isKickguild(), rank.isGmotd(),
									rank.isGchat(), rank.isRankset(),
									rank.isRanktitle(), rank.isRankcreate(),
									rank.isRankdelete(), rank.isRankperms(),
									rank.isPlotcreate(), rank.isPlotdelete(),
									rank.isPlotreset(), rank.isPlothome(),
									rank.isWardeclare(), rank.isWarsurrender(),
									rank.isPvp(), rank.getTitle()));
					General.closeAndOpen(player, rank);

					try {
						Data.mysql.updateSQL("UPDATE guilds SET ranks = '" + nr
								+ "' WHERE guild = '" + guild + "'");
					} catch (ClassNotFoundException | SQLException e) {
						new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
								"Error whilst updating new ranks list");
					}
					break;
				case "Plot home":
					if (item.getType().getId() == 5) {
						rank.setPlothome(false);
					} else {
						rank.setPlothome(true);
					}

					nr = ranksList.replace(original,
							"$" + StringUtil.compileRankForReplace(
									rank.getName(), rank.isInvite(),
									rank.isInviteguild(), rank.isKick(),
									rank.isKickguild(), rank.isGmotd(),
									rank.isGchat(), rank.isRankset(),
									rank.isRanktitle(), rank.isRankcreate(),
									rank.isRankdelete(), rank.isRankperms(),
									rank.isPlotcreate(), rank.isPlotdelete(),
									rank.isPlotreset(), rank.isPlothome(),
									rank.isWardeclare(), rank.isWarsurrender(),
									rank.isPvp(), rank.getTitle()));
					General.closeAndOpen(player, rank);

					try {
						Data.mysql.updateSQL("UPDATE guilds SET ranks = '" + nr
								+ "' WHERE guild = '" + guild + "'");
					} catch (ClassNotFoundException | SQLException e) {
						new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
								"Error whilst updating new ranks list");
					}
					break;
				case "Plot reset":
					if (item.getType().getId() == 5) {
						rank.setPlotreset(false);
					} else {
						rank.setPlotreset(true);
					}

					nr = ranksList.replace(original,
							"$" + StringUtil.compileRankForReplace(
									rank.getName(), rank.isInvite(),
									rank.isInviteguild(), rank.isKick(),
									rank.isKickguild(), rank.isGmotd(),
									rank.isGchat(), rank.isRankset(),
									rank.isRanktitle(), rank.isRankcreate(),
									rank.isRankdelete(), rank.isRankperms(),
									rank.isPlotcreate(), rank.isPlotdelete(),
									rank.isPlotreset(), rank.isPlothome(),
									rank.isWardeclare(), rank.isWarsurrender(),
									rank.isPvp(), rank.getTitle()));
					General.closeAndOpen(player, rank);

					try {
						Data.mysql.updateSQL("UPDATE guilds SET ranks = '" + nr
								+ "' WHERE guild = '" + guild + "'");
					} catch (ClassNotFoundException | SQLException e) {
						new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
								"Error whilst updating new ranks list");
					}
					break;
				case "War declare":
					if (item.getType().getId() == 5) {
						rank.setWardeclare(false);
					} else {
						rank.setWardeclare(true);
					}

					nr = ranksList.replace(original,
							"$" + StringUtil.compileRankForReplace(
									rank.getName(), rank.isInvite(),
									rank.isInviteguild(), rank.isKick(),
									rank.isKickguild(), rank.isGmotd(),
									rank.isGchat(), rank.isRankset(),
									rank.isRanktitle(), rank.isRankcreate(),
									rank.isRankdelete(), rank.isRankperms(),
									rank.isPlotcreate(), rank.isPlotdelete(),
									rank.isPlotreset(), rank.isPlothome(),
									rank.isWardeclare(), rank.isWarsurrender(),
									rank.isPvp(), rank.getTitle()));
					General.closeAndOpen(player, rank);

					try {
						Data.mysql.updateSQL("UPDATE guilds SET ranks = '" + nr
								+ "' WHERE guild = '" + guild + "'");
					} catch (ClassNotFoundException | SQLException e) {
						new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
								"Error whilst updating new ranks list");
					}
					break;
				case "War surrender":
					if (item.getType().getId() == 5) {
						rank.setWarsurrender(false);
					} else {
						rank.setWarsurrender(true);
					}

					nr = ranksList.replace(original,
							"$" + StringUtil.compileRankForReplace(
									rank.getName(), rank.isInvite(),
									rank.isInviteguild(), rank.isKick(),
									rank.isKickguild(), rank.isGmotd(),
									rank.isGchat(), rank.isRankset(),
									rank.isRanktitle(), rank.isRankcreate(),
									rank.isRankdelete(), rank.isRankperms(),
									rank.isPlotcreate(), rank.isPlotdelete(),
									rank.isPlotreset(), rank.isPlothome(),
									rank.isWardeclare(), rank.isWarsurrender(),
									rank.isPvp(), rank.getTitle()));
					General.closeAndOpen(player, rank);

					try {
						Data.mysql.updateSQL("UPDATE guilds SET ranks = '" + nr
								+ "' WHERE guild = '" + guild + "'");
					} catch (ClassNotFoundException | SQLException e) {
						new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
								"Error whilst updating new ranks list");
					}
					break;
				case "PVP":
					if (item.getType().getId() == 5) {
						rank.setPvp(false);
					} else {
						rank.setPvp(true);
					}

					nr = ranksList.replace(original,
							"$" + StringUtil.compileRankForReplace(
									rank.getName(), rank.isInvite(),
									rank.isInviteguild(), rank.isKick(),
									rank.isKickguild(), rank.isGmotd(),
									rank.isGchat(), rank.isRankset(),
									rank.isRanktitle(), rank.isRankcreate(),
									rank.isRankdelete(), rank.isRankperms(),
									rank.isPlotcreate(), rank.isPlotdelete(),
									rank.isPlotreset(), rank.isPlothome(),
									rank.isWardeclare(), rank.isWarsurrender(),
									rank.isPvp(), rank.getTitle()));
					General.closeAndOpen(player, rank);

					try {
						Data.mysql.updateSQL("UPDATE guilds SET ranks = '" + nr
								+ "' WHERE guild = '" + guild + "'");
					} catch (ClassNotFoundException | SQLException e) {
						new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
								"Error whilst updating new ranks list");
					}
					break;
				}
			}
		}
	}
}