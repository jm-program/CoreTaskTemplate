package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserServiceImpl implements UserService {

    static Connection connection = Util.getConnection();

    public void createUsersTable() {
        String createTableUser = "CREATE TABLE `preprojectjm`.`user` (\n" +
                "  `id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(50) NULL,\n" +
                "  `last_name` VARCHAR(50) NULL,\n" +
                "  `age` INT(4) NULL,\n" +
                "  PRIMARY KEY (`id`));";
        try (
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableUser);
            System.out.println("Таблица user добавленв");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        final String sql = "DROP TABLE user";
        try (
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Таблица user удалена");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("что-то пошло не так");
        }
    }

    public void saveUser(String name, String lastName, byte age) {

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
