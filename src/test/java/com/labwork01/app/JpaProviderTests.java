package com.labwork01.app;

import com.labwork01.app.flower.FlowerType;
import com.labwork01.app.flower.model.Flower;
import com.labwork01.app.flower.service.FlowerService;
import com.labwork01.app.order.model.Order;
import com.labwork01.app.provider.model.Provider;
import com.labwork01.app.provider.service.ProviderService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JpaProviderTests {
//    private static final Logger log = LoggerFactory.getLogger(JpaProviderTests.class);
//    @Autowired
//    private ProviderService providerService;
//    @Autowired
//    private FlowerService flowerService;
//    @Test
//    public void testCreateProvider() {
//        providerService.deleteAllProviders();
//        Provider testProvider = new Provider("Denis", "Musoev");
//        Provider createdProvider = providerService.createProvider(testProvider);
//        assertNotNull(createdProvider);
//        assertNotNull(createdProvider.getId());
//        assertEquals(testProvider.getName(), createdProvider.getName());
//    }
//
//    @Test
//    public void testDeleteProvider() {
//        providerService.deleteAllProviders();
//        Provider testProvider = new Provider("Denis", "Musoev");
//        Provider createdProvider = providerService.createProvider(testProvider);
//        providerService.deleteProvider(createdProvider.getId());
//        assertNull(providerService.findProvider(createdProvider.getId()));
//    }
//
//    @Test
//    public void testDeleteAllProviders() {
//        providerService.deleteAllProviders();
//        providerService.deleteAllProviders();
//        List<Provider> providers = providerService.findAllProviders();
//        assertTrue(providers.isEmpty());
//    }
//
//    @Test
//    public void testFindProvider() {
//        providerService.deleteAllProviders();
//        Provider testProvider = new Provider("Denis", "Musoev");
//        Provider createdProvider = providerService.createProvider(testProvider);
//        Provider foundProvider = providerService.findProvider(createdProvider.getId());
//        assertNotNull(foundProvider);
//        assertEquals(createdProvider.getId(), foundProvider.getId());
//        assertEquals(createdProvider.getName(), foundProvider.getName());
//    }
//
//    @Test
//    public void testFindAllProviders() {
//        providerService.deleteAllProviders();
//        Provider testProvider1 = new Provider("Denis", "Musoev");
//        Provider testProvider2 = new Provider("Alina", "Batylkina");
//        providerService.createProvider(testProvider1);
//        providerService.createProvider(testProvider2);
//        List<Provider> providers = providerService.findAllProviders();
//        assertEquals(2, providers.size());
//    }
//
//    @Test
//    public void testUpdateProvider() {
//        providerService.deleteAllProviders();
//        Provider testProvider = new Provider("Denis", "Musoev");
//        Provider createdProvider = providerService.createProvider(testProvider);
//        createdProvider.setName("Updated Provider");
//        Provider updatedProvider = providerService.updateProvider(createdProvider);
//        assertNotNull(updatedProvider);
//        assertEquals(createdProvider.getId(), updatedProvider.getId());
//        assertEquals(createdProvider.getName(), updatedProvider.getName());
//    }
//
//    @Test
//    public void testAddFlower() {
//        Flower flower = new Flower("Rose", 10, FlowerType.Высокорослые);
//        flowerService.createFlower(flower);
//        Provider provider = new Provider("Denis", "Musoev");
//        providerService.createProvider(provider);
//        provider.addFlower(flower);
//        providerService.updateProvider(provider);
//        Provider updatedProvider = providerService.findProvider(provider.getId());
//        assertEquals(1, updatedProvider.getFlowers().size());
//    }
//
//    @Test
//    public void testRemoveFlower() {
//        Flower flower = new Flower("Rose", 10, FlowerType.Высокорослые);
//        flowerService.createFlower(flower);
//        Provider provider = new Provider("Denis", "Musoev");
//        providerService.createProvider(provider);
//        provider.addFlower(flower);
//        providerService.updateProvider(provider);
//        Provider updatedProvider = providerService.findProvider(provider.getId());
//        updatedProvider.removeFlower(flower);
//        providerService.updateProvider(updatedProvider);
//        Provider removedFlowerProvider = providerService.findProvider(updatedProvider.getId());
//        assertEquals(0, removedFlowerProvider.getFlowers().size());
//    }
}
