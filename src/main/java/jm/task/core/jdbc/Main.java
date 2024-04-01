package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        User[] si = {new User("John", "Wein", (byte) 21),
                new User("Mike", "Schmidt", (byte) 12),
            new User("Steve", "Odd", (byte) 16),
            new User("Akane", "Minori", (byte) 17)};
        for (User di: si) {
            userService.saveUser(di.getName(), di.getLastName(), di.getAge());
        }
        userService.getAllUsers().forEach(a -> System.out.println(a.toString()));
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
