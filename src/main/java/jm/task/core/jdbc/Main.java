package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        //This is a branch 114*
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
