package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
            private static String url = "jdbc:mysql://localhost:3306/ultra_low?useSSL=false";
            private static String username = "rootalex";
            private static String password = "rootalex";

            public static Connection getConnection() {
                Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
         }
}

