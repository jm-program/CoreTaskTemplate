package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
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
        int id = 1;
        Util util = new Util();
        Connection connection = util.getConnection();
        //PreparedStatement preparedStatement = null;
        try {
            //String sql = "INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT INTO  user (id, name, lastName, age) VALUES(?, ?, ?, ?);");
            //preparedStatement = connection.prepareStatement();
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastName);
            preparedStatement.setInt(4, age);

            preparedStatement.executeUpdate();

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
