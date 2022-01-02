package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
        dao.createUsersTable();
        User roman = new User("Roman", "Patrushev", (byte) 26);
        dao.saveUser(roman.getName(), roman.getLastName(), roman.getAge());
        User vova = new User("Vladimir", "Putin", (byte) 69);
        dao.saveUser(vova.getName(), vova.getLastName(), vova.getAge());
        User andrey = new User("Andrey", "Gubin", (byte) 47);
        dao.saveUser(andrey.getName(), andrey.getLastName(), andrey.getAge());
        User pjer = new User("Pjer", "Narciss", (byte) 44);
        dao.saveUser(pjer.getName(), pjer.getLastName(), pjer.getAge());
        List<User> users = dao.getAllUsers();
        System.out.println(users);
        dao.cleanUsersTable();
        dao.dropUsersTable();
    }
}
