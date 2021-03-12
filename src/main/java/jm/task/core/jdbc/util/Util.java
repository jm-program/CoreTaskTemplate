package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соеденения с БД

    public static void main(String[] args) {
        try{
            String url = "jdbc:mysql://localhost:3306/ultra_low?useSSL=false";
            String username = "rootalex";
            String password = "rootalex";

           // Class.forName("com.mysql.jdbc.Driver");
            Connection connection = null;
            try (Connection connection = DriverManager.getConnection(url, username, password)){

                System.out.println("Connection to Store DB succesfull!");
                //1
                //2
                //new 10-03//
                try {
                    Statement statement =connection.createStatement();
                    //вставка в таблицу
                    statement.execute("INSERT INTO developers(name, salary) VALUES('biba', 100500);");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //new end//
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }
}
