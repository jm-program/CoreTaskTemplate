package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.saveUser("АЛЁШКА", "Й", (byte) 66);
        userDaoJDBC.saveUser("АРКАДИЧ", "ПИКАЧУ", (byte) 13);
        userDaoJDBC.saveUser("АРМАНЫЧ", "DEAD", (byte) 999);
        userDaoJDBC.getAllUsers();
        userDaoJDBC.removeUserById(1);
        userDaoJDBC.dropUsersTable();


    }
}
