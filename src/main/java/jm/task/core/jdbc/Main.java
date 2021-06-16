package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService us = new UserServiceImpl();
        us.createUsersTable();
        us.saveUser("Ruslan","Baratov", (byte) 26);
        us.saveUser("Bobik","Popik", (byte) 16);
        us.saveUser("Andry","Pavlov", (byte) 25);
        us.saveUser("Maria","Sergeevna", (byte) 18);
        System.out.println(us.getAllUsers());
        us.cleanUsersTable();
        us.dropUsersTable();
    }
}
