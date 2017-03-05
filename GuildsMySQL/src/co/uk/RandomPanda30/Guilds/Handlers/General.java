package co.uk.RandomPanda30.Guilds.Handlers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import co.uk.RandomPanda30.Guilds.Data;
import co.uk.RandomPanda30.Guilds.Objects.GItem;
import co.uk.RandomPanda30.Guilds.Objects.Rank;
import co.uk.RandomPanda30.Guilds.Util.Basics.EUtil;
import co.uk.RandomPanda30.Guilds.Util.Basics.EUtil.Cause;

public class General {

	public static Inventory permsInv(Player player, Rank rank) {
		Inventory i = Bukkit.createInventory(null, 25, "Edit permissions");
		i.setItem(0, GItem.createItem("Invite", Material.WOOL, 0,
				(rank.isInvite() ? 5 : 14), new ArrayList<String>()));
		i.setItem(1, GItem.createItem("Guild invite", Material.WOOL, 0,
				(rank.isInviteguild() ? 5 : 14), new ArrayList<String>()));
		i.setItem(2, GItem.createItem("Kick", Material.WOOL, 0,
				(rank.isKick() ? 5 : 14), new ArrayList<String>()));
		i.setItem(3, GItem.createItem("Kick guild", Material.WOOL, 0,
				(rank.isKickguild() ? 5 : 14), new ArrayList<String>()));
		i.setItem(4, GItem.createItem("GMOTD", Material.WOOL, 0,
				(rank.isGmotd() ? 5 : 14), new ArrayList<String>()));
		i.setItem(5, GItem.createItem("GChat", Material.WOOL, 0,
				(rank.isGchat() ? 5 : 14), new ArrayList<String>()));
		i.setItem(6, GItem.createItem("Rank set", Material.WOOL, 0,
				(rank.isRankset() ? 5 : 14), new ArrayList<String>()));
		i.setItem(7, GItem.createItem("Rank title", Material.WOOL, 0,
				(rank.isRanktitle() ? 5 : 14), new ArrayList<String>()));
		i.setItem(8, GItem.createItem("Rank create", Material.WOOL, 0,
				(rank.isRankcreate() ? 5 : 14), new ArrayList<String>()));
		i.setItem(9, GItem.createItem("Rank delete", Material.WOOL, 0,
				(rank.isRankdelete() ? 5 : 14), new ArrayList<String>()));
		i.setItem(10, GItem.createItem("Rank perms", Material.WOOL, 0,
				(rank.isRankperms() ? 5 : 14), new ArrayList<String>()));
		i.setItem(11, GItem.createItem("Plot create", Material.WOOL, 0,
				(rank.isPlotcreate() ? 5 : 14), new ArrayList<String>()));
		i.setItem(12, GItem.createItem("Plot delete", Material.WOOL, 0,
				(rank.isPlotdelete() ? 5 : 14), new ArrayList<String>()));
		i.setItem(13, GItem.createItem("Plot home", Material.WOOL, 0,
				(rank.isPlothome() ? 5 : 14), new ArrayList<String>()));
		i.setItem(14, GItem.createItem("Plot reset", Material.WOOL, 0,
				(rank.isPlotreset() ? 5 : 14), new ArrayList<String>()));
		i.setItem(15, GItem.createItem("War declare", Material.WOOL, 0,
				(rank.isWardeclare() ? 5 : 14), new ArrayList<String>()));
		i.setItem(16, GItem.createItem("War surrender", Material.WOOL, 0,
				(rank.isWarsurrender() ? 5 : 14), new ArrayList<String>()));
		i.setItem(17, GItem.createItem("PVP", Material.WOOL, 0,
				(rank.isPvp() ? 5 : 14), new ArrayList<String>()));
		Data.editing.put(player, rank);
		return i;
	}
	
	public static void closeAndOpen(Player player, Rank rank) {
		player.closeInventory();
		Data.editing.put(player, rank);
		player.openInventory(permsInv(player, rank));
	}

	public static void checkSystem(Player player) {
		UUID uuid = player.getUniqueId();
		String check = "SELECT * FROM data WHERE uuid = ?";
		String name = "SELECT name FROM data WHERE uuid = '" + uuid.toString()
				+ "'";
		PreparedStatement pss;

		try {
			pss = Data.mysql.getConnection().prepareStatement(check);
			pss.setString(1, uuid.toString());
			ResultSet rs = pss.executeQuery();

			ArrayList<String> uuids = new ArrayList<>();

			while (rs.next()) {
				uuids.add(rs.getString("uuid"));
			}

			if (!uuids.contains(uuid.toString())) {
				String sql = "INSERT INTO data (uuid, name, guild, rank) VALUES (?, ?, ?, ?)";
				PreparedStatement insert = Data.mysql.getConnection()
						.prepareStatement(sql);
				insert.setString(1, uuid.toString());
				insert.setString(2, player.getName());
				insert.setString(3, "-");
				insert.setString(4, "-");
				insert.executeUpdate();
			}
		} catch (SQLException e) {
			new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
					"Error whilst checking if a player exists in the data table. General class");
		}

		try {
			ResultSet rs = Data.mysql.querySQL(name);
			while (rs.next()) {
				if (!rs.getString("name").equals(player.getName())) {
					Data.mysql.updateSQL("UPDATE data SET name = '"
							+ player.getName() + "' WHERE uuid = '"
							+ uuid.toString() + "'");
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
					"Error updating name in table. OnPlayerJoinEvent");
		}
	}

}