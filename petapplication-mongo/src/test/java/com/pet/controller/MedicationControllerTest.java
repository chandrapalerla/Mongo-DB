package com.pet.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.pet.controller.MedicationController;
import com.pet.model.Medication;
import com.pet.service.impl.MedicationServiceImpl;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(MedicationController.class)
public class MedicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicationServiceImpl medicationService;

    @InjectMocks
    private MedicationController medicationController;

    private ObjectId id;
    private Medication medication;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(medicationController).build();
        id = new ObjectId();
        medication = new Medication();
        medication.setId(id);
        medication.setName("Aspirin");
    }

    @Test
    public void testCreateMedication() throws Exception {
        when(medicationService.createMedication(any(Medication.class))).thenReturn(medication);

        mockMvc.perform(post("/medications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Aspirin\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Aspirin"));

        verify(medicationService, times(1)).createMedication(any(Medication.class));
    }

    @Test
    public void testGetMedicationById() throws Exception {
        when(medicationService.findMedicationById(id)).thenReturn(Optional.of(medication));

        mockMvc.perform(get("/medications/{id}", id.toHexString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Aspirin"));

        verify(medicationService, times(1)).findMedicationById(id);
    }

    @Test
    public void testGetAllMedications() throws Exception {
        when(medicationService.findAllMedications()).thenReturn(List.of(medication));

        mockMvc.perform(get("/medications"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Aspirin"));

        verify(medicationService, times(1)).findAllMedications();
    }

    @Test
    public void testDeleteMedication() throws Exception {
        doNothing().when(medicationService).deleteMedication(id);

        mockMvc.perform(delete("/medications/{id}", id.toHexString()))
                .andExpect(status().isNoContent());

        verify(medicationService, times(1)).deleteMedication(id);
    }
}
