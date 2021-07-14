package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    static int ids = 0;
    Connection connection;

    public UserDaoJDBCImpl() {
        connection = Util.util();
    }

    public void createUsersTable() {
        try {
            connection.createStatement().execute("CREATE TABLE `newBD`.`users` " +
                    "(`id` INT NOT NULL AUTO_INCREMENT,`name` VARCHAR(45) NOT NULL," +
                    "`lastName` VARCHAR(45) NOT NULL,`age` INT NOT NULL," +
                    " PRIMARY KEY (`id`), UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);");
            //connection.close();
        } catch (SQLException throwables) {
        }
    }

    public void dropUsersTable() {
        try {
            connection.createStatement().execute("DROP TABLE users");
            //connection.close();
        } catch (SQLException throwables) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO users VALUES (?,?,?,?)");
            preparedStatement.setInt(1, ids);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastName);
            preparedStatement.setInt(4, age);
            preparedStatement.execute();
            ids++;
            preparedStatement.close();
        } catch (SQLException throwables) {
        }
    }

    public void removeUserById(long id) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("delete from users where id = ?");
            preparedStatement.setInt(1, (int) id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
        }
    }

    public List<User> getAllUsers() {
        PreparedStatement preparedStatement;
        List<User> users = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("select * from users");
            ResultSet resultSet = preparedStatement.executeQuery();
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
        try {
            connection.createStatement().execute("delete from users");
            //connection.close();
        } catch (SQLException throwables) {
        }
    }
}
