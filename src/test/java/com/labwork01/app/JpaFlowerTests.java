package com.labwork01.app;

import com.labwork01.app.bouquet.model.Bouquet;
import com.labwork01.app.bouquet.model.BouquetDTO;
import com.labwork01.app.bouquet.service.BouquetService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.MalformedURLException;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JpaFlowerTests {
    private static final Logger log = LoggerFactory.getLogger(JpaFlowerTests.class);
    private Bouquet bouquet = new Bouquet("bouquet1", 1, 1, new byte[]{ 10, 20, 30, 40, 50 });
    @Autowired
    private BouquetService bouquetService;

    @Test
    public void testCreateBouquet() throws MalformedURLException {
        bouquetService.deleteAll();
        Bouquet bouquet = new Bouquet("bouquet1", 1, 1, new byte[]{ 10, 20, 30, 40, 50 });
        Bouquet createdbouquet = bouquetService.insert(new BouquetDTO(bouquet));
        assertNotNull(createdbouquet);
        assertNotNull(createdbouquet.getId());
        assertEquals(bouquet.getName(), createdbouquet.getName());
        assertEquals(bouquet.getPrice(), createdbouquet.getPrice());
        assertEquals(bouquet.getQuantityOfFlowers(), createdbouquet.getQuantityOfFlowers());
    }

    @Test
    public void testFindBouquet() {
        bouquetService.deleteAll();
        Bouquet bouquet = new Bouquet("bouquet1", 1, 1, new byte[]{ 10, 20, 30, 40, 50 });
        Bouquet createdbouquet = bouquetService.insert(new BouquetDTO(bouquet));
        Bouquet foundBouquet = bouquetService.findById(createdbouquet.getId());
        assertNotNull(foundBouquet);
        assertEquals(createdbouquet.getName(), foundBouquet.getName());
        assertEquals(createdbouquet.getPrice(), foundBouquet.getPrice());
        assertEquals(createdbouquet.getQuantityOfFlowers(), foundBouquet.getQuantityOfFlowers());
    }

    @Test
    public void testFindAllBouquets() {
        bouquetService.deleteAll();
        Bouquet createdbouquet = bouquetService.insert(new BouquetDTO(bouquet));
        List<Bouquet> bouquets = bouquetService.findAll();
        assertNotNull(bouquets);
        assertEquals(1, bouquets.size());
        Bouquet foundBouquet = bouquets.get(0);
        assertNotNull(foundBouquet.getId());
        assertEquals(createdbouquet.getName(), foundBouquet.getName());
        assertEquals(createdbouquet.getPrice(), foundBouquet.getPrice());
        assertEquals(createdbouquet.getQuantityOfFlowers(), foundBouquet.getQuantityOfFlowers());
    }

    @Test
    public void testUpdateBouquet() {
        bouquetService.deleteAll();
        Bouquet createdBouquet = bouquetService.insert(new BouquetDTO(bouquet));
        createdBouquet.setName("New Bouquet Name");
        createdBouquet.setPrice(100);
        // Другие изменения свойств, если нужно
        Bouquet updatedBouquet = bouquetService.update(new BouquetDTO(createdBouquet));
        assertNotNull(updatedBouquet);
        assertEquals(createdBouquet.getId(), updatedBouquet.getId());
        assertEquals(createdBouquet.getName(), updatedBouquet.getName());
        assertEquals(createdBouquet.getPrice(), updatedBouquet.getPrice());
        // Проверка других свойств
    }

    @Test
    public void testDeleteBouquet() {
        bouquetService.deleteAll();
        Bouquet createdBouquet = bouquetService.insert(new BouquetDTO(bouquet));
        assertNotNull(createdBouquet.getId());
        bouquetService.delete(createdBouquet.getId());
        assertNull(bouquetService.findById(createdBouquet.getId()));
    }

    @Test
    public void testDeleteAllBouquets() {
        bouquetService.deleteAll();
        bouquetService.insert(new BouquetDTO(bouquet));
        bouquetService.deleteAll();
        List<Bouquet> bouquets = bouquetService.findAll();
        assertNotNull(bouquets);
        assertTrue(bouquets.isEmpty());
    }
}
