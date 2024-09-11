package com.pet.controller;

import com.pet.model.Vet;
import com.pet.service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vets")
public class VetController {

    @Autowired
    private VetService vetService;

    @GetMapping
    public List<Vet> getAllVets() {
        return vetService.getAllVets();
    }

    @GetMapping("/{id}")
    public Vet getVetById(@PathVariable Long id) {
        return vetService.getVetById(id);
    }

    @PostMapping
    public Vet createVet(@RequestBody Vet vet) {
        return vetService.saveVet(vet);
    }

    @PutMapping("/{id}")
    public Vet updateVet(@PathVariable Long id, @RequestBody Vet vet) {
        Vet existingVet = vetService.getVetById(id);
        if (existingVet != null) {
            vet.setVetId(id);
            return vetService.saveVet(vet);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteVet(@PathVariable Long id) {
        vetService.deleteVet(id);
    }
}
