package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    public static Connection getConnectionToDatabase() throws ClassNotFoundException, SQLException {

        final String DB_URL = "jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC";
        final String DB_Driver = "java.sql.Driver";

        Connection connection = DriverManager.getConnection(DB_URL, "root", "Wusleng567706"); //соединениесБД
        System.out.println("Соединение с СУБД выполнено.");

        return connection;
    }
}
