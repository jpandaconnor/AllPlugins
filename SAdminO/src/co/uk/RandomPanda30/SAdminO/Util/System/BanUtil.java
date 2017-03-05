package co.uk.RandomPanda30.SAdminO.Util.System;

import co.uk.RandomPanda30.SAdminO.Data;
import co.uk.RandomPanda30.SAdminO.Files.Messages.MessagesValues;
import org.bukkit.entity.Player;

import java.util.Calendar;
import java.util.UUID;

public class BanUtil {

    public UUID uuid;
    public Player player;

    public String time;
    public String reason;

    public BanUtil(UUID uuid, String time, String reason) {
        this.uuid = uuid;
        this.time = time;
        this.reason = reason;
    }

    public BanUtil(Player player, String time, String reason) {
        this.player = player;
        this.uuid = player.getUniqueId();
        this.time = time;
        this.reason = reason;
    }

    public void ban() {
        String[] splitter = {time};

        long days = 0;
        long hours = 0;
        long minutes = 0;
        long seconds = 0;

        String finalReason = (String) MessagesValues.BAN_YOUBANNED.value.toString()
                + (reason == null ? MessagesValues.BAN_NOREASON.value.toString() + "%N." : reason + "%N.");

        if (!time.contains("d") && !time.contains("h") && !time.contains("m") && !time.contains("s")) {
            try {
                minutes = Integer.parseInt(time);
            } catch (NumberFormatException e) {
                time = "0";
            }
        } else {
            if (time.contains("d")) {
                splitter = splitter[0].split("d");
                days = Integer.parseInt(splitter[0]);
                if (splitter.length == 2) {
                    splitter[0] = splitter[1];
                }
            }

            if (time.contains("h")) {
                splitter = splitter[0].split("h");
                hours = Integer.parseInt(splitter[0]);
                if (splitter.length == 2) {
                    splitter[0] = splitter[1];
                }
            }

            if (time.contains("m")) {
                splitter = splitter[0].split("m");
                minutes = Integer.parseInt(splitter[0]);
                if (splitter.length == 2) {
                    splitter[0] = splitter[1];
                }
            }

            if (time.contains("s")) {
                splitter = splitter[0].split("s");
                seconds = Integer.parseInt(splitter[0]);
            }

            String finalt = "";

            if (days != 0) {
                finalt += " %A" + days + (String) MessagesValues.BAN_DAYS.value;
            }

            if (hours != 0) {
                finalt += " %A" + hours + (String) MessagesValues.BAN_HOURS.value;
            }

            if (minutes != 0) {
                finalt += " %A" + minutes + (String) MessagesValues.BAN_MINUTES.value;
            }

            if (seconds != 0) {
                finalt += " %A" + seconds + (String) MessagesValues.BAN_SECONDS.value;
            }

            finalReason += "&u" + (String) MessagesValues.BAN_LENGTH.value
                    + (time.equals("0") ? (String) MessagesValues.BAN_PERM.value + "%N." : "%A" + finalt + "%N.");
            finalt = (time.equals("0") ? (String) MessagesValues.BAN_PERM.value : finalt);

            long length = 0;
            if (!time.equals("0")) {
                length += Calendar.getInstance().getTimeInMillis() + (days * 1000 * 60 * 60 * 24)
                        + (hours * 1000 * 60 * 60) + (minutes * 1000 * 60) + (seconds * 1000);
            }

            // http://www.cubrid.org/wiki_tutorials/entry/how-to-insert-data-into-tables-with-auto_increment-columns

            // Look at tables here
            Data.mysql.updateSQL("INSERT INTO bans");

										/*
										 * Insert from UUID above.
										 * 
										 * MySQL table insert here
										 */
        }

        if (player != null) {
            player.kickPlayer(finalReason);
        }
    }

    public void unban() {

    }

    public boolean isBanned() {
        return true;
    }

    public UUID getUUID() {
        return null;
    }

    public String getName() {
        return "";
    }

    public UUID getBannerUUID() {
        return null;
    }

    public String getBannerName() {
        return "";
    }

    public String getBanTime() {
        if (!isBanned()) {
            return "";
        }
        return "";
    }

    public long getRawBanTime() {
        return 2512;
    }

    public String getDate() {
        return "";
    }

    public String getReason() {
        return "";
    }
}