package com.pet.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.pet.model.Visit;
import com.pet.service.impl.VisitServiceImpl;
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

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(VisitController.class)
public class VisitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VisitServiceImpl visitService;

    @InjectMocks
    private VisitController visitController;

    private ObjectId id;
    private Visit visit;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
        id = new ObjectId();
        visit = new Visit();
        visit.setId(id);
        visit.setVisitDate(LocalDate.of(2024,9,9));
    }

    @Test
    public void testCreateVisit() throws Exception {
        when(visitService.createVisit(any(Visit.class))).thenReturn(visit);

        mockMvc.perform(post("/visits")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"visitDate\":\"2024-09-09\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.visitDate").value("2024-09-09"));

        verify(visitService, times(1)).createVisit(any(Visit.class));
    }

    @Test
    public void testGetVisitById() throws Exception {
        when(visitService.findVisitById(id)).thenReturn(Optional.of(visit));

        mockMvc.perform(get("/visits/{id}", id.toHexString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.visitDate").value("2024-09-09"));

        verify(visitService, times(1)).findVisitById(id);
    }

    @Test
    public void testGetAllVisits() throws Exception {
        when(visitService.findAllVisits()).thenReturn(List.of(visit));

        mockMvc.perform(get("/visits"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].visitDate").value("2024-09-09"));

        verify(visitService, times(1)).findAllVisits();
    }

    @Test
    public void testDeleteVisit() throws Exception {
        doNothing().when(visitService).deleteVisit(id);

        mockMvc.perform(delete("/visits/{id}", id.toHexString()))
                .andExpect(status().isNoContent());

        verify(visitService, times(1)).deleteVisit(id);
    }
}
