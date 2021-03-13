package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }
    SessionFactory sessionFactory = Util.getHibernateSessionFactory();
    @Override
    public void createUsersTable() {
        try(Session session = sessionFactory.openSession();){
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS userTable (" +
                    "id BIGINT(19) NOT NULL AUTO_INCREMENT UNIQUE," +
                    "name VARCHAR(45) NOT NULL," +
                    "lastName VARCHAR(45) NOT NULL," +
                    "age TINYINT(3) NOT NULL," +
                    "PRIMARY KEY (id));").executeUpdate();
        }catch (Exception exception){
            System.out.println("Error createUsersTable");
        }
    }

    @Override
    public void dropUsersTable() {
        try(Session session = sessionFactory.openSession();){
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS usertable;").executeUpdate();
        }catch (Exception exception){
            System.out.println("Error dropUsersTable");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = sessionFactory.openSession();){
            session.beginTransaction();
            session.save(new User(name, lastName, age));
        }catch (Exception exception){
            System.out.println("Error saveUser\n" + exception);
        }
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = sessionFactory.openSession();){
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM user WHERE Id = id;")
                    .setParameter("id", id)
                    .executeUpdate();
        }catch (Exception exception){
            System.out.println("Error removeUserById\n" + exception);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try(Session session = sessionFactory.openSession();){
            session.beginTransaction();
            userList = session.createQuery("FROM User").list();
        }catch (Exception exception){
            System.out.println("Error getAllUsers\n" + exception);
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = sessionFactory.openSession();){
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE usertable;").executeUpdate();
        }catch (Exception exception){
            System.out.println("Error cleanUsersTable\n" + exception);
        }
    }
}
