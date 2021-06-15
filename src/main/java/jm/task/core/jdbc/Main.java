package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("Тимур", "Батрутдинов", (byte) 38);
        service.saveUser("Гарик", "Мартиросян", (byte) 44);
        service.saveUser("Павел", "Воля", (byte) 40);
        service.saveUser("Семен", "Слепаков", (byte) 41);
        for (User user : service.getAllUsers()) {
            System.out.printf("%s", user);
        }
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
