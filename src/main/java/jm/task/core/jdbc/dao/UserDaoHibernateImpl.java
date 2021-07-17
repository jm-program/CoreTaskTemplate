package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();//открыли сессию
        Transaction tx1 = session.beginTransaction();
        SQLQuery query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS `newBD`.`users` " +
                "(`id` INT NOT NULL AUTO_INCREMENT,`name` VARCHAR(45) NOT NULL," +
                "`lastName` VARCHAR(45) NOT NULL,`age` INT NOT NULL," +
                " PRIMARY KEY (`id`), UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);").addEntity(User.class);
        query.executeUpdate();
        tx1.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();//открыли сессию
        Transaction tx1 = session.beginTransaction();
        SQLQuery query = session.createSQLQuery("DROP TABLE if EXISTS users").addEntity(User.class);
        query.executeUpdate();
        tx1.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name,lastName,age);
        Session session = Util.getSessionFactory().openSession();//открыли сессию
        Transaction tx1 = session.beginTransaction();//получил транзакцию
        session.save(user);
        tx1.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();//открыли сессию
        Transaction tx1 = session.beginTransaction();//получил транзакцию
        Query query = session.createQuery("DELETE User WHERE id = :id");
        query.setParameter("id", id).executeUpdate();
        tx1.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();//открыли сессию
        Transaction tx1 = session.beginTransaction();//получил транзакцию
        Query query = session.createQuery("FROM User");
        List<User> users = query.list();
        tx1.commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();//открыли сессию
        Transaction tx1 = session.beginTransaction();//получил транзакцию
        session.createQuery("DELETE FROM User").executeUpdate();
        tx1.commit();
        session.close();
    }
}
