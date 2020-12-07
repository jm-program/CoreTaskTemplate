package jm.task.core.jdbc;

import com.mysql.fabric.jdbc.FabricMySQLDriver;



import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main {
    //static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL ="jdbc:mysql://localhost:3306/mydbtest?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Driver driver;

        try {
            driver = new FabricMySQLDriver();
            System.out.println("Удалось загрузить драйвер");
        } catch (SQLException throwables) {
            System.out.println("Не удалось загрузиь драйвер");
            return;// дальше нечего выполнять
        }
        try {
            DriverManager.registerDriver(driver);
            System.out.println("Удалось зареистрировать драйвер");
        } catch (SQLException throwables) {
            System.out.println("Не удалось зарегистрировать драйвер");
            return;
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Создали коннект и рабоаем");
        } catch(SQLException ex){
            System.out.println("НЕ удалось создать соединение");
            return;
        }
        finally {
            if(connection!=null)
                connection.close();
        }

        //final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        // реализуйте алгоритм здесь
    }
}
