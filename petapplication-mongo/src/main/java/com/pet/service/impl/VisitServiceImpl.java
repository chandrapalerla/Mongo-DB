package com.pet.service.impl;

import com.pet.model.Pet;
import com.pet.model.Vet;
import com.pet.model.Visit;
import com.pet.repository.PetRepository;
import com.pet.repository.VetRepository;
import com.pet.repository.VisitRepository;
import com.pet.service.VisitService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitServiceImpl implements VisitService {
    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private VetRepository vetRepository;

    public Visit createVisit(Visit visit) {
        return visitRepository.save(visit);
    }

    public Optional<Visit> findVisitById(ObjectId id) {
        return visitRepository.findById(id);
    }

    public List<Visit> findAllVisits() {
        List<Visit> all = visitRepository.findAll();
        return all.stream().map(this::populateRelationships).toList();
    }

    public void deleteVisit(ObjectId id) {
        visitRepository.deleteById(id);
    }

    private Visit populateRelationships(Visit visit) {
        if (visit.getPet() != null && visit.getPet().getId() != null) {
            Optional<Pet> pet = petRepository.findById(visit.getPet().getId());
            pet.ifPresent(visit::setPet);
        }
        if (visit.getVet() != null && visit.getVet().getId() != null) {
            Optional<Vet> vet = vetRepository.findById(visit.getVet().getId());
            vet.ifPresent(visit::setVet);
        }
        return visit;
    }
}
