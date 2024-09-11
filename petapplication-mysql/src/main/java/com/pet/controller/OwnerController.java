package com.pet.controller;

import com.pet.model.Owner;
import com.pet.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public List<Owner> getAllOwners() {
        return ownerService.getAllOwners();
    }

    @GetMapping("/{id}")
    public Owner getOwnerById(@PathVariable Long id) {
        return ownerService.getOwnerById(id);
    }

    @PostMapping
    public Owner createOwner(@RequestBody Owner owner) {
        return ownerService.saveOwner(owner);
    }

    @PutMapping("/{id}")
    public Owner updateOwner(@PathVariable Long id, @RequestBody Owner owner) {
        Owner existingOwner = ownerService.getOwnerById(id);
        if (existingOwner != null) {
            owner.setOwnerId(id);
            return ownerService.saveOwner(owner);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwner(id);
    }
}
