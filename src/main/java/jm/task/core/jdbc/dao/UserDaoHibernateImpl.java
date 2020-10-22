package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao{
    final String DATABASE = "USER";
    final String TABLE_NAME = "User";
    private static Statement myStatement;
    Util util = new Util();
    private static SessionFactory sessionFactory;
    private static long counter = 0;
    private static boolean isbasecreate;

    public UserDaoHibernateImpl() {
        if (isbasecreate) {
            sessionFactory = util.getSessionFactory();
            System.out.println("Connection seccess");
        }

        try {
            util.connect();
            myStatement = util.getmyStatement();
            System.out.println("Connection seccess");
        } catch (Exception e) {
            System.out.println("Connection ERROR!!");
        }
    }


    @Override
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
        isbasecreate = true;
        try {
            myStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            myStatement.execute("DROP DATABASE " + DATABASE);
            System.out.println("Database " + "'" + DATABASE + "'" + " was removed");
        } catch (SQLException throwables) {
            System.out.println("ERROR!! Database" + "'" + DATABASE + "'" + " remove problem");
        }
        try {
            myStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            User user = new User();
            counter++;
            user.setId(counter);
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);

            session.save(user);
            session.getTransaction().commit();
            System.out.println("User с именем " + "'" + name + "'" + "добавлен в базу данных");
            session.close();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            myStatement.execute("DELETE FROM " + DATABASE + "." + TABLE_NAME + " WHERE id='" + id + "';");
            System.out.println("User with id=" + id + " deleted");
        } catch (SQLException throwables) {
        }
        try {
            myStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
<<<<<<< Updated upstream
        try {
            Session session = sessionFactory.openSession();
            session.getTransaction();
=======
        try (Session session = sessionFactory.openSession()) {
>>>>>>> Stashed changes

            allUsers = session.createQuery("From " + User.class.getSimpleName()).list();
            session.close();
        } catch (Exception x) {
            System.out.println("ERROR!");
        }
        return allUsers;
    }


    @Override
    public void cleanUsersTable() {
        try {
            myStatement.execute("DELETE FROM " + DATABASE + "." + TABLE_NAME);
            System.out.println("Clearing table success");
        } catch (SQLException throwables) {
        }
        try {
            myStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}