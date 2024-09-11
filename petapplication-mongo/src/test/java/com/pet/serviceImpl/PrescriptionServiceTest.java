package com.pet.serviceImpl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.pet.model.Prescription;
import com.pet.repository.PrescriptionRepository;
import com.pet.service.impl.PrescriptionServiceImpl;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PrescriptionServiceTest {

    @Mock
    private PrescriptionRepository prescriptionRepository;

    @InjectMocks
    private PrescriptionServiceImpl prescriptionService;

    @Test
    public void testCreatePrescription() {
        Prescription prescription = new Prescription();
        prescription.setStartDate(LocalDate.of(2024,01,10));
        prescription.setEndDate(LocalDate.of(2024,01,10));

        when(prescriptionRepository.save(prescription)).thenReturn(prescription);

        Prescription createdPrescription = prescriptionService.createPrescription(prescription);

        assertEquals(prescription.getStartDate(), createdPrescription.getStartDate());
        assertEquals(prescription.getEndDate(), createdPrescription.getEndDate());
        verify(prescriptionRepository, times(1)).save(prescription);
    }

    @Test
    public void testFindPrescriptionById() {
        ObjectId id = new ObjectId();
        Prescription prescription = new Prescription();
        prescription.setId(id);

        when(prescriptionRepository.findById(id)).thenReturn(Optional.of(prescription));

        Optional<Prescription> foundPrescription = prescriptionService.findPrescriptionById(id);

        assertTrue(foundPrescription.isPresent());
        assertEquals(id, foundPrescription.get().getId());
        verify(prescriptionRepository, times(1)).findById(id);
    }

    @Test
    public void testFindAllPrescriptions() {
        Prescription prescription1 = new Prescription();
        Prescription prescription2 = new Prescription();

        when(prescriptionRepository.findAll()).thenReturn(List.of(prescription1, prescription2));

        List<Prescription> prescriptions = prescriptionService.findAllPrescriptions();

        assertEquals(2, prescriptions.size());
        verify(prescriptionRepository, times(1)).findAll();
    }

    @Test
    public void testDeletePrescription() {
        ObjectId id = new ObjectId();

        doNothing().when(prescriptionRepository).deleteById(id);

        prescriptionService.deletePrescription(id);

        verify(prescriptionRepository, times(1)).deleteById(id);
    }
}
