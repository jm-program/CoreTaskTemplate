package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {
    UserDao dao = new UserDaoJDBCImpl();

    public UserDaoHibernateImpl() {

    }

    public void createUsersTable() {
        dao.createUsersTable();
    }

    public void dropUsersTable() {
        dao.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = this.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = this.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Session session = this.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            list = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
            System.out.println(list);
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = this.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
