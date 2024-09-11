package com.pet.service.impl;

import com.pet.model.Pet;
import com.pet.repository.PetRepository;
import com.pet.service.PetService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {
    @Autowired
    private PetRepository petRepository;

    public Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }

    public Optional<Pet> findPetById(ObjectId id) {
        return petRepository.findById(id);
    }

    public List<Pet> findAllPets() {
        return petRepository.findAll();
    }

    public void deletePet(ObjectId id) {
        petRepository.deleteById(id);
    }
}
