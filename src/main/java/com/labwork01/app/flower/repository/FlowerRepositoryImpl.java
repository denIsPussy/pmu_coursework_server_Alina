package com.labwork01.app.flower.repository;

import com.labwork01.app.flower.model.Flower;
import com.labwork01.app.provider.model.Provider;
import com.labwork01.app.provider.repository.ProviderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class FlowerRepositoryImpl implements FlowerRepositoryExtension {

    @Autowired
    @Lazy
    private ProviderRepository providerRepository;

    @PersistenceContext
    private EntityManager em;
    @Override
    @Transactional
    public Optional<Flower> addProviders(Long flowerId, List<Long> providersId) {
        Optional<Flower> optionalFlower = Optional.ofNullable(em.find(Flower.class, flowerId));
        List<Provider> providers = providerRepository.findAllById(providersId);
        if (optionalFlower.isEmpty() || providers.isEmpty()){
            return optionalFlower;
        }
        Flower flower = optionalFlower.get();
        for (Provider provider : providers) {
            flower.addProvider(provider);
        }
        em.merge(flower);
        em.flush();
        optionalFlower.ifPresent(value -> em.refresh(value));
        return optionalFlower;
    }

    @Override
    @Transactional
    public Optional<Flower> removeProviders(Long flowerId, List<Long> providersId) {
        Optional<Flower> optionalFlower = Optional.ofNullable(em.find(Flower.class, flowerId));
        List<Provider> providers = providerRepository.findAllById(providersId);
        if (optionalFlower.isEmpty() || providers.isEmpty()){
            return optionalFlower;
        }
        Flower flower = optionalFlower.get();
        for (Provider provider : providers) {
            flower.removeProvider(provider);
        }
        em.merge(flower);
        em.flush();
        optionalFlower.ifPresent(value -> em.refresh(value));
        return optionalFlower;
    }
}
