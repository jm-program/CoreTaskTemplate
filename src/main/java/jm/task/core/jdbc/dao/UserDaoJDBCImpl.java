package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection connection = Util.getInstance().getMySQLConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users (" +
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "name varchar(255) NOT NULL," +
                    "lastName varchar(255) NOT NULL," +
                    "age int NOT NULL);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getInstance().getMySQLConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS Users;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getInstance().getMySQLConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("INSERT INTO Users SET name = '%s', lastName = '%s', age = %d;", name, lastName, age));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getInstance().getMySQLConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("DELETE FROM Users where id = %d;", id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> listUsers = new ArrayList<>(0);
        try (Connection connection = Util.getInstance().getMySQLConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users;");
            while(resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                listUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUsers;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getInstance().getMySQLConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE TABLE Users;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
