package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Main {


    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        UserService userServ = new UserServiceImpl();
        userServ.createUsersTable();
        userServ.saveUser("John", "Nikson", (byte) 56);
        userServ.saveUser("Sarah", "Konor", (byte) 34);
        userServ.saveUser("Peter", "Mexes", (byte) 33);
        userServ.saveUser("Leam", "Howlet", (byte) 51);

        System.out.println(userServ.getAllUsers());

        userServ.removeUserById(2);
        userServ.cleanUsersTable();
        userServ.dropUsersTable();

        Util.getConnectionToDatabase().close();
    }
}


