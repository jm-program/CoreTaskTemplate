package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class    UserDaoJDBCImpl implements UserDao {

    private final Connection connection = Util.SQLConnection();

    public UserDaoJDBCImpl()  {

    }

    public void createUsersTable() {
        String SqlCreate = "CREATE TABLE IF NOT EXISTS user " +
                "( id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR (20), lastName VARCHAR (20), " +
                "age SMALLINT NOT NULL, PRIMARY KEY (id))";

        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlCreate)) {
            preparedStatement.executeUpdate(SqlCreate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(PreparedStatement preparedStatement = connection.prepareStatement
                ("INSERT INTO user  (name,lastName,age) VALUES(?,?,?)")) {
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();
            connection.setAutoCommit(false);
            connection.commit();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try(PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE id = ?")){
            statement.setLong(1,id);
            statement.executeUpdate();
            connection.setAutoCommit(false);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List <User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM user");
             ResultSet resultSet = preparedStatement.executeQuery("SELECT * FROM user")){
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
                connection.setAutoCommit(false);
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try(Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM user");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
