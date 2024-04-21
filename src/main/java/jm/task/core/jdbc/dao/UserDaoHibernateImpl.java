package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final SessionFactory SESSIONFACTORY = Util.getSessionFactory();
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = SESSIONFACTORY.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS User " +
                    "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                    "age INT(3) NOT NULL)").executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = SESSIONFACTORY.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS User;").executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = SESSIONFACTORY.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = SESSIONFACTORY.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DELETE User WHERE id = :id")
                    .setParameter("id", id);
            transaction.commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = SESSIONFACTORY.openSession()) {
            Query<User> query = session.createQuery("FROM User", User.class);
            return query.list();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = SESSIONFACTORY.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DELETE FROM User;").executeUpdate();
            transaction.commit();
        }
    }
}
