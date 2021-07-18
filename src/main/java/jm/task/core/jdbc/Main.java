package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 25);
        userService.saveUser("Petr", "Petrov", (byte) 33);
        userService.saveUser("Alexandr", "Alexandrov", (byte) 32);
        userService.saveUser("Denis", "Denisov", (byte) 35);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
