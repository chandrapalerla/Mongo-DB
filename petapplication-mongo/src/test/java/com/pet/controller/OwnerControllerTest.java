package com.pet.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.pet.model.Owner;
import com.pet.service.impl.OwnerServiceImpl;
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
@WebMvcTest(OwnerController.class)
public class OwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OwnerServiceImpl ownerService;

    @InjectMocks
    private OwnerController ownerController;

    private ObjectId id;
    private Owner owner;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
        id = new ObjectId();
        owner = new Owner();
        owner.setId(id);
        owner.setFirstName("John");
        owner.setLastName("Doe");
    }

    @Test
    public void testCreateOwner() throws Exception {
        when(ownerService.createOwner(any(Owner.class))).thenReturn(owner);

        mockMvc.perform(post("/owners")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"John\", \"lastName\":\"Doe\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"));

        verify(ownerService, times(1)).createOwner(any(Owner.class));
    }

    @Test
    public void testGetOwnerById() throws Exception {
        when(ownerService.findOwnerById(id)).thenReturn(Optional.of(owner));

        mockMvc.perform(get("/owners/{id}", id.toHexString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"));

        verify(ownerService, times(1)).findOwnerById(id);
    }

    @Test
    public void testGetAllOwners() throws Exception {
        when(ownerService.findAllOwners()).thenReturn(List.of(owner));

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"));

        verify(ownerService, times(1)).findAllOwners();
    }

    @Test
    public void testDeleteOwner() throws Exception {
        doNothing().when(ownerService).deleteOwner(id);

        mockMvc.perform(delete("/owners/{id}", id.toHexString()))
                .andExpect(status().isNoContent());

        verify(ownerService, times(1)).deleteOwner(id);
    }
}
