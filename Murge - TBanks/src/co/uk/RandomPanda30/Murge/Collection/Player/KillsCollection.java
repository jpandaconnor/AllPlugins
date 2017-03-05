package co.uk.RandomPanda30.Murge.Collection.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Collection.Bases.PlayerBase;
import co.uk.RandomPanda30.Murge.Methods.ConfigMethods;
import co.uk.RandomPanda30.Murge.MySQL.MySQL;
import co.uk.RandomPanda30.Murge.Values.Enums.ConfigValues;

public class KillsCollection implements PlayerBase {

	private static KillsCollection instance = new KillsCollection();

	public static KillsCollection getCollection() {
		return instance;
	}

	MySQL mysql = Murge.getMysql();

	@Override
	public int getValue(UUID uuid) {
		if ((boolean) Murge.cMap.getStat(ConfigValues.MYSQL_ENABLE)) {
			int value = 0;
			ResultSet result;
			try {
				result = mysql.querySQL("SELECT * FROM murge"
						+ " WHERE uuid = '" + uuid.toString() + "'");
				while (result.next()) {
					value = result.getInt("kills");
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			return value;
		}
		return (Integer) Murge.cso.getDataC().get(uuid.toString() + ".kills");
	}

	@Override
	public void addValue(UUID uuid) {
		if ((boolean) Murge.cMap.getStat(ConfigValues.MYSQL_ENABLE)) {
			try {
				mysql.updateSQL("UPDATE murge SET kills = '"
						+ (getValue(uuid) + 1) + "'" + " WHERE uuid = '"
						+ uuid.toString() + "'");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		} else {
			Murge.cso.getDataC().set(uuid.toString() + ".kills",
					getValue(uuid) + 1);
			ConfigMethods.saveData();
		}
	}

	@Override
	public void removeValue(UUID uuid) {
		if ((boolean) Murge.cMap.getStat(ConfigValues.MYSQL_ENABLE)) {
			if (getValue(uuid) != 0) {
				try {
					mysql.updateSQL("UPDATE murge SET kills = '"
							+ (getValue(uuid) - 1) + "'" + " WHERE uuid = '"
							+ uuid.toString() + "'");
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			} else {
				try {
					mysql.updateSQL("UPDATE murge SET kills = '" + 0 + "'"
							+ " WHERE uuid = '" + uuid.toString() + "'");
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			if (getValue(uuid) != 0) {
				Murge.cso.getDataC().set(uuid.toString() + ".kills",
						getValue(uuid) - 1);
			} else {
				Murge.cso.getDataC().set(uuid.toString() + ".kills", 0);
			}
			ConfigMethods.saveData();
		}
	}

	@Override
	public void addValues(UUID uuid, int amount) {
		if ((boolean) Murge.cMap.getStat(ConfigValues.MYSQL_ENABLE)) {
			try {
				mysql.updateSQL("UPDATE murge SET kills = '"
						+ (getValue(uuid) + amount) + "'" + " WHERE uuid = '"
						+ uuid.toString() + "'");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		} else {
			Murge.cso.getDataC().set(uuid.toString() + ".kills",
					getValue(uuid) + amount);
			ConfigMethods.saveData();
		}
	}

	@Override
	public void setValue(UUID uuid, int amount) {
		if ((boolean) Murge.cMap.getStat(ConfigValues.MYSQL_ENABLE)) {
			try {
				mysql.updateSQL("UPDATE murge SET kills = '" + amount + "'"
						+ " WHERE uuid = '" + uuid.toString() + "'");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		} else {
			Murge.cso.getDataC()
					.set(uuid.toString() + ".kills", getValue(uuid));
			ConfigMethods.saveData();
		}
	}

	@Override
	public void removeValues(UUID uuid, int amount) {
		if ((boolean) Murge.cMap.getStat(ConfigValues.MYSQL_ENABLE)) {
			if (getValue(uuid) - amount != 0) {
				try {
					mysql.updateSQL("UPDATE murge SET kills = '"
							+ (getValue(uuid) - amount) + "'"
							+ " WHERE uuid = '" + uuid.toString() + "'");
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			} else {
				try {
					mysql.updateSQL("UPDATE murge SET kills = '" + 0 + "'"
							+ " WHERE uuid = '" + uuid.toString() + "'");
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			if (getValue(uuid) - amount != 0) {
				Murge.cso.getDataC().set(uuid.toString() + ".kills",
						getValue(uuid) - amount);
			} else {
				Murge.cso.getDataC().set(uuid.toString() + ".kills", 0);
			}
			ConfigMethods.saveData();
		}
	}

	@Override
	public void resetValues(UUID uuid) {
		if ((boolean) Murge.cMap.getStat(ConfigValues.MYSQL_ENABLE)) {
			try {
				mysql.updateSQL("UPDATE murge SET kills = '" + 0 + "'"
						+ " WHERE uuid = '" + uuid.toString() + "'");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		} else {
			Murge.cso.getDataC().set(uuid.toString() + ".kills", 0);
			ConfigMethods.saveData();
		}
	}
}
