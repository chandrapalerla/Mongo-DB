package com.pet.serviceImpl;

import com.pet.model.Owner;
import com.pet.repository.OwnerRepository;
import com.pet.service.impl.OwnerServiceImpl;
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
public class OwnerServiceTest {

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private OwnerServiceImpl ownerService;

    @Test
    public void testCreateOwner() {
        Owner owner = new Owner();
        owner.setFirstName("John");
        owner.setLastName("Doe");

        when(ownerRepository.save(owner)).thenReturn(owner);

        Owner createdOwner = ownerService.createOwner(owner);

        assertEquals("John", createdOwner.getFirstName());
        assertEquals("Doe", createdOwner.getLastName());
        verify(ownerRepository, times(1)).save(owner);
    }

    @Test
    public void testFindOwnerById() {
        ObjectId id = new ObjectId();
        Owner owner = new Owner();
        owner.setId(id);

        when(ownerRepository.findById(id)).thenReturn(Optional.of(owner));

        Optional<Owner> foundOwner = ownerService.findOwnerById(id);

        assertTrue(foundOwner.isPresent());
        assertEquals(id, foundOwner.get().getId());
        verify(ownerRepository, times(1)).findById(id);
    }

    @Test
    public void testFindAllOwners() {
        Owner owner1 = new Owner();
        Owner owner2 = new Owner();

        when(ownerRepository.findAll()).thenReturn(List.of(owner1, owner2));

        List<Owner> owners = ownerService.findAllOwners();

        assertEquals(2, owners.size());
        verify(ownerRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteOwner() {
        ObjectId id = new ObjectId();

        doNothing().when(ownerRepository).deleteById(id);

        ownerService.deleteOwner(id);

        verify(ownerRepository, times(1)).deleteById(id);
    }
}
