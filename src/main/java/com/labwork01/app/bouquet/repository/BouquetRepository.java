package com.labwork01.app.bouquet.repository;

import com.labwork01.app.bouquet.model.Bouquet;
import com.labwork01.app.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BouquetRepository extends JpaRepository<Bouquet, Long> {
    // JPQL запрос для получения списка букетов по идентификатору заказа
    @Query("SELECT b FROM Bouquet b JOIN b.orders o WHERE o.id = :orderId")
    List<Bouquet> findByOrderId(Long orderId);
}
