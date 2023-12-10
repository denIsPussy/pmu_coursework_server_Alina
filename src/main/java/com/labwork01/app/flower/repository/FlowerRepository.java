package com.labwork01.app.flower.repository;

import com.labwork01.app.flower.model.Flower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlowerRepository extends JpaRepository<Flower, Long>, FlowerRepositoryExtension {
}
