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

    public static final String CREATE_TABLE = "CREATE TABLE users" +
            "(" +
            "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
            "name VARCHAR(64), " +
            "last_name VARCHAR(64), " +
            "age TINYINT" +
            ");";

    public static final String DROP_TABLE = "DROP TABLE users;";

    private static final String INSERT_INTO = "INSERT users(name, last_name, age) " +
            "VALUES (?, ?, ?);";

    private static final String REMOVE_BY_ID = "DELETE FROM users " +
            "WHERE id = ?;";

    private static final String GET_ALL = "SELECT * FROM users";

    private static final String CLEAR_TABLE = "DELETE FROM users";

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection connection = Util.open();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TABLE)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.open();
        PreparedStatement preparedStatement = connection.prepareStatement(DROP_TABLE)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.open();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.open();
        PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.open();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(buildUser(resultSet));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getString("name"),
                resultSet.getString("last_name"),
                resultSet.getByte("age"));
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.open();
        PreparedStatement preparedStatement = connection.prepareStatement(CLEAR_TABLE)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
