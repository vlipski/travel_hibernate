package by.andersenlab.repository;

import by.andersenlab.pojo.User;

import java.util.List;

public interface UserRepository {

    Long save(User user ) throws RepositoryException;

    User findByLogin(String string) throws RepositoryException;

    void update(User user) throws RepositoryException;

    void delete(User user) throws RepositoryException;

    User get(Long id) throws RepositoryException;

    List<User> list () throws RepositoryException;
}
