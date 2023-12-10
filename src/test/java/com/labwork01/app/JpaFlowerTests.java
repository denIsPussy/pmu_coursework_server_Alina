package com.labwork01.app;

import com.labwork01.app.flower.FlowerType;
import com.labwork01.app.flower.model.Flower;
import com.labwork01.app.flower.service.FlowerService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JpaFlowerTests {
//    private static final Logger log = LoggerFactory.getLogger(JpaFlowerTests.class);
//    private Flower testFlower = new Flower("Rose", 100, FlowerType.Высокорослые);
//    @Autowired
//    private FlowerService flowerService;
//
//    @Test
//    public void testCreateFlower() {
//        flowerService.deleteAllFlowers();
//        Flower createdFlower = flowerService.createFlower(testFlower);
//        assertNotNull(createdFlower);
//        assertNotNull(createdFlower.getId());
//        assertEquals(testFlower.getName(), createdFlower.getName());
//        assertEquals(testFlower.getPrice(), createdFlower.getPrice());
//        assertEquals(testFlower.getType(), createdFlower.getType());
//    }
//
//    @Test
//    public void testFindFlower() {
//        flowerService.deleteAllFlowers();
//        flowerService.createFlower(testFlower);
//        Flower foundFlower = flowerService.findFlower(testFlower.getId());
//        assertNotNull(foundFlower);
//        assertEquals(testFlower.getName(), foundFlower.getName());
//        assertEquals(testFlower.getPrice(), foundFlower.getPrice());
//        assertEquals(testFlower.getType(), foundFlower.getType());
//    }
//
//    @Test
//    public void testFindAllFlowers() {
//        flowerService.deleteAllFlowers();
//        flowerService.createFlower(testFlower);
//        List<Flower> flowers = flowerService.findAllFlowers();
//        assertNotNull(flowers);
//        assertEquals(1, flowers.size());
//        Flower foundFlower = flowers.get(0);
//        assertNotNull(foundFlower.getId());
//        assertEquals(testFlower.getName(), foundFlower.getName());
//        assertEquals(testFlower.getPrice(), foundFlower.getPrice());
//        assertEquals(testFlower.getType(), foundFlower.getType());
//    }
//
//    @Test
//    public void testUpdateFlower() {
//        flowerService.deleteAllFlowers();
//        Flower createdFlower = flowerService.createFlower(testFlower);
//        createdFlower.setName("Lily");
//        createdFlower.setPrice(50);
//        createdFlower.setType(FlowerType.Высокорослые);
//        Flower updatedFlower = flowerService.updateFlower(createdFlower);
//        assertNotNull(updatedFlower);
//        assertEquals(createdFlower.getId(), updatedFlower.getId());
//        assertEquals(createdFlower.getName(), updatedFlower.getName());
//        assertEquals(createdFlower.getPrice(), updatedFlower.getPrice());
//        assertEquals(createdFlower.getType(), updatedFlower.getType());
//    }
//
//    @Test
//    public void testDeleteFlower() {
//        flowerService.deleteAllFlowers();
//        Flower createdFlower = flowerService.createFlower(testFlower);
//        assertNotNull(createdFlower.getId());
//        flowerService.deleteFlower(createdFlower.getId());
//        assertNull(flowerService.findFlower(createdFlower.getId()));
//    }
//
//    @Test
//    public void testDeleteAllFlowers() {
//        flowerService.deleteAllFlowers();
//        flowerService.createFlower(testFlower);
//        flowerService.deleteAllFlowers();
//        List<Flower> flowers = flowerService.findAllFlowers();
//        assertNotNull(flowers);
//        assertEquals(0, flowers.size());
//    }
}
