package jm.task.core.jdbc;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        User user = new User("a", "aa", (byte) 00);
        User user0 = new User("b", "bb", (byte) 11);
        User user1 = new User("c", "cc", (byte) 22);
        User user2 = new User("d", "dd", (byte) 33);
        userService.saveUser(user.getName(), user.getLastName(), user.getAge());
        userService.saveUser(user0.getName(), user0.getLastName(), user0.getAge());
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());

        List<User> list = userService.getAllUsers();
        for (User a : list) {
            System.out.print(a.toString());
        }

        userService.removeUserById(3);

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
