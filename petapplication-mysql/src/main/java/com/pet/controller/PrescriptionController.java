package com.pet.controller;

import com.pet.model.Prescription;
import com.pet.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping
    public List<Prescription> getAllPrescriptions() {
        return prescriptionService.getAllPrescriptions();
    }

    @GetMapping("/{id}")
    public Prescription getPrescriptionById(@PathVariable Long id) {
        return prescriptionService.getPrescriptionById(id);
    }

    @PostMapping
    public Prescription createPrescription(@RequestBody Prescription Prescription) {
        return prescriptionService.savePrescription(Prescription);
    }

    @PutMapping("/{id}")
    public Prescription updatePrescription(@PathVariable Long id, @RequestBody Prescription prescription) {
        Prescription existingPrescription = prescriptionService.getPrescriptionById(id);
        if (existingPrescription != null) {
            prescription.setPrescriptionId(id);
            return prescriptionService.savePrescription(prescription);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletePrescription(@PathVariable Long id) {
        prescriptionService.deletePrescription(id);
    }
}