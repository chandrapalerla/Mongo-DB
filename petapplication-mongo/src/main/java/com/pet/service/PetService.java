package com.pet.service;

import com.pet.model.Pet;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface PetService {

    Pet createPet(Pet pet);

    List<Pet> findAllPets();

    Optional<Pet> findPetById(ObjectId id);

    void deletePet(ObjectId id);
}
