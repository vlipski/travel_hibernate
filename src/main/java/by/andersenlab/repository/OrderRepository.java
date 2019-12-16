package by.andersenlab.repository;

import by.andersenlab.pojo.Order;
import by.andersenlab.pojo.User;

import java.util.List;

public interface OrderRepository {

    Long save(Order order) throws RepositoryException;

    void Update(Order order) throws RepositoryException;

    void delete(Order order) throws RepositoryException;

    Order get(Long id) throws RepositoryException;

    List<Order> list () throws RepositoryException;

}
