package co.uk.RandomPanda30.SAO.Manager;

import co.uk.RandomPanda30.SAO.SAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataManager {

    public static int getInt(String table, String what, String where, String wherev) {
        ResultSet rs;
        int r = 0;
        try {
            String query = "SELECT " + what + " FROM " + table + " WHERE " + where + " = '" + wherev + "'";
            rs = SAO.mysql.querySQL(query);
            while (rs.next()) {
                r = rs.getInt(what);
            }
        } catch (SQLException | ClassNotFoundException e) {
            // e.printStackTrace();
        }
        return r;
    }

    public static double getDouble(String table, String what, String where, String wherev) {
        ResultSet rs;
        double r = 0;
        try {
            rs = SAO.mysql.querySQL("SELECT " + what + " FROM " + table + " WHERE " + where + " = '" + wherev + "'");
            while (rs.next()) {
                r = rs.getDouble(what);
            }
        } catch (SQLException | ClassNotFoundException e) {
            // e.printStackTrace();
        }
        return r;
    }

    public static void set(String table, String what, String value, String where, String wherev) {
        try {
            SAO.mysql.updateSQL("UPDATE " + table + " SET " + what + " = '" + value + "' WHERE " + where + " = '" + wherev + "'");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void set(String table, String what, int value, String where, String wherev) {
        try {
            SAO.mysql.updateSQL("UPDATE " + table + " SET " + what + " = " + value + " WHERE " + where + " = '" + wherev + "'");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void set(String table, String what, double value, String where, String wherev) {
        try {
            SAO.mysql.updateSQL("UPDATE " + table + " SET " + what + " = " + value + " WHERE " + where + " = '" + wherev + "'");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void set(String table, String what, float value, String where, String wherev) {
        try {
            SAO.mysql.updateSQL("UPDATE " + table + " SET " + what + " = " + value + " WHERE " + where + " = '" + wherev + "'");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}