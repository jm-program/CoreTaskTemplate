package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    //private Connection connection;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try(Connection connection = Util.util()) {
            connection.setAutoCommit(false);
            connection.createStatement().execute("CREATE TABLE IF NOT EXISTS `newBD`.`users` " +
                    "(`id` INT NOT NULL AUTO_INCREMENT,`name` VARCHAR(45) NOT NULL," +
                    "`lastName` VARCHAR(45) NOT NULL,`age` INT NOT NULL," +
                    " PRIMARY KEY (`id`), UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);");
            connection.commit();
        } catch (SQLException throwables) {
        }
    }

    public void dropUsersTable() {
        try(Connection connection = Util.util()) {
            connection.setAutoCommit(false);
            connection.createStatement().execute("DROP TABLE if EXISTS users");
            connection.commit();
        } catch (SQLException throwables) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement;
        try (Connection connection = Util.util()){
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.execute();
            connection.commit();
            preparedStatement.close();
        } catch (SQLException throwables) {
        }
    }

    public void removeUserById(long id) {
        PreparedStatement preparedStatement;
        try (Connection connection = Util.util()){
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("delete from users WHERE EXISTS id = ?");
            preparedStatement.setInt(1, (int) id);
            preparedStatement.executeUpdate();
            connection.commit();
            preparedStatement.close();
        } catch (SQLException throwables) {
        }
    }

    public List<User> getAllUsers() {
        PreparedStatement preparedStatement;
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.util()){
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("select * from users");
            ResultSet resultSet = preparedStatement.executeQuery();
            connection.commit();
            while (resultSet.next()){
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastName"),resultSet.getByte("age"));
                user.setId((long) resultSet.getInt("id"));
                users.add(user);
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.util()){
            connection.setAutoCommit(false);
            connection.createStatement().execute("delete from users");
            connection.commit();
        } catch (SQLException throwables) {

        }
    }
}
