package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
    Util util = new Util();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        userDaoJDBC.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        userDaoJDBC.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        util.getFactory().getCurrentSession().beginTransaction();
        util.getFactory().getCurrentSession().save(new User(name, lastName, age));
        util.getFactory().getCurrentSession().getTransaction().commit();
        System.out.println("Миньён c именем " + name + " добавлен в таблицу");

    }

    @Override
    public void removeUserById(long id) {
        util.getFactory().getCurrentSession().beginTransaction();
        util.getFactory().getCurrentSession().delete(util.getFactory().getCurrentSession().get(User.class, id));
        util.getFactory().getCurrentSession().getTransaction().commit();
        System.out.println("Миньён под номером :" + id + " был удален.");
    }

    @Override
    public List<User> getAllUsers() {
        util.getFactory().getCurrentSession().beginTransaction();
        List<User> minions = util.getFactory().getCurrentSession().createQuery("from User").getResultList();
        util.getFactory().getCurrentSession().getTransaction().commit();
        System.out.println(minions);
        return minions;
    }

    @Override
    public void cleanUsersTable() {
        util.getFactory().getCurrentSession().beginTransaction();
        util.getFactory().getCurrentSession().createQuery("delete User").executeUpdate();
        util.getFactory().getCurrentSession().getTransaction().commit();
    }
}