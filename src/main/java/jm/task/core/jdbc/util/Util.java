package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL_CONNECTION = "jdbc:mysql://localhost:3306/preprojectjm";
//            "?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL_CONNECTION,
                    USERNAME,
                    PASSWORD);

            return connection;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
