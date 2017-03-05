package org.jpanda.cityrp.mysql;

import org.jpanda.cityrp.CityRP;

import java.sql.*;

public class MySQL extends Database {

    private String HOST;
    private String DB_NAME;
    private String USER;
    private String PASS;
    private int PORT;

    private Connection connection;

    public MySQL(CityRP plugin, String HOST, String DB_NAME, String USER, String PASS, int PORT) {
        super(plugin);
        this.HOST = HOST;
        this.PORT = PORT;
        this.DB_NAME = DB_NAME;
        this.USER = USER;
        this.PASS = PASS;

        connection = null;

        try {
            openConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
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