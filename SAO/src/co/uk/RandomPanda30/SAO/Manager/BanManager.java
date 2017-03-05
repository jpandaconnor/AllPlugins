package co.uk.RandomPanda30.SAO.Manager;

import co.uk.RandomPanda30.SAO.SAO;
import org.bukkit.Bukkit;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.UUID;

public class BanManager {

    private String time = "";

    public BanManager() {
        SimpleDateFormat f = new SimpleDateFormat("dd-MMM-yyyy-HH:mm:ss-z");
        f.setTimeZone(TimeZone.getTimeZone("Europe/London"));
        time = f.format(GregorianCalendar.getInstance().getTime());
    }

    public void banPlayer(UUID uuid, String name, UUID buuid, long length, String reason) {
        SAO.mysql.updateTable("playerdata", "banned", "uuid", uuid.toString(), 1);

        if (length == 0) {
            SAO.mysql.updateTable("playerdata", "pbanned", "uuid", uuid.toString(), 1);
        }

        int bans = DataManager.getInt("playerdata", "bans", "uuid", uuid.toString());
        bans = bans + 1;
        SAO.mysql.updateTable("playerdata", "bans", "uuid", uuid.toString(), bans);

        try {
            SAO.mysql.updateSQL("INSERT INTO bans (date_time, uuid, name, buuid, bname, banamount, length, expires, reason) VALUES " +
                    "('" + time + "', '" + uuid.toString() + "', '" + name + "', '" + (buuid == null ? "CONSOLE" : uuid.toString()) + "', '" +
                    (buuid == null ? "CONSOLE" : Bukkit.getPlayer(buuid).getName()) + "', " + bans + ", " + length + ", " +
                    (Calendar.getInstance().getTimeInMillis() + length) + ", '" + reason + "'");
        } catch (SQLException | ClassNotFoundException e) {
            // e.printStackTrace();
        }
    }


    public void banOfflinePlayer(UUID uuid, UUID buuid, long length, String reason) {
        SAO.mysql.updateTable("playerdata", "banned", "uuid", uuid.toString(), 1);

        if (length == 0) {
            SAO.mysql.updateTable("playerdata", "pbanned", "uuid", uuid.toString(), 1);
        }

        int bans = DataManager.getInt("playerdata", "bans", "uuid", uuid.toString());
        bans = bans + 1;
        SAO.mysql.updateTable("playerdata", "bans", "uuid", uuid.toString(), bans);

        try {
            SAO.mysql.updateSQL("INSERT INTO bans (date_time, uuid, buuid, bname, banamount, length, expires, reason) VALUES " +
                    "('" + time + "', '" + uuid.toString() + "', '" + (buuid == null ? "CONSOLE" : uuid.toString()) + "', '" +
                    (buuid == null ? "CONSOLE" : Bukkit.getPlayer(buuid).getName()) + "', " + bans + ", " + length + ", " +
                    (Calendar.getInstance().getTimeInMillis() + length) + ", '" + reason + "'");
        } catch (SQLException | ClassNotFoundException e) {
            // e.printStackTrace();
        }
    }
}