package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Дмитрий", "Коледа", (byte) 27);
        userService.saveUser("Арина", "Науменко", (byte) 27);
        userService.saveUser("Сергей", "Науменко", (byte) 28);
        userService.saveUser("Николай", "Науменко", (byte) 53);

        List<User> allUsers = userService.getAllUsers();
        allUsers.forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
