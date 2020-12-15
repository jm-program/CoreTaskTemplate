package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    String table = "new_table";
    public UserDaoHibernateImpl() {

    }
// Сюда по новой записать методы
    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS `mydbtest2`.`"+ table  + "` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NULL,\n" +
                "  `lastname` VARCHAR(45) NULL,\n" +
                "  `age` TINYINT NULL,\n" +
                "  PRIMARY KEY (`id`));";
        Session session = Util.getSession();
        session.createSQLQuery(sql).executeUpdate();//прочитать подробнее почему executeUpdate озвращает int
        Transaction transaction = session.beginTransaction();
        transaction.commit();
        //System.out.println("Таблица готова");
        session.close();
    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = Util.getSession();
            Transaction transaction = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS new_table";
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();
        session.close();

    }

    @Override
    public void removeUserById(long id) {
    Session session = Util.getSession();
    Transaction transaction = session.beginTransaction();

    Query query = session.createQuery("DELETE User WHERE id = :id");
//  String SQL = "DELETE FROM " + table + " WHERE id = " + id;
//  Query query = session.createSQLQuery(SQL);
    query.setParameter("id", id);
    query.executeUpdate();
    transaction.commit();
    session.close();
    }

    @Override

    public List<User> getAllUsers()
    {
    Session session = Util.getSession();
    List <User> user =null;
    user = session.createQuery("From User").list();
    session.close();
        return user;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();//
        String HQL= "DELETE FROM User";
        session.createQuery(HQL).executeUpdate();
        transaction.commit();
        session.close();
    }

}
