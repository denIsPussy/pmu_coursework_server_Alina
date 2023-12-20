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
import jakarta.transaction.Transactional;
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
public class JpaOrderTests {
    private static final Logger log = LoggerFactory.getLogger(JpaOrderTests.class);

    @Autowired
    private OrderService orderService;
    @Autowired
    private BouquetService bouquetService;
    @Autowired
    private UserService userService;


    @Test
    @Transactional
    public void testCreateOrder() {
        Bouquet bouquet1 = new Bouquet("bouquet1", 1, 1, new byte[]{ 10, 20, 30, 40, 50 });
        Bouquet bouquet2 = new Bouquet("bouquet2", 1, 1, new byte[]{ 10, 20, 30, 40, 50 });
        bouquet1 = bouquetService.insert(new BouquetDTO(bouquet1));
        bouquet2 = bouquetService.insert(new BouquetDTO(bouquet2));
        Order order = new Order("2023-04-10", 100, Arrays.asList(bouquet1, bouquet2));
        Order testOrder = orderService.insert(new OrderDTO(order));
        assertNotNull(testOrder.getId());
        assertNotNull(testOrder.getBouquets());
    }

    @Test
    @Transactional
    public void testFindByUserId() {
        userService.deleteAll();
        orderService.deleteAll();

        Bouquet bouquet1 = new Bouquet("bouquet1", 1, 1, new byte[]{ 10, 20, 30, 40, 50 });
        Bouquet bouquet2 = new Bouquet("bouquet2", 1, 1, new byte[]{ 10, 20, 30, 40, 50 });
        bouquet1 = bouquetService.insert(new BouquetDTO(bouquet1));
        bouquet2 = bouquetService.insert(new BouquetDTO(bouquet2));
        Order order1 = new Order("2023-04-10", 100, Arrays.asList(bouquet1, bouquet2));
        Order order2 = new Order("2023-04-10", 100, Arrays.asList(bouquet1, bouquet2));
//        order1 = orderService.insert(new OrderDTO(order1));
//        order2 = orderService.insert(new OrderDTO(order2));

        User user = new User("Alina", "23.09.2005", "89278362447", "123");
        user = userService.insert(new UserDTO(user));

        User userWithOrders = user;
        userWithOrders.setOrders(Arrays.asList(order1, order2));

        userWithOrders = userService.addOrdersToUser(new UserDTO(userWithOrders));

        List<Order> orders = orderService.findByUserId(userWithOrders.getId());
        assertEquals(2, orders.size());
    }

    @Test
    @Transactional
    public void testDeleteOrder() {
        Bouquet bouquet1 = new Bouquet("bouquet1", 1, 1, new byte[]{ 10, 20, 30, 40, 50 });
        Bouquet bouquet2 = new Bouquet("bouquet2", 1, 1, new byte[]{ 10, 20, 30, 40, 50 });
        bouquet1 = bouquetService.insert(new BouquetDTO(bouquet1));
        bouquet2 = bouquetService.insert(new BouquetDTO(bouquet2));
        Order order = new Order("2023-04-10", 100, Arrays.asList(bouquet1, bouquet2));
        order = orderService.insert(new OrderDTO(order));
        Long id = order.getId();
        orderService.delete(id);
        Order deletedOrder = orderService.findById(id);
        assertNull(deletedOrder);
    }

    @Test
    @Transactional
    public void testDeleteAllOrders() {
        orderService.deleteAll();
        Bouquet bouquet1 = new Bouquet("bouquet1", 1, 1, new byte[]{ 10, 20, 30, 40, 50 });
        Bouquet bouquet2 = new Bouquet("bouquet2", 1, 1, new byte[]{ 10, 20, 30, 40, 50 });
        bouquet1 = bouquetService.insert(new BouquetDTO(bouquet1));
        bouquet2 = bouquetService.insert(new BouquetDTO(bouquet2));
        Order order1 = new Order("2023-04-11", 100, Arrays.asList(bouquet1, bouquet2));
        Order order2 = new Order("2023-04-10", 100, Arrays.asList(bouquet1, bouquet2));
        orderService.insert(new OrderDTO(order1));
        orderService.insert(new OrderDTO(order2));
        orderService.deleteAll();
        List<Order> orders = orderService.findAll();
        assertTrue(orders.isEmpty());
    }
}
