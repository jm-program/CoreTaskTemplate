package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connect;

    public UserDaoJDBCImpl() {}

    public void createUsersTable() throws SQLException {
        connect = new Util().connect();
        Statement statement = connect.createStatement();
        try {
            connect.setAutoCommit(false);
            statement.execute("create table if not exists users " +
                    "(id Integer auto_increment unique not null, " +
                    "name varchar (255), " +
                    "lastName varchar (255), " +
                    "age int, primary key (id))");
            connect.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Невозможно создать таблицу пользователей");
        } finally {
            if (statement != null && connect != null) {
                statement.close();
                connect.close();
            }
        }
    }

    public void dropUsersTable() throws SQLException {
        connect = new Util().connect();
        Statement statement = connect.createStatement();
        try {
            connect.setAutoCommit(false);
            statement.executeUpdate("drop table if exists users cascade");
            connect.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connect.rollback();
            System.out.println("Невозможно удалить таблицу пользователей");
        } finally {
            if (statement != null && connect != null) {
                statement.close();
                connect.close();
            }
        }
    }

    public void saveUser(String firstName, String lastName, byte age) throws SQLException {
        connect = new Util().connect();
        Statement statement = connect.createStatement();
        try {
            connect.setAutoCommit(false);

            statement.executeUpdate("insert into users " +
                    "(name, lastName, age) " +
                    "values ('" + firstName + "', '" + lastName + "', " + age + ")");

            ResultSet request = connect
                    .createStatement()
                    .executeQuery("select name from users where name = '" + firstName + "'");
            connect.commit();

            User user = new User();
            while (request.next()) {
                user.setName(request.getString("name"));
            }
            System.out.printf("Пользователь %s добавлен\n", user.getName());
        } catch (SQLException e) {
            System.out.println("Невозможно сохранить пользователей");
        } finally {
            if (statement != null && connect != null) {
                statement.close();
                connect.close();
            }
        }
    }

    public void removeUserById(long id) throws SQLException {
        connect = new Util().connect();
        Statement statement = connect.createStatement();
        try {
            if (id > 0) {
                connect.setAutoCommit(false);
                statement.executeUpdate("delete from users where id =" + id + "");
                connect.commit();
            }
        } catch (SQLException e) {
            System.out.println("Невозможно удалить пользователя");
        } finally {
            if (statement != null && connect != null) {
                statement.close();
                connect.close();
            }
        }
    }

    public List<User> getAllUsers() {
        try (Connection connect = new Util().connect()) {
            List<User> allUsers = new ArrayList<>();
            Statement statement = connect.createStatement();
            ResultSet request = statement.executeQuery("select * from users");
            while (request.next()) {
                User users = new User();
                users.setId(request.getLong("id"));
                users.setName(request.getString("name"));
                users.setLastName(request.getString("lastName"));
                users.setAge((byte)request.getInt("age"));
                allUsers.add(users);
            }
            return allUsers;
        } catch (SQLException e) {
            System.out.println("Невозможно получить пользователей");
        }
        return null;
    }

    public void cleanUsersTable() throws SQLException {
        connect = new Util().connect();
        Statement statement = connect.createStatement();
        try {
            connect.setAutoCommit(false);
            statement.execute("TRUNCATE TABLE users");
            connect.commit();
        } catch (SQLException e) {
            System.out.println("Невозможно очистить таблицу пользователей");
        } finally {
            if (statement != null && connect != null) {
                statement.close();
                connect.close();
            }
        }
    }
}
