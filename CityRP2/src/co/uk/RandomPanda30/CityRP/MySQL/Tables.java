package co.uk.RandomPanda30.CityRP.MySQL;

import java.sql.SQLException;

import co.uk.RandomPanda30.CityRP.CityRPData;
import co.uk.RandomPanda30.CityRP.Configs.Enums.Config;
import co.uk.RandomPanda30.CityRP.Resources.ExceptionUtil;
import co.uk.RandomPanda30.CityRP.Resources.ExceptionUtil.Cause;

public class Tables {

	public enum TableType {
		DATA;
	}

	public static void checkTables() {
		addTable(TableType.DATA);
	}

	public static void addTable(TableType type) {
		try {
			CityRPData.mysql.querySQL(
					"USE `" + (String) Config.MYSQL_DBNAME.value + "`;");
		} catch (ClassNotFoundException | SQLException e) {
			new ExceptionUtil(e.getStackTrace(), Cause.MYSQLERROR,
					"Error whilst trying to use specified database: '"
							+ (String) Config.MYSQL_DBNAME.value + "'");
		}
		switch (type) {
		case DATA:
			try {
				CityRPData.mysql.updateSQL(
						"CREATE TABLE IF NOT EXISTS data (uuid VARCHAR(36) PRIMARY KEY);");
			} catch (ClassNotFoundException | SQLException e) {
				new ExceptionUtil(e.getStackTrace(), Cause.MYSQLERROR,
						"Error whilst trying to create DATA table");
			}
			break;
		}
	}
}