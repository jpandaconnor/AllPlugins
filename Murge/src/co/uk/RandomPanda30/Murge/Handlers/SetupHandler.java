package co.uk.RandomPanda30.Murge.Handlers;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Collection.Special.MySQLCollection;
import co.uk.RandomPanda30.Murge.Displays.Subdisplays.PurgeOptionsMenu;
import co.uk.RandomPanda30.Murge.Methods.StringMethods;
import co.uk.RandomPanda30.Murge.MySQL.MySQL;
import co.uk.RandomPanda30.Murge.Values.Enums.ConfigValues;
import co.uk.RandomPanda30.Murge.Values.Enums.MessagesValues;

public class SetupHandler {

	public enum Stages {
		HOST, DATABASE, USER, PASSWORD, PORT;
	}

	private Stages stage;
	private Player player;

	private String host;
	private String database;
	private String user;
	private String password;
	private int port;

	public SetupHandler (Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}

	public Stages getStage() {
		return stage;
	}

	@SuppressWarnings("unchecked")
	public void setup() {
		MurgeData.setSetup(this);
		stage = Stages.HOST;
		for (String s : (ArrayList<String>) Murge.mMap
				.getStat(MessagesValues.MYSQLSETUP_GENERAL)) {
			StringMethods.sendMessage(s, this.player);
		}
		StringMethods.sendMessage(
				(String) Murge.mMap.getStat(MessagesValues.MYSQLSETUP_HOST),
				getPlayer());
	}

	public void exit() {
		MurgeData.setSetup(null);
		setMySQL();
		StringMethods.sendMessage(
				(String) Murge.mMap.getStat(MessagesValues.MYSQL_CONFIRMED),
				getPlayer());
	}

	public void terminate() {
		MySQLCollection.getCollection().setMySQL(false);
		PurgeOptionsMenu.openPurgeOptionsMenu(player);
	}

	public void next() {
		switch (stage) {
		case HOST:
			stage = Stages.DATABASE;
			break;
		case DATABASE:
			stage = Stages.USER;
			break;
		case USER:
			stage = Stages.PASSWORD;
			break;
		case PASSWORD:
			stage = Stages.PORT;
			break;
		case PORT:
			stage = null;
			break;
		}
	}

	public void save() {
		Murge.cMap.setStat(ConfigValues.MYSQL_HOST, host);
		Murge.cMap.setStat(ConfigValues.MYSQL_DATABASE, database);
		Murge.cMap.setStat(ConfigValues.MYSQL_USER, user);
		Murge.cMap.setStat(ConfigValues.MYSQL_PASSWORD, password);
		Murge.cMap.setStat(ConfigValues.MYSQL_PORT, port);
	}

	public void setMySQL() {
		try {
			Murge.setMysql(new MySQL(MurgeData.getPlugin(), host, database,
					user, EncryptionHandler.getHandle().decrypt(password), port));
			Murge.getMysql().openConnection();
			MySQLCollection.getCollection().setup();
			save();
		} catch (Exception e) {
			StringMethods.sendMessage((String) Murge.mMap
					.getStat(MessagesValues.MYSQL_BADDETAILS), player);
			terminate();
			e.printStackTrace();
		}
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setStage(Stages stage) {
		this.stage = stage;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}