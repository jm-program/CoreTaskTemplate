package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.SqlQuery;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static final String CREATE_TABLE = SqlQuery.CREATE.toString();
    private static final String DROP_TABLE = SqlQuery.DROP.toString();
    private static final String TRUNCATE_TABLE = SqlQuery.TRUNCATE.toString();
    private static final String SELECT_ALL = SqlQuery.SELECT.toString();
    private static final String SELECT_USER = SqlQuery.SELECT_USER.toString();
    private static final String SELECT_USER_ID = SqlQuery.SELECT_BY_ID.toString();
    private static final String INSERT_USER = SqlQuery.INSERT.toString();
    private static final String UPDATE_USER = SqlQuery.UPDATE_USER.toString();
    private static final String DELETE_USER = SqlQuery.DELETE.toString();
    public UserDaoHibernateImpl() {

    }
    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        try {
            if (session != null) {
                session.beginTransaction();
                Query query = session.createSQLQuery(CREATE_TABLE);
                query.executeUpdate();
                session.getTransaction().commit();
                session.close();
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        try {
            if (session != null) {
                session.beginTransaction();
                Query query = session.createSQLQuery(DROP_TABLE);
                query.executeUpdate();
                session.getTransaction().commit();
                session.close();
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        try {
            if (session != null) {
                Query select = session.createSQLQuery(SELECT_USER);
                select.setParameter(1, name)
                      .setParameter(2, lastName)
                      .setParameter(3, age);
                if (((org.hibernate.query.Query)select).list().isEmpty()) {
                    session.beginTransaction();
                    Query query = session.createSQLQuery(INSERT_USER);
                    query.setParameter(1, name)
                         .setParameter(2, lastName)
                         .setParameter(3, age);
                    query.executeUpdate();
                    session.getTransaction().commit();
                    System.out.printf("\nПользователь %s %s успешно добавлен.", lastName, name);
                } else {
                    System.err.println("\nТакой пользователь уже существует.");
                }
                session.close();
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        try {
            if (session != null) {
                session.beginTransaction();
                Query query = session.createSQLQuery(DELETE_USER);
                query.setParameter(1, id);
                query.executeUpdate();
                session.getTransaction().commit();
                session.close();
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(long id, String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        try {
            if (session != null) {
                Query select = session.createSQLQuery(SELECT_USER_ID);
                select.setParameter(1, id);
                if (((org.hibernate.query.Query)select).list().isEmpty()) {
                    session.beginTransaction();
                    Query query = session.createSQLQuery(UPDATE_USER);
                    query.setParameter(1, name)
                            .setParameter(2, lastName)
                            .setParameter(3, age);
                    query.executeUpdate();
                    session.getTransaction().commit();
                    System.out.println("Данные успешно изменены.");
                } else {
                    System.out.println("В процессе изменения данных произошла ошибка.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        Session session = Util.getSessionFactory().openSession();
        try {
            if (session != null) {
                session.beginTransaction();
                users = session.createNativeQuery(SELECT_ALL, User.class).list();
                session.getTransaction().commit();
                session.close();
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        try {
            if (session != null) {
                session.beginTransaction();
                Query query = session.createSQLQuery(TRUNCATE_TABLE);
                query.executeUpdate();
                session.getTransaction().commit();
                session.close();
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }
}
