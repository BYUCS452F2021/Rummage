package edu.byu.cs.tweeter.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {

    private static DBManager instance = null;
    private static Connection connection;

    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
            initializeDB();
        }
        return instance;
    }

    public static void initializeDB() {
        String url = "jdbc:sqlite:C:\\Users\\jacdavwill\\projects\\Rummage.db";
        String sql = "" +
        "CREATE TABLE IF NOT EXISTS \"user\" (\n" +
                "\t\"username\"\tTEXT,\n" +
                "\t\"password\"\tTEXT,\n" +
                "\t\"contactID\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"username\")\n" +
                ");"
        ;

        try{
            connection = DriverManager.getConnection(url);
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
