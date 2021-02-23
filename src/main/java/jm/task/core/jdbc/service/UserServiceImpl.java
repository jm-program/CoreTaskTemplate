package jm.task.core.jdbc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;
import jm.task.core.jdbc.util.Util;

public class UserServiceImpl extends UserDaoJDBCImpl implements UserService {
  public void createUsersTable() {
    super.createUsersTable();
  }

  public void dropUsersTable() {
    super.dropUsersTable();
  }

  public void saveUser(String name, String lastName, byte age) {
    super.saveUser(name,lastName,age);
  }

  public void removeUserById(long id) {
    super.removeUserById(id);
  }

  public List<User> getAllUsers() {
    return super.getAllUsers();
  }

  public void cleanUsersTable() {
    super.cleanUsersTable();
  }
}
