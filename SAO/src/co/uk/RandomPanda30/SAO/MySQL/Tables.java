package co.uk.RandomPanda30.SAO.MySQL;

import co.uk.RandomPanda30.SAO.SAO;

import java.sql.SQLException;

public class Tables {

    public static void checkAllTables() {
        createTable(TableType.PLAYERDATA);
        createTable(TableType.BANS);
        createTable(TableType.KICKS);
        createTable(TableType.MUTES);
        createTable(TableType.JAILS);
        createTable(TableType.TEMPJAILS);
        createTable(TableType.NAMECHANGES);
    }

    private static void createTable(TableType type) {
        switch (type) {
            case PLAYERDATA:
                try {
                    SAO.mysql.createTable("playerdata", "uuid VARCHAR(36) PRIMARY KEY, name VARCHAR (32), sao_exp INT, " +
                            "sao_level INT, banned TINYINT, muted TINYINT, pbanned TINYINT, jailed TINYINT, bans INT, kicks INT, mutes INT, " +
                            "tempbans INT, tempmutes INT, jails INT");
                } catch (SQLException | ClassNotFoundException e) {
                    // -
                }
                break;
            case BANS:
                try {
                    SAO.mysql.createTable("bans", "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, date_time VARCHAR(20), uuid VARCHAR(36), name VARCHAR(50), " +
                            "buuid VARCHAR(36), bname VARCHAR(50), banamount INT, length BIGINT, expires BIGINT, reason TEXT");
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case KICKS:
                try {
                    SAO.mysql.createTable("kicks", "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, date_time VARCHAR(20), uuid VARCHAR(36), name VARCHAR(50), " +
                            "kuuid VARCHAR(36), kname VARCHAR(50), kickamount INT, reason TEXT");
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case MUTES:
                try {
                    SAO.mysql.createTable("mutes", "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, date_time VARCHAR(20), uuid VARCHAR(36), name VARCHAR(50), " +
                            "muuid VARCHAR(36), mname VARCHAR(50), muteamount INT, reason TEXT");
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case JAILS:
                try {
                    SAO.mysql.createTable("jails", "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, date_time VARCHAR(20), uuid VARCHAR(36), name VARCHAR(50), " +
                            "juuid VARCHAR(36), jname VARCHAR(50), jail INT, reason TEXT");
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case TEMPJAILS:
                try {
                    SAO.mysql.createTable("tempjails", "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, date_time VARCHAR(20), uuid VARCHAR(36), name VARCHAR(50), " +
                            "juuid VARCHAR(36), jname VARCHAR(50), jailamount INT, length BIGINT, expires BIGINT, reason TEXT");
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case NAMECHANGES:
                try {
                    SAO.mysql.createTable("namechanges", "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, uuid VARCHAR(36), name VARCHAR(50), oldname VARCHAR(50)");
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    enum TableType {
        PLAYERDATA, BANS, KICKS, MUTES, JAILS, TEMPJAILS, NAMECHANGES;
    }
}