package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl daoHibernate = new UserDaoHibernateImpl();
        daoHibernate.createUsersTable();
        User roman = new User("Roman", "Patrushev", (byte) 26);
        daoHibernate.saveUser(roman.getName(), roman.getLastName(), roman.getAge());
        User vova = new User("Vladimir", "Putin", (byte) 69);
        daoHibernate.saveUser(vova.getName(), vova.getLastName(), vova.getAge());
        User andrey = new User("Andrey", "Gubin", (byte) 47);
        daoHibernate.saveUser(andrey.getName(), andrey.getLastName(), andrey.getAge());
        User pjer = new User("Pjer", "Narciss", (byte) 44);
        daoHibernate.saveUser(pjer.getName(), pjer.getLastName(), pjer.getAge());
        List<User> users = daoHibernate.getAllUsers();
        System.out.println(users);
        daoHibernate.cleanUsersTable();
        daoHibernate.dropUsersTable();
    }
}
