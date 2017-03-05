package co.uk.RandomPanda30.SAO.Manager;

import co.uk.RandomPanda30.SAO.SAO;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.UUID;

public class KickManager {

    String time = "";

    public KickManager() {
        SimpleDateFormat f = new SimpleDateFormat("dd-MMM-yyyy-HH:mm:ss-z");
        f.setTimeZone(TimeZone.getTimeZone("Europe/London"));
        time = f.format(GregorianCalendar.getInstance().getTime());
    }

    public void kick(Player player, UUID buuid, String reason) {
        UUID uuid = player.getUniqueId();

        int kicks = DataManager.getInt("playerdata", "kicks", "uuid", uuid.toString());
        kicks = kicks + 1;

        SAO.mysql.updateTable("playerdata", "kicks", "uuid", uuid.toString(), kicks);

        try {
            SAO.mysql.updateSQL("INSERT INTO kicks (date_time, uuid, name, kuuid, kname, kickamount, reason) VALUES " +
                    "('" + time + "', '" + uuid.toString() + "', '" + player.getName() + "', '" + (buuid == null ? "CONSOLE" : uuid.toString()) + "', '" +
                    (buuid == null ? "CONSOLE" : Bukkit.getPlayer(buuid).getName()) + "', " + kicks + ", '" + reason + "'");
        } catch (SQLException | ClassNotFoundException e) {
            // e.printStackTrace();
        }
    }

}
