package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    void createUsersTable() throws SQLException, ClassNotFoundException;

    void dropUsersTable() throws SQLException, ClassNotFoundException;

    void saveUser(String name, String lastName, byte age) throws SQLException, ClassNotFoundException;

    void removeUserById(long id) throws SQLException, ClassNotFoundException;

    List<User> getAllUsers() throws SQLException, ClassNotFoundException;

    void cleanUsersTable() throws SQLException, ClassNotFoundException;
}
