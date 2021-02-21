package jm.task.core.jdbc;

import java.util.ArrayList;
import java.util.List;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        List <User> listUser = new ArrayList<>();

         final String testName = "Ivan";
         final String testLastName = "Ivanov";
         final byte testAge = 5;
//        listUser.add(new User("Ivan","Ivanov",(byte)36));
//        listUser.add(new User("Petr","Petrov",(byte)27));
//        listUser.add(new User("Klim","Klimov",(byte)42));
//
//        for(User user: listUser){
//            System.out.println(user.getName() + " " + user.getLastName() + " " + user.getAge() + " лет");
//        }
        UserServiceImpl usi = new UserServiceImpl();
//        usi.cleanUsersTable();
        usi.createUsersTable();
        usi.saveUser(testName,testLastName, testAge);
        usi.dropUsersTable();
    }
}
