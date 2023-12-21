package com.labwork01.app.bouquet.service;

import com.labwork01.app.bouquet.model.Bouquet;
import com.labwork01.app.bouquet.model.BouquetDTO;
import com.labwork01.app.bouquet.repository.BouquetRepository;
import com.labwork01.app.order.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BouquetService {
    private final BouquetRepository bouquetRepository;
    private final OrderRepository orderRepository;

    public BouquetService(BouquetRepository bouquetRepository, OrderRepository orderRepository) {
        this.bouquetRepository = bouquetRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Bouquet insert(BouquetDTO bouquetDTO) {
        Bouquet bouquet = new Bouquet(bouquetDTO);
        return bouquetRepository.save(bouquet);
    }
    @Transactional
    public Bouquet delete(Long id) {
        Bouquet bouquet = findById(id);
        bouquetRepository.delete(bouquet);
        return bouquet;
    }
    @Transactional
    public void deleteAll() {
        bouquetRepository.deleteAll();
    }
    @Transactional
    public Bouquet findById(Long id) {
        return bouquetRepository.findById(id).orElse(null);
    }
    @Transactional
    public Page<Bouquet> findAll(Pageable pageable) {
        return bouquetRepository.findAll(pageable);
    }
    @Transactional
    public Bouquet update(BouquetDTO bouquetDTO) {
        Bouquet updateBouquet = findById(bouquetDTO.getId());
        updateBouquet.setName(bouquetDTO.getName());
        updateBouquet.setPrice(bouquetDTO.getPrice());
        updateBouquet.setQuantityOfFlowers(bouquetDTO.getQuantityOfFlowers());
        updateBouquet.setImage(bouquetDTO.getImage().getBytes());
        return bouquetRepository.save(updateBouquet);
    }

    @Transactional
    public List<Bouquet> findByOrderId(Long id) {
        return bouquetRepository.findByOrderId(id);
    }
}
