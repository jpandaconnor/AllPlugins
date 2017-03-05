package co.uk.RandomPanda30.SAdminO.MySQL;

import co.uk.RandomPanda30.SAdminO.Data;
import co.uk.RandomPanda30.SAdminO.Files.Config.ConfigValues;
import co.uk.RandomPanda30.SAdminO.Util.Basic.EUtil;
import co.uk.RandomPanda30.SAdminO.Util.Basic.EUtil.Cause;

import java.sql.SQLException;

public class Tables {

    public static void checkTables() {
        addTable(TableType.DATA);
        addTable(TableType.BANS);
        addTable(TableType.KICKS);
        addTable(TableType.MUTES);
        addTable(TableType.VOTEBANS);
        addTable(TableType.VOTEKICKS);
        addTable(TableType.VOTEMUTES);

    }

    public static void addTable(TableType type) {
        try {
            Data.mysql.querySQL("USE `" + (String) ConfigValues.MYSQL_DBNAME.value + "`;");
        } catch (ClassNotFoundException | SQLException e) {
            new EUtil(e.getStackTrace(), Cause.MYSQLERROR, "Error whilst trying to use specified database: '"
                    + (String) ConfigValues.MYSQL_DBNAME.value + "'");
        }
        switch (type) {
            case DATA:
                try {
                    Data.mysql.updateSQL(
                            "CREATE TABLE IF NOT EXISTS data (uuid VARCHAR(36) PRIMARY KEY, bans INT, kicks INT, perm INT);");
                } catch (ClassNotFoundException | SQLException e) {
                    new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
                            "Error whilst trying to create the" + type.toString() + " table");
                }
                break;
            case BANS:
                try {
                    Data.mysql.updateSQL(
                            "CREATE TABLE IF NOT EXISTS bans (id INT NOT NULL AUTO_INCREMENT, uuid VARCHAR(36), uuid_name VARCHAR(50), banner VARCHAR(36), banner_name VARCHAR(50), ban_count INT, time BIGINT, expired INT, when VARCHAR(15), reason VARCHAR(100), PRIMARY KEY (id));");
                } catch (ClassNotFoundException | SQLException e) {
                    new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
                            "Error whilst trying to create the" + type.toString() + " table");
                }
                break;
            case KICKS:
                try {
                    Data.mysql.updateSQL(
                            "CREATE TABLE IF NOT EXISTS kicks (id INT NOT NULL AUTO_INCREMENT, uuid VARCHAR(36), uuid_name VARCHAR(50), kicker VARCHAR(36), kicker_name VARCHAR(50), kick_count INT, when VARCHAR(15), reason VARCHAR(100), PRIMARY KEY (id));");
                } catch (ClassNotFoundException | SQLException e) {
                    new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
                            "Error whilst trying to create the" + type.toString() + " table");
                }
                break;
            case MUTES:
                try {
                    Data.mysql.updateSQL(
                            "CREATE TABLE IF NOT EXISTS mutes (id INT NOT NULL AUTO_INCREMENT, uuid VARCHAR(36), uuid_name VARCHAR(50), muter VARCHAR(36), muter_name VARCHAR(50), mute_count INT,  time BIGINT, expired INT, when VARCHAR(15), reason VARCHAR(100), PRIMARY KEY (id));");
                } catch (ClassNotFoundException | SQLException e) {
                    new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
                            "Error whilst trying to create the" + type.toString() + " table");
                }
                break;
            case VOTEBANS:
                try {
                    Data.mysql.updateSQL(
                            "CREATE TABLE IF NOT EXISTS votebans (id INT NOT NULL AUTO_INCREMENT, uuid VARCHAR(36), uuid_name VARCHAR(50), commander VARCHAR(36), commander_name VARCHAR(50), voteban_count INT,  time BIGINT, expired INT, when VARCHAR(15), reason VARCHAR(100), PRIMARY KEY (id));");
                } catch (ClassNotFoundException | SQLException e) {
                    new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
                            "Error whilst trying to create the" + type.toString() + " table");
                }
                break;
            case VOTEKICKS:
                try {
                    Data.mysql.updateSQL(
                            "CREATE TABLE IF NOT EXISTS votekicks (id INT NOT NULL AUTO_INCREMENT, uuid VARCHAR(36), uuid_name VARCHAR(50), commander VARCHAR(36), commander_name VARCHAR(50), votekick_count INT, when VARCHAR(15), reason VARCHAR(100), PRIMARY KEY (id));");
                } catch (ClassNotFoundException | SQLException e) {
                    new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
                            "Error whilst trying to create the" + type.toString() + " table");
                }
                break;
            case VOTEMUTES:
                try {
                    Data.mysql.updateSQL(
                            "CREATE TABLE IF NOT EXISTS votemutes (id INT NOT NULL AUTO_INCREMENT, uuid VARCHAR(36), uuid_name VARCHAR(50), commander VARCHAR(36), commander_name VARCHAR(50), votemute_count INT, time BIGINT, expired INT, when VARCHAR(15), reason VARCHAR(100), PRIMARY KEY (id));");
                } catch (ClassNotFoundException | SQLException e) {
                    new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
                            "Error whilst trying to create the" + type.toString() + " table");
                }
                break;
            case UNBANS:
                try {
                    Data.mysql.updateSQL(
                            "CREATE TABLE IF NOT EXISTS unans (id INT NOT NULL AUTO_INCREMENT, uuid VARCHAR(36), uuid_name VARCHAR(50), commander VARCHAR(36), commander_name VARCHAR(50), when VARCHAR(15), reason VARCHAR(100), PRIMARY KEY (id));");
                } catch (ClassNotFoundException | SQLException e) {
                    new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
                            "Error whilst trying to create the" + type.toString() + " table");
                }
                break;
            default:
                break;
        }
    }

    public enum TableType {
        DATA, BANS, UNBANS, KICKS, VOTEBANS, VOTEKICKS, VOTEMUTES, MUTES;
    }
}