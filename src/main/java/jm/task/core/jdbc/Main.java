package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();
        userDaoHibernate.saveUser("Pikachu", "Raychu", (byte)66);
        userDaoHibernate.saveUser("Pik5achu", "Ray5chu", (byte)6);
        userDaoHibernate.saveUser("Piktfdtachu", "Ray5chu", (byte)6);
        userDaoHibernate.saveUser("Pik5chu", "Ray5chu", (byte)6);
        userDaoHibernate.saveUser("Pik5u", "Ray5chu", (byte)6);
        userDaoHibernate.saveUser("Pik5achu", "Ry5chu", (byte)6);
        userDaoHibernate.saveUser("Pik5hu", "Ray5chu", (byte)6);
        userDaoHibernate.cleanUsersTable();
//         userDaoHibernate.removeUserById(1);
        userDaoHibernate.getAllUsers();

//        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
//        userDaoJDBC.createUsersTable();
//        userDaoJDBC.cleanUsersTable();
//        userDaoJDBC.saveUser("АЛЁШКА", "Й", (byte) 66);
//        userDaoJDBC.saveUser("АРКАДИЧ", "ПИКАЧУ", (byte) 13);
//        userDaoJDBC.saveUser("АРМАНЫЧ", "DEAD", (byte) 999);
//        userDaoJDBC.getAllUsers();
////        userDaoJDBC.removeUserById(1);
////        userDaoJDBC.dropUsersTable();


    }
}
