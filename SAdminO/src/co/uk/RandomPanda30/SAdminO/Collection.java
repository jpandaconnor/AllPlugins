package co.uk.RandomPanda30.SAdminO;

import co.uk.RandomPanda30.SAdminO.Files.Config.ConfigValues;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class Collection {

	/*
     * General mysql queries here
	 */

    public boolean isInSystem(UUID uuid) {
        boolean t = true;
        ArrayList<String> uuids = new ArrayList<String>();
        ResultSet ret;
        try {
            ret = Data.mysql.querySQL("SELECT uuid FROM " + (String) ConfigValues.MYSQL_DBNAME.value + ".data");
            while (ret.next()) {
                uuids.add(ret.getString("uuid"));
            }

            if (!uuids.contains(uuid.toString())) {
                t = false;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    public boolean isInSystem(Player player) {
        boolean t = true;
        ArrayList<String> uuids = new ArrayList<String>();
        ResultSet ret;
        try {
            ret = Data.mysql.querySQL("SELECT uuid FROM " + (String) ConfigValues.MYSQL_DBNAME.value + ".data");
            while (ret.next()) {
                uuids.add(ret.getString("uuid"));
            }

            if (!uuids.contains(player.getUniqueId().toString())) {
                t = false;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    public void addToSystem(UUID uuid) {

    }

    public void addToSystem(Player player) {

    }

	/*
	 * All ban things here
	 */

    public boolean isBanned() {
        return true;
    }

    public void ban(UUID uuid) {

    }

    public void unban(UUID uuid) {

    }

}