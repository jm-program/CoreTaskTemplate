package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import static jm.task.core.jdbc.util.Util.getConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try {
            Statement statement = getConnection().createStatement();
            statement.execute("CREATE TABLE `my_db_test`.`user` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NULL,\n" +
                    "  `lastName` VARCHAR(45) NULL,\n" +
                    "  `age` TINYINT NULL,\n" +
                    "  PRIMARY KEY (`id`))\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;");
        } catch (SQLException ignored) {
        }
    }

    public void dropUsersTable() {
        try (Statement statement = getConnection().createStatement()) {
            statement.execute("drop table user;");
        } catch (SQLException ignored) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO my_db_test.user (name, lastName, age) VALUES (?, ?, ?);";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");

        } catch (SQLException e) {
            e.getMessage();
            System.err.println("Не удалось добавить User'a ");
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM my_db_test.user WHERE id = ?;";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
            System.err.println("Не удалось Удалить User'a ");
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM user;";
        try (Statement statement = getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                list.add(user);
            }
            return list;
        } catch (SQLException e) {
            e.getMessage();
            System.err.println("Не удалось получить список User'ов ");
        }

        return null;
    }

    public void cleanUsersTable() {
        int i = 0;
        String sql = "SELECT * FROM user;";
        try (Statement statement = getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                i++;
                PreparedStatement preparedStatement = getConnection().prepareStatement("DELETE\n" +
                        "FROM my_db_test.user\n" +
                        "WHERE id = ?;");
                preparedStatement.setLong(1, i);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.getMessage();
            System.err.println("Не удалось очистить таблицу");
        }
    }
}
