package com.labwork01.app.order.service;

import com.labwork01.app.order.model.Order;
import com.labwork01.app.order.model.OrderDTO;
import com.labwork01.app.order.repository.OrderRepository;
import com.labwork01.app.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Order insert(OrderDTO orderDTO) {
        Order order = new Order(orderDTO);
        return orderRepository.save(order);
    }

    @Transactional
    public Order delete(Long id) {
        Order order = findById(id);
        orderRepository.clearBouquets(id);
        orderRepository.delete(order);
        return order;
    }

    @Transactional
    public void deleteAll() {
        orderRepository.deleteAll();
    }

    @Transactional
    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Transactional
    public Order updateOrder(OrderDTO orderDTO) {
        Order updateOrder = findById(orderDTO.getId());
        updateOrder.setDate(orderDTO.getDate());
        updateOrder.setSum(orderDTO.getSum());
        return orderRepository.save(updateOrder);
    }

    @Transactional
    public List<Order> findByUserId(Long id) {
        return orderRepository.findByUserId(id);
    }
}
