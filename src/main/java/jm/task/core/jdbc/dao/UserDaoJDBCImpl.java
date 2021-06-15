package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Util util = new Util();

    public UserDaoJDBCImpl() {}

    public void createUsersTable() {
        try (PreparedStatement statement = util.connect()
                .prepareStatement("create table if not exists users " +
                        "(id Integer auto_increment unique not null, " +
                        "name varchar (255), " +
                        "lastName varchar (255), " +
                        "age int, " +
                        "primary key (id))")) {
            statement.execute();
            System.out.println("Таблица готова.");
        } catch (SQLException e) {
            System.err.format("Упсс...\nПохоже пользователь не был добавлен.\nSQL ERROR: %s\n", e.getSQLState());
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (PreparedStatement statement = util.connect().prepareStatement("drop table if exists users cascade")) {
            statement.execute();
            System.out.println("Таблица успешно удалена.");
        } catch (SQLException e) {
            System.err.format("Упсс...\nПохоже пользователь не был добавлен.\nSQL ERROR: %s\n", e.getSQLState());
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = util.connect()
                .prepareStatement("insert into users (name, lastName, age) values (?, ?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.executeUpdate();
            ResultSet test = statement.executeQuery("select * from users where name = '" + name + "'");
            while (test.next()) {
                System.out.printf("Пользователь с именем %s %s успешно добавлен\n", test.getString("name"), test.getString("lastName"));
            }
        } catch (SQLException e) {
            System.err.format("Упсс...\nПохоже пользователь %s %s не был добавлен.\nSQL ERROR: %s\n",
                    name, lastName, e.getSQLState());
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement statement = util.connect().prepareStatement("delete from users where id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("Пользователь удален\n");
        } catch (SQLException e) {
            System.err.format("Мы не уверены, но кажется id c номером %d в базе данных нет.\nSQL ERROR: %s\n",
                    id, e.getSQLState());
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> all = new ArrayList<>();
        try (PreparedStatement statement = util.connect().prepareStatement("select * from users")) {
            ResultSet response = statement.executeQuery();
            while (response.next()) {
                User users = new User();
                users.setId(response.getLong("id"));
                users.setName(response.getString("name"));
                users.setLastName(response.getString("lastName"));
                users.setAge((byte)response.getInt("age"));
                all.add(users);
            }
        } catch (SQLException e) {
            System.err.format("Не знаем, что вы задумали, но данных вам не видать!\nSQL ERROR: %s\n", e.getSQLState());
            e.printStackTrace();
        }
        return all;
    }

    public void cleanUsersTable() {
        try (Connection connection = util.connect()) {
            PreparedStatement statement = connection.prepareStatement("TRUNCATE TABLE users");
            statement.executeUpdate();
            System.out.println("\nТаблица успешно очищена.");
        } catch (SQLException e) {
            System.out.println("Невозможно очистить таблицу пользователей");
        }
    }
}
