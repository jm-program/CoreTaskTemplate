package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL =
            "jdbc:mysql://localhost/firstdb?useUnicode=true&serverTimezone=UTC";
    private static final String USERNAME  = "root";
    private static final String PASS = "E8d4a51000";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection SQLConnection () {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    // реализуйте настройку соеденения с БД
}
