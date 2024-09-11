package com.pet.controller;

import com.pet.model.Medication;
import com.pet.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Medications")
public class MedicationController {

    @Autowired
    private MedicationService medicationService;

    @GetMapping
    public List<Medication> getAllMedications() {
        return medicationService.getAllMedications();
    }

    @GetMapping("/{id}")
    public Medication getMedicationById(@PathVariable Long id) {
        return medicationService.getMedicationById(id);
    }

    @PostMapping
    public Medication createMedication(@RequestBody Medication medication) {
        return medicationService.saveMedication(medication);
    }

    @PutMapping("/{id}")
    public Medication updateMedication(@PathVariable Long id, @RequestBody Medication medication) {
        Medication existingMedication = medicationService.getMedicationById(id);
        if (existingMedication != null) {
            medication.setMedicationId(id);
            return medicationService.saveMedication(medication);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteMedication(@PathVariable Long id) {
        medicationService.deleteMedication(id);
    }
}
