package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("John", "Connor", (byte) 31);
        userService.saveUser("Jane", "Dow", (byte) 23);
        userService.saveUser("Donald", "Duck", (byte) 38);
        userService.saveUser("Elon", "Musk", (byte) 51);
        System.out.println(userService.getAllUsers().toString());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
