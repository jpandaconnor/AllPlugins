package co.uk.RandomPanda30.Murge.Collection.Special;

import java.sql.SQLException;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.MySQL.MySQL;
import co.uk.RandomPanda30.Murge.Values.Enums.ConfigValues;

public class MySQLCollection {

	private static MySQLCollection instance = new MySQLCollection();

	public static MySQLCollection getCollection() {
		return instance;
	}

	public boolean configured() {
		return Murge.cMap.getStat(ConfigValues.MYSQL_PASSWORD) != "change";
	}

	public void setup() {
		MySQL sql = Murge.getMysql();
		try {
			sql.updateSQL("USE `"
					+ Murge.cMap.getStat(ConfigValues.MYSQL_DATABASE) + "`;");
			sql.updateSQL("CREATE TABLE IF NOT EXISTS murge (uuid VARCHAR(36) PRIMARY KEY, kills INT, money INT, deaths INT, ps INT);");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isMySQL() {
		return (Boolean) Murge.cMap.getStat(ConfigValues.MYSQL_ENABLE);
	}

	public void setMySQL(boolean c) {
		Murge.cMap.setStat(ConfigValues.MYSQL_ENABLE, c);
	}
}