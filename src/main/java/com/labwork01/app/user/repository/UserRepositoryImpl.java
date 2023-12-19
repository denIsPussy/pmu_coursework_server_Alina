package com.labwork01.app.user.repository;

import com.labwork01.app.bouquet.model.Bouquet;
import com.labwork01.app.bouquet.repository.BouquetRepository;
import com.labwork01.app.order.model.Order;
import com.labwork01.app.order.repository.OrderRepository;
import com.labwork01.app.order.repository.OrderRepositoryExtension;
import com.labwork01.app.user.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRepositoryImpl implements UserRepositoryExtension {
    @Autowired
    @Lazy
    private OrderRepository orderRepository;
    @PersistenceContext
    private EntityManager em;
    @Override
    @Transactional
    public void addOrders(long id, List<Long> ordersId) {
        User user = em.find(User.class, id);
        if (user == null) {
            return;
        }

        for (Long orderId : ordersId) {
            orderRepository.findById(orderId).ifPresent(user::addOrder);
        }

        em.merge(user);
    }

    @Override
    @Transactional
    public void removeOrders(long id, List<Long> ordersId) {
        User user = em.find(User.class, id);
        if (user == null) {
            return;
        }

        for (Long orderId : ordersId) {
            orderRepository.findById(orderId).ifPresent(user::removeOrder);
        }

        em.merge(user);
    }
    @Override
    @Transactional
    public void clearOrders(long id) {
        User user = em.find(User.class, id);
        if (user == null) {
            return;
        }

        user.getOrders().forEach(Order::removeUser);
        user.getOrders().clear();
        em.merge(user);
    }
}
