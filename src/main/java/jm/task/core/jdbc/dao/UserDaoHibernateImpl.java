package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UserDaoHibernateImpl extends UserDaoJDBCImpl implements UserDao {
    private SessionFactory sessionFactory = null;
    private Session session = null;
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try {
            String creatTable = "CREATE TABLE IF NOT EXISTS user_table"
                + "(id INTEGER not NULL AUTO_INCREMENT PRIMARY KEY ,"
                + "name VARCHAR(50),"
                + "lastName VARCHAR(50),"
                + "age INT(3))";
            sessionFactory = Util.getSessionFactory();
            session = sessionFactory.openSession();
//            session.beginTransaction();
            session.createSQLQuery(creatTable)
                .executeUpdate();
        }catch (Exception e){
            System.out.println("Ошибка creatTable: " + e);
        }finally {
            try{session.close();}catch (Exception ignored){};
            try{
                sessionFactory.getCurrentSession().close();

            }catch (Exception ignored){};
        }

    }

    @Override
    public void dropUsersTable() {
        try {
            sessionFactory = Util.getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.createSQLQuery("drop table user_table")
                .executeUpdate();
        }catch (Exception e){
            System.out.println("Ошибка dropTable: " + e);
        }finally {
            try{session.close();}catch (Exception ignored){};
            try{
                sessionFactory.getCurrentSession().close();

            }catch (Exception ignored){};
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
//        User user = new User(name,lastName,age);
        try{
            sessionFactory = Util.getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(new User(name,lastName,age));
            session.getTransaction().commit();
            String addUser = new String("User с именем – " + name +" добавлен в базу данных");
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
        try {
            sessionFactory = Util.getSessionFactory();
            session = sessionFactory.openSession();
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
        List<User> list = null;
        try {
            sessionFactory = Util.getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            list = session.createQuery("from User").list();
            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println("Ошибка в getAllUsers: "+e);
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
        try {
            sessionFactory = Util.getSessionFactory();
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println("Ошибка в cleanUsersTable: "+e);
        }finally {
            try{session.close();
                sessionFactory.getCurrentSession().close();
            }catch (Exception ignored){

            };
        }
    }
}
