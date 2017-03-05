package co.uk.RandomPanda30.GXP.co.uk.RandomPanda30.MySQL;

import co.uk.RandomPanda30.Guilds.Config.Messages.MessagesValues;
import co.uk.RandomPanda30.Guilds.Guilds;
import co.uk.RandomPanda30.Guilds.Util.Basics.EUtil;
import co.uk.RandomPanda30.Guilds.Util.Basics.EUtil.Cause;
import co.uk.RandomPanda30.Guilds.Util.Basics.MUtil;
import org.bukkit.Bukkit;

import java.sql.*;

public class MySQL extends Database {

	private String HOST;
	private String DB_NAME;
	private String USER;
	private String PASS;
	private int PORT;

	private Connection connection;

	public MySQL (Guilds plugin, String HOST, String DB_NAME, String USER,
			String PASS, int PORT) {
		super(plugin);
		this.HOST = HOST;
		this.PORT = PORT;
		this.DB_NAME = DB_NAME;
		this.USER = USER;
		this.PASS = PASS;
		connection = null;

		try {
			openConnection();
			MUtil.sendMessage((String) MessagesValues.MYSQL_LOADING.value,
					null);
		} catch (ClassNotFoundException | SQLException e) {
			new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
					"Failed to open connection in the constructor");
			MUtil.sendMessage((String) MessagesValues.MYSQL_BADSETTINGS.value,
					null);
			Bukkit.getServer().shutdown();
		}
	}

	@Override
	public Connection openConnection()
			throws SQLException, ClassNotFoundException {
		if (checkConnection()) {
			return connection;
		}

		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(
				"jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME, USER,
				PASS);
		return connection;
	}

	@Override
	public boolean checkConnection() throws SQLException {
		return connection != null && !connection.isClosed();
	}

	@Override
	public Connection getConnection() {
		return connection;
	}

	@Override
	public boolean closeConnection() throws SQLException {
		if (connection == null) {
			return false;
		}

		connection.close();
		return true;
	}

	@Override
	public ResultSet querySQL(String query)
			throws SQLException, ClassNotFoundException {
		if (checkConnection()) {
			openConnection();
		}

		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(query);
		return result;
	}

	@Override
	public int updateSQL(String query)
			throws SQLException, ClassNotFoundException {
		if (checkConnection()) {
			openConnection();
		}

		Statement statement = connection.createStatement();
		int result = statement.executeUpdate(query);
		return result;
	}

	public void setHost(String host) {
		HOST = host;
	}

	public void setDatabaseName(String name) {
		DB_NAME = name;
	}

	public void setUser(String user) {
		USER = user;
	}

	public void setPass(String pass) {
		PASS = pass;
	}

	public void setPort(int port) {
		PORT = port;
	}

	public String getHost() {
		return HOST;
	}

	public String getDatabaseName() {
		return DB_NAME;
	}

	public String getUser() {
		return USER;
	}

	public String getPass() {
		return PASS;
	}

	public int getPort() {
		return PORT;
	}
}