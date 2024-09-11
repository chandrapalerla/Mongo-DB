package com.pet.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.pet.model.Pet;
import com.pet.service.impl.PetServiceImpl;
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
@WebMvcTest(PetController.class)
public class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetServiceImpl petService;

    @InjectMocks
    private PetController petController;

    private ObjectId id;
    private Pet pet;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
        id = new ObjectId();
        pet = new Pet();
        pet.setId(id);
        pet.setName("Buddy");
    }

    @Test
    public void testCreatePet() throws Exception {
        when(petService.createPet(any(Pet.class))).thenReturn(pet);

        mockMvc.perform(post("/pets")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Buddy\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Buddy"));

        verify(petService, times(1)).createPet(any(Pet.class));
    }

    @Test
    public void testGetPetById() throws Exception {
        when(petService.findPetById(id)).thenReturn(Optional.of(pet));

        mockMvc.perform(get("/pets/{id}", id.toHexString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Buddy"));

        verify(petService, times(1)).findPetById(id);
    }

    @Test
    public void testGetAllPets() throws Exception {
        when(petService.findAllPets()).thenReturn(List.of(pet));

        mockMvc.perform(get("/pets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Buddy"));

        verify(petService, times(1)).findAllPets();
    }

    @Test
    public void testDeletePet() throws Exception {
        doNothing().when(petService).deletePet(id);

        mockMvc.perform(delete("/pets/{id}", id.toHexString()))
                .andExpect(status().isNoContent());

        verify(petService, times(1)).deletePet(id);
    }
}
