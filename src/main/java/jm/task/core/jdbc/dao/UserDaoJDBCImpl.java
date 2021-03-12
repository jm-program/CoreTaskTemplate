package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    // Создание таблицы User(ов)
    public void createUsersTable() {
        Util util = new Util();
        Connection connection = util.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            //statement.execute("INSERT INTO developers(name, salary) VALUES('biba', 100500);");
            statement.execute("CREATE TABLE User(\n" +
                    "   id INT,\n" +
                    "   name VARCHAR(50),\n" +
                    "   lastName VARCHAR(50),\n" +
                    "   age INT\n" +
                    ")");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Создание таблицы User");
    }

    //Удаление таблицы
    public void dropUsersTable() {

    }
    //Добавление 4 User(ов) в таблицу с данными на свой выбор
    public void saveUser(String name, String lastName, byte age) {
        Util util = new Util();
        Connection connection = util.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            //statement.execute("INSERT INTO developers(name, salary) VALUES('biba', 100500);");
            statement.execute("INSERT INTO user (name, lastname, age) VALUES " +
                    "(?, ?, ?); ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Добавление 4 User(ов) в таблицу");
    }
    //???
    public void removeUserById(long id) {

    }
    //Получение всех User из базы и вывод в консоль
    public List<User> getAllUsers() {
        return null;
    }
    //Очистка таблицы User(ов)
    public void cleanUsersTable() {

    }
}
