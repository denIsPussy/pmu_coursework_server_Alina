package com.labwork01.app.provider.repository;

import com.labwork01.app.provider.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
}
