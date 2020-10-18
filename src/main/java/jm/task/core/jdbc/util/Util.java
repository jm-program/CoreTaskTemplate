package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соеденения с БД
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String CONNECTION = "jdbc:mysql://localhost:3306/?useUnicode=true&serverTimezone=UTC";
    private final String username = "Test";
    private final String password = "1262";
    private Statement mypipe;

    public Statement getMypipe() {
        return mypipe;
    }

    public void connect() {
        try {
            Class.forName(DRIVER);
            System.out.println("JDBC driver connected");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver problem");
        }
        try {
            Connection connection_link = DriverManager.getConnection(CONNECTION, username, password);
            mypipe = connection_link.createStatement();
            System.out.println("SQL Server connection success");
        } catch (SQLException throwables) {
            throw new IllegalStateException("Cannot connect SQL Server");
        }
    }

    public void close() {
        if (mypipe != null){
            try {
                mypipe.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}