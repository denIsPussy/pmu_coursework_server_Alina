package com.labwork01.app.provider.service;

import com.labwork01.app.flower.model.Flower;
import com.labwork01.app.provider.model.Provider;
import com.labwork01.app.provider.model.ProviderDTO;
import com.labwork01.app.provider.repository.ProviderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderService {
    private final ProviderRepository providerRepository;

    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }
    @Transactional
    public Provider addProvider(ProviderDTO providerDTO) {
        Provider provider = new Provider(providerDTO);
        return providerRepository.save(provider);
    }
    @Transactional
    public Provider deleteProvider(Long id) {
        Provider provider = findProvider(id);
        providerRepository.delete(provider);
        return provider;
    }
    @Transactional
    public void deleteAllProviders() {
        providerRepository.deleteAll();
    }
    @Transactional
    public Provider findProvider(Long id) {
        Optional<Provider> provider = providerRepository.findById(id);
        return provider.get();
    }
    @Transactional
    public List<Provider> findAllProviders() {
        List<Provider> provider = providerRepository.findAll();
        return provider;
    }
    @Transactional
    public Provider updateProvider(ProviderDTO providerDTO) {
        Provider provider = findProvider(providerDTO.getId());
        provider.setName(providerDTO.getName());
        provider.setSurname(providerDTO.getSurname());
        provider.setPatronymic(providerDTO.getPatronymic());
        return providerRepository.save(provider);
    }
}
