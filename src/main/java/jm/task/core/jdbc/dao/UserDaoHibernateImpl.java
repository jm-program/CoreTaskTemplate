package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        sessionFactory = Util.getSessionFactory();
    }

    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.createSQLQuery("CREATE TABLE USERS (" +
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "name varchar(255) NOT NULL," +
                    "lastName varchar(255) NOT NULL," +
                    "age int NOT NULL);")
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getCause().getCause().getMessage());
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.createSQLQuery("DROP TABLE USERS")
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getCause().getCause().getMessage());
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM User WHERE id = :id")
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> listUsers = session.createQuery("from User").list();
        session.getTransaction().commit();
        session.close();
        return listUsers;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM User")
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
