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

    private static Connection connection;
    public void createUsersTable() {
        try {
            if(connection == null){
                connection = Util.getDBConnection();
            }
            Statement stmt = connection.createStatement();

            String creatTable = "CREATE TABLE IF NOT EXISTS user_table"
                + "(id INTEGER not NULL AUTO_INCREMENT PRIMARY KEY ,"
                + "name VARCHAR(50),"
                + "lastName VARCHAR(50),"
                + "age INT(3))";
            stmt.executeUpdate(creatTable);
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try{
            if(connection == null){
                connection = Util.getDBConnection();
            }
            Statement stmt = connection.createStatement();
            String dropTable = "DROP TABLE IF EXISTS user_table";
            stmt.executeUpdate(dropTable);
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
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
            PreparedStatement stmt = connection.prepareStatement(data);
            stmt.setString(1,user.getName());
            stmt.setString(2,user.getLastName());
            stmt.setInt(3,user.getAge());
            stmt.executeUpdate();

            ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
            if(rs.next()){
                id = rs.getLong(1);
            }

            stmt.close();
//          Statement statement = connection.createStatement();
//           statement.executeQuery("");
//            user.setId(rs.getLong(1));
            String add = new String("User с именем – " + user.getName() +" добавлен в базу данных");
            System.out.println(add);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        user.setId( id);

    }

    public void removeUserById(long id) {
        String SQL = ("DELETE FROM user_table WHERE ID = "+ id);
        try {
            if(connection == null){
                connection = Util.getDBConnection();
            }
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(SQL);
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String SQL = "SELECT * FROM user_table";
        List<User> list = new ArrayList<>();
        try{
            if(connection == null){
                connection = Util.getDBConnection();
            }
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(SQL);
            while (resultSet.next()){
                list.add( new User(resultSet.getLong(1),resultSet.getString(2)
                    ,resultSet.getString(3)
                    ,(byte)resultSet.getInt(4)));
            }
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        String SQL = "DELETE FROM user_table";
        try {
            if(connection == null){
                connection = Util.getDBConnection();
            }
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(SQL);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
