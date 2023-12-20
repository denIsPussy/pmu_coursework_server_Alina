package com.labwork01.app;

import com.labwork01.app.bouquet.model.Bouquet;
import com.labwork01.app.bouquet.model.BouquetDTO;
import com.labwork01.app.bouquet.service.BouquetService;
import com.labwork01.app.order.model.Order;
import com.labwork01.app.order.model.OrderDTO;
import com.labwork01.app.order.service.OrderService;
import com.labwork01.app.user.model.User;
import com.labwork01.app.user.model.UserDTO;
import com.labwork01.app.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JpaProviderTests {
    private static final Logger log = LoggerFactory.getLogger(JpaProviderTests.class);
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private BouquetService bouquetService;
    @Test
    public void testCreateUser() {
        userService.deleteAll();
        User user = new User("Alina", "23.09.2005", "89278362447", "123");
        user = userService.insert(new UserDTO(user));
        User foundUser = userService.findById(user.getId());
        assertNotNull(foundUser);
        assertNotNull(foundUser.getId());
        assertEquals(user.getUserName(), foundUser.getUserName());
    }

    @Test
    public void testDeleteUser() {
        userService.deleteAll();
        User user = new User("Alina", "23.09.2005", "89278362447", "123");
        user = userService.insert(new UserDTO(user));
        userService.delete(user.getId());
        User foundUser = userService.findById(user.getId());
        assertNull(foundUser);
    }

    @Test
    public void testDeleteAllUsers() {
        userService.deleteAll();
        User user = new User("Alina", "23.09.2005", "89278362447", "123");
        User user1 = new User("Denis", "23.09.2005", "89278362447", "123");
        user = userService.insert(new UserDTO(user));
        user = userService.insert(new UserDTO(user1));
        userService.deleteAll();
        List<User> users = userService.findAll();
        assertTrue(users.isEmpty());
    }

    @Test
    public void testFindUser() {
        userService.deleteAll();
        User user = new User("Alina", "23.09.2005", "89278362447", "123");
        user = userService.insert(new UserDTO(user));
        User foundUser = userService.findById(user.getId());
        assertNotNull(foundUser);
        assertEquals(user.getId(), foundUser.getId());
        assertEquals(user.getUserName(), foundUser.getUserName());
    }

    @Test
    public void testFindAllProviders() {
        userService.deleteAll();
        User user = new User("Alina", "23.09.2005", "89278362447", "123");
        User user1 = new User("Denis", "23.09.2005", "89278362447", "123");
        user = userService.insert(new UserDTO(user));
        user1 = userService.insert(new UserDTO(user1));
        assertEquals(2, userService.findAll().size());
    }

    @Test
    public void testUpdateUser() {
        userService.deleteAll();
        User user = new User("Alina", "23.09.2005", "89278362447", "123");
        user = userService.insert(new UserDTO(user));
        user.setUserName("Denis");
        user.setPhoneNumber("89021227351");
        user = userService.update(new UserDTO(user));
        User updateUser = new User("Denis", "23.09.2005", "89021227351", "123");
        assertNotNull(updateUser);
        assertEquals(user.getUserName(), updateUser.getUserName());
        assertEquals(user.getPhoneNumber(), updateUser.getPhoneNumber());
    }

    @Test
    public void testAddOrder() {
        userService.deleteAll();
        orderService.deleteAll();

        Bouquet bouquet1 = new Bouquet("bouquet1", 1, 1, new byte[]{ 10, 20, 30, 40, 50 });
        Bouquet bouquet2 = new Bouquet("bouquet2", 1, 1, new byte[]{ 10, 20, 30, 40, 50 });
        bouquet1 = bouquetService.insert(new BouquetDTO(bouquet1));
        bouquet2 = bouquetService.insert(new BouquetDTO(bouquet2));


        User user = new User("Alina", "23.09.2005", "89278362447", "123");
        user = userService.insert(new UserDTO(user));

        Order order1 = new Order("2023-04-11", 100, Arrays.asList(bouquet1, bouquet2), user);
        Order order2 = new Order("2023-04-10", 100, Arrays.asList(bouquet1, bouquet2), user);
        order1 = orderService.insert(new OrderDTO(order1));
        order2 = orderService.insert(new OrderDTO(order2));

        User updatedUser = userService.findById(user.getId());
        assertEquals(2, updatedUser.getOrders().size());
    }

    @Test
    public void testRemoveOrder() {
        userService.deleteAll();
        orderService.deleteAll();

        Bouquet bouquet1 = new Bouquet("bouquet1", 1, 1, new byte[]{ 10, 20, 30, 40, 50 });
        Bouquet bouquet2 = new Bouquet("bouquet2", 1, 1, new byte[]{ 10, 20, 30, 40, 50 });
        bouquet1 = bouquetService.insert(new BouquetDTO(bouquet1));
        bouquet2 = bouquetService.insert(new BouquetDTO(bouquet2));

        User user = new User("Alina", "23.09.2005", "89278362447", "123");
        user = userService.insert(new UserDTO(user));

        Order order1 = new Order("2023-04-11", 100, Arrays.asList(bouquet1, bouquet2), user);
        Order order2 = new Order("2023-04-10", 100, Arrays.asList(bouquet1, bouquet2), user);
        order1 = orderService.insert(new OrderDTO(order1));
        order2 = orderService.insert(new OrderDTO(order2));
        user = userService.removeOrdersFromUser(user, Arrays.asList(order1, order2));
        User updatedUser = userService.findById(user.getId());
        assertEquals(0, updatedUser.getOrders().size());
    }
}
