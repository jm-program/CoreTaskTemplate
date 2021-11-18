package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    Util conect = new Util();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Statement statement;
        String sql = "create table if not exists minions\n" +
                "(\n" +
                "\tid int auto_increment,\n" +
                "\tname varchar(50) null,\n" +
                "\tlastName varchar(50) null,\n" +
                "\tage int null,\n" +
                "\tconstraint minions_pk\n" +
                "\t\tprimary key (id)\n" +
                ");";

        try (Connection connection = conect.getConnection()) {
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        Statement statement;
        String dropSql = "drop table if exists minions";
        try (Connection connection = conect.getConnection()) {
            statement = connection.createStatement();
            statement.executeUpdate(dropSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement;
        String userSql = "insert into minions (name, lastname, age) values (?, ?, ?)";
        Connection connection = conect.getConnection();
        try  {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(userSql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("Миньён c именем " + name + " добавлен в таблицу");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


    public void removeUserById(long id) {
        PreparedStatement preparedStatement;
        String removeSql = "delete from minions where id = ?";
        Connection connection = conect.getConnection();
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(removeSql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("Миньён под номером :" + id + " был удален.");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    public List<User> getAllUsers() {

        List<User> list = new ArrayList<>();
        Statement statement;
        String allSql = "select * from minions";
        Connection connection = conect.getConnection();
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(allSql);
            while (result.next()) {
                User user = new User();
                user.setId(result.getLong("id"));
                user.setName(result.getString("name"));
                user.setLastName(result.getString("lastName"));
                user.setAge(result.getByte("age"));
                list.add(user);
            }
            connection.commit();
            System.out.println(list);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }

    public void cleanUsersTable() {
        Statement statement;
        String cleanSql = "truncate table minions";
        try (Connection connection = conect.getConnection()) {
            statement = connection.createStatement();
            statement.executeUpdate(cleanSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


