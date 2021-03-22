package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService  {
    UserDao usd = new UserDaoJDBCImpl();

    public void createUsersTable() throws SQLException, ClassNotFoundException {
        usd.createUsersTable();
    }

    public void dropUsersTable() throws SQLException, ClassNotFoundException {
        usd.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException, ClassNotFoundException {
        usd.saveUser(name,lastName, age);
    }

    public void removeUserById(long id) throws SQLException, ClassNotFoundException {
        usd.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {

        return usd.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException, ClassNotFoundException {
        usd.cleanUsersTable();

    }
}
