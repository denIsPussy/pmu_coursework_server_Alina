package com.labwork01.app.order.service;

import com.labwork01.app.bouquet.model.Bouquet;
import com.labwork01.app.bouquet.repository.BouquetRepository;
import com.labwork01.app.order.model.Order;
import com.labwork01.app.order.model.OrderDTO;
import com.labwork01.app.order.repository.OrderRepository;
import com.labwork01.app.user.model.User;
import com.labwork01.app.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final BouquetRepository bouquetRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, BouquetRepository bouquetRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.bouquetRepository = bouquetRepository;
    }

    @Transactional
    public Order insert(OrderDTO orderDTO) {
        if (orderDTO == null) {
            throw new IllegalArgumentException("OrderDTO cannot be null");
        }
        Order order = new Order(orderDTO);

        if (orderDTO.getBouquets() != null) {
            List<Bouquet> bouquets = orderDTO.getBouquets().stream()
                    .map(Bouquet::new)
                    .collect(Collectors.toList());
            addBouquetsToOrder(order, bouquets);
        }

        if (orderDTO.getUser() != null) {
            User user = new User(orderDTO.getUser());
            addUserToOrder(order, user);
        }

        return orderRepository.save(order);
    }

    @Transactional
    public Order insert1(OrderDTO orderDTO) {
        if (orderDTO == null) {
            throw new IllegalArgumentException("OrderDTO cannot be null");
        }
        Order order = new Order(orderDTO);

        if (orderDTO.getBouquets() != null) {
            List<Bouquet> bouquets = orderDTO.getBouquets().stream()
                    .map(Bouquet::new)
                    .collect(Collectors.toList());
            addBouquetsToOrder(order, bouquets);
        }

        if (orderDTO.getUser() != null) {
            User user = new User(orderDTO.getUser());
            addUserToOrder(order, user);
        }

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
    public void addBouquetsToOrder(Order order, List<Bouquet> bouquets) {
        bouquets.forEach(
                bouquet-> {
                    Bouquet foundBouquet = bouquetRepository.findById(bouquet.getId()).orElse(null);
                    if (foundBouquet != null) order.addBouquet(foundBouquet);
                }
        );
    }

    @Transactional
    public void addUserToOrder(Order order, User user) {
        if (order == null || user == null) return;
        User foundUser = userRepository.findById(user.getId()).orElse(null);

        if (foundUser == null) return;
        order.addUser(foundUser);
    }

    @Transactional
    public void removeBouquetsFromOrder(Order order, List<Bouquet> bouquets) {
        bouquets.forEach(
                bouquet-> {
                    Bouquet foundBouquet = bouquetRepository.findById(bouquet.getId()).orElse(null);
                    if (foundBouquet != null) order.removeBouquet(foundBouquet);
                }
        );
    }

    @Transactional
    public void removeUserFromOrder(Order order, User user) {
        if (order == null || user == null) return;
        User foundUser = userRepository.findById(user.getId()).orElse(null);

        if (foundUser == null) return;
        order.removeUser();
    }

    @Transactional
    public List<Order> findByUserId(Long id) {
        return orderRepository.findByUserId(id);
    }
}
