package com.pet.serviceImpl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.pet.model.Medication;
import com.pet.repository.MedicationRepository;
import com.pet.service.impl.MedicationServiceImpl;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class MedicationServiceTest {

    @Mock
    private MedicationRepository medicationRepository;

    @InjectMocks
    private MedicationServiceImpl medicationService;

    @Test
    public void testCreateMedication() {
        Medication medication = new Medication();
        medication.setName("Antibiotic");

        when(medicationRepository.save(medication)).thenReturn(medication);

        Medication createdMedication = medicationService.createMedication(medication);

        assertEquals("Antibiotic", createdMedication.getName());
        verify(medicationRepository, times(1)).save(medication);
    }

    @Test
    public void testFindMedicationById() {
        ObjectId id = new ObjectId();
        Medication medication = new Medication();
        medication.setId(id);

        when(medicationRepository.findById(id)).thenReturn(Optional.of(medication));

        Optional<Medication> foundMedication = medicationService.findMedicationById(id);

        assertTrue(foundMedication.isPresent());
        assertEquals(id, foundMedication.get().getId());
        verify(medicationRepository, times(1)).findById(id);
    }

    @Test
    public void testFindAllMedications() {
        Medication medication1 = new Medication();
        Medication medication2 = new Medication();

        when(medicationRepository.findAll()).thenReturn(List.of(medication1, medication2));

        List<Medication> medications = medicationService.findAllMedications();

        assertEquals(2, medications.size());
        verify(medicationRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteMedication() {
        ObjectId id = new ObjectId();

        doNothing().when(medicationRepository).deleteById(id);

        medicationService.deleteMedication(id);

        verify(medicationRepository, times(1)).deleteById(id);
    }
}
