package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Ivan","Ivanov", (byte) 25);
        userDaoJDBC.saveUser("Petr","Petrov", (byte) 33);
        userDaoJDBC.saveUser("Alexandr","Alexandrov", (byte) 32);
        userDaoJDBC.saveUser("Denis","Denisov", (byte) 35);
        System.out.println(userDaoJDBC.getAllUsers());
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();
    }
}
