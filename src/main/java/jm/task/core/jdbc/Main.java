package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("Батрутдинов", "Тимур", (byte) 38);
        service.saveUser("Мартиросян", "Гарик", (byte) 44);
        service.saveUser("Воля", "Павел", (byte) 40);
        service.saveUser("Слепаков", "Семен", (byte) 41);
        System.out.println(service.getAllUsers());
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
