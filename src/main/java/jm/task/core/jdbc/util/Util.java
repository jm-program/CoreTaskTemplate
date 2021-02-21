package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
  private static Connection connection = null;
  private static final String URL = "jdbc:mysql://localhost:3306/db_user?autoReconnect=true&useSSL=false";
  private static final String USERNAME = "newroot";
  private static final String PASSWORD = "root";
  private Util(){

  }

  public static Connection getDBConnection(){
    try {
      if(connection == null){
        connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
      }
    } catch (SQLException  throwables) {
      System.out.println("Ошибка подключения: " + throwables);
    }
    return connection;
  }
}
