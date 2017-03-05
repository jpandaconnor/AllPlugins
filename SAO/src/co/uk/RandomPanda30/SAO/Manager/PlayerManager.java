package co.uk.RandomPanda30.SAO.Manager;

import co.uk.RandomPanda30.SAO.SAO;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerManager {

    public static boolean isInSystem(Player player) {
        return isInSystem(player.getUniqueId());
    }

    public static boolean isInSystem(UUID uuid) {
        boolean isIn = false;
        try {
            ResultSet rs = SAO.mysql.querySQL("SELECT * FROM playerdata WHERE uuid = '" + uuid.toString() + "'");
            while (rs.next()) {
                isIn = true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return isIn;
    }

    public static boolean isInSystem(String name) {
        boolean isIn = false;
        try {
            ResultSet rs = SAO.mysql.querySQL("SELECT * FROM playerdata WHERE name = '" + name + "'");
            while (rs.next()) {
                isIn = true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return isIn;
    }

    public static int getLevel(String name) {
        int i = 0;
        if (isInSystem(name)) {
            i = DataManager.getInt("sao", "sao_level", "name", name);
        }
        return i;
    }

    public static int getLevel(UUID uuid) {
        int i = 0;
        if (isInSystem(uuid)) {
            i = DataManager.getInt("sao", "sao_level", "uuid", uuid.toString());
        }
        return i;
    }

    public static void addNewPlayer(Player player) {
        String sql = "INSERT INTO playerdata (uuid, name, sao_exp, sao_level) VALUES (?, ?, ?, ?)";
        PreparedStatement insert = null;
        try {
            insert = SAO.mysql.getConnection().prepareStatement(sql);
            insert.setString(1, player.getUniqueId().toString());
            insert.setString(2, player.getName());
            insert.setInt(3, 0);
            insert.setInt(4, 0);
            insert.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addNewOfflinePlayer(String uuid) {
        String sql = "INSERT INTO playerdata (uuid, name, sao_exp, sao_level) VALUES (?, ?, ?, ?)";
        PreparedStatement insert = null;
        try {
            insert = SAO.mysql.getConnection().prepareStatement(sql);
            insert.setString(1, uuid);
            insert.setString(2, null);
            insert.setInt(3, 0);
            insert.setInt(4, 0);
            insert.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updatePlayerName(Player player) {
        updatePlayerName(player.getUniqueId());
    }

    public static void updatePlayerName(UUID uuid) {
        // Get old name from database
        // Insert into name changes table
        // Insert new into name changes table + playerdata table
        // done
    }

}