package com.labwork01.app.bouquet.repository;

import com.labwork01.app.bouquet.model.Bouquet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BouquetRepository extends JpaRepository<Bouquet, Long> {
    Page<Bouquet> findAll(Pageable pageable);
    @Query("SELECT b FROM Bouquet b JOIN b.orders o WHERE o.id = :orderId")
    List<Bouquet> findByOrderId(Long orderId);
}
