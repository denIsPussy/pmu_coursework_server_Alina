package com.labwork01.app.flower.repository;

import com.labwork01.app.flower.model.Flower;

import java.util.List;
import java.util.Optional;

public interface FlowerRepositoryExtension {
    Optional<Flower> addProviders(Long flowerId, List<Long> providersId);
    Optional<Flower> removeProviders(Long flowerId, List<Long> providersId);
}
