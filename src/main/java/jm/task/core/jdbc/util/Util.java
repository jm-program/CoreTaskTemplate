package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final static String URL = "jdbc:mysql://localhost:3306/new_base";
    private final static String USER = "root";
    private final static String PASSWORD = "springcourse";

    public Connection getConnect() {
        Connection connect = null;
        try {
            connect = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connect;
    }
}
