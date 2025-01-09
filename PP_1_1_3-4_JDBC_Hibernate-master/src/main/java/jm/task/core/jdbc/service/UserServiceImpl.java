package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserServiceImpl implements UserService {

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), lastName VARCHAR(50), age TINYINT)").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void saveUser(User user) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery("FROM User", User.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery("DELETE FROM users").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
