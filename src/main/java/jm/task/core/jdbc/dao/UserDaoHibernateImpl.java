package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS user (id INTEGER not NULL AUTO_INCREMENT, " +
                " name VARCHAR(50), lastName VARCHAR (50),  age INTEGER,PRIMARY KEY (id))")
                .executeUpdate();
        t.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS user").executeUpdate();
        t.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(new User(name, lastName, age));
        tx1.commit();
        System.out.println(" User с именем – " + name + " добавлен в базу данных");
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        if (session.get(User.class, id) != null) {
            session.delete(session.get(User.class, id));
        }
        tx1.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        //  List<User> users = session.createSQLQuery("SELECT * FROM user").addEntity(User.class).list(); // SQL
        Query query = session.createQuery("from User"); // HQL место таблицы указывается имя класса
        List<User> users = query.list();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction t1 = session.beginTransaction();
         session.createQuery("delete from User").executeUpdate(); // HQL запрос
//        for (User user : getAllUsers()) {
//            session.delete(user); // CRITERIA API не проходит проверку почему-то =( ?
//        }
        t1.commit();
        session.close();
    }
}
