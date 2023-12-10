package com.labwork01.app.flower.service;

import com.labwork01.app.flower.FlowerType;
import com.labwork01.app.flower.model.FlowerDTO;
import com.labwork01.app.flower.repository.FlowerRepository;
import com.labwork01.app.provider.model.Provider;
import com.labwork01.app.flower.model.Flower;
import com.labwork01.app.order.model.Order;
import com.labwork01.app.provider.repository.ProviderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class FlowerService {
    private final FlowerRepository flowerRepository;

    public FlowerService(FlowerRepository flowerRepository) {
        this.flowerRepository = flowerRepository;
    }

    @Transactional
    public Flower addFlower(FlowerDTO flowerDTO) {
        Flower flower = new Flower(flowerDTO);
        flowerRepository.save(flower);
        if (!flowerDTO.getProvidersId().isEmpty()) flowerRepository.addProviders(flower.getId(), flowerDTO.getProvidersId());
        return flowerRepository.save(flower);
    }
    @Transactional
    public Flower deleteFlower(Long id) {
        Flower flower = findFlower(id);
        flowerRepository.delete(flower);
        return flower;
    }
    @Transactional
    public void deleteAllFlowers() {
        flowerRepository.deleteAll();
    }
    @Transactional
    public Flower findFlower(Long id) {
        return flowerRepository.findById(id).orElseThrow();
    }
    @Transactional
    public List<Flower> findAllFlowers() {
        return flowerRepository.findAll();
    }
    @Transactional
    public Flower updateFlower(FlowerDTO flowerDTO) {
        Flower updateFlower = findFlower(flowerDTO.getId());
        updateFlower.setName(flowerDTO.getName());
        updateFlower.setPrice(flowerDTO.getPrice());
        updateFlower.setType(FlowerType.fromString(flowerDTO.getType()));
        updateFlower.setImage(flowerDTO.getImage());
        List<Long> providersId = updateFlower.getProviders().stream().map(Provider::getId).toList();
        if (!flowerDTO.getProvidersId().equals(providersId)){
            flowerRepository.removeProviders(updateFlower.getId(), providersId);
            flowerRepository.addProviders(updateFlower.getId(), flowerDTO.getProvidersId());
        }
        return flowerRepository.save(updateFlower);
    }
}
