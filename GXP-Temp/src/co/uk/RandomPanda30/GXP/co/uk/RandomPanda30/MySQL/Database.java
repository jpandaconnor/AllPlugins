package co.uk.RandomPanda30.GXP.co.uk.RandomPanda30.MySQL;

import co.uk.RandomPanda30.Guilds.Guilds;
import org.bukkit.plugin.Plugin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Database {

	protected Plugin plugin;

	protected Database (Guilds plugin) {
		this.plugin = plugin;
	}

	public abstract Connection openConnection()
			throws SQLException, ClassNotFoundException;

	public abstract boolean checkConnection() throws SQLException;

	public abstract Connection getConnection();

	public abstract boolean closeConnection() throws SQLException;

	public abstract ResultSet querySQL(String query)
			throws SQLException, ClassNotFoundException;

	public abstract int updateSQL(String query)
			throws SQLException, ClassNotFoundException;
}