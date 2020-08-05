package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private String tableName = "users";
    Util util = new Util();
    Connection connection = null;
    Statement statement = null;

    public void createUsersTable() {
        String createUsersTable = "CREATE TABLE IF NOT EXISTS " +
                tableName +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY , " +
                "name VARCHAR(30) NOT NULL, " +
                "lastName VARCHAR(30) NOT NULL, " +
                "age MEDIUMINT NOT NULL)";
        try {
            connection = util.setConnection();
            statement = connection.createStatement();
            statement.execute(createUsersTable);
            System.out.println("Таблица создана");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        String dropUsersTable = "DROP TABLE IF EXISTS " +
                tableName;
        try {
            connection = util.setConnection();
            statement = connection.createStatement();
            statement.execute(dropUsersTable);
            System.out.println("Таблица удалена");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String saveUser = "INSERT INTO " +
                tableName +
                "(name , lastName, age) " +
                " values ('" + name + "', '" + lastName + "', '" + age + "')";
        try {
            connection = util.setConnection();
            statement = connection.createStatement();
            statement.executeUpdate(saveUser);
            System.out.println("user " + name + " добавлен");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        String removeUserById = "DELETE FROM " +
                tableName +
                " WHERE id = " + Long.toString(id);
        try {
            connection = util.setConnection();
            statement = connection.createStatement();
            statement.executeUpdate(removeUserById);
            System.out.println("user id = " + id +" удален");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try {
            connection = util.setConnection();
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT  * FROM " + tableName);

            while (resultSet.next()) {
                long id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                byte age = resultSet.getByte(4);
                User user = new User(name, lastName, age);
                users.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    public void cleanUsersTable() {
        String cleanUsersTable = "TRUNCATE TABLE " +
                tableName;
        try {
            connection = util.setConnection();
            statement = connection.createStatement();
            statement.execute(cleanUsersTable);
            System.out.println("Таблица очищена");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
