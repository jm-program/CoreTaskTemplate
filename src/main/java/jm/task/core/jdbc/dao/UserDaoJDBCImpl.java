package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();
    private static Statement myStatement;
    final String DATABASE = "USER";
    final String TABLE_NAME = "User";

    public UserDaoJDBCImpl() {
        try {
            util.connect();
            myStatement = util.getmyStatement();
            System.out.println("Connection seccess");
        } catch (Exception e) {
            System.out.println("Connection ERROR!!");
        }
    }

    public void createUsersTable() {

        try {
            myStatement.execute("CREATE DATABASE " + DATABASE);
            System.out.println("Database " + "'" + DATABASE + "'" + " creation success");
        } catch (SQLException throwables) {
            System.out.println("Database " + "'" + DATABASE + "'" + " is exits");
        }
        try {
            myStatement.executeUpdate("CREATE TABLE " + DATABASE + "." + TABLE_NAME + "(" +
                    "id INT AUTO_INCREMENT ," +
                    "name VARCHAR(40) NOT NULL," +
                    "lastname VARCHAR(40)," +
                    "age TINYINT(3)," +
                    "PRIMARY KEY (id))");
            System.out.println("Table " + TABLE_NAME + " creation success");
        } catch (SQLException throwables) {
            System.out.println(throwables);
            System.out.println("ERROR!! Table " + TABLE_NAME + " creation");
        }
    }

    public void dropUsersTable() {
        try {
            myStatement.execute("DROP DATABASE " + DATABASE);
            System.out.println("Database " + "'" + DATABASE + "'" + " was removed");
        } catch (SQLException throwables) {
            System.out.println("ERROR!! Database" + "'" + DATABASE + "'" + " remove problem");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            myStatement.execute("INSERT INTO " + DATABASE + "." + TABLE_NAME + " (name, lastname, age) " +
                    "VALUES(" + "'" + name + "', '" + lastName + "', '" + age + "')");
            System.out.println("User с именем " + "'" + name + "'" + "добавлен в базу данных");
        } catch (SQLException throwables) {
        }
    }

    public void removeUserById(long id) {
        try {
            myStatement.execute("DELETE FROM " + DATABASE + "." + TABLE_NAME + " WHERE id='" + id + "';");
            System.out.println("User with id=" + id + " deleted");
        } catch (SQLException throwables) {
        }
    }

    public List<User> getAllUsers() {
        ResultSet tmp = null;
        try {
            tmp = myStatement.executeQuery("SELECT * FROM " + DATABASE + "." + TABLE_NAME + ";");
        } catch (SQLException throwables) {
            System.out.println("Reading Table Error");
        }
        List<User> userlist = new ArrayList<>();
        try {
            while (tmp.next()) {
                User user = new User(String.valueOf(tmp.getObject(2)),
                        String.valueOf(tmp.getObject(3)), Byte.parseByte(String.valueOf(tmp.getObject(4))));
                user.setId(Long.parseLong(String.valueOf(tmp.getObject(1))));
                userlist.add(user);
            }
        } catch (SQLException throwables) {
            System.out.println("Table '" + TABLE_NAME + "' was flush");
        }
        for (int i = 0; i < userlist.size(); i++) {
            System.out.println(userlist.get(i));
        }

        return userlist;
    }

    public void cleanUsersTable() {
        try {
            myStatement.execute("DELETE FROM " + DATABASE + "." + TABLE_NAME);
            System.out.println("Clearing table success");
        } catch (SQLException throwables) {
        }
    }
}