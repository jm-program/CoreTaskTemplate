package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String URL ="jdbc:mysql://localhost:3306/mydbtest2?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    String table = "new_table2";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {

        Util util = new Util();
        Connection conn = util.getConnection(URL,USERNAME,PASSWORD);

        Statement statement = conn.createStatement();
        String SQL = "CREATE TABLE IF NOT EXISTS `mydbtest2`.`"+ table  + "` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NULL,\n" +
                "  `lastname` VARCHAR(45) NULL,\n" +
                "  `age` TINYINT NULL,\n" +
                "  PRIMARY KEY (`id`));";
        //statement.executeUpdate("USE new_table2;");//назначить бд используемой
        statement.executeUpdate(SQL);
        System.out.println("Базу " +"new_table2" + " создали.");
        statement.close();
        conn.close();

    }

    public void dropUsersTable() throws SQLException {
        Util util = new Util();
        Connection conn = util.getConnection(URL,USERNAME,PASSWORD);

        String SQL = "DROP TABLE IF EXISTS " + table; // String table = "new_table2";
        Statement statement = conn.createStatement();// создаем соединение

        statement.executeUpdate("USE mydbtest2");// Установили бд в качестве используемой
        try {
            statement.executeUpdate(SQL);
        } catch (SQLException throwables) {
            System.out.println("Не удалось удалить");
        }
        System.out.println("Базу " +"new_table2" + " удалили.");
        statement.close();
        conn.close();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        //добавить вывод в консиль
        Util util = new Util();
        Connection conn = util.getConnection(URL,USERNAME,PASSWORD);
        String SQL = "INSERT `" + table + "`(`name`, `lastname`, `age`) VALUES ('" +
                name + "', '" + lastName + "', '" + age + "')";
        Statement statement = conn.createStatement();// создаем соединение

        statement.executeUpdate("USE mydbtest2");// Установили бд в качестве используемой
        try {
            statement.executeUpdate(SQL);
        } catch (SQLException throwables) {
            System.out.println("Не удалось Сохранить юзера");
        }
        System.out.println(" User с именем – " + name + " добавлен в базу данных");
        statement.close();
        conn.close();
    }

    public void removeUserById(long id) throws SQLException {
        String SQL = "DELETE FROM " + table + " WHERE id = " + id;
        Util util = new Util();
        Connection conn = util.getConnection(URL,USERNAME,PASSWORD);

        Statement statement = conn.createStatement();// создаем соединение

        statement.executeUpdate("USE mydbtest2");// Установили бд в качестве используемой
        try {
            statement.executeUpdate(SQL);
        } catch (SQLException throwables) {
            System.out.println("Не удалось удалить юзера");
        }
        System.out.println("удалили  юзера с номером " +id);
        statement.close();
        conn.close();
    }

    public List<User> getAllUsers() throws SQLException {
        String SQL = "SELECT * FROM " + table ;
        List<User> listUsers = new ArrayList<User>();
        Util util = new Util();
        Connection conn = util.getConnection(URL,USERNAME,PASSWORD);
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL);
        // добавить запрос

        while (resultSet.next()) {
            int i = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String lastname = resultSet.getString("lastname");
            byte age = (byte) resultSet.getInt("age");

            User User = new User(name, lastname, age);
            listUsers.add(User);
        }
        return listUsers;

    }

    public void cleanUsersTable() throws SQLException {
        String SQL = "TRUNCATE TABLE " + table;
        Util util = new Util();
        Connection conn = util.getConnection(URL,USERNAME,PASSWORD);

        Statement statement = conn.createStatement();// создаем соединение

        statement.executeUpdate("USE mydbtest2");// Установили бд в качестве используемой
        try {
            statement.executeUpdate(SQL);
        } catch (SQLException throwables) {
            System.out.println("Не удалось очистить базу");
        }
        System.out.println("Базу очистили");
        statement.close();
        conn.close();
    }
}
