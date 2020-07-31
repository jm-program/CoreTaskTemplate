package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private String userName = "root";
    private String password = "password";
    private String connectionUrl = "jdbc:mysql://localhost:3306/lesson113?serverTimezone=Europe/Moscow&useSSL=false";
    Connection connection = null;

    public Connection setConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(connectionUrl, userName, password);
            System.out.println("We're connected");
        } catch (SQLException e) {
            System.out.println("Didn't connected (class Util)");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
