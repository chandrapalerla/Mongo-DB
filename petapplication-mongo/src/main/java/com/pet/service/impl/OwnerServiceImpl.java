package com.pet.service.impl;

import com.pet.model.Owner;
import com.pet.repository.OwnerRepository;
import com.pet.service.OwnerService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

    public Owner createOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    public Optional<Owner> findOwnerById(ObjectId id) {
        return ownerRepository.findById(id);
    }

    public List<Owner> findAllOwners() {
        return ownerRepository.findAll();
    }

    public void deleteOwner(ObjectId id) {
        ownerRepository.deleteById(id);
    }
}
