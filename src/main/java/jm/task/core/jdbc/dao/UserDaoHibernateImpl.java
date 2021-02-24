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
        }catch (Exception e){
            System.out.println("Ошибка saveUser: " + e);
        }finally {
            try{session.close();}catch (Exception ignored){};
            try{sessionFactory.getCurrentSession().close();}catch (Exception ignored){};
        }


    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
