package jm.task.core.jdbc;

import java.sql.*;
import java.util.List;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Миша", "Иванов", (byte) 20);
        userService.saveUser("Дима", "Сидоров", (byte) 28);
        userService.saveUser("Женя", "Петухов", (byte) 35);
        userService.saveUser("Коля", "Петров", (byte) 40);
        List<User> tmp = userService.getAllUsers();
        userService.removeUserById(1L);
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}