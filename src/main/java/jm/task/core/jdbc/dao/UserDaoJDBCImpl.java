package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        staticStatement("CREATE TABLE mydbtest.users (\n" +
                "  id INT NOT NULL AUTO_INCREMENT,\n" +
                "  name VARCHAR(45) NOT NULL,\n" +
                "  lastName VARCHAR(45) NOT NULL,\n" +
                "  age INT(3) NOT NULL,\n" +
                "  PRIMARY KEY (id));");
    }

    public void dropUsersTable() {
        staticStatement("DROP TABLES users;");
    }

    public void saveUser(String name, String lastName, byte age) {
        staticStatement(String.format("INSERT INTO users (name, lastName, age) VALUES ('%s', '%s', %d)",
                name, lastName, age));
    }

    public void removeUserById(long id) {
        staticStatement(String.format("DELETE FROM users WHERE id = %d;", id));
    }

    public List<User> getAllUsers() {
        LinkedList<User> list = new LinkedList<>();
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users;");
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                list.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void cleanUsersTable() {
        staticStatement("DELETE FROM users;");
    }
    private void staticStatement(String command) {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(command);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
