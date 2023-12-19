package com.labwork01.app;

import com.labwork01.app.order.model.Order;
import com.labwork01.app.order.model.OrderDTO;
import com.labwork01.app.order.service.OrderService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class JpaOrderTests {
    private static final Logger log = LoggerFactory.getLogger(JpaOrderTests.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    public void testCreateOrder() {
        Order order = new Order("2023-04-10", 100);
        Order testOrder = orderService.insert(new OrderDTO(order));
        assertNotNull(testOrder.getId());
    }

    @Test
    @Transactional
    public void testDeleteOrder() {
        Order order = new Order("2023-04-10", 100);
        order = orderService.insert(new OrderDTO(order));
        Long id = order.getId();
        orderService.delete(id);
        Order deletedOrder = em.find(Order.class, id);
        assertNull(deletedOrder);
    }

    @Test
    @Transactional
    public void testDeleteAllOrders() {
        orderService.deleteAll();
        orderService.insert(new OrderDTO(new Order("2023-04-10", 100)));
        orderService.insert(new OrderDTO(new Order("2023-04-11", 200)));
        orderService.deleteAll();
        List<Order> orders = orderService.findAll();
        assertTrue(orders.isEmpty());
    }

}
