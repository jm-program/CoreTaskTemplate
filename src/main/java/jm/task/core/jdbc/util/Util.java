package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static final String MY_HELL = "jdbc:mysql://localhost:3306/my_db?useUnicode=true&serverTimezone=UTC&useSSL=false";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "springcourse";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(MY_HELL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


}
