package jm.task.core.jdbc.util;
import java.sql.*;


public class Util {
    private static final String URL ="jdbc:mysql://localhost:3306/my_db_test";
    private static final String USERNAME ="root";
    private static final String PASSWORD ="192837465aA.A1";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
