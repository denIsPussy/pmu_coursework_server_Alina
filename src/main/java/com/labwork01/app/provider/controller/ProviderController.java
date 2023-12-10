package com.labwork01.app.provider.controller;

import com.labwork01.app.provider.model.ProviderDTO;
import com.labwork01.app.provider.service.ProviderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provider")
public class ProviderController {
    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping("/{id}")
    public ProviderDTO getProvider(@PathVariable Long id) {
        return new ProviderDTO(providerService.findProvider(id));
    }

    @GetMapping
    public List<ProviderDTO> getProviders() {
        return providerService.findAllProviders().stream()
                .map(ProviderDTO::new)
                .toList();
    }

    @PostMapping
    public ProviderDTO createProvider(@RequestBody ProviderDTO providerDTO) {
        return new ProviderDTO(providerService.addProvider(providerDTO));
    }

    @PutMapping("/{id}")
    public ProviderDTO updateProvider(@RequestBody ProviderDTO providerDTO) {
        return new ProviderDTO(providerService.updateProvider(providerDTO));
    }

    @DeleteMapping("/{id}")
    public ProviderDTO deleteProvider(@PathVariable Long id) {
        return new ProviderDTO(providerService.deleteProvider(id));
    }
}
