package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        sessionFactory = Util.getInstance().getSessionFactory();
    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS USERS (" +
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "name varchar(255) NOT NULL," +
                    "lastName varchar(255) NOT NULL," +
                    "age int NOT NULL);")
                    .executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction =  session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS USERS")
                    .executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.createQuery("DELETE FROM User WHERE id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> listUsers = new ArrayList<>(0);
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            listUsers = session.createQuery("from User").list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listUsers;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User")
                    .executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
