package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import jm.task.core.jdbc.util.Util;

public class UserServiceImpl implements UserService {
    public void createUsersTable() throws SQLException {
        final String INSERT_NEW = "CREATE TABLE IF NOT EXISTS `users` (\n" +
                "  `id` bigint NOT NULL AUTO_INCREMENT,\n" +
                "  `name` varchar(255) NOT NULL,\n" +
                "  `lastName` varchar(255) NOT NULL,\n" +
                "  `age` tinyint NOT NULL,\n" +
                "  PRIMARY KEY (`id`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8";

        Util worker = new Util();
        PreparedStatement preparedStatement = worker.getConnection().prepareStatement(INSERT_NEW);
        preparedStatement.execute();

    }

    public void dropUsersTable() throws SQLException {
        final String INSERT_NEW = "DROP TABLE  IF EXISTS users";
        final String INSERT_USERTABLE_EXIST = "";

        Util worker = new Util();
        PreparedStatement preparedStatement = worker.getConnection().prepareStatement(INSERT_NEW);
        preparedStatement.execute();

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        final String INSERT_NEW = "INSERT INTO users(name,lastName,age) VALUES(?,?,?)";
//                                  INSERT INTO users (name,lastName,age) VALUES('Ivan','Ivanov',25)
        Util worker = new Util();
        PreparedStatement preparedStatement = worker.getConnection().prepareStatement(INSERT_NEW);

        preparedStatement.setString(1, name);
        preparedStatement.setString(2, lastName);
        preparedStatement.setByte(3, age);

        preparedStatement.execute();
    }

    public void removeUserById(long id) throws SQLException {
        final String INSERT_NEW = "DELETE FROM users WHERE id =?";

        Util worker = new Util();
        PreparedStatement preparedStatement = worker.getConnection().prepareStatement(INSERT_NEW);
        preparedStatement.setLong(1, id);
        preparedStatement.execute();

    }

    public List<User> getAllUsers() throws SQLException {

        Util worker = new Util();
        List<User> users = new ArrayList<>();

        String query = "SELECT * FROM users";
        Statement statement = worker.getConnection().createStatement();

        ResultSet resultSet = statement.executeQuery(query);


        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setLastName(resultSet.getString("lastName"));
            user.setAge(resultSet.getByte("age"));
            users.add(user);
        }

        return users;
    }

    public void cleanUsersTable() throws SQLException {
        final String INSERT_NEW = "DELETE FROM users";

        Util worker = new Util();
        PreparedStatement preparedStatement = worker.getConnection().prepareStatement(INSERT_NEW);
        preparedStatement.execute();
    }
}
