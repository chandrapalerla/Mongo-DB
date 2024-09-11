package com.pet.serviceImpl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.pet.model.Vet;
import com.pet.repository.VetRepository;
import com.pet.service.impl.VetServiceImpl;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class VetServiceTest {

    @Mock
    private VetRepository vetRepository;

    @InjectMocks
    private VetServiceImpl vetService;

    @Test
    public void testCreateVet() {
        Vet vet = new Vet();
        vet.setFirstName("Jane");
        vet.setLastName("Doe");

        when(vetRepository.save(vet)).thenReturn(vet);

        Vet createdVet = vetService.createVet(vet);

        assertEquals("Jane", createdVet.getFirstName());
        assertEquals("Doe", createdVet.getLastName());
        verify(vetRepository, times(1)).save(vet);
    }

    @Test
    public void testFindVetById() {
        ObjectId id = new ObjectId();
        Vet vet = new Vet();
        vet.setId(id);

        when(vetRepository.findById(id)).thenReturn(Optional.of(vet));

        Optional<Vet> foundVet = vetService.findVetById(id);

        assertTrue(foundVet.isPresent());
        assertEquals(id, foundVet.get().getId());
        verify(vetRepository, times(1)).findById(id);
    }

    @Test
    public void testFindAllVets() {
        Vet vet1 = new Vet();
        Vet vet2 = new Vet();

        when(vetRepository.findAll()).thenReturn(List.of(vet1, vet2));

        List<Vet> vets = vetService.findAllVets();

        assertEquals(2, vets.size());
        verify(vetRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteVet() {
        ObjectId id = new ObjectId();

        doNothing().when(vetRepository).deleteById(id);

        vetService.deleteVet(id);

        verify(vetRepository, times(1)).deleteById(id);
    }
}
