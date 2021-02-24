package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UserDaoHibernateImpl extends UserDaoJDBCImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        super.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        super.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name,lastName,age);
        SessionFactory sessionFactory = null;
        Session session = null;
        try{
            sessionFactory = Util.getSessionFactory();
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            String addUser = new String("User с именем – " + user.getName() +" добавлен в базу данных");
            System.out.println(addUser);
        }catch (Exception e){
            System.out.println("Ошибка saveUser: " + e);
        }finally {
            try{session.close();}catch (Exception ignored){};
            try{
                sessionFactory.getCurrentSession().close();

            }catch (Exception ignored){};
        }

    }

    @Override
    public void removeUserById(long id) {
        SessionFactory sessionFactory = null;
        Session session = null;
        try {
            sessionFactory = Util.getSessionFactory();
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            User user = (User) session.load(User.class, id);
            session.delete(user);
            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println("Ошибка в removeUserById: "+e);
        }finally {
            try{session.close();}catch (Exception ignored){};
            try{
                sessionFactory.getCurrentSession().close();
            }catch (Exception ignored){};
        }
    }

    @Override
    public List<User> getAllUsers() {
        SessionFactory sessionFactory = null;
        Session session = null;
        List<User> list = null;
        try {
            sessionFactory = Util.getSessionFactory();
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            list = session.createQuery("from User").list();
            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println("Ошибка в removeUserById: "+e);
        }finally {
            try{session.close();}catch (Exception ignored){};
            try{
                sessionFactory.getCurrentSession().close();
            }catch (Exception ignored){};
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        SessionFactory sessionFactory = null;
        Session session = null;
        try {
            sessionFactory = Util.getSessionFactory();
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println("Ошибка в removeUserById: "+e);
        }finally {
            try{session.close();}catch (Exception ignored){};
            try{
                sessionFactory.getCurrentSession().close();
            }catch (Exception ignored){};
        }
    }
}
