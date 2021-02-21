package jm.task.core.jdbc.util;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

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
      System.out.println("connection: " + connection);
      if(connection == null){
//        Class.forName("com.mysql.jdbc.Driver");

//        try {
//      Driver driver = new FabricMySQLDriver();
//      DriverManager.registerDriver(driver);
//      } catch (SQLException e) {
//        e.printStackTrace();
//      }
        connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        System.out.println("Соединение установлено");
      }
    } catch (SQLException  throwables) {
      System.out.println("Ошибка подключения: " + throwables);
    }
    return connection;
  }



  private static String data = "INSERT INTO user_table(id,name,lastName,age) VALUES (?,?,?,?)";
  private static String creatTable = "CREATE TABLE user_table"
      + "(id INTEGER not NULL," + "name VARCHAR(50)," + "lastName VARCHAR(50)," + "age INTEGER(3)";


//  public void newConnection(String action, Object o) {
//
//    try {
//      Driver driver = new FabricMySQLDriver();
//      DriverManager.registerDriver(driver);
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
//    try(Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
//        Statement statement = connection.createStatement();){
//      if(action == "save" && o instanceof User){
//
//        PreparedStatement stmt = connection.prepareStatement(data);
//        stmt.setString(2,user.getName());
//        stmt.setString(3,user.getLastName());
//        stmt.setInt(4,user.getAge());
//        stmt.executeUpdate();
//      }else if (action == "removeTable"){
//       UserServiceImpl usi = new UserServiceImpl();
//       usi.dropUsersTable();
//      }
//
//      //statement.execute("INSERT INTO user_table(name,lastName,age) VALUES ('name','desc',35);");
//      System.out.println(connection.isClosed());
//    }catch (SQLException s){
//      s.printStackTrace();
//    }
//
//  }


}
