package com.labwork01.app;

import com.labwork01.app.flower.FlowerType;
import com.labwork01.app.flower.model.Flower;
import com.labwork01.app.flower.service.FlowerService;
import com.labwork01.app.order.model.Order;
import com.labwork01.app.order.service.OrderService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JpaOrderTests {
//    private static final Logger log = LoggerFactory.getLogger(JpaOrderTests.class);
//    @Autowired
//    private FlowerService flowerService;
//    @Autowired
//    private OrderService orderService;
//    @Autowired
//    private EntityManager em;
//
//    @Test
//    public void testCreateOrder() {
//        Order order = new Order("2023-04-10");
//        orderService.createOrder(order);
//        assertNotNull(order.getId());
//    }
//
//    @Test
//    public void testDeleteOrder() {
//        Order order = new Order("2023-04-10");
//        orderService.createOrder(order);
//        Long id = order.getId();
//        orderService.deleteOrder(id);
//        Order deletedOrder = em.find(Order.class, id);
//        assertNull(deletedOrder);
//    }
//
//    @Test
//    public void testDeleteAllOrders() {
//        Order order1 = new Order("2023-04-10");
//        Order order2 = new Order("2023-04-10");
//        orderService.createOrder(order1);
//        orderService.createOrder(order2);
//        orderService.deleteAllOrders();
//        List<Order> orders = orderService.findAllOrders();
//        assertEquals(0, orders.size());
//    }
//
//    @Test
//    public void testFindOrder() {
//        Order order = new Order("2023-04-10");
//        orderService.createOrder(order);
//        Long id = order.getId();
//        Order foundOrder = orderService.findOrder(id);
//        assertNotNull(foundOrder);
//        assertEquals(id, foundOrder.getId());
//    }
//
//    @Test
//    public void testFindAllOrders() {
//        orderService.deleteAllOrders();
//        Order order1 = new Order("2023-04-10");
//        Order order2 = new Order("2023-04-10");
//        orderService.createOrder(order1);
//        orderService.createOrder(order2);
//        List<Order> orders = orderService.findAllOrders();
//        assertEquals(2, orders.size());
//    }
//
//    @Test
//    public void testUpdateOrder() {
//        Order order = new Order("2023-04-10");
//        orderService.createOrder(order);
//        Long id = order.getId();
//        order.setDateCreate("2023-04-11");
//        orderService.updateOrder(order);
//        Order updatedOrder = orderService.findOrder(id);
//        assertEquals("2023-04-11", updatedOrder.getDateCreate());
//    }
//
//    @Test
//    public void testAddFlower() {
//        Flower flower = new Flower("Rose", 10, FlowerType.Высокорослые);
//        flowerService.createFlower(flower);
//        Order order = new Order("2023-04-10");
//        orderService.createOrder(order);
//        order.addFlower(flower);
//        orderService.updateOrder(order);
//        Order updatedOrder = orderService.findOrder(order.getId());
//        assertEquals(1, updatedOrder.getFlowers().size());
//        assertEquals(10, updatedOrder.getSum());
//    }
//
//    @Test
//    public void testRemoveFlower() {
//        Flower flower = new Flower("Rose", 10, FlowerType.Высокорослые);
//        flowerService.createFlower(flower);
//        Order order = new Order("2023-04-10");
//        orderService.createOrder(order);
//        order.addFlower(flower);
//        orderService.updateOrder(order);
//        Order updatedOrder = orderService.findOrder(order.getId());
//        updatedOrder.removeFlower(flower);
//        orderService.updateOrder(updatedOrder);
//        Order removedFlowerOrder = orderService.findOrder(updatedOrder.getId());
//        assertEquals(0, removedFlowerOrder.getFlowers().size());
//        assertEquals(0, removedFlowerOrder.getSum());
//    }
}
