package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDaoJDBCImpl implements UserDao {
    private final String URL = "jdbc:mysql://localhost:3306/test?useSSL=false";
    private final String USER = "root";
    private final String PASS = "root";
    private final List<User> users = new ArrayList<>();
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public UserDaoJDBCImpl() {
        Util.driverRegister();
    }

    public void createUsersTable() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS test.User (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NULL,\n" +
                    "  `lastname` VARCHAR(45) NULL,\n" +
                    "  `age` INT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);");
            statement.executeBatch();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (statement != null) {statement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS test.User;");
            statement.executeBatch();
            users.clear();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (statement != null) {statement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            preparedStatement = connection.prepareStatement("INSERT INTO test.User (name, lastname, age) VALUES (?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            System.out.println("User " + name + " was added to DB!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
                try {
                    if (preparedStatement != null) {preparedStatement.close();}
                    if (connection != null) {connection.close();}
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
    }

    public void removeUserById(long id) {
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            preparedStatement = connection.prepareStatement("DELETE FROM test.User WHERE id = ?;");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {preparedStatement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public List<User> getAllUsers() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM test.User");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                if (users.stream().noneMatch(t -> t.equals(user))) {
                    users.add(user);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {resultSet.close();}
                if (statement != null) {statement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return users;
    }

    public void cleanUsersTable() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            statement = connection.createStatement();
            statement.execute("TRUNCATE TABLE test.User;");
            statement.executeBatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
