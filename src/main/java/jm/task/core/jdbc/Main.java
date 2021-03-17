package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class Main {


    private static final String INSERT_NEW = "INSERT INTO users VALUES(?,?,?,?)";


    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        final UserService userService = new UserServiceImpl();

        try {
            userService.createUsersTable();

            userService.saveUser("Stive2", "Libovski", (byte) 43);
            userService.saveUser("Stive3", "Libovski", (byte) 43);
            userService.saveUser("Stive4", "Libovski", (byte) 43);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
