package co.uk.RandomPanda30.Guilds.Handlers;

import co.uk.RandomPanda30.Guilds.Data;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerHandler {

    public static boolean isInGuild(UUID uuid) {
        String guild = "";

        ResultSet result;
        try {
            result = Data.mysql.querySQL("SELECT guild FROM data WHERE uuid = '" + uuid.toString() + "'");
            while(result.next()) {
                guild = result.getString("guild");
            }
        }catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return guild.equals("-");
    }

    public static boolean isInGuild(Player player) {
        return isInGuild(player.getUniqueId());
    }

}