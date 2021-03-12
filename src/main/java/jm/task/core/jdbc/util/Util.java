package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соеденения с БД

            String url = "jdbc:mysql://localhost:3306/ultra_low?useSSL=false";
            String username = "rootalex";
            String password = "rootalex";

            //Class.forName("com.mysql.jdbc.Driver");
             public Connection getConnection() {
                Connection connection = null;
            try {
               // Class.forName("com.mysql.cj.jdbc.Drive");
                 connection = DriverManager.getConnection(url, username, password);
                System.out.println("Connection to Store DB succesfull!");
                try {
                    Statement statement = connection.createStatement();
                    //вставка в таблицу
                   // statement.execute("INSERT INTO developers(name, salary) VALUES('biba', 100500);");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //return connection;
                //new end//
            } catch (Exception ex) {
                System.out.println("Connection failed...");

                System.out.println(ex);
            }
            return connection;
         }
}

