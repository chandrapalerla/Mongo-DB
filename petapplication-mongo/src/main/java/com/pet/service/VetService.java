package com.pet.service;

import com.pet.model.Vet;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface VetService {
    Vet createVet(Vet vet);
    Optional<Vet> findVetById(ObjectId id);
    List<Vet> findAllVets();
    void deleteVet(ObjectId id);
}
