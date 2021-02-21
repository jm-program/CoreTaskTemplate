package jm.task.core.jdbc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import jm.task.core.jdbc.model.User;

import java.util.List;
import jm.task.core.jdbc.util.Util;

public class UserServiceImpl implements UserService {

  public void createUsersTable() {
        try {Connection connection = Util.getDBConnection();
            Statement stmt = connection.createStatement();

          String creatTable = "CREATE TABLE IF NOT EXISTS user_table"
              + "(id INTEGER not NULL AUTO_INCREMENT PRIMARY KEY ,"
              + "name VARCHAR(50),"
              + "lastName VARCHAR(50),"
              + "age INT(3))";
              stmt.executeUpdate(creatTable);
              stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try{ Connection connection = Util.getDBConnection();
            Statement stmt = connection.createStatement();
            String dropTable = "DROP TABLE IF EXISTS user_table";
            stmt.executeUpdate(dropTable);
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        try {Connection connection = Util.getDBConnection();
          String data = "INSERT INTO user_table(name,lastName,age) VALUES (?,?,?)";
          PreparedStatement stmt = connection.prepareStatement(data);
            stmt.setString(1,name);
            stmt.setString(2,lastName);
            stmt.setInt(3,age);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void removeUserById(long id) {
      String SQL = ("DELETE FROM user_table WHERE ID = "+ id);
      try {Connection connection = Util.getDBConnection();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(SQL);
        stmt.close();
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }

    public List<User> getAllUsers() {
      String SQL = "SELECT * FROM user_table";
      List<User> list = new ArrayList<>();
      try{ Connection connection = Util.getDBConnection() ;
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(SQL);
        while (resultSet.next()){
          list.add( new User(resultSet.getString(2)
              ,resultSet.getString(3)
              ,(byte)resultSet.getInt(4)));
        }
        stmt.close();
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
      return list;
    }

    public void cleanUsersTable() {
      String SQL = "DELETE FROM user_table";
      try {Connection connection = Util.getDBConnection();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(SQL);

      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
}
