package com.pet.serviceImpl;

import com.pet.model.Visit;
import com.pet.repository.VisitRepository;
import com.pet.service.impl.VisitServiceImpl;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VisitServiceTest {

    @Mock
    private VisitRepository visitRepository;

    @InjectMocks
    private VisitServiceImpl visitService;

    @Test
    public void testCreateVisit() {
        Visit visit = new Visit();
        visit.setVisitDate(LocalDate.of(2024, 9, 9));

        when(visitRepository.save(visit)).thenReturn(visit);

        Visit createdVisit = visitService.createVisit(visit);

        assertEquals(visit.getVisitDate(), createdVisit.getVisitDate());
        verify(visitRepository, times(1)).save(visit);
    }

    @Test
    public void testFindVisitById() {
        ObjectId id = new ObjectId();
        Visit visit = new Visit();
        visit.setId(id);

        when(visitRepository.findById(id)).thenReturn(Optional.of(visit));

        Optional<Visit> foundVisit = visitService.findVisitById(id);

        assertTrue(foundVisit.isPresent());
        assertEquals(id, foundVisit.get().getId());
        verify(visitRepository, times(1)).findById(id);
    }

    @Test
    public void testFindAllVisits() {
        Visit visit1 = new Visit();
        Visit visit2 = new Visit();

        when(visitRepository.findAll()).thenReturn(List.of(visit1, visit2));

        List<Visit> visits = visitService.findAllVisits();

        assertEquals(2, visits.size());
        verify(visitRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteVisit() {
        ObjectId id = new ObjectId();

        doNothing().when(visitRepository).deleteById(id);

        visitService.deleteVisit(id);

        verify(visitRepository, times(1)).deleteById(id);
    }
}
