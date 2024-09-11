package com.pet.serviceImpl;

import com.pet.model.Pet;
import com.pet.repository.PetRepository;
import com.pet.service.impl.PetServiceImpl;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetServiceImpl petService;

    @Test
    public void testCreatePet() {
        Pet pet = new Pet();
        pet.setName("Buddy");

        when(petRepository.save(pet)).thenReturn(pet);

        Pet createdPet = petService.createPet(pet);

        assertEquals("Buddy", createdPet.getName());
        verify(petRepository, times(1)).save(pet);
    }

    @Test
    public void testFindPetById() {
        ObjectId id = new ObjectId();
        Pet pet = new Pet();
        pet.setId(id);

        when(petRepository.findById(id)).thenReturn(Optional.of(pet));

        Optional<Pet> foundPet = petService.findPetById(id);

        assertTrue(foundPet.isPresent());
        assertEquals(id, foundPet.get().getId());
        verify(petRepository, times(1)).findById(id);
    }

    @Test
    public void testFindAllPets() {
        Pet pet1 = new Pet();
        Pet pet2 = new Pet();

        when(petRepository.findAll()).thenReturn(List.of(pet1, pet2));

        List<Pet> pets = petService.findAllPets();

        assertEquals(2, pets.size());
        verify(petRepository, times(1)).findAll();
    }

    @Test
    public void testDeletePet() {
        ObjectId id = new ObjectId();

        doNothing().when(petRepository).deleteById(id);

        petService.deletePet(id);

        verify(petRepository, times(1)).deleteById(id);
    }
}
