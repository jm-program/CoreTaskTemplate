package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl serv = new UserServiceImpl();
        serv.createUsersTable();

        serv.saveUser("Petr", "Petrov", (byte) 24);
        serv.saveUser("Igor", "Ivanov", (byte) 35);
        serv.saveUser("Ivan", "Sidorov", (byte) 30);
        serv.saveUser("Alexsander", "Romanov", (byte) 40);

        List<User> users = serv.getAllUsers();
        for(User u: users){
            System.out.println(u.toString());
        }

        serv.cleanUsersTable();
        serv.dropUsersTable();
    }
}
