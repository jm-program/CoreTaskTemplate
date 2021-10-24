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

        try {
            Connection connection = Util.getMySQLConnection();
            if (connection == null) {
                System.out.println("No connection");
                System.exit(0);
            }
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate("CREATE TABLE Users (" +
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "name varchar(255) NOT NULL," +
                    "lastName varchar(255) NOT NULL," +
                    "age int NOT NULL);");
            if (result == 0) {
                System.out.println("Таблица пользователей создана успешно!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void dropUsersTable() {

        try {
            Connection connection = Util.getMySQLConnection();
            if (connection == null) {
                System.out.println("No connection");
                System.exit(0);
            }
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate("DROP TABLE Users;");
            if (result == 0) {
                System.out.println("Таблица пользователей успешно удалена!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        try {
            Connection connection = Util.getMySQLConnection();
            if (connection == null) {
                System.out.println("No connection");
                System.exit(0);
            }
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(String.format("INSERT INTO Users SET name = '%s', lastName = '%s', age = %d;", name, lastName, age));
            if (result == 1) {
                System.out.println("Пользователь " + name + " добавлен в базу данных");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void removeUserById(long id) {

        try {
            Connection connection = Util.getMySQLConnection();
            if (connection == null) {
                System.out.println("No connection");
                System.exit(0);
            }
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(String.format("DELETE FROM Users where id = %d;", id));
            if (result == 1) {
                System.out.println(String.format("Пользовател с id = %d успешно удален!", id));
            } else {
                System.out.println(String.format("Пользователь с указанным id = %d не найден.", id));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> listUsers = new ArrayList<>(0);
        try {
            Connection connection = Util.getMySQLConnection();
            if (connection == null) {
                System.out.println("No connection");
                System.exit(0);
            }
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
            System.out.println(e.getMessage());
        }
        return listUsers;
    }

    public void cleanUsersTable() {

        try {
            Connection connection = Util.getMySQLConnection();
            if (connection == null) {
                System.out.println("No connection");
                System.exit(0);
            }
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate("TRUNCATE TABLE Users;");
            if (result == 0) {
                System.out.println("Таблица Users успешно очищена.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
