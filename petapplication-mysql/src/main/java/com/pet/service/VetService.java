package com.pet.service;

import com.pet.repository.VetRepository;
import com.pet.model.Vet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VetService {

    @Autowired
    private VetRepository vetRepository;

    public List<Vet> getAllVets() {
        return vetRepository.findAll();
    }

    public Vet getVetById(Long id) {
        return vetRepository.findById(id).orElse(null);
    }

    public Vet saveVet(Vet vet) {
        return vetRepository.save(vet);
    }

    public void deleteVet(Long id) {
        vetRepository.deleteById(id);
    }
}
