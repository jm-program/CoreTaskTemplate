package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
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
            statement.execute("CREATE TABLE User3(\n" +
                    //auto_increment + primare key, для генерации id
                    "   id INT AUTO_INCREMENT,\n" +
                    "   PRIMARY KEY (id), \n" +
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
            statement.execute("DROP TABLE developers;");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Удаление таблицы");

    }
    //Добавление 4 User(ов) в таблицу с данными на свой выбор
    public void saveUser(String name, String lastName, byte age) {
       // int id = 1;
        Util util = new Util();
        Connection connection = util.getConnection();
        //PreparedStatement preparedStatement = null;
        try {
            //String sql = "INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT INTO  user3 (name, lastName, age) VALUES(?, ?, ?);");
            //preparedStatement = connection.prepareStatement();
            //preparedStatement.setInt(1, id);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);

            //закидывает данные в table
            preparedStatement.executeUpdate();
            System.out.println("User с именем – "+name+" добавлен в базу данных");

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

        List<User> userList = new ArrayList<>();

        Util util = new Util();
        Connection connection = util.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM USER3");
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        try {
            //executeQuery применяется для SELECT
            result = preparedStatement.executeQuery();

            while(result.next()) {
                //System.out.println(result.getString("user_name")); // считываем имя пользователя, полученной записи
                String name = result.getString("name");
                String lastName = result.getString("lastName");
                Byte age = result.getByte("age");

                userList.add(new User(name, lastName, age));
                //System.out.println(user.get(1)+"asdasdasdas");
            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //System.out.println("LIST");


        return userList;
    }

    //Очистка таблицы User(ов)
    public void cleanUsersTable() {

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
            statement.execute("DELETE FROM user;");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Создание таблицы User");
    }
}
