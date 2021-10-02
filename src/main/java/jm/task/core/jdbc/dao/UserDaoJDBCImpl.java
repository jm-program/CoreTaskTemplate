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
        final String createTableUser = "CREATE TABLE IF NOT EXISTS `preprojectjm`.`user` (\n" +
                "  `id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(50) NULL,\n" +
                "  `last_name` VARCHAR(50) NULL,\n" +
                "  `age` INT(4) NULL,\n" +
                "  PRIMARY KEY (`id`));";
        try (Connection connection = Util.getConnection();
             Statement statement = connection != null ? connection.createStatement() : null) {
            if (statement != null) {
                statement.executeUpdate(createTableUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        final String sql = "DROP TABLE IF EXISTS preprojectjm.user";
        try (Connection connection = Util.getConnection();
             Statement statement = connection != null ? connection.createStatement() : null) {
            if (statement != null) {
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        final String sql = "INSERT INTO preprojectjm.user(name, last_name, age) VALUES (?,?,?)";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection != null ? connection.prepareStatement(sql) : null) {
            if (preparedStatement != null) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM preprojectjm.user WHERE id=?";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection != null ? connection.prepareStatement(sql) : null) {
            if (preparedStatement != null) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT*FROM preprojectjm.user";
        try (Connection connection = Util.getConnection();
             Statement statement = connection != null ? connection.createStatement() : null) {
            ResultSet resultSet = statement != null ? statement.executeQuery(sql) : null;

            while (resultSet.next()) {
                User user = new User(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("last_name"),
                        resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM preprojectjm.user";
        try (Connection connection = Util.getConnection();
             Statement statement = connection != null ? connection.createStatement() : null) {
            if (statement != null) {
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
