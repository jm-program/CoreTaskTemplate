package jm.task.core.jdbc;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;


public class Main {

    public static void main(String[] args) throws SQLException {

        new UserServiceImpl().createUsersTable();// добавление таблицы

        new UserServiceImpl().saveUser("Александр", "Пушкин", (byte) 37);
        new UserServiceImpl().saveUser("Владимир", "Высотский", (byte) 41);
        new UserServiceImpl().saveUser("Зинаида", "Серебрякова", (byte) 69);
        new UserServiceImpl().saveUser("Владимир", "Набоков", (byte) 65);

       // new UserServiceImpl().dropUsersTable();// Удаление таблицы


    }
}
