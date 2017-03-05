package co.uk.RandomPanda30.Guilds.MySQL;

import java.sql.SQLException;

import co.uk.RandomPanda30.Guilds.Data;
import co.uk.RandomPanda30.Guilds.Config.Config.ConfigValues;
import co.uk.RandomPanda30.Guilds.Util.Basics.EUtil;
import co.uk.RandomPanda30.Guilds.Util.Basics.EUtil.Cause;

public class Tables {

	public enum TableType {
		DATA, GUILDS;
	}

	public static void checkTables() {
		addTable(TableType.DATA);
		addTable(TableType.GUILDS);
	}

	public static void addTable(TableType type) {
		try {
			Data.mysql.querySQL(
					"USE `" + (String) ConfigValues.MYSQL_DBNAME.value + "`;");
		} catch (ClassNotFoundException | SQLException e) {
			new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
					"Error whilst trying to use specified database: '"
							+ (String) ConfigValues.MYSQL_DBNAME.value + "'");
		}
		switch (type) {
		case DATA:
			try {
				Data.mysql.updateSQL(
						"CREATE TABLE IF NOT EXISTS data (uuid VARCHAR(36) PRIMARY KEY, name VARCHAR(36), guild VARCHAR(50), rank VARCHAR(50), allies TEXT, warWith VARCHAR(30));");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
						"Error whilst trying to create " + type.toString()
								+ " table");
			}
			break;
		case GUILDS:
			try {
				Data.mysql.updateSQL(
						"CREATE TABLE IF NOT EXISTS guilds (guild VARCHAR(50), tag VARCHAR(10), leader_uuid VARCHAR(36), leader_name VARCHAR (50), GMOTD TEXT, "
								+ "ranks TEXT, players TEXT, plot_world VARCHAR(50), plot_1_x INTEGER, plot_2_x INTEGER, plot_3_x INTEGER, plot_1_y INTEGER, plot_2_y INTEGER, "
								+ "plot_3_y INTEGER , plot_1_z INTEGER, plot_2_z INTEGER, plot_3_z INTEGER);");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
						"Error whilst trying to create " + type.toString()
								+ " table");
			}
			break;
		}
	}
}