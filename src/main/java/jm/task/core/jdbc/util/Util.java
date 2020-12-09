package jm.task.core.jdbc.util;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL ="jdbc:mysql://localhost:3306/business?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Connection connection;
      public Util(){// в конструкторе регистрируем драйвер, а соединение в методе
          try {
              DriverManager.registerDriver(new FabricMySQLDriver());
          } catch (SQLException ex) {
              System.out.println("Ошибка регистриции драйвера");
              return;
          }
      }

    public Connection getConnection(String url, String username, String password) throws SQLException {
        if (connection != null)
            return connection;
        connection = DriverManager.getConnection(url, username, password);
        System.out.println("Соединение установленно");
        return connection;
      }

}
