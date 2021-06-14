package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.*;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao query = new UserDaoJDBCImpl();

    public void createUsersTable() throws SQLException {
        query.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        query.dropUsersTable();
    }

    public void saveUser(String firstName, String lastName, byte age) throws SQLException {
        query.saveUser(firstName, lastName, age);
    }

    public void removeUserById(long id) throws SQLException {
        query.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return new  UserDaoJDBCImpl().getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        query.cleanUsersTable();
    }
}
