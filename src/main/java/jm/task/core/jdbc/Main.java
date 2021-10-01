package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.dropUsersTable();
//        userService.createUsersTable();
    }
}
