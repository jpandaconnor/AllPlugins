package org.jpanda.PE.mysql;

import org.jpanda.PE.PE;

import java.sql.*;

public class MySQL extends Database {

    private String HOST;
    private String DB_NAME;
    private String USER;
    private String PASS;
    private int PORT;

    private Connection connection;

    public MySQL(PE plugin, String HOST, String DB_NAME, String USER, String PASS, int PORT) {
        super(plugin);
        this.HOST = HOST;
        this.DB_NAME = DB_NAME;
        this.USER = USER;
        this.PASS = PASS;
        this.PORT = PORT;

        connection = null;

        try {
            openConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateTable(String table, String set, String where, String wherev, int value) {
        try {
            updateSQL("UPDATE " + table + " SET " + set + " = " + value + " WHERE " + where + " = '" + wherev + "'");
        } catch (ClassNotFoundException | SQLException e) {

        }
    }

    public void createTable(String name, String details) throws SQLException, ClassNotFoundException {
        updateSQL("CREATE TABLE IF NOT EXISTS " + name + " (" + details + ");");
    }

    @Override
    public Connection openConnection() throws SQLException, ClassNotFoundException {
        if (checkConnection()) {
            return connection;
        }

        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME, USER,
                PASS);
        return connection;
    }

    @Override
    public boolean checkConnection() throws SQLException {
        return connection != null && !connection.isClosed();
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public boolean closeConnection() throws SQLException {
        if (connection == null) {
            return false;
        }

        connection.close();
        return true;
    }

    @Override
    public ResultSet querySQL(String query) throws SQLException, ClassNotFoundException {
        if (checkConnection()) {
            openConnection();
        }

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);
        return result;
    }

    @Override
    public int updateSQL(String query) throws SQLException, ClassNotFoundException {
        if (checkConnection()) {
            openConnection();
        }

        Statement statement = connection.createStatement();
        int result = statement.executeUpdate(query);
        return result;
    }
}