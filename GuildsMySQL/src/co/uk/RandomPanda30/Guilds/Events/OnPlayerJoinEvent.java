package co.uk.RandomPanda30.Guilds.Events;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import co.uk.RandomPanda30.Guilds.Data;
import co.uk.RandomPanda30.Guilds.Guilds;
import co.uk.RandomPanda30.Guilds.Util.Basics.EUtil;
import co.uk.RandomPanda30.Guilds.Util.Basics.EUtil.Cause;

public class OnPlayerJoinEvent implements Listener {

	public Guilds plugin;

	public OnPlayerJoinEvent (Guilds plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		UUID uuid = player.getUniqueId();

		String check = "SELECT * FROM data WHERE uuid = ?";
		String name = "SELECT name FROM data WHERE uuid = '" + uuid.toString()
				+ "'";

		PreparedStatement ps;

		try {
			ps = Data.mysql.getConnection().prepareStatement(check);
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();

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
					"Error whilst checking if a player exists in the data table. OnPlayerJoinEvent class");
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