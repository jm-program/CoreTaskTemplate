package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;

public class UserServiceImpl implements UserService {
   // Надо поменять ообъект на Hibernate

    private final UserDaoHibernateImpl userDaoJDBC = new UserDaoHibernateImpl();
    //private final UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

    public void createUsersTable() throws SQLException {
        userDaoJDBC.createUsersTable();
       // sessionFactory.close();
    }

    public void dropUsersTable() throws SQLException {
        userDaoJDBC.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        userDaoJDBC.saveUser(name, lastName, age);

    }

    public void removeUserById(long id) throws SQLException {
        userDaoJDBC.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
return  userDaoJDBC.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        userDaoJDBC.cleanUsersTable();
    }
}
