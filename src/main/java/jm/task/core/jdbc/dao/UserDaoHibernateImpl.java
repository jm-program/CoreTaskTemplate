package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

import static jm.task.core.jdbc.util.Util.*;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = getSessionFactory();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (`id` INT NOT NULL AUTO_INCREMENT," +
                    " `name` VARCHAR(45) NULL, " +
                    "`lastName` VARCHAR(45) NULL, " +
                    "`age` INT(45) NULL, PRIMARY KEY (`id`), " +
                    "UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);")
                    .executeUpdate();
            session.getTransaction().commit();
            session.close();
    }

    @Override
    public void dropUsersTable() {Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS my_db_test.users;").executeUpdate();
            session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            session.close();

        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    @Override
    public void removeUserById(long id) {Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
            session.close();
    }

    @Override
    public List<User> getAllUsers() {Session session = sessionFactory.openSession();
        return session.createQuery("From User").list();
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("DELETE from User").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
