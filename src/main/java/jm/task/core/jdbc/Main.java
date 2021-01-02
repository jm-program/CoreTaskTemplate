package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl service = new UserServiceImpl();
        User user1 = new User("Ivan", "Baranov", (byte) 29);
        User user2 = new User("Igor", "Krenov", (byte) 45);
        User user3 = new User("Kirill", "Struganov", (byte) 67);
        User user4 = new User("Mosya", "Robinson", (byte) 89);
        service.createUsersTable();
        service.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        service.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        service.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        service.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        System.out.println(service.getAllUsers());
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
