package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

public class Main {

    public static void main(String[] args) {
        // реализуйте алгоритм здесь
      UserService us = new UserServiceImpl();

        us.createUsersTable();
        us.saveUser("Ivan","Ivanov",(byte)37);
        us.saveUser("Petr","Petrov",(byte)14);
        us.saveUser("Gleb","Zemnuhov",(byte)17);
        us.saveUser("Ivan","Petrov",(byte)34);
//      System.out.println();
//      for (User u: us.getAllUsers()){
//        System.out.println(u.toString());
//      }
//      us.cleanUsersTable();
//      us.dropUsersTable();

      }
}
