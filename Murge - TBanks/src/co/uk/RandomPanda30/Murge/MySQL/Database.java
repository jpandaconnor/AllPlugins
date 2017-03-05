package co.uk.RandomPanda30.Murge.MySQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.plugin.Plugin;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.MurgeData;

public abstract class Database {

	protected Plugin plugin;

	protected Database (Murge plugin) {
		MurgeData.plugin = plugin;
	}

	public abstract Connection openConnection() throws SQLException,
			ClassNotFoundException;

	public abstract boolean checkConnection() throws SQLException;

	public abstract Connection getConnection();

	public abstract boolean closeConnection() throws SQLException;

	public abstract ResultSet querySQL(String query) throws SQLException,
			ClassNotFoundException;

	public abstract int updateSQL(String query) throws SQLException,
			ClassNotFoundException;

}
