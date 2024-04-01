package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static Util table = null;
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            table =  Util.createTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try {
            table.dropTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        table = null;
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            table.saveUser(name, lastName, age);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try {
            table.removeUserById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        LinkedList<User> list;
        try {
            list = table.getAllUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void cleanUsersTable() {
        try {
            table.clearTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
