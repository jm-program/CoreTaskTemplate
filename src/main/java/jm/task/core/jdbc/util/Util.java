package jm.task.core.jdbc.util;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?useSSL=false";

    private static final String USERNAME = "root1";
    private static final String PASSWORD = "12345_Aa";

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

//    public void setConnection(Connection connection) {
//        this.connection = connection;
//    }

    public Util(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);

        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }
}
