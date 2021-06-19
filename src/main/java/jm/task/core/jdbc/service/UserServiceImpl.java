package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.*;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao query = new UserDaoJDBCImpl();
    private final UserDao hQuery = new UserDaoHibernateImpl();

    @Override
    public void createUsersTable() throws SQLException {
        query.createUsersTable();
        hQuery.createUsersTable();
    }

    @Override
    public void dropUsersTable() throws SQLException {
        query.dropUsersTable();
        hQuery.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        query.saveUser(name, lastName, age);
        hQuery.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) throws SQLException {
        query.removeUserById(id);
        hQuery.removeUserById(id);
    }

    @Override
    public void updateUser(long id, String name, String lastName, byte age) {
        query.updateUser(id, name, lastName, age);
        hQuery.updateUser(id, name, lastName, age);
    }

    @Override
    public List<User> getAllUsers() {
        return hQuery.getAllUsers();
    }

    @Override
    public void cleanUsersTable() throws SQLException {
        query.cleanUsersTable();
        hQuery.cleanUsersTable();
    }
}
