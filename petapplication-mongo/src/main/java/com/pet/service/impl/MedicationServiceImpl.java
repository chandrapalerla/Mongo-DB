package com.pet.service.impl;

import com.pet.model.Medication;
import com.pet.repository.MedicationRepository;
import com.pet.service.MedicationService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicationServiceImpl implements MedicationService {
    @Autowired
    private MedicationRepository medicationRepository;

    public Medication createMedication(Medication medication) {
        return medicationRepository.save(medication);
    }

    public Optional<Medication> findMedicationById(ObjectId id) {
        return medicationRepository.findById(id);
    }

    public List<Medication> findAllMedications() {
        return medicationRepository.findAll();
    }

    public void deleteMedication(ObjectId id) {
        medicationRepository.deleteById(id);
    }
}
