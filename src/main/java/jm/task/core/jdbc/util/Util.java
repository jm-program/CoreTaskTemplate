package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/newBD";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection util(){
        Connection connection = null;// реализуйте настройку соеденения с БД
        try {
            connection =DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
