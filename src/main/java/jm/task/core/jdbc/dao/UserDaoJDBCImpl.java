package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    // Создание таблицы User(ов)
    public void createUsersTable() {
        Connection connection = Util.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            statement.execute("CREATE TABLE IF NOT EXISTS user3(\n" +
                    "   id INT AUTO_INCREMENT,\n" +
                    "   PRIMARY KEY (id), \n" +
                    "   name VARCHAR(50),\n" +
                    "   lastName VARCHAR(50),\n" +
                    "   age INT\n" +
                    ")");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Удаление таблицы
    public void dropUsersTable() {
        Connection connection = Util.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            statement.execute("DROP TABLE IF EXISTS user3;");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Добавление 4 User(ов) в таблицу с данными на свой выбор
    public void saveUser(String name, String lastName, byte age) {
        Connection connection = Util.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT INTO  user3 (name, lastName, age) VALUES(?, ?, ?);");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();

            System.out.println("User с именем – "+name+" добавлен в базу данных");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Удаление User из таблицы ( по id )
    public void removeUserById(long id) {
        Connection connection = Util.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("DELETE FROM user3 WHERE id = (?)");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Получение всех User из базы и вывод в консоль
    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();

        Connection connection = Util.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM user3");
            result = preparedStatement.executeQuery();

            while(result.next()) {
                String name = result.getString("name");
                String lastName = result.getString("lastName");
                Byte age = result.getByte("age");

                userList.add(new User(name, lastName, age));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    //Очистка таблицы User(ов)
    public void cleanUsersTable() {
        Connection connection = Util.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            statement.execute("DELETE FROM user3;");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
