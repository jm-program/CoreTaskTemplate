package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createUsersTable = "CREATE TABLE IF NOT EXISTS user (" +
                "id BIGINT(19) NOT NULL AUTO_INCREMENT," +
                "name VARCHAR(45) NOT NULL," +
                "lastName VARCHAR(45) NOT NULL," +
                "age TINYINT(3) NOT NULL," +
                "PRIMARY KEY (id))";
        operations(createUsersTable);
    }

    public void dropUsersTable() {
        String dropUsersTable = "DROP TABLE IF EXISTS user;";
        operations(dropUsersTable);
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUser = "INSERT INTO user(name, lastName, age) VALUES (?, ?, ?);";
        User user = new User(name, lastName, age);
        try (Connection connection = Util.getUtilConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(saveUser);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setByte(3, user.getAge());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String removeUserById = "DELETE FROM user WHERE Id = ?;";
        try (Connection connection = Util.getUtilConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(removeUserById);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String getAllUsers = "SELECT * from user;";
        try (Connection connection = Util.getUtilConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(getAllUsers);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String cleanUsersTable = "TRUNCATE user";
        operations(cleanUsersTable);
    }

    public void operations(String operation){
        try (Connection connection = Util.getUtilConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(operation);
            preparedStatement.execute();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}