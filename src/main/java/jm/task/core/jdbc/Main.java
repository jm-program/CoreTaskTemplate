package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    //Comment
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Vladimir", "Nuroev", (byte)53);
        userService.saveUser("Vadim", "Rolnin", (byte)37);
        userService.saveUser("Stanislav", "Petrov", (byte)44);
        userService.saveUser("Oleg", "Igorev", (byte)29);
        List<User> listUsers = userService.getAllUsers();
        System.out.println(listUsers);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
