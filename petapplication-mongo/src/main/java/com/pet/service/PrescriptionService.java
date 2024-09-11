package com.pet.service;

import com.pet.model.Prescription;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface PrescriptionService {

    Prescription createPrescription(Prescription prescription);
    Optional<Prescription> findPrescriptionById(ObjectId id);
    List<Prescription> findAllPrescriptions();
    void deletePrescription(ObjectId id);
}
