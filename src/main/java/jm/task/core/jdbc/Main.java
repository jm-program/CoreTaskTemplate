package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDao daoHibernate = new UserDaoHibernateImpl();
        daoHibernate.createUsersTable();
        daoHibernate.saveUser("Roman", "Patrushev", (byte) 26);
        daoHibernate.saveUser("Vladimir", "Putin", (byte) 69);;
        daoHibernate.saveUser("Andrey", "Gubin", (byte) 47);
        daoHibernate.saveUser("Pjer", "Narciss", (byte) 44);
        System.out.println(daoHibernate.getAllUsers());
        daoHibernate.cleanUsersTable();
        daoHibernate.dropUsersTable();
    }
}
