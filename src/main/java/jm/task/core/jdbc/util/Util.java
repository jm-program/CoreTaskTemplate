package jm.task.core.jdbc.util;


import java.sql.*;


public class Util implements AdapterDB {
    private static Connection conn;


    @Override
    public Connection connect() throws ClassNotFoundException, SQLException {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb?useSSL=false","root", "root");
    }

    @Override
    public void disconnect() throws SQLException {
        conn.close();
    }
    // реализуйте настройку соеденения с БД
}
