package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.*;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService service = new UserServiceImpl();
        UserService service2 = new UserServiceHibernateImpl();
        Connection connection = Util.connect();
        Util.printConnectionInfo(connection);

        service.createUsersTable();
        getUsers(service);
        System.out.println("\nДобавляем пользователей...");
        service.saveUser("Тимур", "Батрутдинов", (byte) 38);
        service.saveUser("Гарик", "Мартиросян", (byte) 44);
        service.saveUser("Гарик", "Мартиросян", (byte) 44);
        service.saveUser("Павел", "Воля", (byte) 40);
        service.saveUser("Гарик", "Харламов", (byte) 44);
        service.saveUser("Семен", "Слепаков", (byte) 41);
        System.out.println();
        getUsers(service);

        System.out.print("\nУдаляем пользователя с номером id 3");
        service2.removeUserById(3);
        getUsers(service);

        System.out.println("\nМеняем данные пользователя с номером id 4");
        service2.updateUser(4, "Нурлан", "Сабуров", (byte) 36);
        getUsers(service);

        System.out.println("\nОчищаем и удаляем таблицу пользователей");
        service.cleanUsersTable();
        getUsers(service);
        service.dropUsersTable();




        service2.createUsersTable();
        getUsers(service2);
        System.out.println("Добавляем пользователей...");
        service2.saveUser("Тимур", "Батрутдинов", (byte) 44);
        service2.saveUser("Гарик", "Мартиросян", (byte) 44);
        service2.saveUser("Гарик", "Харламов", (byte) 44);
        service2.saveUser("Павел", "Воля", (byte) 40);
        service2.saveUser("Гарик", "Мартиросян", (byte) 44);
        service2.saveUser("Семен", "Слепаков", (byte) 41);
        getUsers(service2);

        System.out.print("\nУдаляем пользователя с номером id 3");
        service2.removeUserById(3);
        getUsers(service2);

        System.out.println("\nМеняем данные пользователя с номером id 4");
        service2.updateUser(4, "Нурлан", "Сабуров", (byte) 36);
        getUsers(service2);

        System.out.println("Очищаем и удаляем таблицу пользователей");
        service2.cleanUsersTable();
        getUsers(service2);
        service2.dropUsersTable();
    }

    private static void getUsers(UserService service) {
        List<User> users = service.getAllUsers();
        if (users.isEmpty()) {
            System.out.printf("\nТекущее количество пользователей пользователей: %d\n", 0);
        } else {
            System.out.printf("\nТекущее количество пользователей пользователей: %d\n", users.size());
            System.out.printf("%-9s%s\n", "", "ТАБЛИЦА ПОЛЬЗОВАТЕЛЕЙ");
            System.out.printf("%-1s%-6s%-10s%-15s%s\n", "", "ID", "ИМЯ", "ФАМИЛМЯ", "ВОЗРАСТ");
            System.out.println("----:-------:-----------------:--------:");
            users.stream()
                 .sorted((u1, u2) -> u1.getName().compareToIgnoreCase(u2.getName()))
                 .forEach(System.out::println);
        }
    }
}
