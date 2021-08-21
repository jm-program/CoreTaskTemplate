package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String BD_URL = "jdbc:mysql://localhost:3306/idea";
    private static final String BD_USER = "root";
    private static final String BD_PASSWORD = "qw009719er";
    private static final String BD_DRIVER = "com.mysql.cj.jdbc.Driver";


    public Connection setConnection() {
        Connection con = null;
        try {
            Class.forName(BD_DRIVER);
            con = DriverManager.getConnection(BD_URL, BD_USER, BD_PASSWORD);
            System.out.println("Соединение установленно");
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Ошибка соединения");
        }
        return con;
    }
}
