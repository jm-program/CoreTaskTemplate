package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args){
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();
//        userDaoHibernate.saveUser("First", "first", (byte)10);
//        userDaoHibernate.saveUser("Second", "second", (byte)20);
//        userDaoHibernate.saveUser("Third", "third", (byte)30);
//        userDaoHibernate.saveUser("Fourth", "fourth", (byte)40);
        userDaoHibernate.getAllUsers();
//        userDaoHibernate.cleanUsersTable();
//        userDaoHibernate.dropUsersTable();
////
//     UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
//        userDaoJDBC.createUsersTable();
//        userDaoJDBC.saveUser("Lwrw", "Alewrvich", (byte)24);
//        userDaoJDBC.saveUser("QRQrq", "Aqr3y", (byte)29);
//        userDaoJDBC.getAllUsers();
//        userDaoJDBC.cleanUsersTable();
//        userDaoJDBC.dropUsersTable();



    }
}
