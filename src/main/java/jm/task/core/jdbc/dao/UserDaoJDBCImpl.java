package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        Statement statement;
        String sql = "create table if not exists zergy\n" +
                "(\n" +
                "\tid int auto_increment,\n" +
                "\tname varchar(33) null,\n" +
                "\tlastName varchar(33) null,\n" +
                "\tage int null,\n" +
                "\tconstraint zergy_pk\n" +
                "\t\tprimary key (id)\n" +
                ");";
        try {
            statement = getConnect().createStatement();
            statement.execute(sql);
            System.out.println("Улей зародился, вызываем морпехов");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        Statement statement;
        String delete = "drop table if exists zergy";
        try {
            statement = getConnect().createStatement();
            statement.execute(delete);
            System.err.println("Проведена орбитальная бомбардировка, Улей уничтожен");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement;
        String newUser = "insert into zergy (name, lastName, age) values (?, ?, ?)";
        try {
            getConnect().setAutoCommit(false);
            try {
                preparedStatement = getConnect().prepareStatement(newUser);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);
                preparedStatement.execute();
                getConnect().commit();
                System.out.println("Морпех " + name + " прибыл");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                getConnect().rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        PreparedStatement preparedStatement;
        String remove = "delete from zergy where id = ?";
        try {
            getConnect().setAutoCommit(false);
            try{
                preparedStatement = getConnect().prepareStatement(remove);
                preparedStatement.setLong(1, id);
                preparedStatement.execute();
                getConnect().commit();
                System.err.println("Морпеха под номером " + id + " сожрали Zergy");
            } catch (SQLException e) {
                e.printStackTrace();
        }
    } catch (SQLException throwables) {
        throwables.printStackTrace();
        try {
            getConnect().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Statement statement;
        try {
            statement = getConnect().createStatement();
            ResultSet resultSet = statement
                    .executeQuery("select name, lastName, age from zergy");
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                String lastName = resultSet.getString(2);
                Byte age = resultSet.getByte(3);
                User user = new User(name, lastName, age);
                list.add(user);
            }
            System.out.println(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public void cleanUsersTable() {
        Statement statement;
        String clean = "truncate table zergy";
        try {
            statement = getConnect().createStatement();
            statement.execute(clean);
            System.err.println("Zergy очищенны огнём");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
