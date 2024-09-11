package com.pet.controller;

import com.pet.model.Prescription;
import com.pet.service.impl.PrescriptionServiceImpl;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PrescriptionController.class)
public class PrescriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PrescriptionServiceImpl prescriptionService;

    @InjectMocks
    private PrescriptionController prescriptionController;

    private ObjectId id;
    private Prescription prescription;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(prescriptionController).build();
        id = new ObjectId();
        prescription = new Prescription();
        prescription.setId(id);
        prescription.setStartDate(LocalDate.of(2024,9,9));
        prescription.setEndDate(LocalDate.of(2024,9,10));
    }

    @Test
    public void testCreatePrescription() throws Exception {
        when(prescriptionService.createPrescription(any(Prescription.class))).thenReturn(prescription);

        mockMvc.perform(post("/prescriptions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"startDate\":\"2024-01-01\", \"endDate\":\"2024-01-10\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.startDate").value("2024-09-09"))
                .andExpect(jsonPath("$.endDate").value("2024-09-10"));

        verify(prescriptionService, times(1)).createPrescription(any(Prescription.class));
    }

    @Test
    public void testGetPrescriptionById() throws Exception {
        when(prescriptionService.findPrescriptionById(id)).thenReturn(Optional.of(prescription));

        mockMvc.perform(get("/prescriptions/{id}", id.toHexString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.startDate").value("2024-09-09"))
                .andExpect(jsonPath("$.endDate").value("2024-09-10"));

        verify(prescriptionService, times(1)).findPrescriptionById(id);
    }

    @Test
    public void testGetAllPrescriptions() throws Exception {
        when(prescriptionService.findAllPrescriptions()).thenReturn(List.of(prescription));

        mockMvc.perform(get("/prescriptions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].startDate").value("2024-09-09"))
                .andExpect(jsonPath("$[0].endDate").value("2024-09-10"));

        verify(prescriptionService, times(1)).findAllPrescriptions();
    }

    @Test
    public void testDeletePrescription() throws Exception {
        doNothing().when(prescriptionService).deletePrescription(id);

        mockMvc.perform(delete("/prescriptions/{id}", id.toHexString()))
                .andExpect(status().isNoContent());

        verify(prescriptionService, times(1)).deletePrescription(id);
    }
}
