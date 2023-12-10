package com.labwork01.app.flower.controller;

import com.labwork01.app.flower.service.FlowerService;
import com.labwork01.app.flower.model.FlowerDTO;
import com.labwork01.app.flower.service.FlowerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flower")
public class FlowerController {
    private final FlowerService flowerService;

    public FlowerController(FlowerService flowerService) {
        this.flowerService = flowerService;
    }

    @GetMapping("/{id}")
    public FlowerDTO getFlower(@PathVariable Long id) {
        return new FlowerDTO(flowerService.findFlower(id));
    }

    @GetMapping
    public List<FlowerDTO> getFlowers() {
        return flowerService.findAllFlowers().stream()
                .map(FlowerDTO::new)
                .toList();
    }

    @PostMapping
    public FlowerDTO createFlower(@RequestBody FlowerDTO flowerDTO) {
        return new FlowerDTO(flowerService.addFlower(flowerDTO));
    }

    @PutMapping("/{id}")
    public FlowerDTO updateFlower(@RequestBody FlowerDTO flowerDTO) {
        return new FlowerDTO(flowerService.updateFlower(flowerDTO));
    }

    @DeleteMapping("/{id}")
    public FlowerDTO deleteFlower(@PathVariable Long id) {
        return new FlowerDTO(flowerService.deleteFlower(id));
    }
}
