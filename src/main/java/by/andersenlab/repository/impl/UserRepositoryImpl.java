package by.andersenlab.repository.impl;

import by.andersenlab.factory.Factory;
import by.andersenlab.pojo.User;
import by.andersenlab.repository.RepositoryException;
import by.andersenlab.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


@Slf4j
public class UserRepositoryImpl implements UserRepository {

    private static volatile UserRepository INSTANCE = null;

    Session session;
    Transaction transaction;

    private UserRepositoryImpl() {
        session = Factory.getInstance().getSession();
    }

    public static UserRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (UserRepositoryImpl.class) {
                INSTANCE = new UserRepositoryImpl();
                return INSTANCE;
            }
        }
        return INSTANCE;
    }

    @Override
    public Long save(User user) throws RepositoryException {
        try {
            transaction = session.beginTransaction();
            Long id = (Long) session.save(user);
            transaction.commit();
            return id;
        } catch (Exception e) {
            transaction.rollback();
            log.error("Error save user", e);
            throw new RepositoryException("Error save user " + e.getMessage());
        }
    }

    @Override
    public User findByLogin(String login) throws RepositoryException {
        try {
            return session.createQuery("from User where login = :login ", User.class)
                    .setParameter("login", login)
                    .uniqueResultOptional()
                    .orElse(null);
        } catch (Exception e) {
            log.error("Error find user", e);
            throw new RepositoryException("Error find user " + e.getMessage());
        }
    }

    @Override
    public void update(User user) throws RepositoryException {
        try {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            log.error("Error update user", e);
            throw new RepositoryException("Error update user " + e.getMessage());
        }
    }

    @Override
    public void delete(User user) throws RepositoryException {
        try {
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            log.error("Error delete user", e);
            throw new RepositoryException("Error delete user " + e.getMessage());
        }
    }

    @Override
    public User get(Long id) throws RepositoryException {
        try {
            return session.get(User.class, id);
        } catch (Exception e) {
            log.error("Error get user", e);
            throw new RepositoryException("Error get user " + e.getMessage());
        }
    }

    @Override
    public List<User> list() throws RepositoryException {
        try {
            return session.createQuery("from User", User.class).list();
        } catch (Exception e) {
            log.error("Error get list users", e);
            throw new RepositoryException("Error get  list users " + e.getMessage());
        }
    }


}
