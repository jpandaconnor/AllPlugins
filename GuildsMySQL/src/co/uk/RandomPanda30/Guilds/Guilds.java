package co.uk.RandomPanda30.Guilds;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import co.uk.RandomPanda30.Guilds.Commands.Register;
import co.uk.RandomPanda30.Guilds.Config.Config;
import co.uk.RandomPanda30.Guilds.Config.Config.ConfigValues;
import co.uk.RandomPanda30.Guilds.Config.Messages;
import co.uk.RandomPanda30.Guilds.Config.Messages.MessagesValues;
import co.uk.RandomPanda30.Guilds.MySQL.MySQL;
import co.uk.RandomPanda30.Guilds.MySQL.Tables;
import co.uk.RandomPanda30.Guilds.Plots.Plot;
import co.uk.RandomPanda30.Guilds.Plots.PlotsGenerator;
import co.uk.RandomPanda30.Guilds.Setup.DatabaseSetup;
import co.uk.RandomPanda30.Guilds.Util.EncryptionUtil;
import co.uk.RandomPanda30.Guilds.Util.Basics.EUtil;
import co.uk.RandomPanda30.Guilds.Util.Basics.EUtil.Cause;
import co.uk.RandomPanda30.Guilds.Util.Basics.MUtil;

/*
 * Be sure to make something that will update the players name on change
 * 
 * Clear all invite requestss
 */

@SuppressWarnings("unchecked")
public class Guilds extends JavaPlugin {

	public Guilds plugin;
	public PluginDescriptionFile pdfFile;

	@Override
	public void onEnable() {
		plugin = this;
		pdfFile = this.getDescription();

		new Config(this);
		new Messages(this);

		for (String s : (ArrayList<String>) MessagesValues.START.value) {
			MUtil.sendMessage(s.replace("%no", this.pdfFile.getVersion()),
					null);
		}

		if (!(Boolean) ConfigValues.MYSQL_SET.value) {
			MUtil.sendMessage(
					(String) MessagesValues.NOTIFICATIONS_NOMYSQL.value
							.toString().replace("%time",
									Integer.toString(
											(Integer) ConfigValues.MYSQL_SETUPDELAY.value)),
					null);
			getServer().getScheduler().scheduleSyncDelayedTask(this,
					new Runnable() {
						@Override
						public void run() {
							new DatabaseSetup(plugin);
						}
					}, new Long((Integer) ConfigValues.MYSQL_SETUPDELAY.value));
		} else {
			EncryptionUtil eu = new EncryptionUtil(
					(String) ConfigValues.MYSQL_DBPASS.value);
			try {
				Data.mysql = new MySQL(this,
						(String) ConfigValues.MYSQL_DBIP.value,
						(String) ConfigValues.MYSQL_DBNAME.value,
						(String) ConfigValues.MYSQL_DBUSER.value, eu.decrypt(),
						(Integer) ConfigValues.MYSQL_DBPORT.value);
				Tables.checkTables();
			} catch (InvalidKeyException | NoSuchPaddingException
					| NoSuchAlgorithmException | BadPaddingException
					| IllegalBlockSizeException e) {
				new EUtil(e.getStackTrace(), Cause.ENCRYPTIONERROR,
						"Failed to decrypt loading in MySQL variable");
			}
		}

		for (Player player : Bukkit.getOnlinePlayers()) {
			UUID uuid = player.getUniqueId();

			String check = "SELECT * FROM data WHERE uuid = ?";
			String name = "SELECT name FROM data WHERE uuid = '"
					+ uuid.toString() + "'";

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
					String sql = "INSERT INTO data (uuid, name, guild, rank, pendingI) VALUES (?, ?, ?, ?, ?)";
					PreparedStatement insert = Data.mysql.getConnection()
							.prepareStatement(sql);
					insert.setString(1, uuid.toString());
					insert.setString(2, player.getName());
					insert.setString(3, "-");
					insert.setString(4, "-");
					insert.setString(5, "");
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

		new Register(this);
		Plot.load();
	}

	@Override
	public void onDisable() {
		for (String s : (ArrayList<String>) MessagesValues.STOP.value) {
			MUtil.sendMessage(s.replace("%no", this.pdfFile.getVersion()),
					null);
		}

		plugin = null;
	}
	
	public ChunkGenerator getDefaultWorldGenerator(String worldName,
			String id) {
		return new PlotsGenerator();
	}
}