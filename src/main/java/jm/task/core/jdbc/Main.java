package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static void printUSer (User user) {
        System.out.println("User с именем - " + user.getName() + " добавлен в базу");
    }

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        final User user1 = new User("Harry","Potter",(byte) 20);
        final User user2 = new User("Peter","Parker",(byte) 21);
        final User user3 = new User("Harvey","Wanstein",(byte) 22);
        final  User user4 = new User ("Dastin","Hoffman",(byte) 23);

        userService.createUsersTable();

        userService.saveUser(user1.getName(), user1.getLastName(),user1.getAge());
        printUSer(user1);
        userService.saveUser(user2.getName(),user2.getLastName(), user2.getAge());
        printUSer(user2);
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        printUSer(user3);
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        printUSer(user4);
        userService.removeUserById(2);

        List<User> allUsers = userService.getAllUsers();
        allUsers.forEach(System.out :: println);

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

