package jm.task.core.jdbc;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import java.util.Iterator;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws SQLException {
//через hibernate
        new UserServiceImpl().createUsersTable();// добавление таблицы

        new UserServiceImpl().saveUser("Александр", "Пушкин", (byte) 37);
        new UserServiceImpl().saveUser("Владимир", "Высотский", (byte) 41);
        new UserServiceImpl().saveUser("Зинаида", "Серебрякова", (byte) 69);
        new UserServiceImpl().saveUser("ВладимирДоп", "Набоков", (byte) 65);
        new UserServiceImpl().saveUser("new", "new_Набоков", (byte) 65);

//        new UserDaoHibernateImpl().removeUserById(2);

        List<User> arr = new UserServiceImpl().getAllUsers();
        arr.stream().forEach(user -> System.out.println(user.toString()));
        new UserServiceImpl().cleanUsersTable();// Работет



        Util.getSessionFactory().close();
        //new UserServiceImpl().dropUsersTable();// Удаление таблицы
//------------

        //new UserServiceImpl().removeUserById(3);


       //List<User> arr = new UserServiceImpl().getAllUsers();
       //arr.stream().forEach(user -> System.out.println(user.toString()));

        //new UserServiceImpl().cleanUsersTable();// Раблтет
      //new UserServiceImpl().dropUsersTable();// Удаление таблицы


    }
}
