package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {


    public void createUsersTable() {
        UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
        dao.createUsersTable();
    }

    public void dropUsersTable() {
        UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
        dao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
        dao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
        dao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return new UserDaoJDBCImpl().getAllUsers();
    }

    public void cleanUsersTable() {
        UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
        dao.cleanUsersTable();
    }
}
