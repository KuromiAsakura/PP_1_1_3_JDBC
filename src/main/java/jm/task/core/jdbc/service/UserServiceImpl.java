package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.LinkedList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserDaoJDBCImpl daoJDBC = null;
    public void createUsersTable() {
        if(daoJDBC == null) {
            daoJDBC = new UserDaoJDBCImpl();
            daoJDBC.createUsersTable();
        }
    }

    public void dropUsersTable() {
        if (daoJDBC != null) {
            daoJDBC.dropUsersTable();
            daoJDBC = null;
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        if (daoJDBC != null) {
            daoJDBC.saveUser(name, lastName, age);
        }
    }

    public void removeUserById(long id) {
        if (daoJDBC != null) {
            daoJDBC.removeUserById(id);
        }
    }

    public List<User> getAllUsers() {
        if (daoJDBC != null) {
            return daoJDBC.getAllUsers();
        }
        return new LinkedList<>();
    }

    public void cleanUsersTable() {
        if (daoJDBC != null) {
            daoJDBC.cleanUsersTable();
        }
    }
}
