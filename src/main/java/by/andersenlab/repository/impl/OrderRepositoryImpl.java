package by.andersenlab.repository.impl;

import by.andersenlab.factory.Factory;
import by.andersenlab.pojo.Order;
import by.andersenlab.pojo.User;
import by.andersenlab.repository.OrderRepository;
import by.andersenlab.repository.RepositoryException;
import by.andersenlab.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@Slf4j
public class OrderRepositoryImpl implements OrderRepository {

    private static volatile OrderRepository INSTANCE = null;

    Session session;
    Transaction transaction;

    private OrderRepositoryImpl() {
        session = Factory.getInstance().getSession();
    }

    public static OrderRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (OrderRepositoryImpl.class) {
                INSTANCE = new OrderRepositoryImpl();
                return INSTANCE;
            }
        }
        return INSTANCE;
    }


    @Override
    public Long save(Order order) throws RepositoryException {
        try {
            transaction = session.beginTransaction();
            Long id = (Long) session.save(order);
            transaction.commit();
            return id;
        } catch (Exception e) {
            transaction.rollback();
            log.error("Error save order", e);
            throw new RepositoryException("Error save order " + e.getMessage());
        }
    }

    @Override
    public void Update(Order order) throws RepositoryException {
        try {
            transaction = session.beginTransaction();
            session.update(order);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            log.error("Error update order", e);
            throw new RepositoryException("Error update order " + e.getMessage());
        }
    }

    @Override
    public void delete(Order order) throws RepositoryException {
        try {
            transaction = session.beginTransaction();
            session.delete(order);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            log.error("Error delete order", e);
            throw new RepositoryException("Error delete order " + e.getMessage());
        }
    }

    @Override
    public Order get(Long id) throws RepositoryException {
        try {
            return session.get(Order.class, id);
        } catch (Exception e) {
            log.error("Error get order", e);
            throw new RepositoryException("Error get order " + e.getMessage());
        }
    }

    @Override
    public List<Order> list() throws RepositoryException {
        try {
            return session.createQuery("from `order`", Order.class).list();
        } catch (Exception e) {
            log.error("Error get list orders", e);
            throw new RepositoryException("Error get  list orders " + e.getMessage());
        }
    }
}
