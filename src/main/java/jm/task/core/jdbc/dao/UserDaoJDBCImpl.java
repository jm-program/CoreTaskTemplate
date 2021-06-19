package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.SqlQuery;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static String CREATE_TABLE = SqlQuery.CREATE.toString();
    private static String DROP_TABLE = SqlQuery.DROP.toString();
    private static String TRUNCATE_TABLE = SqlQuery.TRUNCATE.toString();
    private static String SELECT_ALL = SqlQuery.SELECT.toString();
    private static String SELECT_USER = SqlQuery.SELECT_USER.toString();
    private static String SELECT_USER_ID = SqlQuery.SELECT_BY_ID.toString();
    private static String INSERT_USER = SqlQuery.INSERT.toString();
    private static String UPDATE_USER = SqlQuery.UPDATE_USER.toString();
    private static String DELETE_USER = SqlQuery.DELETE.toString();

    public UserDaoJDBCImpl() {
    }

    @Override
    public void createUsersTable() {
        Connection connection = Util.connect();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_TABLE);
            connection.close();
        } catch (SQLException e) {
            e.getSQLState();
        }
    }

    @Override
    public void dropUsersTable() {
        Connection connection = Util.connect();
        try (Statement statement = connection.createStatement()) {
            statement.execute(DROP_TABLE);
            connection.close();
        } catch (SQLException e) {
            e.getSQLState();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Connection connection = Util.connect();
        PreparedStatement pstm = null;
        try {
            pstm = connection.prepareStatement(SELECT_USER);
            pstm.setString(1, name);
            pstm.setString(2, lastName);
            pstm.setByte(3, age);
            ResultSet result = pstm.executeQuery();
            if (result.next()) {
                System.err.print("\nТакой пользователь уже существует.");
                pstm.close();
            } else {
                pstm = connection.prepareStatement(INSERT_USER);
                pstm.setString(1, name);
                pstm.setString(2, lastName);
                pstm.setByte(3, age);
                int i = pstm.executeUpdate();
                if (i > 0) {
                    System.out.printf("\nПользователь  %s %s добавлен в таблицу.\n", lastName, name);
                } else {
                    System.err.println("\nНе удалось добавить пользователя.");
                }
                pstm.close();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Connection connection = Util.connect();
        PreparedStatement pstm = null;
        try {
            pstm = connection.prepareStatement(SELECT_USER_ID);
            pstm.setLong(1, id);
            ResultSet result = pstm.executeQuery();
            if (result.next()) {
                String name = result.getString("name");
                String lastName = result.getString("lastName");
                pstm = connection.prepareStatement(DELETE_USER);
                pstm.setLong(1, id);
                int i = pstm.executeUpdate();
                if (i > 0) {
                    System.out.printf("\nПользователь %s %s успешно удален\n", lastName, name);
                }
            } else {
                System.err.println("Пользователя с таким ID не существует.");
            }
            pstm.close();
            connection.close();
        } catch (SQLException e) {
            e.getSQLState();
        }
    }

    @Override
    public void updateUser(long id, String name, String lastName, byte age) {
        Connection connection = Util.connect();
        PreparedStatement pstm = null;
        try {
            connection.setAutoCommit(false);
            pstm = connection.prepareStatement(SELECT_USER_ID);
            pstm.setLong(1, id);
            ResultSet response = pstm.executeQuery();
            if (response.next()) {
                String oldUserName = response.getString("name");
                String oldUserLastName = response.getString("lastname");
                pstm = connection.prepareStatement(UPDATE_USER);
                pstm.setString(1, name);
                pstm.setString(2, lastName);
                pstm.setByte(3, age);
                pstm.setLong(4, id);
                int i = pstm.executeUpdate();
                connection.commit();
                if (i > 0) {
                    System.out.printf("Данные пользователя %s %s успешно изменены.", oldUserLastName, oldUserName);
                    System.out.printf("\nТекущие данные пользователя с id %d:" +
                            "\nФамилия: %s Имя: %s, возраст: %d.\n", id, lastName, name, age);
                    connection.close();
                } else {
                    System.err.println("\nНе удалось обновить данные пользователя.");
                    connection.close();
                }
            }
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    System.err.print("Выполняется откат транзакции");
                    connection.rollback();
                }
            } catch (SQLException throwables) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> all = new ArrayList<>();
        Connection connection = Util.connect();
        try (Statement statement = connection.createStatement()) {
            ResultSet response = statement.executeQuery(SELECT_ALL);
            while (response.next()) {
                User user = new User();
                user.setId(response.getLong("id"));
                user.setName(response.getString("name"));
                user.setLastName(response.getString("lastName"));
                user.setAge(response.getByte("age"));
                all.add(user);
            }
            connection.close();
        } catch (SQLException e) {
            e.getSQLState();
        }
        return all;
    }

    @Override
    public void cleanUsersTable() {
        Connection connection = Util.connect();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(TRUNCATE_TABLE);
            connection.close();
        } catch (SQLException e) {
            e.getSQLState();
        }
    }
}
