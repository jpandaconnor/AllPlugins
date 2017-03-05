package org.jpanda.PE.mysql;

import org.jpanda.PE.PE;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Database {

    protected PE plugin;

    protected Database(PE plugin) {
        this.plugin = plugin;
    }

    public abstract Connection openConnection()
            throws SQLException, ClassNotFoundException;

    public abstract boolean checkConnection() throws SQLException;

    public abstract Connection getConnection();

    public abstract boolean closeConnection() throws SQLException;

    public abstract ResultSet querySQL(String query)
            throws SQLException, ClassNotFoundException;

    public abstract int updateSQL(String query)
            throws SQLException, ClassNotFoundException;
}