package com.labwork01.app.order.service;

import com.labwork01.app.flower.FlowerType;
import com.labwork01.app.flower.model.Flower;
import com.labwork01.app.flower.repository.FlowerRepository;
import com.labwork01.app.order.model.Order;
import com.labwork01.app.order.model.OrderDTO;
import com.labwork01.app.order.model.OrderFlower;
import com.labwork01.app.order.model.OrderFlowerDTO;
import com.labwork01.app.order.repository.OrderFlowerRepository;
import com.labwork01.app.order.repository.OrderRepository;
import com.labwork01.app.provider.model.Provider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderFlowerRepository orderFlowerRepository;

    public OrderService(OrderRepository orderRepository, OrderFlowerRepository orderFlowerRepository) {
        this.orderRepository = orderRepository;
        this.orderFlowerRepository = orderFlowerRepository;
    }

    @Transactional
    public Order addOrder(OrderDTO orderDTO) {
        boolean isEdit = (orderDTO.getId() != 0) ? true : false;

        Order order = new Order(orderDTO);
        orderRepository.save(order);
        orderRepository.addFlowers(order.getId(), orderDTO.getOrderFlower(), isEdit);
        return orderRepository.save(order);
    }
    @Transactional
    public Order deleteOrder(Long id) {
        Order order = findOrder(id);
        orderRepository.removeAllFlowers(id);
        orderFlowerRepository.deleteByOrderId(id);
        orderRepository.delete(order);
        return order;
    }
    @Transactional
    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }
    @Transactional
    public Order findOrder(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }
    @Transactional
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }
    @Transactional
    public Order updateOrder(OrderDTO orderDTO) {
        Order updateOrder = findOrder(orderDTO.getId());
        updateOrder.setDateCreate(orderDTO.getDateCreate());
        List<OrderFlowerDTO> newOrderFlowers = orderDTO.getOrderFlower();
        List<OrderFlowerDTO> currentOrderFlowers = updateOrder.getFlowers().stream().map(OrderFlowerDTO::new).toList();
        if (!currentOrderFlowers.equals(newOrderFlowers)){
            orderRepository.removeFlowers(updateOrder.getId(), currentOrderFlowers);
            orderRepository.addFlowers(updateOrder.getId(), newOrderFlowers, true);
        }
        return orderRepository.save(updateOrder);
    }
}
