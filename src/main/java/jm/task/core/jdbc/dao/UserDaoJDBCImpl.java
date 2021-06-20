package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Util util;
    private Statement statement;
    private Connection connection;
    private PreparedStatement pstmt;

    public UserDaoJDBCImpl() {
        util = new Util();
        try {
            connection = util.connect();
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка загрузки драйвера JDBC");
        } catch (SQLException e) {
            System.out.println("Ошибка подключение к БД " + e);
        }
    }

    public void createUsersTable() {
        try {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS user (id INTEGER not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(50), lastName VARCHAR (50),  age INTEGER,PRIMARY KEY (id))");
        } catch (SQLException e) {
            System.out.println("Ошибка создание таблицы");
        }
    }

    public void dropUsersTable() {
        try {
            pstmt = connection.prepareStatement("DROP TABLE IF EXISTS user");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) { //Сохранить пользователя
        try {
            pstmt = connection.prepareStatement("insert into user (name, lastName, age)" +
                    " values (?, ?, ?)");
            pstmt.setString(1, name);
            pstmt.setString(2, lastName);
            pstmt.setInt(3, age);
            pstmt.executeUpdate();
            System.out.println(" User с именем – "+name+" добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try {
            pstmt = connection.prepareStatement("delete from user where id = ?");
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        int count = 0;
        try {
            pstmt = connection.prepareStatement("select * from user");
            //pstmt.setString(1,"user");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getString(2), rs.getString(3), rs.getByte(4)));
                list.get(count).setId(rs.getLong(1));
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try {
            pstmt = connection.prepareStatement("TRUNCATE TABLE user;");
            // pstmt.setString(1,"user");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
