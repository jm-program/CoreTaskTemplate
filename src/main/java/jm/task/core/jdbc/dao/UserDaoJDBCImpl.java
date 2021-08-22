package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        Connection connection = setConnection();
        Statement statement = null;
        String sql = "CREATE TABLE users (ID BIGINT NOT NULL AUTO_INCREMENT, NAME VARCHAR(100) NOT NULL, " +
                "LASTNAME VARCHAR(100) NOT NULL, AGE INT NOT NULL, PRIMARY KEY (ID))";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e){
            System.out.println("Таблица уже существует");
            //e.printStackTrace();
        } finally {
            if (statement != null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
    }

    public void dropUsersTable() throws SQLException {
        Connection connection = setConnection();
        Statement statement = null;
        String sql = "DROP TABLE users";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e){
            //ignored
        } finally {
            if (statement != null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Connection connection = setConnection();
        PreparedStatement preparestatement = null;
        String sql = "INSERT INTO users (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";
        try {
            preparestatement = connection.prepareStatement(sql);

            preparestatement.setString(1, name);
            preparestatement.setString(2, lastName);
            preparestatement.setInt(3, age);

            preparestatement.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных ");
        } catch (SQLException e){
            //ignored
        } finally {
            if (preparestatement != null){
                preparestatement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
    }

    public void removeUserById(long id) throws SQLException {
        Connection connection = setConnection();
        PreparedStatement preparestatement = null;
        String sql = "DELETE FROM users WHERE ID = (?)";
        try {
            preparestatement = connection.prepareStatement(sql);

            preparestatement.setInt(1, (int) id);
            preparestatement.executeUpdate();

        } catch (SQLException e){
            //ignored
        } finally {
            if (preparestatement != null){
                preparestatement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
    }

    public List<User> getAllUsers() throws SQLException {
        Connection connection = setConnection();
        Statement statement = null;
        ResultSet result;
        String sql = "SELECT * FROM  users";
        List<User> userList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while (result.next()){
                User us = new User(result.getString(2), result.getString(3), (byte) result.getInt(4));
                us.setId((long) result.getInt(1));
                //System.out.println(us.toString());
                userList.add(us);
            }
        } catch (SQLException e) {
            //ignored
        } finally {
            if (statement != null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        Connection connection = setConnection();
        Statement statement = null;
        String sql = "TRUNCATE TABLE users";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e){
            System.out.println("Таблицы не существует");
        } finally {
            if (statement != null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
    }
}
