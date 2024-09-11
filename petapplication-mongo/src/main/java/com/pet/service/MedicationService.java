package com.pet.service;

import com.pet.model.Medication;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface MedicationService {

    Medication createMedication(Medication medication);
    Optional<Medication> findMedicationById(ObjectId id);
    List<Medication> findAllMedications();
    void deleteMedication(ObjectId id);
}
