package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.LinkedList;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    public static void main(String[] args) {

    }
    private static Connection getConnection()  throws SQLException{
        Connection connection;
        Driver driver = new com.mysql.cj.jdbc.Driver();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return connection;
    }
    private static void staticCommand(String command) throws  SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.execute(command);
        statement.close();
        connection.close();
    }

    public static Util createTable() throws SQLException {
        staticCommand("CREATE TABLE mydbtest.users (\n" +
                "  id INT NOT NULL AUTO_INCREMENT,\n" +
                "  name VARCHAR(45) NOT NULL,\n" +
                "  lastName VARCHAR(45) NOT NULL,\n" +
                "  age INT(3) NOT NULL,\n" +
                "  PRIMARY KEY (id));");
        return new Util();
    }
    public void dropTable() throws SQLException {
        staticCommand("DROP TABLES users;");
    }
    public void clearTable() throws SQLException {
        staticCommand("DELETE FROM users;");
    }
    public LinkedList<User> getAllUsers() throws SQLException {
        LinkedList<User> list = new LinkedList<>();
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users;");
        while (resultSet.next()) {
            User user = new User(resultSet.getString("name"),
                    resultSet.getString("lastName"),
                    resultSet.getByte("age"));
            user.setId(resultSet.getLong("id"));
            list.add(user);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return list;
    }
    public void removeUserById(long id) throws  SQLException {
        staticCommand(String.format("DELETE FROM users WHERE id = %d;", id));
    }
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        staticCommand(String.format("INSERT INTO users (name, lastName, age) VALUES ('%s', '%s', %d)",
                name, lastName, age));
    }
}
