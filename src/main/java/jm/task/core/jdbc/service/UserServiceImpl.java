package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static final String URL ="jdbc:mysql://localhost:3306/business?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    String table = "new_table2";

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
        System.out.println("Сохранили юзера");
        statement.close();
        conn.close();

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
