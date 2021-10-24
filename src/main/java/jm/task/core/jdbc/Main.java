package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {

    public static void main(String[] args) {
//        UserService userService = new UserServiceImpl();
//        userService.createUsersTable();
//        userService.saveUser("Vladimir", "Nuroev", (byte)53);
//        userService.saveUser("Vadim", "Rolnin", (byte)37);
//        userService.saveUser("Stanislav", "Petrov", (byte)44);
//        userService.saveUser("Oleg", "Igorev", (byte)29);
//        List<User> listUsers = userService.getAllUsers();
//        System.out.println(listUsers);
//        userService.cleanUsersTable();
//        userService.dropUsersTable();

        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();
        userDaoHibernate.saveUser("Vasily", "Petrov", (byte)48);
        userDaoHibernate.saveUser("Ivan", "Kuncevich", (byte)59);
        userDaoHibernate.saveUser("Alena", "Tibrec", (byte)22);
        userDaoHibernate.saveUser("Gleb", "Kuchma", (byte)36);
        List<User> listUsers = userDaoHibernate.getAllUsers();
        System.out.println(listUsers);
        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();
    }

}
