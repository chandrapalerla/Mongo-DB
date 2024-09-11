package com.pet.service.impl;

import com.pet.model.Vet;
import com.pet.repository.VetRepository;
import com.pet.service.VetService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VetServiceImpl implements VetService {
    @Autowired
    private VetRepository vetRepository;

    public Vet createVet(Vet vet) {
        return vetRepository.save(vet);
    }

    public Optional<Vet> findVetById(ObjectId id) {
        return vetRepository.findById(id);
    }

    public List<Vet> findAllVets() {
        return vetRepository.findAll();
    }

    public void deleteVet(ObjectId id) {
        vetRepository.deleteById(id);
    }
}
