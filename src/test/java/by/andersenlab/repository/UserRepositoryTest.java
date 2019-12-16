package by.andersenlab.repository;

import by.andersenlab.pojo.Order;
import by.andersenlab.pojo.User;
import by.andersenlab.repository.impl.UserRepositoryImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class UserRepositoryTest {

    UserRepository userRepository;


    @Before
    public void setUp() throws Exception {
        userRepository = UserRepositoryImpl.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        List<User> userList = userRepository.list();
        userList.forEach(userRepository::delete);
    }

    @Test
    public void saveAndGetTest() {
        Long id = userRepository.save(new User("password", "login"));
        User user = userRepository.get(id);
        assertEquals(user.getLogin(), "login");
    }

    @Test
    public void findByLoginTest() {
        userRepository.save(new User("password", "lipski"));
        User user = userRepository.findByLogin("lipski");
        System.out.println(user);
        assertEquals(user.getLogin(), "lipski");
    }

    @Test
    public void updateTest() {
        userRepository.save(new User("password", "lipski"));
        User user = userRepository.findByLogin("lipski");
        System.out.println(user);
        List<Order> orderList = List.of(new Order(new Date(), user));
        user.setOrderList(orderList);
        user.setPassword("newPassword");
        userRepository.update(user);
        System.out.println(userRepository.findByLogin("lipski").getOrderList().size());
    }

}