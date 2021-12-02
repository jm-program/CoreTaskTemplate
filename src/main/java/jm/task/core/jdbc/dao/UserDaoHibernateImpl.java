package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }
    SessionFactory sessionFactory = Util.getSession();
    @Override
    public void createUsersTable() {
        Session session = null;
        try  {
            session = sessionFactory.openSession();
            session.createSQLQuery("CREATE TABLE users.users" +
                    "(id int not null auto_increment," +
                    "name VARCHAR(50), " +
                    "lastname VARCHAR(50), " +
                    "age INT, " +
                    "PRIMARY KEY (id))").executeUpdate();
            session.beginTransaction().commit();
            System.out.println("Таблица создана");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.createSQLQuery("DROP TABLE users.users").executeUpdate();
            session.beginTransaction().commit();
            System.out.println("Таблица удалена");
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.save(new User(name, lastName, age));
            session.beginTransaction().commit();
            System.out.print(String.format("Пользователь-%s добавлен!%n", name));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        User user = null;
        try {
            session = sessionFactory.openSession();
            user = (User) session.get(User.class, id);
            session.delete(user);
            session.beginTransaction().commit();
            System.out.print(String.format("Пользователь под номером %d - удален!%n", id));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = null;
        List<User> list = new ArrayList<>();
        try {
            session = sessionFactory.openSession();
            Query qu = session.createSQLQuery("select id, name, lastName, age from users.users").addEntity(User.class);
            list = qu.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.createSQLQuery("DELETE FROM users.users").executeUpdate();
            session.beginTransaction().commit();
            System.out.println("Все пользователи удалены!");
        } catch (Exception a) {
            a.printStackTrace();
        } finally {
            session.close();
        }
    }
}
