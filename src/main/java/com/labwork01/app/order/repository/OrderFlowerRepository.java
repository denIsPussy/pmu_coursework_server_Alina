package com.labwork01.app.order.repository;

import com.labwork01.app.flower.model.Flower;
import com.labwork01.app.order.model.Order;
import com.labwork01.app.order.model.OrderFlower;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderFlowerRepository extends JpaRepository<OrderFlower, Long> {
    @Modifying
    @Query("DELETE FROM order_flower of WHERE of.order.id = :orderId")
    void deleteByOrderId(Long orderId);
}
