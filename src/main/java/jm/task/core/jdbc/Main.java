package jm.task.core.jdbc;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("a", "aa", (byte) 20);
        User user2 = new User("b", "bb", (byte) 21);
        User user3 = new User("c", "cc", (byte) 22);
        User user4 = new User("d", "dd", (byte) 23);


        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        ArrayList<User> a = (ArrayList<User>) userService.getAllUsers();
        for (User b : a) {
            System.out.print(b.toString());
        }
        userService.removeUserById(2);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
