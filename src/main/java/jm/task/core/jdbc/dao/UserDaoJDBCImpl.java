package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private String create = "CREATE TABLE IF NOT EXISTS user" +
            "(id BIGINT not NULL AUTO_INCREMENT , /n " +
            " name VARCHAR(50) not NULL, /n" +
            " lastname VARCHAR(50) not NULL, /n " +                                    // так ?
            " age TINYINT not NULL, /n" +
            " PRIMARY KEY (id)) /n";
    private String insert = "INSERT INTO user (name, last name, age) Values(?, ?, ?);";
    private String removeUserById = "DELETE FROM user WHERE id = ?";
    private String delete = "DELETE FROM tb_name;";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException, ClassNotFoundException {
        Statement statement2 = Util.getConnectionToDatabase().createStatement();
        statement2.executeUpdate("CREATE TABLE IF NOT EXISTS user2" +
                "(id BIGINT not NULL AUTO_INCREMENT ," +
                " name VARCHAR(50) not NULL," +
                " lastname VARCHAR(50) not NULL, " +
                " age TINYINT not NULL," +
                " PRIMARY KEY (id))");

        System.out.println("Таблица создана успешно");
        statement2.close();
        System.out.println("statement закрыт");
    }

    public void dropUsersTable() throws SQLException, ClassNotFoundException {
        Statement statement2 = Util.getConnectionToDatabase().createStatement(); // Создаем соединение с БД
        statement2.executeUpdate("TRUNCATE user2;");
        System.out.println("Таблица удалена");
        statement2.close();
        System.out.println("Соединение закрыто");



    }

    public void saveUser(String name, String lastName, byte age) throws SQLException, ClassNotFoundException {
        Statement statement2 = Util.getConnectionToDatabase().createStatement();// Создаем соединение с БД
//        statement2.executeUpdate(String.format("INSERT INTO FROM user2 WHERE name=%s, lastname=%s, age=%d;", name, lastName, age));
        statement2.executeUpdate("insert user2 (name, lastName, age) values " +
                "(\'" + name + "\', \'" + lastName +"\', " + age + ");");
        statement2.close();
        System.out.println("Соединение закрыто");
    }

    public void removeUserById(long id) throws SQLException, ClassNotFoundException {
        Statement statement2 = Util.getConnectionToDatabase().createStatement();// Создаем соединение с БД
        statement2.executeUpdate(String.format("DELETE FROM user2 WHERE id=%d;", id)); // созд. стэйтмент для раработы с данными
        System.out.println("удаление юзера под id= " + id + " выполнено");
        statement2.close();
        System.out.println("Соединение закрыто");
    }

    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {
        String showALL = "SELECT * FROM user2;";

        Statement statement2 = Util.getConnectionToDatabase().createStatement();
        ResultSet resultSet = statement2.executeQuery(showALL);
        List<User> list = new ArrayList<>();

        while (resultSet.next()) {
            String name;
            String lastname;
            byte age;
            name = resultSet.getString("name");
            lastname = resultSet.getString("lastname");
            age = resultSet.getByte("age");
            list.add(new User(name, lastname, age));
        }
        statement2.close();
        Util.getConnectionToDatabase().close();
        System.out.println("Соединение закрыто");
        return list;
    }

    public void cleanUsersTable() throws SQLException, ClassNotFoundException {
        Statement statement2 = Util.getConnectionToDatabase().createStatement();// Создаем соединение с БД
        statement2.executeUpdate("DELETE FROM user2;");
        System.out.println("Удаление выполнено");
        statement2.close();
        System.out.println("Соединение закрыто");
    }
}
