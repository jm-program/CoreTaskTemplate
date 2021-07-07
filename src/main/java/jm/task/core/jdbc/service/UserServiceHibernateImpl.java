package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.*;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceHibernateImpl implements UserService {
    private final UserDao hQuery = new UserDaoHibernateImpl();

    @Override
    public void createUsersTable() throws SQLException {
        hQuery.createUsersTable();
    }

    @Override
    public void dropUsersTable() throws SQLException {
        hQuery.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        hQuery.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) throws SQLException {
        hQuery.removeUserById(id);
    }

    @Override
    public void updateUser(long id, String name, String lastName, byte age) {
        hQuery.updateUser(id, name, lastName, age);
    }

    @Override
    public List<User> getAllUsers() {
        return hQuery.getAllUsers();
    }

    @Override
    public void cleanUsersTable() throws SQLException {
        hQuery.cleanUsersTable();
    }
}
