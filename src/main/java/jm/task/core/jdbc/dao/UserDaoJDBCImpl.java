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
        try (Connection conn = Util.getConn();
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS users\n" +
                    "(\n" +
                    "\tid bigint(8) PRIMARY KEY AUTO_INCREMENT,\n" +
                    "\tname varchar(40) null,\n" +
                    "\tlastName varchar(40) null,\n" +
                    "\tage smallint null\n" +
                    ");";
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Database connection failed while creating new table");
        }
    }

    public void dropUsersTable() {
        try (Connection conn = Util.getConn();
             Statement stmt = conn.createStatement()) {
            String sql = "DROP TABLE IF EXISTS users";
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Database connection failed while dropping a table");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection conn = Util.getConn()) {
            String insert = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?);";
            PreparedStatement preparedStatement = conn.prepareStatement(insert);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.execute();
            System.out.println("User с именем – " + name + " добавлен в базу данных ");
        } catch (SQLException e) {
            System.err.println("Database connection failed while saving new user");
        }

    }

    public void removeUserById(long id) {
        try (Connection conn = Util.getConn();
             Statement stmt = conn.createStatement()) {
            String sql = "DELETE FROM users WHERE id=" + id;
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Database connection failed while removing user");
        }
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        try (Connection conn = Util.getConn();
             Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = (byte) resultSet.getInt("age");
                User user = new User(name, lastName, age);
                user.setId(id);
                result.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Database connection failed while getting all users");
        }
        return result;
    }

    public void cleanUsersTable() {
        try (Connection conn = Util.getConn();
             Statement stmt = conn.createStatement()) {
            String sql = "TRUNCATE TABLE users";
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Database connection failed while cleaning table");
        }
    }
}
