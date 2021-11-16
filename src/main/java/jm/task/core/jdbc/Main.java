package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args){

     UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Lwrw", "Alewrvich", (byte)24);
        userDaoJDBC.saveUser("QRQrq", "Aqr3y", (byte)29);
//        userDaoJDBC.getAllUsers();
//        userDaoJDBC.cleanUsersTable();
//        userDaoJDBC.dropUsersTable();



    }
}
