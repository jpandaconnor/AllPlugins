package co.uk.RandomPanda30.Murge.Collection;

import java.sql.SQLException;
import java.util.UUID;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Methods.ConfigMethods;
import co.uk.RandomPanda30.Murge.MySQL.MySQL;

public class DefaultCollection {

	private static DefaultCollection instance = new DefaultCollection();

	public static DefaultCollection getCollection() {
		return instance;
	}

	public void setNewPlayer_FILE(UUID uuid) {
		Murge.cso.getDataC().set(uuid.toString() + ".kills", 0);
		Murge.cso.getDataC().set(uuid.toString() + ".deaths", 0);
		Murge.cso.getDataC().set(uuid.toString() + ".ps", 0);
		Murge.cso.getDataC().set(uuid.toString() + ".money", 0);
		ConfigMethods.saveData();
	}

	public void setNewPlayer_MYSQL(UUID uuid) {
		MySQL mysql = Murge.getMysql();
		try {
			mysql.updateSQL("INSERT INTO murge (uuid) VALUES ('"
					+ uuid.toString() + "');");
			mysql.updateSQL("UPDATE murge SET kills = '" + 0 + "'"
					+ " WHERE uuid = '" + uuid.toString() + "'");
			mysql.updateSQL("UPDATE murge SET money = '" + 0 + "'"
					+ " WHERE uuid = '" + uuid.toString() + "'");
			mysql.updateSQL("UPDATE murge SET deaths = '" + 0 + "'"
					+ " WHERE uuid = '" + uuid.toString() + "'");
			mysql.updateSQL("UPDATE murge SET ps = '" + 0 + "'"
					+ " WHERE uuid = '" + uuid.toString() + "'");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}