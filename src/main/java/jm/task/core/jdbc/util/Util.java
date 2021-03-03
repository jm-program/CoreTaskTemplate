package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соеденения с БД
    static private String URL = "jdbc:mysql://localhost:3306/database";
    static private String URL_FIX = "jdbc:mysql://localhost:3306/database?useUnicode=true&useSSL=false&" +
            "useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    static private String LOG = "root";
    static private String PASS = "root";
    private static Connection connection;

    public Util(){   }

    public static Connection getUtilConnection() { // геттер приватного поля connection + создаем connection
        try {
            connection = DriverManager.getConnection(URL_FIX, LOG, PASS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return connection;
    }

}
