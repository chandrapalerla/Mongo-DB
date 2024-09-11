package com.pet.service.impl;

import com.pet.model.Prescription;
import com.pet.repository.PrescriptionRepository;
import com.pet.service.PrescriptionService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public Prescription createPrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    public Optional<Prescription> findPrescriptionById(ObjectId id) {
        return prescriptionRepository.findById(id);
    }

    public List<Prescription> findAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    public void deletePrescription(ObjectId id) {
        prescriptionRepository.deleteById(id);
    }
}
