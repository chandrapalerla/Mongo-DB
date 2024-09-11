package com.pet.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.pet.model.Vet;
import com.pet.service.impl.VetServiceImpl;
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
@WebMvcTest(VetController.class)
public class VetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VetServiceImpl vetService;

    @InjectMocks
    private VetController vetController;

    private ObjectId id;
    private Vet vet;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(vetController).build();
        id = new ObjectId();
        vet = new Vet();
        vet.setId(id);
        vet.setFirstName("Jane");
        vet.setLastName("Doe");
    }

    @Test
    public void testCreateVet() throws Exception {
        when(vetService.createVet(any(Vet.class))).thenReturn(vet);

        mockMvc.perform(post("/vets")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"Jane\", \"lastName\":\"Doe\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Jane"))
                .andExpect(jsonPath("$.lastName").value("Doe"));

        verify(vetService, times(1)).createVet(any(Vet.class));
    }

    @Test
    public void testGetVetById() throws Exception {
        when(vetService.findVetById(id)).thenReturn(Optional.of(vet));

        mockMvc.perform(get("/vets/{id}", id.toHexString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Jane"))
                .andExpect(jsonPath("$.lastName").value("Doe"));

        verify(vetService, times(1)).findVetById(id);
    }

    @Test
    public void testGetAllVets() throws Exception {
        when(vetService.findAllVets()).thenReturn(List.of(vet));

        mockMvc.perform(get("/vets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Jane"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"));

        verify(vetService, times(1)).findAllVets();
    }

    @Test
    public void testDeleteVet() throws Exception {
        doNothing().when(vetService).deleteVet(id);

        mockMvc.perform(delete("/vets/{id}", id.toHexString()))
                .andExpect(status().isNoContent());

        verify(vetService, times(1)).deleteVet(id);
    }
}
