package com.labwork01.app.order.repository;

import com.labwork01.app.flower.model.Flower;
import com.labwork01.app.order.model.OrderFlower;
import com.labwork01.app.order.model.OrderFlowerDTO;
import com.labwork01.app.flower.repository.FlowerRepository;
import com.labwork01.app.order.model.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderRepositoryImpl implements OrderRepositoryExtension{
    @Autowired
    @Lazy
    private FlowerRepository flowerRepository;
    @Autowired
    @Lazy
    private OrderFlowerRepository orderFlowerRepository;
    @PersistenceContext
    private EntityManager em;
    @Override
    @Transactional
    public void addFlowers(long orderId, List<OrderFlowerDTO> orderFlowers, boolean isEdit) {
        for (OrderFlowerDTO orderFlowerDTO : orderFlowers) {
            Long flowerId = orderFlowerDTO.getFlowerId();
            int quantity = orderFlowerDTO.getQuantity();

            Optional<Order> optionalOrder = Optional.ofNullable(em.find(Order.class, orderId));

            Order order = optionalOrder.get();
            Flower flower = em.find(Flower.class, flowerId);
            List<OrderFlower> orderFlower = order.getFlowers().stream()
                    .filter(of -> of.getFlower().equals(flower))
                    .collect(Collectors.toList());

            if (!orderFlower.isEmpty()){
                if (isEdit) orderFlower.get(0).setQuantity(quantity);
                else orderFlower.get(0).setQuantity(orderFlower.get(0).getQuantity() + quantity);
            }
            else{
                OrderFlower newOrderFlower = new OrderFlower(order, flower, quantity);
                em.persist(newOrderFlower);
                order.addFlower(newOrderFlower);
            }

            em.merge(order);
            em.flush();
            optionalOrder.ifPresent(value -> em.refresh(value));
        }
    }

    @Override
    @Transactional
    public void removeFlowers(long orderId, List<OrderFlowerDTO> orderFlowers) {
        for (OrderFlowerDTO orderFlowerDTO : orderFlowers) {
            Long flowerId = orderFlowerDTO.getFlowerId();
            int quantity = orderFlowerDTO.getQuantity();

            Optional<Order> optionalOrder = Optional.ofNullable(em.find(Order.class, orderId));

            Order order = optionalOrder.get();
            Flower flower = em.find(Flower.class, flowerId);

            OrderFlower orderFlower = new OrderFlower(order, flower, quantity);
            em.persist(orderFlower);

            order.removeFlower(orderFlower);
            em.merge(order);
            em.flush();
            optionalOrder.ifPresent(value -> em.refresh(value));
        }
    }
    @Override
    @Transactional
    public void removeAllFlowers(long orderId) {
        Order order = em.find(Order.class, orderId);
        if (order != null) {
            List<OrderFlower> orderFlowers = new ArrayList<>(order.getFlowers());
            int size = orderFlowers.size();
            for (int i = 0; i < size; i++) {
                OrderFlower orderFlower = orderFlowers.get(i);
                order.removeFlower(orderFlower);
                orderFlower.setOrder(null);
                em.merge(orderFlower);
            }
        }
        em.merge(order);
        em.flush();
    }
}
