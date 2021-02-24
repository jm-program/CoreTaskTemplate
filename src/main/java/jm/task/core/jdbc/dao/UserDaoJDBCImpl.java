package jm.task.core.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection connection = null;
        Statement stmt = null;
        try {
            if(connection == null){
                connection = Util.getDBConnection();
            }

            stmt = connection.createStatement();
            String creatTable = "CREATE TABLE IF NOT EXISTS user_table"
                + "(id LONG not NULL AUTO_INCREMENT PRIMARY KEY ,"
                + "name VARCHAR(50),"
                + "lastName VARCHAR(50),"
                + "age INT(3))";
            stmt.executeUpdate(creatTable);

            System.out.println("Таблица создана!!!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try{connection.close();}catch (Exception ignored){}
            try{stmt.close();}catch (Exception ignored){}
        }
    }

    public void dropUsersTable() {
        Connection connection = null;
        Statement stmt = null;
        try{
            if(connection == null){
                connection = Util.getDBConnection();
            }
            stmt = connection.createStatement();
            String dropTable = "DROP TABLE IF EXISTS user_table";
            stmt.executeUpdate(dropTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try{connection.close();}catch (Exception ignored){}
            try{stmt.close();}catch (Exception ignored){}
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = null;
        PreparedStatement stmt = null;
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        long id  = -1;
        user.setId( id);
        try {
            if(connection == null){
                connection = Util.getDBConnection();
            }
            String data = "INSERT INTO user_table(name,lastName,age) VALUES (?,?,?)";
            stmt = connection.prepareStatement(data);
            stmt.setString(1,user.getName());
            stmt.setString(2,user.getLastName());
            stmt.setInt(3,user.getAge());
            stmt.executeUpdate();

            ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
            if(rs.next()){
                id = rs.getLong(1);
            }
            String add = new String("User с именем – " + user.getName() +" добавлен в базу данных");
            System.out.println(add);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try{connection.close();}catch (Exception ignored){}
            try{stmt.close();}catch (Exception ignored){}
        }
        user.setId( id);

    }

    public void removeUserById(long id) {
        Connection connection = null;
        Statement stmt = null;
        String SQL = ("DELETE FROM user_table WHERE ID = "+ id);
        try {
            if(connection == null){
                connection = Util.getDBConnection();
            }
            stmt = connection.createStatement();
            stmt.executeUpdate(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try{connection.close();}catch (Exception ignored){}
            try{stmt.close();}catch (Exception ignored){}
        }
    }

    public List<User> getAllUsers() {
        Connection connection = null;
        Statement stmt = null;
        String SQL = "SELECT * FROM user_table";
        List<User> list = new ArrayList<>();
        try{
            if(connection == null){
                connection = Util.getDBConnection();
            }
            stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(SQL);
            while (resultSet.next()){
                list.add( new User(resultSet.getLong(1),resultSet.getString(2)
                    ,resultSet.getString(3)
                    ,(byte)resultSet.getInt(4)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try{connection.close();}catch (Exception ignored){}
            try{stmt.close();}catch (Exception ignored){}
            try{stmt.close();}catch (Exception ignored){}
        }
        return list;
    }

    public void cleanUsersTable() {
        Connection connection = null;
        Statement stmt = null;
        String SQL = "DELETE FROM user_table";
        try {
            if(connection == null){
                connection = Util.getDBConnection();
            }
            stmt = connection.createStatement();
            stmt.executeUpdate(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try{connection.close();}catch (Exception ignored){}
            try{stmt.close();}catch (Exception ignored){}
        }
    }
}
