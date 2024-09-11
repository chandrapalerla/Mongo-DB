package com.pet.service;

import com.pet.repository.VisitRepository;
import com.pet.model.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitService {

    @Autowired
    private VisitRepository visitRepository;

    public List<Visit> getAllVisits() {
        return visitRepository.findAll();
    }

    public Visit getVisitById(Long id) {
        return visitRepository.findById(id).orElse(null);
    }

    public Visit saveVisit(Visit visit) {
        return visitRepository.save(visit);
    }

    public void deleteVisit(Long id) {
        visitRepository.deleteById(id);
    }
}
