package com.labwork01.app.bouquet.controller;

import com.labwork01.app.bouquet.model.Bouquet;
import com.labwork01.app.bouquet.model.BouquetDTO;
import com.labwork01.app.bouquet.service.BouquetService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<BouquetDTO> getBouquets(
            @RequestParam(value = "_page", defaultValue = "0") int page,
            @RequestParam(value = "_limit", defaultValue = "10") int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<Bouquet> bouquetPage = bouquetService.findAll(pageable);
        return bouquetPage.getContent().stream()
                .map(BouquetDTO::new)
                .collect(Collectors.toList());
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
