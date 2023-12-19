package com.labwork01.app.order.repository;

import com.labwork01.app.bouquet.model.Bouquet;
import com.labwork01.app.bouquet.repository.BouquetRepository;
import com.labwork01.app.order.model.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderRepositoryImpl implements OrderRepositoryExtension{
    @Autowired
    @Lazy
    private BouquetRepository bouquetRepository;
    @PersistenceContext
    private EntityManager em;
    @Override
    @Transactional
    public void addBouquets(long id, List<Long> bouquetsId) {
        Order order = em.find(Order.class, id);
        if (order == null) {
            return;
        }

        for (Long bouquetId : bouquetsId) {
            Bouquet bouquet = bouquetRepository.findById(bouquetId).orElse(null);
            if (bouquet != null) {
                order.addBouquet(bouquet);
                bouquet.addOrder(order);
            }
        }

        em.merge(order);
    }

    @Override
    @Transactional
    public void removeBouquets(long id, List<Long> bouquetsId) {
        Order order = em.find(Order.class, id);
        if (order == null) {
            return;
        }

        for (Long bouquetId : bouquetsId) {
            Bouquet bouquet = bouquetRepository.findById(bouquetId).orElse(null);
            if (bouquet != null) {
                order.removeBouquet(bouquet);
                bouquet.removeOrder(order);
            }
        }

        em.merge(order);
    }
    @Override
    @Transactional
    public void clearBouquets(long id) {
        Order order = em.find(Order.class, id);
        if (order == null) {
            return;
        }
        if (order.getBouquets() == null) return;
        order.getBouquets().forEach(bouquet -> bouquet.getOrders().remove(order));
        order.getBouquets().clear();
        em.merge(order);
    }
}
