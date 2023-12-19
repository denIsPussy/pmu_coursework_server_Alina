package com.labwork01.app.bouquet.controller;

import com.labwork01.app.bouquet.model.BouquetDTO;
import com.labwork01.app.bouquet.service.BouquetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bouquet")
public class BouquetController {
    private final BouquetService bouquetService;

    public BouquetController(BouquetService bouquetService) {
        this.bouquetService = bouquetService;
    }

    @GetMapping("/{id}")
    public BouquetDTO getById(@PathVariable Long id) {
        return new BouquetDTO(bouquetService.findById(id));
    }

    @GetMapping("/byOrder{id}")
    public List<BouquetDTO> getByOrderId(@PathVariable Long id) {
        return bouquetService.findByOrderId(id).stream().map(BouquetDTO::new).toList();
    }

    @GetMapping
    public List<BouquetDTO> getAll() {
        return bouquetService.findAll().stream()
                .map(BouquetDTO::new)
                .toList();
    }

    @PostMapping
    public BouquetDTO insert(@RequestBody BouquetDTO bouquetDTO) {
        return new BouquetDTO(bouquetService.insert(bouquetDTO));
    }

    @PutMapping("/{id}")
    public BouquetDTO update(@RequestBody BouquetDTO bouquetDTO) {
        return new BouquetDTO(bouquetService.update(bouquetDTO));
    }

    @DeleteMapping("/{id}")
    public BouquetDTO delete(@PathVariable Long id) {
        return new BouquetDTO(bouquetService.delete(id));
    }
}
